package com.summer.framework.controller;

import com.summer.framework.domain.SimpleRequest;
import com.summer.framework.domain.SimpleResponse;
import com.summer.framework.servelet.Servelet;

import java.io.IOException;

/**
 * @Author: dqiang
 * @Date: 2020/11/17 16:05
 * @Version: 1.0
 */
public class LoginController extends Servelet {
    @Override
    public void doGet(SimpleRequest request, SimpleResponse response) {
        try {
            response.write("Login Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(SimpleRequest request, SimpleResponse response) {
        try {
            response.write("Login Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doUnsupport(SimpleRequest request, SimpleResponse response) {
        super.doUnsupport(request, response);
    }
}
