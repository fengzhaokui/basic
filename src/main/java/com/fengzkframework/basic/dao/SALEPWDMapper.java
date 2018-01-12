package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.SALEPWD;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SALEPWDMapper {
    int deleteByPrimaryKey(Long hyid);

    int insert(SALEPWD record);

    int insertSelective(SALEPWD record);

    SALEPWD selectByPrimaryKey(Long hyid);

    int updateByPrimaryKeySelective(SALEPWD record);

    int updateByPrimaryKey(SALEPWD record);
}