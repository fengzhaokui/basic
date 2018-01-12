package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.MEM_BASEINFO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MEM_BASEINFOMapper {


    MEM_BASEINFO selectByPrimaryKey(Long hyid);

    MEM_BASEINFO selectByopenid(String openid);
    List<MEM_BASEINFO> selectByphone(String phone);


}