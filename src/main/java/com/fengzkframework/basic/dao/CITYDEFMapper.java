package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.CITYDEF;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CITYDEFMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CITYDEF record);

    int insertSelective(CITYDEF record);

    CITYDEF selectByPrimaryKey(Long id);
    List<CITYDEF> selectall();

    int updateByPrimaryKeySelective(CITYDEF record);

    int updateByPrimaryKey(CITYDEF record);
}