package com.course.code;

import org.testng.annotations.*;

public class BasicAnnotation {

    @Test
    public void testCase2(){
        System.out.printf("这是测试用例2的Thread Id : %s%n",Thread.currentThread().getId());
        System.out.println("这是测试用例2");
    }

    @Test
    public void testcase1(){
        System.out.printf("这是测试用例1的Thread Id : %s%n",Thread.currentThread().getId());
        System.out.println("这是测试用例1");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("方法前");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("方法后");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("类运行前");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("类运行后");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BeforeSuite测试套件");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("AfterSuite测试套件");
    }
}
