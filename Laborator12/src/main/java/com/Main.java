package com;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class Main {
    public static void main(String[] arg) throws ClassNotFoundException {
        MyClassloader classLoader = new MyClassloader();
        Class myClass = classLoader.loadClass("/Users/cosminpasat/Desktop/PA/laborator5/target/classes/","com.company.Laborator12.MyTest");

        MyTestFramework myTestFramework = new MyTestFramework();
        myTestFramework.printDetails(myClass);
        myTestFramework.testMethods(myClass, classLoader.loadClass("/Users/cosminpasat/Desktop/PA/laborator5/target/classes/","com.company.Laborator12.Test"));


    }
}
