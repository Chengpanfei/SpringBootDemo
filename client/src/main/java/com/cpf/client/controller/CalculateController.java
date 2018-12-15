package com.cpf.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName CalculateController
 * Description 对外提供服务的接口
 *
 * @author CPF
 * @version 1.0
 * @date 2018/12/8
 */
@RefreshScope
@RestController
public class CalculateController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${from}")
    String from;

    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        logger.info("call add success!");
        return a + b;
    }

    @GetMapping("/from")
    public String config() {
        return from;
    }
}
