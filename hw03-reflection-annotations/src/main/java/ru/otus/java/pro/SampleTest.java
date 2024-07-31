package ru.otus.java.pro;

import ru.otus.java.pro.annotations.After;
import ru.otus.java.pro.annotations.Before;
import ru.otus.java.pro.annotations.Test;

import java.util.logging.Logger;

public class SampleTest {
    Logger logger = Logger.getLogger(SampleTest.class.getName());
    @Before
    public void before_01() {
        logger.info("Before 1");
    }

    @Before
    public void before_02() {
        logger.info("Before 2");
    }

    @Test
    public void test_01() {
        logger.info("Test 1");
    }

    @Test
    public void test_02() {
        logger.info("Test 2");
        throw new RuntimeException();
    }

    @After
    public void after_01() {
        logger.info("After 1");
    }
}
