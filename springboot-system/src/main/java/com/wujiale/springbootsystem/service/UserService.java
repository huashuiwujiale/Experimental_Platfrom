package com.wujiale.springbootsystem.service;

import com.wujiale.springbootsystem.entity.User;
import com.wujiale.springbootsystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 登录判断
     * @param username
     * @param password
     * @return
     */
    public String login_auth(String username,String password){
        String msg="";
        User user = userMapper.login_auth(username);
        if (user==null){
            System.out.println("你还没有注册");
            msg = "你还没有注册";
        }else{
            System.out.println(user.getPassword());
            if (user.getPassword().equals(password)){
                System.out.println("登录成功");
                msg = "登录成功";
            }
            else {
                System.out.println("密码错误");
                msg = "密码错误";
            }
        }
        return msg;
    }
    /**
     * 用户注册
     */
    public String register_auth(String username,String password){
//        i为影响的行数
        int i = userMapper.register_auth(username, password);
        String register_result=null;
//        System.out.println(i);
        if (i>0){
            register_result="注册成功";
        }
        return register_result;
    }

    public List<User> getContainerList(String username){
        List<User> containerList = userMapper.getContainerList(username);
        System.out.println(containerList);
        return containerList;
    }

}
