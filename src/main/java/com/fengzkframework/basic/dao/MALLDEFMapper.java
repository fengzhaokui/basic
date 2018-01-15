package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.MALLDEF;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MALLDEFMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MALLDEF record);

    int insertSelective(MALLDEF record);

    MALLDEF selectByPrimaryKey(Integer id);

    List<MALLDEF> selectByytandcity(MALLDEF def);

    List<MALLDEF> selectByytandcity2(MALLDEF def);

    int updateByPrimaryKeySelective(MALLDEF record);

    int updateByPrimaryKey(MALLDEF record);
}