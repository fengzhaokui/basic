package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.memcardmxMapper;
import com.fengzkframework.basic.dao.vo.memcardmx;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MemCardMXServiceImpl {
    @Autowired
    memcardmxMapper mapper;

    public int addmx(memcardmx mx)
    {
      return  mapper.insertSelective(mx);
    }
public  memcardmx selectByPrimaryKey(Long id)
{
    return  mapper.selectByPrimaryKey(id);
}
    public List<memcardmx> Getcardmx(long hyid)
    {
        return  mapper.selectByhyid(hyid);
    }
    public List<memcardmx> selectByhyidpage(Long hyId, int pageStart, int pageEnd)
    {
        return  mapper.selectByhyidpage(hyId,pageStart,pageEnd);
    }
    public List<memcardmx>  sumByhyid(List<String> ywlxlist,Long hyid)
    {
        return mapper.sumByhyid(ywlxlist,hyid);
    }
    public  List<memcardmx> selectOldmx(String sktno,int jlbh)
    {
        return  mapper.selectOldmx(sktno,jlbh);
    }
    public   BigDecimal sumByhyidywlx(List<String> ywlxlist, Long hyid)
    {
        return  mapper.sumByhyidywlx(ywlxlist,hyid);
    }
    public   int updateByPrimaryKeySelective(memcardmx record)
    {
        return  mapper.updateByPrimaryKeySelective(record);
    }
}
