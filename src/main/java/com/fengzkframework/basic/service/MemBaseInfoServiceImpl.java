package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.MEM_BASEINFOMapper;
import com.fengzkframework.basic.dao.vo.MEM_BASEINFO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//("MemBaseInfoServiceImpl")
public class MemBaseInfoServiceImpl {
	@Autowired
	private MEM_BASEINFOMapper memMapper;

	public MEM_BASEINFO selectByPrimaryKey(Long hyid) {
		// TODO Auto-generated method stub
		return memMapper.selectByPrimaryKey(hyid);
	}

	public String selectNameByPrimaryKey(Long hyid) {
		// TODO Auto-generated method stub
		return memMapper.selectByPrimaryKey(hyid).getHyname();
	}

	public MEM_BASEINFO selectByopenid(String openid) {
		// TODO Auto-generated method stub
		return memMapper.selectByopenid(openid);
	}

	public List<MEM_BASEINFO> selectByphone(String phone) {
		// TODO Auto-generated method stub
		return memMapper.selectByphone(phone);
	}
	
}
