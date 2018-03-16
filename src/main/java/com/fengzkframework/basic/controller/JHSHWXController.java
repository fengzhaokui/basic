package com.fengzkframework.basic.controller;

import com.fengzkframework.basic.aes.AESCrypt;
import com.fengzkframework.basic.dao.*;
import com.fengzkframework.basic.dao.vo.*;
import com.fengzkframework.basic.domain.RequestData;
import com.fengzkframework.basic.domain.RequestLMXQJLData;
import com.fengzkframework.basic.domain.RequestLMXQJLRateData;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.enums.ResultEnum;
import com.fengzkframework.basic.utils.CommonUtil;
import com.fengzkframework.basic.utils.MD5Utils;
import com.fengzkframework.basic.utils.ResultUtil;
import com.fengzkframework.basic.utils.StringUtil;
import com.google.gson.Gson;
import org.dom4j.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

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
    @Autowired
    LMXQJLMapper lmxqjlMapper;

    /*
    根据openid判断当前状态；
     */
    @PostMapping(value = "/shuserstatus", produces = "application/json")
    public ResultData shuserstatus(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if (StringUtil.strisnull(data)) {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(), ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            String openid = map.get("openid");
            List<PERSONOPENID> old = personopenidMapper.selectByopenidall(openid);
            if (old != null) {
                for (PERSONOPENID item : old) {
                    if (item.getBj_login() == 1) {
                        return ResultUtil.success("");
                    }
                }
            }
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
        return ResultUtil.error(ResultEnum.SHUSERSTATUSFAIL.getCode(), ResultEnum.SHUSERSTATUSFAIL.getMsg());
    }


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
            String personcode = map.get("personcode");
            PERSONINFO personinfo = personinfoMapper.selectBycode(personcode);
            if (personinfo == null) {
                logger.info("用户不存在");
                return ResultUtil.error(ResultEnum.NOHY.getCode(), ResultEnum.NOHY.getMsg());
            }
            PERSONOPENID personopenid = new PERSONOPENID();
            personopenid.setOpenid(openid);
            personopenid.setPersonId(personinfo.getPersonId());
            PERSONOPENID item = personopenidMapper.selectByPrimaryKey(personopenid);
            if (item != null && item.getBj_login() == 1) {
                item.setUsername(username);
                personopenidMapper.updateByPrimaryKeySelective(item);
                return ResultUtil.success("");
            }
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
        return ResultUtil.error(ResultEnum.SETSHUSERNAMEFAIL.getCode(), ResultEnum.SETSHUSERNAMEFAIL.getMsg());
    }


    /*
      商户登录
      personcode,pwd,openid
       */
    @Transactional
    @PostMapping(value = "/shhylogin", produces = "application/json")
    public ResultData shhylogin(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
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
                    for (PERSONOPENID item : old) {
                        if (item.getBj_login() == 1) {
                            item.setBj_login(0);
                            personopenidMapper.updateByPrimaryKeySelective(item);
                        }
                    }
                }
                // int a=1/0;
                PERSONOPENID oldkeydata = personopenidMapper.selectByPrimaryKey(personopenid);
                String username = null;
                if (oldkeydata == null) {
                    int num = personopenidMapper.insertSelective(personopenid);
                    if (num == 0)//插入失败
                    {
                        logger.info("插入PERSONOPENID失败");
                        return ResultUtil.error(ResultEnum.SHHYOPENIDCW.getCode(), ResultEnum.SHHYOPENIDCW.getMsg());
                    }
                } else {
                    personopenid.setUsername(oldkeydata.getUsername());
                    int num = personopenidMapper.updateByPrimaryKeySelective(personopenid);
                    username = oldkeydata.getUsername();
                    if (num == 0)//插入失败
                    {
                        logger.info("更新PERSONOPENID失败");
                        return ResultUtil.error(ResultEnum.SHHYOPENIDCW.getCode(), ResultEnum.SHHYOPENIDCW.getMsg());
                    }
                }
                ResultData result = new ResultData();

                result.setRetcode(ResultEnum.SUCCESS.getCode());
                result.setRetmsg(ResultEnum.SUCCESS.getMsg());
                result.setData(username);

                return result;
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
    @Transactional
    @PostMapping(value = "/usecoupon", produces = "application/json")
    public ResultData usecoupon(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if (StringUtil.strisnull(data)) {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(), ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            String openid = map.get("openid");
            String couponcode = map.get("couponcode");
            String money = map.get("money");

            PERSONOPENIDKey key = new PERSONOPENIDKey();
            key.setOpenid(openid);
            List<PERSONOPENID> list = personopenidMapper.selectByopenid(key);
            if (list.size() > 0) {
                PERSONINFO personinfo = personinfoMapper.selectByPrimaryKey(list.get(0).getPersonId());
                MEM_COUPON_ACCOUNT memCouponAccount = memCouponAccountMapper.selectBycode(couponcode);
                if (memCouponAccount == null) {
                    return ResultUtil.error(ResultEnum.COUPONNOTEXIST.getCode(), ResultEnum.COUPONNOTEXIST.getMsg());
                }
                MEM_COUPON_DEF def = memCouponDefMapper.selectByPrimaryKey(memCouponAccount.getYhqid());
                if (def.getShopid() != personinfo.getDeptid().intValue()) {
                    return ResultUtil.error(ResultEnum.COUPONNOCANUSE.getCode(), ResultEnum.COUPONNOCANUSE.getMsg());
                }
                BigDecimal je = new BigDecimal(money);
                je = memCouponAccount.getJe().subtract(je);
                if (je.compareTo(new BigDecimal(0)) < 0) {
                    return ResultUtil.error(ResultEnum.COUPONNOMONEY.getCode(), ResultEnum.COUPONNOMONEY.getMsg());
                } else if (je.compareTo(new BigDecimal(0)) == 0)//用完了
                {
                    memCouponAccount.setStatus(7);
                } else {
                    if (def.getMultiFlag() == 1)
                        memCouponAccount.setStatus(2);
                    else
                        memCouponAccount.setStatus(8);
                }
                memCouponAccount.setJe(je);
                int num = memCouponAccountMapper.updateByPrimaryKeySelective(memCouponAccount);
                if (num > 0) {
                    //记录三级帐
                    LMXQJL lmxqjl = new LMXQJL();
                    lmxqjl.setAmount(new BigDecimal(money));
                    lmxqjl.setCreatetime(new Date());
                    lmxqjl.setHyid(memCouponAccount.getHyid());
                    lmxqjl.setOpid(openid);
                    lmxqjl.setQh(couponcode);
                    lmxqjl.setShopid(Long.valueOf(def.getShopid()));
                    lmxqjl.setUsetype(def.getUsetype());

                    lmxqjlMapper.insert(lmxqjl);

                    return ResultUtil.success("消券成功");
                } else
                    return ResultUtil.error(ResultEnum.USEDCOUPONFAIL.getCode(), ResultEnum.USEDCOUPONFAIL.getMsg());
            } else {
                return ResultUtil.error(ResultEnum.SHHYOPENIDCW.getCode(), ResultEnum.SHHYOPENIDCW.getMsg());
            }
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    /*
   商户消券列表
   openid,couponcode,money
    */
    @PostMapping(value = "/usecouponlist", produces = "application/json")
    public ResultData usecouponlist(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if (StringUtil.strisnull(data)) {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(), ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            String openid = map.get("openid");
            //String shopid = map.get("shopid");
            Gson gson = new Gson();
            //String money = map.get("money");
//            if(!StringUtil.strisnull(openid))
//            {
//                //查询openid的消券记录
//              List<LMXQJL> list= lmxqjlMapper.selectByopenid(openid);
//              return ResultUtil.success(list);
//            }
            //else if(!StringUtil.strisnull(shopid))
            PERSONOPENIDKey key = new PERSONOPENIDKey();
            key.setOpenid(openid);
            List<PERSONOPENID> peropenid = personopenidMapper.selectByopenid(key);
            PERSONINFO personinfo = personinfoMapper.selectByPrimaryKey(peropenid.get(0).getPersonId());
            String shopid = personinfo.getDeptid().toString();
            {
                List<LMXQJL> list = lmxqjlMapper.selectByshopid(Long.valueOf(shopid));
                List<RequestLMXQJLData> olist = new ArrayList<RequestLMXQJLData>();
                RequestLMXQJLData odata = null;
                for (LMXQJL item : list) {
                    String ss = gson.toJson(item);
                    odata = gson.fromJson(ss, RequestLMXQJLData.class);
                    odata.setRq(CommonUtil.getDateTimeStr(item.getCreatetime()));
                    MEM_COUPON_ACCOUNT mca = memCouponAccountMapper.selectBycode(item.getQh());
                    MEM_COUPON_DEF def = memCouponDefMapper.selectByPrimaryKey(mca.getYhqid());
                    odata.setYhqname(def.getYhqmc());
                    olist.add(odata);

                }
                return ResultUtil.success(olist);
            }
        }
        return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
    }

    @PostMapping(value = "/usecouponrate", produces = "application/json")
    public ResultData usecouponrate(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if (StringUtil.strisnull(data)) {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(), ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            String openid = map.get("openid");
            PERSONOPENIDKey key = new PERSONOPENIDKey();
            key.setOpenid(openid);
            List<PERSONOPENID> peropenid = personopenidMapper.selectByopenid(key);
            PERSONINFO personinfo = personinfoMapper.selectByPrimaryKey(peropenid.get(0).getPersonId());
            long shopid = personinfo.getDeptid();
            List<MEM_COUPON_DEF> list=  memCouponDefMapper.selectByshopid(shopid);
            List<RequestLMXQJLRateData> olist=new ArrayList<RequestLMXQJLRateData>();
            RequestLMXQJLRateData odata=null;
            for(MEM_COUPON_DEF def:list)
            {
                odata=new RequestLMXQJLRateData();
              Double fqmoney= memCouponAccountMapper.selectByyhqid(def.getYhqid());
                Double yqmoney=lmxqjlMapper.selectByyhqid(def.getYhqid());
              double rate=yqmoney/fqmoney;
                BigDecimal b = new BigDecimal(rate);
               String srate = b.setScale(4, BigDecimal.ROUND_HALF_UP).toString();
               odata.setFqmoney(fqmoney.toString());
               odata.setYhqname(def.getYhqmc());
                odata.setUsetype(def.getUsetype().toString());
               odata.setYqmoney(yqmoney.toString());
               odata.setRate(srate);
               olist.add(odata);
               // DecimalFormat df = new DecimalFormat("#.0000");
                //System.out.println("用券金额："+yqmoney+";用券率"+rate);
            }
            return  ResultUtil.success(olist);

        }
        return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
    }

//    private String Getshopidfromopenid(String openid)
//    {
//      //PERSONOPENID po= personopenidMapper.selectByopenidall(openid);
//    }
}
