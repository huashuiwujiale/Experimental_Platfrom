package com.wujiale.springbootsystem.service;

import com.wujiale.springbootsystem.entity.Image;
import com.wujiale.springbootsystem.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName:ImageService
 * Date:2022/5/210:21
 *
 * @autherWJL
 */
@Service
public class ImageService {
    @Autowired
    ImageMapper imageMapper;
    public List<Image> getImageList(){
        List<Image> imageList = imageMapper.getImageList();
        return imageList;
    }
    public String addImage(Image image){
        Integer addImage = imageMapper.addImage(image);
        if (addImage>0){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }
    public String deleteImage(String value){
        Integer deleteImage = imageMapper.deleteImage(value);
        if (deleteImage>0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }
}
