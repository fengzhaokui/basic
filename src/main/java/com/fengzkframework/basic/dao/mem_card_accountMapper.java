package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.mem_card_account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface mem_card_accountMapper {
    int deleteByPrimaryKey(Long hyid);

    int insert(mem_card_account record);

    int insertSelective(mem_card_account record);

    mem_card_account selectByPrimaryKey(Long hyid);

    int updateByPrimaryKeySelective(mem_card_account record);

    int updateByPrimaryKey(mem_card_account record);
}