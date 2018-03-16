package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.MEM_COUPON_ACCOUNT;
import com.fengzkframework.basic.dao.vo.MEM_COUPON_ACCOUNTKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MEM_COUPON_ACCOUNTMapper {
    int deleteByPrimaryKey(MEM_COUPON_ACCOUNTKey key);

    int insert(MEM_COUPON_ACCOUNT record);

    int insertSelective(MEM_COUPON_ACCOUNT record);

    MEM_COUPON_ACCOUNT selectByPrimaryKey(MEM_COUPON_ACCOUNTKey key);
    MEM_COUPON_ACCOUNT selectBycode(String key);

    Double selectByyhqid(long yhqid);

    int updateByPrimaryKeySelective(MEM_COUPON_ACCOUNT record);

    int updateByPrimaryKey(MEM_COUPON_ACCOUNT record);
}