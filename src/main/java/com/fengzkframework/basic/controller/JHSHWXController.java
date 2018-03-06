package com.fengzkframework.basic.controller;

import com.fengzkframework.basic.aes.AESCrypt;
import com.fengzkframework.basic.dao.*;
import com.fengzkframework.basic.dao.vo.*;
import com.fengzkframework.basic.domain.RequestData;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.enums.ResultEnum;
import com.fengzkframework.basic.utils.MD5Utils;
import com.fengzkframework.basic.utils.ResultUtil;
import com.fengzkframework.basic.utils.StringUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 佳惠异业联盟商户
 */
@RestController
@RequestMapping("/weixin")
public class JHSHWXController {
    Logger logger = LoggerFactory.getLogger(JHSHWXController.class);
    Gson gson = new Gson();

    @Autowired
    XTCZYMapper xtczyMapper;
    @Autowired
    PERSONINFOMapper personinfoMapper;
    @Autowired
    PERSONOPENIDMapper personopenidMapper;
    @Autowired
    MEM_COUPON_ACCOUNTMapper memCouponAccountMapper;
    @Autowired
    MEM_COUPON_DEFMapper memCouponDefMapper;


    @PostMapping(value = "/shuserinfo", produces = "application/json")
    public ResultData shuserinfo(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if (StringUtil.strisnull(data)) {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(), ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            String username = map.get("username");
            String openid = map.get("openid");
            List<PERSONOPENID> old = personopenidMapper.selectByopenidall(openid);
            if (old != null) {
                for(PERSONOPENID item : old)
                {
                    if(item.getBj_login()==1) {
                       item.setUsername(username);
                        personopenidMapper.updateByPrimaryKeySelective(item);
                        return ResultUtil.success("");
                    }
                }
            }

        }
        else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
        return ResultUtil.error(ResultEnum.SETSHUSERNAMEFAIL.getCode(), ResultEnum.SETSHUSERNAMEFAIL.getMsg());
    }


    /*
      商户登录
      personcode,pwd,openid
       */
    @PostMapping(value = "/shhylogin", produces = "application/json")
    public ResultData shhylogin(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
             data= AESCrypt.decryptAES( data);
            if (StringUtil.strisnull(data)) {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(), ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            String pc = map.get("personcode");
            String pwd = map.get("pwd");
            String openid = map.get("openid");
            PERSONINFO personinfo = personinfoMapper.selectBycode(pc);
            if (personinfo == null) {
                logger.info("用户不存在");
                return ResultUtil.error(ResultEnum.NOHY.getCode(), ResultEnum.NOHY.getMsg());
            }
            //验证密码
            XTCZY xtczy = xtczyMapper.selectByPrimaryKey(personinfo.getPersonId());
            pwd = MD5Utils.md5Password(pwd);
            //不区分密码大小写
            if (xtczy != null && xtczy.getLoginPassword().equalsIgnoreCase(pwd)) {
                PERSONOPENID personopenid = new PERSONOPENID();
                personopenid.setStatus(0);
                personopenid.setBj_login(1);
                personopenid.setOpenid(openid);
                personopenid.setPersonId(personinfo.getPersonId());
                List<PERSONOPENID> old = personopenidMapper.selectByopenidall(openid);
                if (old != null) {
                    for(PERSONOPENID item : old)
                    {
                        if(item.getBj_login()==1) {
                            item.setBj_login(0);
                            personopenidMapper.updateByPrimaryKeySelective(item);
                        }
                    }
                }
                PERSONOPENID oldkeydata= personopenidMapper.selectByPrimaryKey(personopenid);
                if(oldkeydata==null) {
                    int num = personopenidMapper.insertSelective(personopenid);
                    if (num == 0)//插入失败
                    {
                        logger.info("插入PERSONOPENID失败");
                        return ResultUtil.error(ResultEnum.SHHYOPENIDCW.getCode(), ResultEnum.SHHYOPENIDCW.getMsg());
                    }
                }
                else
                {
                    int num = personopenidMapper.updateByPrimaryKeySelective(personopenid);
                    if (num == 0)//插入失败
                    {
                        logger.info("更新PERSONOPENID失败");
                        return ResultUtil.error(ResultEnum.SHHYOPENIDCW.getCode(), ResultEnum.SHHYOPENIDCW.getMsg());
                    }
                }

                return ResultUtil.success(personinfo.getDeptid());
            } else {
                logger.info("密码错误");
                return ResultUtil.error(ResultEnum.PWDCW.getCode(), ResultEnum.PWDCW.getMsg());
            }

        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }

    }

    /*
    商户消券
    openid,couponcode,money
     */
    @PostMapping(value = "/usecoupon", produces = "application/json")
    public ResultData usecoupon(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
             data= AESCrypt.decryptAES( data);
            if (StringUtil.strisnull(data)) {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(), ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            String openid = map.get("openid");
            String couponcode = map.get("couponcode");
            String money = map.get("money");

            PERSONOPENIDKey key=new PERSONOPENIDKey();
            key.setOpenid(openid);
           List<PERSONOPENID> list= personopenidMapper.selectByopenid(key);
           if(list.size()>0)
           {
             PERSONINFO personinfo=  personinfoMapper.selectByPrimaryKey(list.get(0).getPersonId());
           MEM_COUPON_ACCOUNT memCouponAccount= memCouponAccountMapper.selectBycode(couponcode);
           if(memCouponAccount==null)
           {
               return ResultUtil.error(ResultEnum.COUPONNOTEXIST.getCode(), ResultEnum.COUPONNOTEXIST.getMsg());
           }
               MEM_COUPON_DEF def= memCouponDefMapper.selectByPrimaryKey(memCouponAccount.getYhqid());
           if(def.getShopid()!=personinfo.getDeptid().intValue())
           {
               return ResultUtil.error(ResultEnum.COUPONNOCANUSE.getCode(), ResultEnum.COUPONNOCANUSE.getMsg());
           }
               BigDecimal je=new BigDecimal(money);
           je=memCouponAccount.getJe().subtract(je);
          if( je.compareTo(new BigDecimal(0))<0)
          {
              return ResultUtil.error(ResultEnum.COUPONNOMONEY.getCode(), ResultEnum.COUPONNOMONEY.getMsg());
          }
          else if ( je.compareTo(new BigDecimal(0))==0)//用完了
          {
              memCouponAccount.setStatus(7);
          }
          else {
            if(def.getMultiFlag()==1)
              memCouponAccount.setStatus(2);
            else
                memCouponAccount.setStatus(8);
          }
               memCouponAccount.setJe(je);
        int num= memCouponAccountMapper.updateByPrimaryKeySelective(memCouponAccount);
        if(num>0)
        {
            //记录三级帐

            return ResultUtil.success("消券成功");
        }
        else
            return ResultUtil.error(ResultEnum.USEDCOUPONFAIL.getCode(), ResultEnum.USEDCOUPONFAIL.getMsg());
           }
           else
           {
               return ResultUtil.error(ResultEnum.SHHYOPENIDCW.getCode(), ResultEnum.SHHYOPENIDCW.getMsg());
           }
        }
        else
        {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }
}
