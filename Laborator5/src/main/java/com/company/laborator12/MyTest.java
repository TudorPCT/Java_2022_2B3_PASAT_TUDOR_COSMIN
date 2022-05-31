package com.company.laborator12;

import org.testng.annotations.Test;

@Test
public class MyTest {

    public Integer test;
    @Test
    public static void printHello(int x, String y, Integer z){
        System.out.println("Int primitive: " + x);
        System.out.println("String: " + y);
        System.out.println("Integer: " + z);
    }
}
