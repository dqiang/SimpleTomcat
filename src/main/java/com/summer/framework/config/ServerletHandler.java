package com.summer.framework.config;

import com.summer.framework.controller.IndexController;
import com.summer.framework.controller.LoginController;
import com.summer.framework.servelet.Servelet;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: dqiang
 * @Date: 2020/11/17 16:03
 * @Version: 1.0
 */
public class ServerletHandler implements MethodInterceptor {
    public static Map<String, String> serverletMap = new HashMap<String, String>();


    private Object target;
    static {
        serverletMap.put("/index", IndexController.class.getName());
        serverletMap.put("/", IndexController.class.getName());
        serverletMap.put("/login", LoginController.class.getName());
    }

    public ServerletHandler(Object target){
        this.target = target;
    }

    @SuppressWarnings("unchecked")
    public <T> T  getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return (T)enhancer.create();
    }


    public static Servelet getInstance(String url){
        String clsName = serverletMap.get(url);
        if (StringUtils.isNotBlank(clsName)){
            try {
                Class cls = Class.forName(clsName);
                ServerletHandler handler = new ServerletHandler(cls.newInstance());
                Servelet serverlet = handler.getProxyInstance();
                return serverlet;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行前----------------");
        Object result = methodProxy.invoke(target,objects);
        System.out.println("执行后----------------");
        return result;
    }
}
