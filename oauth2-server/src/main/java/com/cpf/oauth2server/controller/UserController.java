package com.cpf.oauth2server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * ClassName      UserController
 * Description    提供用户信息的api，作为OAuth2资源服务器提供的资源
 *
 * @author CPF
 * @version 1.0
 * @date 2018/12/4 15:48
 */
@RestController
public class UserController {
    @GetMapping("/oauth/user")
    public Principal userInfo(Principal principal) {
        return principal;
    }
}
