package com.course.code;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.testng.annotations.Test;

public class IgnoreTest {

    @Test
    public void ignore1(){
        System.out.println("ignore1 执行");
    }

    @Test(enabled = false)
    public void ignore2(){
        System.out.println("ignore2 执行");
    }

    @Test
    public void ignore3(){
        System.out.println("ignore3 执行");
    }
}
