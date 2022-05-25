package com.wujiale.springbootsystem.mapper;

import com.wujiale.springbootsystem.entity.Container;
import com.wujiale.springbootsystem.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName:ExperimentMapper
 * Date:2022/5/821:03
 *
 * @autherWJL
 */
@Mapper
public interface ExperimentMapper {
//  根据用户名获取用户信息
    public User getExperimentInfo(String username);
//  存储容器信息
    public int storageContainer(@Param("container") Container container);
//  删除容器
    public int deleteContainer(String container_id);
//  根据id 获取容器对象 javacode使用
    public Container getContainerByContainerId(String container_id);
}
