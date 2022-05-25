package com;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class MyClassloader extends URLClassLoader {
    public MyClassloader() {
        super(new URL[0], ClassLoader.getSystemClassLoader());
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    public Class loadClass(String classPath, String className) throws ClassNotFoundException {
        File path = new File(classPath);
        if(path.exists()) {
            URL url = null;
            try {
                url = path.toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            this.addURL(url);
            return this.loadClass(className);
        }
        return null;
    }

}