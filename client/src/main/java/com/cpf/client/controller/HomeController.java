package com.cpf.client.controller;

import com.cpf.client.pojo.User;
import com.cpf.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
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
     * @param principal 用户信息
     * @return 用户信息
     */
    @GetMapping("/")
    @ResponseBody
    public Principal oauthUser(Principal principal) {
        return principal;
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
            msg.put("error", bindingResult.getFieldError().getDefaultMessage());
            return msg;
        } else {  //表单验证没有错误
            userService.addUser(user);
            msg.put("error", "null");
            msg.put("user", user);
            return msg;
        }

    }
}
