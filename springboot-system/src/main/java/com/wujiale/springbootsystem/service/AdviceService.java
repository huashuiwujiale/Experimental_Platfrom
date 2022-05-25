package com.wujiale.springbootsystem.service;

import com.wujiale.springbootsystem.entity.Advice;
import com.wujiale.springbootsystem.mapper.AdviceMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:AdviceService
 * Date:2022/5/211:44
 *
 * @autherWJL
 */
@Service
public class AdviceService {
    @Autowired
    AdviceMapper adviceMapper;
    public List<Advice> getAdviceList(){
        List<Advice> adviceList = adviceMapper.getAdviceList();
        return adviceList;
    }

    public String addAdvice(Advice advice){
        Integer addAdvice = adviceMapper.addAdvice(advice);
        if (addAdvice>0){
            return "添加成功";
        }else {
            return "添加失败";
        }
    }
}
