package com.fengzkframework.basic.service;

import com.fengzkframework.basic.aes.AESCrypt;
import com.fengzkframework.basic.dao.vo.MEM_BASEINFO;
import com.fengzkframework.basic.domain.RequestCardData;
import com.fengzkframework.basic.domain.RequestData;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.enums.ResultEnum;
import com.fengzkframework.basic.utils.ResultUtil;
import com.fengzkframework.basic.utils.StringUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayService {

    Logger logger= LoggerFactory.getLogger(PayService.class);
    @Autowired
    HttpService hs;
    @Autowired
     MemBaseInfoServiceImpl ms;

    @Value("${msshopcode}")
    private String msshopcode;

    @Value("${ybshopcode}")
    private String ybshopcode;

    Gson gson=new Gson();
    /**
     * 储值卡充值；
     */
    public ResultData cardpay(RequestCardData indata)
    {
        String url="wetchatpay.do";
        MEM_BASEINFO memBaseinfo=  ms.selectByopenid(indata.getOpenid());
        if(memBaseinfo==null)
        {
            return ResultUtil.error(ResultEnum.MoreHY.getCode(),ResultEnum.MoreHY.getMsg());
        }
        Map<String,String> map=new HashMap<String,String>();
        map.put("openId",indata.getOpenid());
        map.put("hyId",memBaseinfo.getHyid().toString());
        if(indata.getPaytype().equals("1"))
        {
            map.put("partnerId",msshopcode);//账户号
        }
        else if (indata.getPaytype().equals("2"))
        {
            map.put("partnerId",ybshopcode);//账户号
        }
        map.put("partnerName","佳惠");
        map.put("orderId",indata.getOrderid());
        map.put("goodsName","购物卡");
        map.put("amount",indata.getCzje().toString());
        map.put("type",indata.getPaytype());//1马上 2易宝
        map.put("iszk","1");//0 不折扣 1 折扣
        map.put("transPassword",indata.getPassword());
        String json=gson.toJson(map);
        logger.info("支付boby:"+json);
        json=AESCrypt.encryptAES(json);
      return hs.GetMSService(url,json);
    }
/*
交易撤回；
 */
    public ResultData cardfund(RequestCardData indata)
    {
        String url="wetpayrefund.do";
        MEM_BASEINFO memBaseinfo=  ms.selectByopenid(indata.getOpenid());
        if(memBaseinfo==null)
        {
            return ResultUtil.error(ResultEnum.MoreHY.getCode(),ResultEnum.MoreHY.getMsg());
        }
        Map<String,String> map=new HashMap<String,String>();
        map.put("openId",indata.getOpenid());
        map.put("orderId",new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ StringUtil.randomString(6));
        map.put("orgorderId",indata.getOrderid());
        map.put("type",indata.getPaytype());//1马上 2易宝
        String json=gson.toJson(map);
        logger.info("退款:"+json);
        json=AESCrypt.encryptAES(json);
        return hs.GetMSService(url,json);
    }
}
