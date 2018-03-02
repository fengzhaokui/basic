package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.XTCZY;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XTCZYMapper {
    int deleteByPrimaryKey(Long personId);

    int insert(XTCZY record);

    int insertSelective(XTCZY record);

    XTCZY selectByPrimaryKey(Long personId);

    int updateByPrimaryKeySelective(XTCZY record);

    int updateByPrimaryKeyWithBLOBs(XTCZY record);

    int updateByPrimaryKey(XTCZY record);
}