package ru.otus.java.pro;

import ru.otus.java.pro.annotations.After;
import ru.otus.java.pro.annotations.Before;
import ru.otus.java.pro.annotations.Test;

import javax.management.ReflectionException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Tester {
    static Logger logger = Logger.getLogger(Tester.class.getName());

    public static void start(String testClassName) throws ReflectionException {
        try {
            var testClass = Class.forName(testClassName);
            Set<Method> beforeMethods = getMethods(testClass, Before.class);
            Set<Method> testMethods = getMethods(testClass, Test.class);
            Set<Method> afterMethods = getMethods(testClass, After.class);

            int testSucessfullCounter = 0;
            int testFailedCounter = 0;

            for (Method testMethod : testMethods) {
                var testClassInstance = testClass.getConstructor().newInstance();
                try {
                    for (Method method : beforeMethods) {
                        invokeMethod(method, testClassInstance);
                    }
                    testMethod.invoke(testClassInstance);
                    testSucessfullCounter++;
                } catch (Exception e) {
                    testFailedCounter++;
                }
                finally {
                    for (Method method : afterMethods) {
                        invokeMethod(method, testClassInstance);
                    }
                }
            }
            logger.log(Level.INFO, "Successful test: {0}", testSucessfullCounter);
            logger.log(Level.INFO, "FAILED test: {0}", testFailedCounter);
            logger.log(Level.INFO, "Tests: {0}", testMethods.size());
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new ReflectionException(e);
        }
    }

    private static Set<Method> getMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(a -> a.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());
    }


    private static <T> void invokeMethod(Method method, T testClassInstance) throws InvocationTargetException, IllegalAccessException {
            method.setAccessible(true);
            method.invoke(testClassInstance);
    }
}
