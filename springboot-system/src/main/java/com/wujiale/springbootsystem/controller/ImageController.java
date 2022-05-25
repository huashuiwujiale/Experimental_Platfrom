package com.wujiale.springbootsystem.controller;

import com.alibaba.fastjson.JSON;
import com.wujiale.springbootsystem.entity.Image;
import com.wujiale.springbootsystem.entity.User;
import com.wujiale.springbootsystem.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName:ImageController
 * Date:2022/5/210:11
 * 管理员对实验环境进行管理
 * @autherWJL
 */
@Controller
public class ImageController {
    @Autowired
    ImageService imageService;
    @ResponseBody
    @RequestMapping("/getImageList")
    public String getImageList(){
        List<Image> imageList = imageService.getImageList();
        String jsonOutput= JSON.toJSONString(imageList);
        System.out.println(jsonOutput);
        return jsonOutput;
    }
    @ResponseBody
    @RequestMapping("/addImage")
    public String addImage(@RequestParam String value,@RequestParam String lable,@RequestParam String describe){

        Image image = new Image();
        image.setValue(value);
        image.setLable(lable);
        image.setDescribe(describe);
        System.out.println(image);
        String addImage = imageService.addImage(image);
        return addImage;
    }
    @ResponseBody
    @RequestMapping("/deleteImage")
    public String deleteImage(@RequestParam String value){
        System.out.println(value);
        String deleteImage = imageService.deleteImage(value);
        return deleteImage;
    }
}
