package com.wujiale.springbootsystem.controller;

import com.wujiale.springbootsystem.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:ExperimentController
 * Date:2022/5/820:55
 *
 * @autherWJL
 */
@Controller
public class ExperimentController {

    @Autowired
    ExperimentService experimentService;

    @ResponseBody
    @RequestMapping("/getExperimentInfo")
    public String getExperimentInfo(@RequestParam String username){
        String experimentInfo = experimentService.getExperimentInfo(username);
        return experimentInfo;
    }
    @ResponseBody
    @RequestMapping("/create_environment")
    public String create_environment(@RequestParam String username,@RequestParam String image_name){
        System.out.println(username);
        System.out.println(image_name);
        String environment = experimentService.create_environment(username, image_name);

        return environment;
    }
    @ResponseBody
    @RequestMapping("/deletecontainer")
    public String deleteContainer(@RequestParam String container_id){
        String delete_result = experimentService.deleteContainer(container_id);
        return delete_result;
    }
}
