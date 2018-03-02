package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.PERSONINFO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PERSONINFOMapper {
    int deleteByPrimaryKey(Long personId);

    int insert(PERSONINFO record);

    int insertSelective(PERSONINFO record);

    PERSONINFO selectByPrimaryKey(Long personId);
    PERSONINFO selectBycode(String rydm);

    int updateByPrimaryKeySelective(PERSONINFO record);

    int updateByPrimaryKey(PERSONINFO record);
}