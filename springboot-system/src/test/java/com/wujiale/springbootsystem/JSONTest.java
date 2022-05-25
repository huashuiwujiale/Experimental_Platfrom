package com.wujiale.springbootsystem;

import com.alibaba.fastjson.JSONObject;
import com.wujiale.springbootsystem.entity.Container;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * ClassName:JSONTest
 * Date:2022/5/1814:45
 *
 * @autherWJL
 */
public class JSONTest {

    @Test
    public void getJsonParam(){
        JSONObject jsonParams = new JSONObject();
    }

    public String codeRun(JSONObject jsonParams){

        Container container = JSONObject.toJavaObject(jsonParams, Container.class);
        System.out.println(container);


        return "运行成功";
    }
}
