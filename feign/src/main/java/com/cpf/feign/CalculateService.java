package com.cpf.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName CalculateService
 * Description Feign的作用就是简化http客户端的开发，这里只需要声明接口
 * 就可以直接作为服务使用，相当于把远程接口服务转化为本地服务
 *
 * @author CPF
 * @version 1.0
 * @date 2018/12/8
 */
@Primary
@FeignClient(value = "eureka-client", fallback = CalculateServiceFallbackImpl.class)
public interface CalculateService {
    /**
     * 提供计算两个数和的微服务
     *
     * @param a 加数a
     * @param b 加数b
     * @return 两个加数的和
     */
    @GetMapping("/client/add")
    Integer add(@RequestParam("a") Integer a, @RequestParam("b") Integer b);

}

@Component
class CalculateServiceFallbackImpl implements CalculateService {
    /**
     * 快速失败断路器，当断路器打开时服务直接返回0
     * 断路器主要是为了防止雪崩效应
     */
    @Override
    public Integer add(Integer a, Integer b) {
        return 0;
    }
}
