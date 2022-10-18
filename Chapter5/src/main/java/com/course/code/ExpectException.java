package com.course.code;

import org.testng.annotations.Test;

public class ExpectException {

    //测试结果失败的异常测试
    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed(){
        System.out.println("这是一个失败异常测试");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess(){
        System.out.println("这是一个成功异常测试");
        throw new RuntimeException();
    }
}
