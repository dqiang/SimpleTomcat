package com.summer.framework.filter;

import com.summer.framework.domain.SimpleRequest;
import com.summer.framework.domain.SimpleResponse;

import java.io.IOException;

/**
 * @Author: dqiang
 * @Date: 2020/11/17 17:32
 * @Version: 1.0
 */
public class TestFilter implements Filter{
    public void doFilter(SimpleRequest request, SimpleResponse response, FilterChain chain) throws IOException {
        System.out.println("TestFilter  Before");
        chain.doFilter(request,response);
        System.out.println("TestFilter  After");
    }
}
