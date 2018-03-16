package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.MEM_COUPON_DEF;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MEM_COUPON_DEFMapper {
    int deleteByPrimaryKey(Long yhqid);

    int insert(MEM_COUPON_DEF record);

    int insertSelective(MEM_COUPON_DEF record);

    MEM_COUPON_DEF selectByPrimaryKey(Long yhqid);

    List<MEM_COUPON_DEF> selectByshopid(Long shopid);

    int updateByPrimaryKeySelective(MEM_COUPON_DEF record);

    int updateByPrimaryKey(MEM_COUPON_DEF record);
}