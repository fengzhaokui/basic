package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.CARDZKL;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CARDZKLMapper {
    int deleteByPrimaryKey(Long skfs);

    int insert(CARDZKL record);

    int insertSelective(CARDZKL record);

    CARDZKL selectByPrimaryKey(Long skfs);

    List<CARDZKL> selectall();

    int updateByPrimaryKeySelective(CARDZKL record);

    int updateByPrimaryKey(CARDZKL record);
}