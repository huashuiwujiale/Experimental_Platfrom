package com.wujiale.springbootsystem.service;

import com.wujiale.springbootsystem.entity.Container;
import com.wujiale.springbootsystem.entity.User;
import com.wujiale.springbootsystem.mapper.ExperimentMapper;
import com.wujiale.springbootsystem.utils.SessionExecCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName:ExperimentService
 * Date:2022/5/821:01
 *
 * @autherWJL
 */
@Service
public class ExperimentService {
    @Autowired
    ExperimentMapper experimentMapper;
    @Autowired
    SessionExecCommand sessionExecCommand;
    public String getExperimentInfo(String username){
        User experimentInfo = experimentMapper.getExperimentInfo(username);
        return "";
    }
//    创建实验环境
    public String create_environment(String username,String image_name){
//        拼接到linux运行的命令行
        String command = generate_create_command(username, image_name);
        String firstline = sessionExecCommand.execCommand(command);
        sessionExecCommand.execCommand("docker ps");
        System.out.println("ExperimentService里的"+firstline);
//        新建容器实体类，并且添加属性，添加到数据库当中
        Container container = new Container();
        container.setUsername(username);
        container.setImage_name(image_name);
        container.setContainer_id(firstline);
        container.setContainer_type(image_name+":v1.0");
        System.out.println(container);
//      储存到数据库
        int i = experimentMapper.storageContainer(container);
        return "成功";
    }

    /**
     * 通过用户名 和 镜像名称生成创建容器的linux命令
     * 例如：docker run --name="wujialejdk11andmaven3.8.5" -it -v /home/wujiale/wujialejdk11andmaven3.8.5:/javafiles jdk11andmaven3.8.5:v1.0 /bin/bash
     * @param username
     * @param image_name
     */
    public String generate_create_command(String username,String image_name){
        StringBuffer docker_run_command = new StringBuffer();
        String container_name = username+image_name;
        docker_run_command.append("docker run --name=\"").append(container_name).append("\" -d -it -v /home/")
                .append(username).append("/").append(container_name).append(":/javafiles ").append(image_name).append(":v1.0");
        System.out.println(docker_run_command.toString());
        return docker_run_command.toString();
    }

    /**
     * 删除分为两步
     * 1、docker stop <CONTAINER ID|NAME>，docker rm <CONTAINER ID|NAME> <CONTAINER ID|NAME>
     * 2、在数据库的表中删除容器信息
     * @param container_id
     * @return
     */
    public String deleteContainer(String container_id){
        //1、docker stop ，rm
        String delete_command = generate_delete_command(container_id);
        sessionExecCommand.execCommand(delete_command);
        //2、在数据库的表中删除容器信息
        int result = experimentMapper.deleteContainer(container_id);
        if (result>0){
            return "删除成功";
        }
        return "";
    }

    public String generate_delete_command(String container_id){
        StringBuffer docker_run_command = new StringBuffer();
        docker_run_command.append("docker stop ").append(container_id).append(";docker rm ").append(container_id);
//        System.out.println(docker_run_command.toString());
        return docker_run_command.toString();
    }
}
