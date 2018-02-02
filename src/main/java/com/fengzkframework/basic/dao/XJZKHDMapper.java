package com.fengzkframework.basic.dao;


import com.fengzkframework.basic.dao.vo.XJZKHD;
import com.fengzkframework.basic.dao.vo.XJZKHDITEMKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XJZKHDMapper {
    int deleteByPrimaryKey(Long id);

    int insert(XJZKHD record);

    int insertSelective(XJZKHD record);

    XJZKHD selectByPrimaryKey(Long id);
    
    XJZKHD selectOneHD(XJZKHDITEMKey key);

    int updateByPrimaryKeySelective(XJZKHD record);

    int updateByPrimaryKey(XJZKHD record);
}