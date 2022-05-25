package com.wujiale.springbootsystem.mapper;

import com.wujiale.springbootsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 对用户进行登录验证
     * @return User
     */
    public User login_auth(String username);

    /**
     * 用户注册
     * @param username
     * @param password
     */
    public int register_auth(String username,String password);

    public List<User> getContainerList(String username);
}
