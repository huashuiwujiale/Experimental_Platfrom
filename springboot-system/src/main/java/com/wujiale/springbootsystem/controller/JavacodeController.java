package com.wujiale.springbootsystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wujiale.springbootsystem.entity.Container;
import com.wujiale.springbootsystem.service.JavacodeService;
import com.wujiale.springbootsystem.utils.SessionExecCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:JavacodeController
 * Date:2022/5/1723:47
 *
 * @autherWJL
 */
@Controller
public class JavacodeController {
    @Autowired
    JavacodeService javacodeService;         //获取运行结果

    /**
     * 接收到用户名
     * @param jsonParams
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/code_run")
    public String codeRun(@RequestBody JSONObject jsonParams){
        System.out.println(jsonParams);
        String username = jsonParams.getJSONObject("data").getString("username");
        String container_id = jsonParams.getJSONObject("data").getString("container_id");
        String code = jsonParams.getJSONObject("data").getString("code");
//        获取运行结果
        StringBuffer stringBuffer = javacodeService.codeRun(username, container_id, code);
        String codeRun_result = stringBuffer.toString();
        System.out.println(codeRun_result);
        String userjson= JSON.toJSONString(codeRun_result);
        return userjson;
    }
    @ResponseBody
    @PostMapping(value = "/code_save")
    public String codeSave(@RequestBody JSONObject jsonParams){
        System.out.println(jsonParams);
        String username = jsonParams.getJSONObject("data").getString("username");
        String container_id = jsonParams.getJSONObject("data").getString("container_id");
        String code = jsonParams.getJSONObject("data").getString("code");
//        获取运行结果
        String s = javacodeService.codeSave(username, container_id, code);
        System.out.println(s);
        String userjson= JSON.toJSONString(s);
        return userjson;
    }
    @ResponseBody
    @PostMapping(value = "/code_read")
    public String codeRead(@RequestBody JSONObject jsonParams){
        System.out.println(jsonParams);
        String username = jsonParams.getJSONObject("data").getString("username");
        String container_id = jsonParams.getJSONObject("data").getString("container_id");
        String code = jsonParams.getJSONObject("data").getString("code");
//        获取运行结果
        String s = javacodeService.codeRead(username, container_id, code);
        System.out.println(s);
        String userjson= JSON.toJSONString(s);
        return userjson;
    }
}
