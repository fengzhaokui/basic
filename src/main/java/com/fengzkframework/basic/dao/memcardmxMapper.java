package com.fengzkframework.basic.dao;

import com.fengzkframework.basic.dao.vo.memcardmx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface memcardmxMapper {
    int deleteByPrimaryKey(Long id);

    List<memcardmx> sumByhyid(@Param("ywlxlist")List<String> ywlxlist,@Param("hyid")Long hyid);

    BigDecimal sumByhyidywlx(@Param("ywlxlist")List<String> ywlxlist,@Param("hyid")Long hyid);

    int insert(memcardmx record);

    int insertSelective(memcardmx record);

    memcardmx selectByPrimaryKey(Long id);

    List<memcardmx> selectByhyid(Long hyid);

     List<memcardmx> selectByhyidpage(@Param("hyid")Long hyid, @Param("start")int pageStart, @Param("end")int pageEnd);
    List<memcardmx> selectOldmx( @Param("sktno")String sktno, @Param("jlbh")int jlbh);

    int updateByPrimaryKeySelective(memcardmx record);

    int updateByPrimaryKey(memcardmx record);
}