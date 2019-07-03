package com.lianlianpay.ibatis.helper.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by chenrs on 2017/11/19.
 */
public class LoadJarUtils {

    public static void loadJar(String path) throws Exception {
        Method addURL = initAddMethod();
        URLClassLoader classloader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        URL classUrl = new URL(path);
        addURL.invoke(classloader, new Object[]{classUrl});
    }

    private static Method initAddMethod() {
        try {
            Method add = URLClassLoader.class.getDeclaredMethod("addURL",
                    new Class[]{URL.class});
            add.setAccessible(true);
            return add;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {

    }
}
