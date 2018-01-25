package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.WXAD;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface WXADMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WXAD record);

    int insertSelective(WXAD record);

    WXAD selectByPrimaryKey(Integer id);

    List<WXAD> selectall();

    int updateByPrimaryKeySelective(WXAD record);

    int updateByPrimaryKey(WXAD record);
}