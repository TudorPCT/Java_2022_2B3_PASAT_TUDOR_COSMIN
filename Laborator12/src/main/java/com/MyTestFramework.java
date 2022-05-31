package com;



import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;


public class MyTestFramework {
    public void testStaticMethods(Class myClass){
        int passed = 0;
        int failed = 0;

        for (Method m : myClass.getMethods()) {
            if (m.isAnnotationPresent(Test.class) && Modifier.isStatic(m.getModifiers())) {
                try {
                    m.invoke(null);
                    passed++;
                } catch (Throwable ex) {
                    System.out.printf("Test %s failed: %s %n", m, ex.getCause());
                    failed++;
                }
            }
        }
        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
    }


    public void testMethods(Class myClass) {
        int passed = 0;
        int failed = 0;

        for (Method m : myClass.getMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                if (invokeTestMethod(myClass,m))
                    passed++;
                else failed++;
            }
        }
        System.out.printf("Passed: %d, Failed %d%n", passed, failed);
    }

    public boolean invokeTestMethod(Class myClass, Method m) {
        Object obj = null;
        try {
            if (!Modifier.isStatic(m.getModifiers())) {
                obj = myClass.newInstance();
            }
            Object[] parameters = generateParameters(m);
            m.invoke(obj, parameters);
            return true;
        } catch (Throwable ex) {
            System.out.printf("Test %s failed: %s %n", m, ex.getCause());
            return false;
        }
    }

    public Object[] generateParameters(Method m){
        Random rand = new Random();
        Object[] parameters = new Object[m.getParameterTypes().length];
        int index = 0;
        for (Class parameterType : m.getParameterTypes()) {
            if (parameterType.isPrimitive()) {
                switch (parameterType.toString()) {
                    case "int" -> parameters[index++] = rand.nextInt();
                    case "float" -> parameters[index++] = rand.nextFloat();
                    case "double" -> parameters[index++] = rand.nextDouble();
                    case "long" -> parameters[index++] = rand.nextLong();
                    case "char" -> parameters[index++] = 'a';
                    case "boolean" -> {
                        if(rand.nextInt() % 2 == 0) parameters[index++] = true;
                        else parameters[index++] = false;
                    }
                }
            } else if(parameterType.toString().equals("class java.lang.String"))
                parameters[index++] = "Laborator 12";
            else parameters[index++] = null;
        }
        return parameters;
    }


    public void printDetails(Class myClass){
        System.out.println("Class methods are: " + Arrays.toString(myClass.getMethods()));
        System.out.println("Class constructors are: " + Arrays.toString(myClass.getConstructors()));
    }

    public void javaPrototype(String classPath, String className) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class myClass = myClassLoader.loadClass(classPath, className);
        StringBuilder classStringBuilder = new StringBuilder();
        var modifiers = myClass.getModifiers();

        if (Modifier.isPublic(modifiers))
            classStringBuilder.append("public ");
        if (Modifier.isFinal(modifiers))
            classStringBuilder.append("final ");
        if (Modifier.isStatic(modifiers))
            classStringBuilder.append("static ");
        if (Modifier.isInterface(modifiers))
            classStringBuilder.append("interface ");
        else if (Modifier.isAbstract(modifiers))
            classStringBuilder.append("abstract ");
        else classStringBuilder.append("class ");

        classStringBuilder.append(myClass.getName()).append(" {\n");

        for (Field field : myClass.getDeclaredFields())
            classStringBuilder.append("     ")
                    .append(Modifier.toString(field.getModifiers())).append(" ")
                    .append(field.getType().getName()).append(" ")
                    .append(field.getName()).append(";\n");

        for (Method method : myClass.getMethods()) {
            classStringBuilder.append("     ")
                    .append(method)
                    .append(";\n");

        }

        classStringBuilder.append("}");
        System.out.println(classStringBuilder);
    }
}
