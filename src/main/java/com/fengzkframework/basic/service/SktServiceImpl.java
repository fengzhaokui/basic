package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.SKTMapper;
import com.fengzkframework.basic.dao.vo.SKT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SktServiceImpl {
	@Autowired
	SKTMapper sktMapper;
	
	//@Override
	public SKT selectByPrimaryKey(String sktno) {
		// TODO Auto-generated method stub
		return sktMapper.selectByPrimaryKey(sktno);
	}

	public SKT selectByMaCode(String macode) {
		// TODO Auto-generated method stub
		return sktMapper.selectByMaCode(macode);
	}
	
	
}
