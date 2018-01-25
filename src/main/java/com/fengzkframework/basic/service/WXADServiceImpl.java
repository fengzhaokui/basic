package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.WXADMapper;
import com.fengzkframework.basic.dao.vo.WXAD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WXADServiceImpl {
    @Autowired
    private WXADMapper wxadMapper;
    public List<WXAD> selectall()
    {
        return  wxadMapper.selectall();
    }
}
