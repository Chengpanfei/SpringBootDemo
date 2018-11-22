package com.cpf.client.service;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    //Before和After注解是在每个测试方法执行前后执行

    @BeforeClass //进行一些数据的创建工作等
    public static void setUp() {
        System.out.println("Before");
    }

    @AfterClass //清空数据等操作,一个类只执行一次
    public static void tearDown() {
        System.out.println("After");
    }

    @Test
    public void getUserInfo() {
        //不应该存在id为0的用户
        Assert.assertEquals("null", userService.getUserInfo(0));
    }

    @Test
    public void listAllUserInfo() {
        System.out.println(userService.listAllUserInfo());
    }

    @Test
    public void getUserInfoByName() {
        Assert.assertTrue(userService.getUserInfoByName("Tom").equals("nothing found!"));
//        Assert.assertEquals("李白",userService.getUserInfoByName("李白"))
    }

    @Test
    public void addUser() {
    }

    @Test
    public void getUserByName() {
        System.out.println(userService.getUserByName("李白"));
    }
}