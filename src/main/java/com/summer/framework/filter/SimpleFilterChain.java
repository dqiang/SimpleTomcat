package com.summer.framework.filter;

import com.summer.framework.domain.SimpleRequest;
import com.summer.framework.domain.SimpleResponse;
import com.summer.framework.serverlet.Serverlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dqiang
 * @Date: 2020/11/17 17:51
 * @Version: 1.0
 */
public class SimpleFilterChain implements FilterChain {
    private int pos = 0; //当前执行filter的offset
    private int n; //当前filter的数量

    private List<Filter> filters = new ArrayList<Filter>();

    private Serverlet serverlet;
    public SimpleFilterChain(Serverlet serverlet,List<Filter> inFilters){
        this.serverlet = serverlet;
        this.filters.addAll(inFilters);
        this.n = inFilters.size();
    }

    public void doFilter(SimpleRequest var1, SimpleResponse var2) throws IOException {
        if (pos < n){
            Filter filter = this.filters.get(pos++);
            filter.doFilter(var1,var2,this);
        }else{
            serverlet.service(var1,var2);
        }
    }

}
