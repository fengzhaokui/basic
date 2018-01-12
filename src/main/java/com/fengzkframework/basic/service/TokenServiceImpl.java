package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.TOKENMapper;
import com.fengzkframework.basic.dao.vo.TOKEN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service//("TokenServiceImpl")
public class TokenServiceImpl {
	@Autowired
	private TOKENMapper tokenMapper;
	
	//@Override
	@Cacheable(value = "record", key = "#tokenguid")
	public TOKEN selectByPrimaryKey(String tokenguid) {
		// TODO Auto-generated method stub
		return tokenMapper.selectByPrimaryKey(tokenguid);
	}
	@CachePut(value = "record", key = "#record.tokenguid")
	public int insert(TOKEN record) {
		// TODO Auto-generated method stub
		System.out.print("插入数据token");
		return tokenMapper.insert(record);
	}
	
	
}
