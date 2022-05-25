package com.wujiale.springbootsystem.mapper;

import com.wujiale.springbootsystem.entity.Advice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName:AdviceMapper
 * Date:2022/5/211:42
 *
 * @autherWJL
 */
@Mapper
public interface AdviceMapper {
    public List<Advice> getAdviceList();

    public Integer addAdvice(@Param("advice") Advice advice);
}
