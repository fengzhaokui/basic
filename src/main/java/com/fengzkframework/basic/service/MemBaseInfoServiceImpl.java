package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.MEM_BASEINFOMapper;
import com.fengzkframework.basic.dao.SALEPWDMapper;
import com.fengzkframework.basic.dao.vo.MEM_BASEINFO;
import com.fengzkframework.basic.dao.vo.SALEPWD;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.enums.ResultEnum;
import com.fengzkframework.basic.utils.ResultUtil;
import com.fengzkframework.basic.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service//("MemBaseInfoServiceImpl")
public class MemBaseInfoServiceImpl {
	@Autowired
	private MEM_BASEINFOMapper memMapper;
	@Autowired
	private SALEPWDMapper salepwdMapper;

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
	public ResultData ExistPWD(Map<String,String> map)
	{
		String openid=map.get("openid");
		if(StringUtil.strisnull(openid))
		{
			return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg());
		}
		MEM_BASEINFO mem_baseinfo=selectByopenid(openid);
		if(mem_baseinfo==null)
		{
			return ResultUtil.error(ResultEnum.NOHY.getCode(),ResultEnum.NOHY.getMsg());
		}
		SALEPWD salepwd=salepwdMapper.selectByPrimaryKey(mem_baseinfo.getHyid());

		if(salepwd!=null &&(!StringUtil.strisnull(salepwd.getPassword())))
		{
			return ResultUtil.success("true");
		}
		else
			return ResultUtil.success("false");

	}

	/*
	设置用户密码
	 */
	public ResultData sethypwd(Map<String,String> map)
	{
		String openid=map.get("openid");
		String pwd=map.get("pwd");
		if(StringUtil.strisnull(openid)||StringUtil.strisnull(pwd))
	{
		return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg());
	}
		MEM_BASEINFO mem_baseinfo=selectByopenid(openid);
		if(mem_baseinfo==null)
		{
			return ResultUtil.error(ResultEnum.NOHY.getCode(),ResultEnum.NOHY.getMsg());
		}
		SALEPWD salepwd=salepwdMapper.selectByPrimaryKey(mem_baseinfo.getHyid());
		if(salepwd!=null &&(!StringUtil.strisnull(salepwd.getPassword())))
		{
			return ResultUtil.error(ResultEnum.PWDISEXIST.getCode(),ResultEnum.PWDISEXIST.getMsg());
		}
		SALEPWD npwd=new SALEPWD();
		npwd.setHyid(mem_baseinfo.getHyid());
		npwd.setPassword(pwd);
		int num=salepwdMapper.insert(npwd);
		if(num>0)
		{
			return ResultUtil.success("");
		}
		else
			 return ResultUtil.error(ResultEnum.SETPWDFAIL.getCode(),ResultEnum.SETPWDFAIL.getMsg());
	}

	/*
	修改用户密码
	 */
	public ResultData updatehypwd(Map<String,String> map)
	{
		String openid=map.get("openid");
		String pwd=map.get("pwd");
		String oldpwd=map.get("oldpwd");
		if(StringUtil.strisnull(openid)||StringUtil.strisnull(pwd))
		{
			return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg());
		}
		MEM_BASEINFO mem_baseinfo=selectByopenid(openid);
		if(mem_baseinfo==null)
		{
			return ResultUtil.error(ResultEnum.NOHY.getCode(),ResultEnum.NOHY.getMsg());
		}
		SALEPWD salepwd=salepwdMapper.selectByPrimaryKey(mem_baseinfo.getHyid());
		if(salepwd==null &&(StringUtil.strisnull(salepwd.getPassword())))
		{
			return ResultUtil.error(ResultEnum.PWDISNOEXIST.getCode(),ResultEnum.PWDISNOEXIST.getMsg());
		}
		if(!salepwd.getPassword().equals(oldpwd))
		{
			return ResultUtil.error(ResultEnum.PWDISERROR.getCode(),ResultEnum.PWDISERROR.getMsg());
		}
		salepwd.setPassword(pwd);
		int num=salepwdMapper.updateByPrimaryKeySelective(salepwd);
		if(num>0)
		{
			return ResultUtil.success("");
		}
		else
			return ResultUtil.error(ResultEnum.SETPWDFAIL.getCode(),ResultEnum.SETPWDFAIL.getMsg());
	}
}
