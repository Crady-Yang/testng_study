package com.course.code.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProvierTest {

    @Test(dataProvider = "data")
    public void testDataProvider(String name,int age){
        System.out.println("name = " + name + "\n age = " + age);
    }

    @DataProvider(name="data")
    public Object[][] providerData(){
        Object[][] o = new Object[][]{
                {"老王",10},
                {"老张",20},
                {"老周",21}
        };
        return o;
    }

    @Test(dataProvider = "methodData")
    public void test1(String name,int age){
        System.out.println("test111方法\nname= " + name + "\n age = " + age);
    }

    @Test(dataProvider = "methodData")
    public void test2(String name,int age){
        System.out.println("test222方法\nname = " + name + "\n age = " + age);
    }

    @DataProvider(name="methodData")
    public Object[][] methodDataTest(Method method){
        Object[][] result = null;
        if(method.getName().equals("test1")){
            result = new Object[][]{
                    {"老王",10},
                    {"老王1",11}
            };
        }

        if(method.getName().equals("test2")){
            result = new Object[][]{
                    {"老王2",20},
                    {"老王2",21}
            };
        }
        return result;
    }
}
