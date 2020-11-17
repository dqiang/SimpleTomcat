package com.summer.framework.domain;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author: dqiang
 * @Date: 2020/11/17 15:37
 * @Version: 1.0
 */
public class SimpleResponse {
    private SocketChannel socketChannel;

    public SimpleResponse(SocketChannel channel)  {
        this.socketChannel = channel;
    }

    public void write(String content) throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type:text/html\n")
                .append("\r\n")
                .append("<html><head><link rel=\"icon\" href=\"data:;base64,=\"></head><body>")
                .append(content)
                .append("</body></html>");
        ByteBuffer sendBuffer = ByteBuffer.allocate(httpResponse.length());
        sendBuffer.put(httpResponse.toString().getBytes());
        sendBuffer.flip();
        this.socketChannel.write(sendBuffer);
    }

    public void write404() throws IOException {
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 404 OK\n")
                .append("Content-Type:text/html\n")
                .append("\r\n")
                .append("<html><head><link rel=\"icon\" href=\"data:;base64,=\"></head><body>")
                .append("Not Found")
                .append("</body></html>");
        ByteBuffer sendBuffer = ByteBuffer.allocate(httpResponse.length());
        sendBuffer.put(httpResponse.toString().getBytes());
        sendBuffer.flip();
        this.socketChannel.write(sendBuffer);
    }
}
