package com.wujiale.springbootsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wujiale.springbootsystem.entity.Advice;
import com.wujiale.springbootsystem.service.AdviceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName:AdviceController
 * Date:2022/5/210:12
 * 用户反馈查看
 * @autherWJL
 */
@Controller
public class AdviceController {
    @Autowired
    AdviceService adviceService;
    @ResponseBody
    @RequestMapping("/getAdviceList")
    public String getAdviceList(){
        List<Advice> adviceList = adviceService.getAdviceList();
        String jsonOutput= JSON.toJSONString(adviceList);
        System.out.println(jsonOutput);
        return jsonOutput;
    }

    @ResponseBody
    @RequestMapping("/addAdvice")
    public String addAdvice(@RequestParam String username,@RequestParam String comment,@RequestParam String date){
        Advice advice = new Advice();
        advice.setUsername(username);
        advice.setComment(comment);
        advice.setDate(date);
        advice.setType("primary");
        String addAdvice = adviceService.addAdvice(advice);
        return addAdvice;
    }
}
