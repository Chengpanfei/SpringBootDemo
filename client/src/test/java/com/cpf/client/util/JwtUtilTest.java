package com.cpf.client.util;

import org.junit.Assert;
import org.junit.Test;

public class JwtUtilTest {

    @Test
    public void createToken() {
        System.out.println(JwtUtil.createToken("Tom"));
    }

    @Test
    public void verifyToken() {
        Assert.assertEquals("Tom", JwtUtil.verifyToken(JwtUtil.createToken("Tom")));
    }
}