package com.cpf.client.controller;

import com.cpf.client.pojo.UserEntity;
import com.cpf.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


// TODO 在这里添加文档说明

@Controller
public class HomeController {


    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
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
    public UserEntity getUser(
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
    public String addUser(@Valid UserEntity user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getFieldError().getDefaultMessage();
        } else {  //表单验证没有错误
            userService.addUser(user);
            return "Create user success!  info:" + user.toString();
        }

    }
}
