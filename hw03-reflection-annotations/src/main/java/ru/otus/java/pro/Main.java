package ru.otus.java.pro;

import javax.management.ReflectionException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws ReflectionException {
        Tester.start(SampleTest.class.getName());
    }
}