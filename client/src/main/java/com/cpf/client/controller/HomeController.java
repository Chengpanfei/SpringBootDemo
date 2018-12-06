package com.cpf.client.controller;

import com.cpf.client.pojo.User;
import com.cpf.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


/**
 * @author CPF
 */
@Controller
public class HomeController {


    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 显示三方认证信息
     *
     * @param authentication 用户信息
     * @return 用户信息
     */
    @GetMapping("/")
    @ResponseBody
    public Authentication oauthUser(Authentication authentication) {
        return authentication;
    }

    /**
     * GetDemo
     *
     * @return null
     */
    @GetMapping(path = "/home")
    @ResponseBody
    public String sayHello() {
        return userService.getUserInfo(1);
    }

    @GetMapping(path = "test")
    @ResponseBody
    @PreAuthorize("hasAuthority('Delete Post')")
    public String methodProtect() {
        return "Congratulations! You passed the authorization test!";
    }

    /**
     * RequestParamDemo
     */
    @GetMapping(path = "/user")
    @ResponseBody
    public String user(
            @RequestParam(required = false, name = "username") String name
    ) {
        String str = userService.getUserInfoByName(name);
        return "您的名字：" + name + "<br>"
                + "查询到的信息：" + str;
    }


    @GetMapping(path = "/getUser")
    @ResponseBody
    public User getUser(
            @RequestParam(required = false, name = "username") String name
    ) {
        return userService.getUserByName(name);
    }

    /**
     * Pathvariable Test Demo
     */
    @ResponseBody
    @RequestMapping(path = "/{username}/info", method = RequestMethod.GET)
    public String userInfo(
            @PathVariable(name = "username") String username
    ) {
        return "PathVariable:" + username;
    }

    /**
     * ListAllUserDemo
     */
    @ResponseBody
    @GetMapping("/list")
    public String list() {
        return userService.listAllUserInfo();
    }

    /**
     * createUserTest
     */
    @PostMapping("/users")
    @ResponseBody
    public Map<String, Object> addUser(@Valid User user, BindingResult bindingResult) {
        Map<String, Object> msg = new HashMap<>(2);

        if (bindingResult.hasErrors()) {
            FieldError error = bindingResult.getFieldError();
            if (error != null) {
                msg.put("error", error.getDefaultMessage());
            }
            return msg;
        } else {  //表单验证没有错误
            userService.addUser(user);
            msg.put("error", "null");
            msg.put("user", user);
            return msg;
        }

    }
}
