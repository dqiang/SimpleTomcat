package com.summer.framework.domain;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @Author: dqiang
 * @Date: 2020/11/17 15:37
 * @Version: 1.0
 */
public class SimpleRequest {
    private SocketChannel channel;
    private String method = "GET";
    private String url = null;

    public SimpleRequest(SocketChannel channel) throws IOException {
        this.channel = channel;
        String httpRequest = "";
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        channel.read(buffer);
        buffer.flip();
        if (buffer.limit() > 10){
            Charset charset = Charset.forName("utf-8");
            httpRequest = charset.decode(buffer).toString();
            String httpHead = httpRequest.split("\n")[0];
            this.method = httpHead.split("\\s")[0].toUpperCase();
            this.url  = httpHead.split("\\s")[1];
            System.out.println( this.url);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
