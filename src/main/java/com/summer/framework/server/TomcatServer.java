package com.summer.framework.server;

import com.summer.framework.config.ServerletHandler;
import com.summer.framework.domain.SimpleRequest;
import com.summer.framework.domain.SimpleResponse;
import com.summer.framework.filter.Filter;
import com.summer.framework.filter.FilterChain;
import com.summer.framework.filter.SimpleFilterChain;
import com.summer.framework.serverlet.Serverlet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Author: dqiang
 * @Date: 2020/11/17 15:23
 * @Version: 1.0
 */
public class TomcatServer extends Thread {
    private Integer port = 8080;

    private Selector selector = null;
    private ServerSocketChannel serverSocketChannel = null;

    private List<Filter> filters = new ArrayList<Filter>();

    private boolean isExist = false;

    public TomcatServer(int port)  {
        this.port = port;
    }

    public void addFilter(Filter filter){
        this.filters.add(filter);
    }

    private int init(){
        try {
            this.selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(this.port));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            return 1;
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void run() {
        if(init() != 1){
            System.out.println("服务器初始化失败");
            return;
        }else{
            System.out.println("服务器启动成功");
            System.out.println("端口号：" + this.port);
        }
        while (!isExist){
            try {
                loop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loop() throws IOException {
        int readys = this.selector.select(1000);
        if (readys <= 0) {
            return;
        }
        Set<SelectionKey> selectionKeys = this.selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            if (key.isAcceptable()) {
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                request(key);
            }
        }
    }

    private void request(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        SimpleRequest request = new SimpleRequest(channel);
        SimpleResponse response = new SimpleResponse(channel);
        dispatch(request, response);
        channel.close();
    }

    private void dispatch(SimpleRequest request, SimpleResponse response) throws IOException {
        Serverlet serverlet = ServerletHandler.getInstance(request.getUrl());
        if (serverlet != null){
            new SimpleFilterChain(serverlet,filters).doFilter(request,response);
        }else {
            try {
                response.write404();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
