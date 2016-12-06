package org.hsq.wjg.demo.classloader;

/**
 * Created by wjg on 2016/11/16.
 */
public class MyClassLoader extends ClassLoader {
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("load class by my classloader.");
        return null;
    }
}
