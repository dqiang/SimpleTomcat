package com.summer.framework.filter;

import com.summer.framework.domain.SimpleRequest;
import com.summer.framework.domain.SimpleResponse;

import java.io.IOException;

/**
 * @Author: dqiang
 * @Date: 2020/11/17 17:37
 * @Version: 1.0
 */
public interface FilterChain {
    void doFilter(SimpleRequest var1, SimpleResponse var2) throws IOException;
}
