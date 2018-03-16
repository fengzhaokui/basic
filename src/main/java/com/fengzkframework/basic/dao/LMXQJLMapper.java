package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.LMXQJL;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LMXQJLMapper {
    int deleteByPrimaryKey(Long id);

    int insert(LMXQJL record);

    int insertSelective(LMXQJL record);

    LMXQJL selectByPrimaryKey(Long id);

    Double selectByyhqid(long yhqid);

    List<LMXQJL> selectByopenid(String openid);

    List<LMXQJL> selectByshopid(Long id);

    int updateByPrimaryKeySelective(LMXQJL record);

    int updateByPrimaryKey(LMXQJL record);
}