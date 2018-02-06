package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.SKTMapper;
import com.fengzkframework.basic.dao.vo.SKT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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

	public SKT selectBymdid(Integer mdid) {
		// TODO Auto-generated method stub
		List<SKT> list=sktMapper.selectBymdid(mdid);
		if(list!=null && list.size()>0)
			return list.get(0);
		else
			return null;
	}

	public SKT selectBymdid2(Integer mdid) {
		// TODO Auto-generated method stub
		List<SKT> list=sktMapper.selectBymdid2(mdid);
		if(list!=null && list.size()>0)
			return list.get(0);
		else
			return null;
	}
	
	
}
