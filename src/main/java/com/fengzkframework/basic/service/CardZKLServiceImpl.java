package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.CARDZKLMapper;
import com.fengzkframework.basic.dao.memcardmxMapper;
import com.fengzkframework.basic.dao.vo.CARDZKL;
import com.fengzkframework.basic.dao.vo.memcardmx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardZKLServiceImpl {
    @Autowired
    CARDZKLMapper mapper;
   public List<CARDZKL> selectall()
    {
      return   mapper.selectall();
    }
}
