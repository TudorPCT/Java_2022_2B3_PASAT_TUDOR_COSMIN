package com;



import java.lang.reflect.Method;
import java.util.Arrays;

public class MyTestFramework {
    public void testMethods(Class myClass, Class myTest){
        int passed = 0, failed = 0;
        for (Method m : myClass.getMethods()) {
            if (m.isAnnotationPresent(myTest)) {
                try {
                    m.invoke(null, 1);
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n", m, ex.getCause());
                    failed++;
                }
            }
        }
        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
    }

    public void printDetails(Class myClass){
        System.out.println("Class methods are: " + Arrays.toString(myClass.getMethods()));
        System.out.println("Class constructors are: " + Arrays.toString(myClass.getConstructors()));
    }
}
