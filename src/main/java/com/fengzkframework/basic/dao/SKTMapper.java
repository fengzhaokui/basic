package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.SKT;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SKTMapper {
    int deleteByPrimaryKey(String sktno);

    int insert(SKT record);

    int insertSelective(SKT record);

    SKT selectByPrimaryKey(String sktno);
    
    SKT selectByMaCode(String macode);

    SKT selectBymdid(Integer mdid);

    int updateByPrimaryKeySelective(SKT record);

    int updateByPrimaryKey(SKT record);
}