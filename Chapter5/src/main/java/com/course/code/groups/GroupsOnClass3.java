package com.course.code.groups;

import org.testng.annotations.Test;

@Test(groups = "teacher")
public class GroupsOnClass3 {

    public void tea1(){
        System.out.println("GroupsOnClass3中的tea1运行");
    }

    public void tea2(){
        System.out.println("GroupsOnClass3中的tea2运行");
    }
}
