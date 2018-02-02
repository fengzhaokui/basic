package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.SKT;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SKTMapper {
    int deleteByPrimaryKey(String sktno);

    int insert(SKT record);

    int insertSelective(SKT record);

    SKT selectByPrimaryKey(String sktno);
    
    SKT selectByMaCode(String macode);

    List<SKT> selectBymdid(Integer mdid);

    int updateByPrimaryKeySelective(SKT record);

    int updateByPrimaryKey(SKT record);
}