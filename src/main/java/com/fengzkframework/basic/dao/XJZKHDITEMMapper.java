package com.fengzkframework.basic.dao;


import com.fengzkframework.basic.dao.vo.XJZKHDITEM;
import com.fengzkframework.basic.dao.vo.XJZKHDITEMKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XJZKHDITEMMapper {
    int deleteByPrimaryKey(XJZKHDITEMKey key);

    int insert(XJZKHDITEM record);

    int insertSelective(XJZKHDITEM record);

    XJZKHDITEM selectByPrimaryKey(XJZKHDITEMKey key);

    int updateByPrimaryKeySelective(XJZKHDITEM record);

    int updateByPrimaryKey(XJZKHDITEM record);
}