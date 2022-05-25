package com.wujiale.springbootsystem.entity;

import lombok.Data;

/**
 * ClassName:Advice
 * Date:2022/5/211:41
 * 用户的反馈实体类
 * @autherWJL
 */
@Data
public class Advice {
    private String username;
    private String comment;
    private String date;
    private String type;
}
