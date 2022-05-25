package com.wujiale.springbootsystem;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName:CommandJointTest
 * Date:2022/5/1623:32
 * Docker 代码拼接测试
 * @autherWJL
 */
public class CommandJointTest {
    @Test
    public void generate(){

        generate_command("wujiale","jdk11andmaven3.8.5");
    }

    public void generate_command(String username,String image_name){
        StringBuffer docker_run_command = new StringBuffer();
        String container_name = username+image_name;
        docker_run_command.append("docker run --name=\"").append(container_name).append("\" -it -v /home/")
                .append(username).append("/").append(container_name).append(":/javafiles ").append(image_name).append(":v1.0 /bin/bash");
        System.out.println(docker_run_command);
    }
}
