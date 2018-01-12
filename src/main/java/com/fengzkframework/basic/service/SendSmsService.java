package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.VERIFYCODEMapper;
import com.fengzkframework.basic.dao.vo.VERIFYCODE;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.enums.ResultEnum;
import com.fengzkframework.basic.utils.SmsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class SendSmsService {
    @Autowired
    private VERIFYCODEMapper mapper;
    @Autowired
    private SmsUtils sms;
    Logger logger = LoggerFactory.getLogger(SendSmsService.class);

   // @CachePut(value="accountCache",key="#account.getName()")// 更新accountCache 缓存
    public void InSms(String phone,String con)
    {
        VERIFYCODE sms=new VERIFYCODE();
        sms.setSktno("001");
        sms.setSkyid(1l);
        sms.setPhone(phone);
        sms.setCode(con);
        sms.setScsj(new Date());

        mapper.insert(sms);
    }

    public ResultData Sendwxsms(String phone)
    {
        ResultData data=new ResultData();
        if(sms!=null && phone!=null) {
            String content = "您好，您佳惠电子会员登录的验证码是:";
            String con="";
            Random rd=new Random();

            for (int i = 0; i < 6; i++)
            {
                con += rd.nextInt(10);
            }
            InSms(phone,con);
            content+=con;
            logger.info( sms.send(phone,content));
            data.setRetcode(ResultEnum.SUCCESS.getCode());
            data.setRetmsg(ResultEnum.SUCCESS.getMsg());
        }
        else
        {
            data.setRetcode(ResultEnum.SENDSMSFAIL.getCode());
            data.setRetmsg(ResultEnum.SENDSMSFAIL.getMsg());
            logger.info(phone+":发送短信失败");
        }
        return  data;
    }
}
