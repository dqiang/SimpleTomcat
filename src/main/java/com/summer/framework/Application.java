package com.summer.framework;

import com.summer.framework.filter.TestFilter;
import com.summer.framework.server.TomcatServer;

import java.io.IOException;

/**
 * @Author: dqiang
 * @Date: 2020/11/17 15:22
 * @Version: 1.0
 */
public class Application {

    public static void main(String[] args) throws IOException {
        TomcatServer tomcatServer = new TomcatServer(9999);
        tomcatServer.addFilter(new TestFilter());
        tomcatServer.start();
    }
}
