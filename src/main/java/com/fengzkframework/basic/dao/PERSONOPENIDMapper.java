package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.PERSONOPENID;
import com.fengzkframework.basic.dao.vo.PERSONOPENIDKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PERSONOPENIDMapper {
    int deleteByPrimaryKey(PERSONOPENIDKey key);

    int insert(PERSONOPENID record);

    int insertSelective(PERSONOPENID record);

    PERSONOPENID selectByPrimaryKey(PERSONOPENIDKey key);

    List<PERSONOPENID> selectByopenid(PERSONOPENIDKey key);

    List<PERSONOPENID> selectByopenidall(String openid);

    int updateByPrimaryKeySelective(PERSONOPENID record);

    int updateByPrimaryKey(PERSONOPENID record);
}