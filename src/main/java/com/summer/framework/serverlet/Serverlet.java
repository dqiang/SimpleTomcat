package com.summer.framework.serverlet;

import com.summer.framework.domain.SimpleRequest;
import com.summer.framework.domain.SimpleResponse;

import java.io.IOException;

/**
 * @Author: dqiang
 * @Date: 2020/11/17 15:52
 * @Version: 1.0
 */
public abstract class Serverlet {
    public static String REQ_GET= "GET";
    public static String REQ_POST = "POST";

    public void service(SimpleRequest request, SimpleResponse response){
        if (REQ_GET.equals(request.getMethod())){
            this.doGet(request,response);
        }else if (REQ_POST.equals(request.getMethod())){
            this.doPost(request,response);
        }
    }

    public abstract void doGet(SimpleRequest request, SimpleResponse response);

    public abstract void doPost(SimpleRequest request, SimpleResponse response);

    public void doUnsupport(SimpleRequest request, SimpleResponse response){
        try {
            response.write("Unsupport Method");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
