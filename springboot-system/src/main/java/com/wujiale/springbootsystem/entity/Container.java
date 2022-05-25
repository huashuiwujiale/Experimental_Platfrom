package com.wujiale.springbootsystem.entity;

import lombok.Data;

/**
 * ClassName:Container
 * Date:2022/5/821:11
 *
 * @autherWJL
 */
@Data
public class Container {
//     自增数字标识
    private Integer number;
//    构建容器的镜像名，不唯一，多个容器可以共用同一个镜像进行构建
    private String image_name;
//    容器自己的id，唯一的
    private String container_id;
//    拥有该容器的用户名
    private String username;
//    该实验环境的描述，例如：jdk8+maven
    private String container_type;
//    到容器运行的代码
    private String code;
    private String javacode_file;

}
