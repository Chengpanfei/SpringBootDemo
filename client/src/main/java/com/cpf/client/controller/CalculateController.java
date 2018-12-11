package com.cpf.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName CalculateController
 * Description 对外提供服务的接口
 *
 * @author CPF
 * @version 1.0
 * @date 2018/12/8
 */
@RestController
public class CalculateController {
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        return a + b;
    }
}
