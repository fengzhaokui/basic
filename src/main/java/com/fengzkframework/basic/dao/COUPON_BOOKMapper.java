package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.COUPON_BOOK;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
@Mapper
public interface COUPON_BOOKMapper {
    int deleteByPrimaryKey(BigDecimal serialid);

    int insert(COUPON_BOOK record);

    int insertSelective(COUPON_BOOK record);

    COUPON_BOOK selectByPrimaryKey(BigDecimal serialid);

    int updateByPrimaryKeySelective(COUPON_BOOK record);

    int updateByPrimaryKey(COUPON_BOOK record);
}