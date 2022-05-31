package com;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] arg) throws ClassNotFoundException, IOException {
        Main lab12 = new Main();
    //    lab12.compulsory();
        lab12.homework();
    }

    public void compulsory() throws ClassNotFoundException {
        MyClassLoader classLoader = new MyClassLoader();
        Class myClass = classLoader.loadClass("D:\\Java_teamWork\\Laborator5\\target\\classes","com.company.laborator12.MyTest");

        MyTestFramework myTestFramework = new MyTestFramework();
        myTestFramework.printDetails(myClass);
        myTestFramework.testStaticMethods(myClass);
    }

    public void homework() throws ClassNotFoundException, IOException {
        MyClassLoader classLoader = new MyClassLoader();MyTestFramework myTestFramework = new MyTestFramework();

        myTestFramework.javaPrototype("/Users/cosminpasat/Desktop/PA/Laborator5/target/classes", "com.company.laborator12.MyTest");
        var classes = classLoader.loadClassesFromJar(new File("/Users/cosminpasat/Desktop/PA/Laborator5/target/classes/test.jar"));
        for (Class loadedClass : classes){
            if(loadedClass.isAnnotationPresent(Test.class) && Modifier.isPublic(loadedClass.getModifiers()))
                myTestFramework.testMethods(loadedClass);
        }
    }
}
