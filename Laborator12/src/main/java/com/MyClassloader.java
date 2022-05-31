package com;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MyClassLoader extends URLClassLoader {
    public MyClassLoader() {
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


    public Set<String> getClassesFromJar(File file) throws IOException {
        Set<String> classes = new HashSet<>();
        try (JarFile jarFile = new JarFile(file)) {
            Enumeration<JarEntry> e = jarFile.entries();
            while (e.hasMoreElements()) {
                JarEntry entry = e.nextElement();
                if (entry.getName().endsWith(".class")) {
                    String className = entry.getName()
                            .replace("/", ".")
                            .replace(".class", "");
                    classes.add(className);
                }
            }
            return classes;
        }
    }

    public Set<Class> loadClassesFromJar(File file) throws IOException, ClassNotFoundException {
        Set<String> classNames = null;
        classNames = getClassesFromJar(file);
        Set<Class> classes = new HashSet<>(classNames.size());
        URLClassLoader classLoader = URLClassLoader.newInstance(
                new URL[]{new URL("jar:file:" + file + "!/")});
        for (String name : classNames) {
            Class myClass = classLoader.loadClass(name);
            classes.add(myClass);
        }
        return classes;
    }

}