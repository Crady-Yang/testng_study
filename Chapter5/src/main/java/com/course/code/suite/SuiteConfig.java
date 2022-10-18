package com.course.code.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/*
    测试套件前需要的
 */
public class SuiteConfig {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("before suite running");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("after suite running");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("beforeTest");
    }

    @AfterTest
    public void afterTest(){
        System.out.println("afterTest");
    }
}
