package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.TOKEN;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TOKENMapper {
    
    TOKEN selectByPrimaryKey(String tokenguid);
    int insert(TOKEN record);
 
}