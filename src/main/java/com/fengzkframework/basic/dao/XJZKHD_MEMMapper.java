package com.fengzkframework.basic.dao;


import com.fengzkframework.basic.dao.vo.XJZKHD_MEM;
import com.fengzkframework.basic.dao.vo.XJZKHD_MEMKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XJZKHD_MEMMapper {
    int deleteByPrimaryKey(XJZKHD_MEMKey key);

    int insert(XJZKHD_MEM record);

    int insertSelective(XJZKHD_MEM record);

    XJZKHD_MEM selectByPrimaryKey(XJZKHD_MEMKey key);

    int updateByPrimaryKeySelective(XJZKHD_MEM record);

    int updateByPrimaryKey(XJZKHD_MEM record);
}