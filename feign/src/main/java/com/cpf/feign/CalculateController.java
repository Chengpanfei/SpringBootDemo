package com.cpf.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName CalculateController
 * Description 作为服务调用者（客户端），接口对外开放
 *
 * @author CPF
 * @version 1.0
 * @date 2018/12/8
 */
@RestController
public class CalculateController {

    private CalculateService calculateService;

    @Autowired
    public CalculateController(CalculateService calculateService) {
        this.calculateService = calculateService;
    }

    @GetMapping("/add")
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        return calculateService.add(a, b);
    }

}
