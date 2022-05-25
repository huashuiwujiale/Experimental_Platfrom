package com.wujiale.springbootsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wujiale.springbootsystem.entity.User;
import com.wujiale.springbootsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 对用户进行登录验证
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login",params = {"username","password"})
    public String login_auth(String username,String password){
//        System.out.println("我是wujiale");

        String auth_result = userService.login_auth(username,password);
        System.out.println(auth_result);
        return auth_result;
    }
    /**
     * 用户进行注册
     */
    @ResponseBody
    @RequestMapping(value = "/register",params = {"username","password"})
    public String register_auth(String username,String password){
        String register_auth = userService.register_auth(username, password);
        System.out.println(register_auth);
        return register_auth;
    }
    @ResponseBody
    @RequestMapping(value = "/container_list")
    public String getContainerList(@RequestParam String username){
        System.out.println(username);

        List<User> containerList = userService.getContainerList(username);
        String jsonOutput= JSON.toJSONString(containerList);
        System.out.println(jsonOutput);
        return jsonOutput;
    }
}
