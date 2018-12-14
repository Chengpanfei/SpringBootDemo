package com.cpf.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName RibbonController
 * Description 使用ribbon+restTemplate实现服务消费者
 * 同时，整合Hystrix断路保护
 *
 * @author CPF
 * @version 1.0
 * @date 2018/12/13
 */
@RestController
public class RibbonController {
    private static Map<String, Object> DEFAULT_MAP;

    static {
        DEFAULT_MAP = new HashMap<>(3);
        DEFAULT_MAP.put("code", 1);
        DEFAULT_MAP.put("msg", "something wrong!");
        DEFAULT_MAP.put("result", 0);
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private RestTemplate restTemplate;

    @Autowired
    public RibbonController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/test")
    @HystrixCommand(fallbackMethod = "testFallback")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("code", 0);
        map.put("msg", "success!");
        map.put("result", restTemplate.getForObject("http://eureka-client/client/add?a=1&b=2", Integer.TYPE));

        logger.info("call test success!");

        return map;
    }

    @GetMapping("/db")
    @HystrixCommand(fallbackMethod = "testFallback")
    public Map<String, Object> dbTest() {
        Map<String, Object> map = new HashMap<>(3);
        map.put("code", 0);
        map.put("msg", "success!");
        map.put("result", restTemplate.getForObject("http://eureka-client/client/user?username=李白", String.class));
        return map;
    }


    public Map<String, Object> testFallback() {

        logger.info("call test failed!");

        return DEFAULT_MAP;
    }
}
