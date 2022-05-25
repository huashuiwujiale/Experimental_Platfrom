package com.wujiale.springbootsystem.mapper;

import com.wujiale.springbootsystem.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ClassName:ImageMapper
 * Date:2022/5/210:24
 *
 * @autherWJL
 */
@Mapper
public interface ImageMapper {
//    获取镜像列表
    public List<Image> getImageList();
    public Integer addImage(@Param("image") Image image);
    public Integer deleteImage(String value);
}
