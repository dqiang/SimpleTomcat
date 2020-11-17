package com.summer.framework.controller;

import com.summer.framework.domain.SimpleRequest;
import com.summer.framework.domain.SimpleResponse;
import com.summer.framework.serverlet.Serverlet;

import java.io.IOException;

/**
 * @Author: dqiang
 * @Date: 2020/11/17 15:59
 * @Version: 1.0
 */
public class IndexController extends Serverlet {

    @Override
    public void doGet(SimpleRequest request, SimpleResponse response) {
        try {
            response.write("Welcome To Index Page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(SimpleRequest request, SimpleResponse response) {
        try {
            response.write("Welcome To Index Page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doUnsupport(SimpleRequest request, SimpleResponse response) {
        super.doUnsupport(request, response);
    }
}
