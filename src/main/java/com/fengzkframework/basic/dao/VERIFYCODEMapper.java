package com.fengzkframework.basic.dao;


import com.fengzkframework.basic.dao.vo.VERIFYCODE;
import com.fengzkframework.basic.dao.vo.VERIFYCODEKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VERIFYCODEMapper {
    int deleteByPrimaryKey(VERIFYCODEKey key);

    int insert(VERIFYCODE record);

    int insertSelective(VERIFYCODE record);

    VERIFYCODE selectByPrimaryKey(VERIFYCODEKey key);

    int updateByPrimaryKeySelective(VERIFYCODE record);

    int updateByPrimaryKey(VERIFYCODE record);
}