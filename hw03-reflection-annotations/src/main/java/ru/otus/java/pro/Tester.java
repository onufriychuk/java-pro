package ru.otus.java.pro;

import ru.otus.java.pro.annotations.After;
import ru.otus.java.pro.annotations.Before;
import ru.otus.java.pro.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Tester {
    static Logger logger = Logger.getLogger(Tester.class.getName());

    public static void start(String testClassName) throws ClassNotFoundException {
        var testClass = Class.forName(testClassName);
        Set<Method> beforeMethods = getMethods(testClass, Before.class);
        Set<Method> testMethods = getMethods(testClass, Test.class);
        Set<Method> afterMethods = getMethods(testClass, After.class);

        int testSucessfullCounter = 0;

        for (Method testMethod : testMethods) {
            testSucessfullCounter += RunTest(testMethod, testClass, beforeMethods, afterMethods);
        }
        logger.log(Level.INFO, "Successful test: {0}", testSucessfullCounter);
        logger.log(Level.INFO, "FAILED test: {0}", testMethods.size() - testSucessfullCounter);
        logger.log(Level.INFO, "Tests: {0}", testMethods.size());
    }

    private static Set<Method> getMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(a -> a.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());
    }

    private static int RunTest(Method testMethod, Class<?> clazz, Set<Method> beforeMethods, Set<Method> afterMethods) {
        try {
            logger.log(Level.INFO, "Start test {0}", testMethod.getName());
            var testClassInstance = clazz.getConstructor().newInstance();

            for (Method method : beforeMethods) {
                invokeMethod(method, testClassInstance);
            }

            invokeMethod(testMethod, testClassInstance);

            for (Method method : afterMethods) {
                invokeMethod(method, testClassInstance);
            }

        } catch (Exception e) {
            logger.log(Level.INFO, "Test {0} FAILED", testMethod.getName());
            return 0;
        }
        return 1;
    }

    private static <T> void invokeMethod(Method method, T testClassInstance) throws RuntimeException{
        try {
            method.setAccessible(true);
            method.invoke(testClassInstance);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
