package com.fengzkframework.basic.controller;

import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.enums.ResultEnum;
import com.fengzkframework.basic.service.SendSmsService;
import com.fengzkframework.basic.utils.SmsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
//@RequestMapping
public class SendSMSController {
    Logger logger = LoggerFactory.getLogger(SendSMSController.class);
    @Autowired
    private SmsUtils sms;
    @Autowired
    private SendSmsService smss;
    @GetMapping("/sendsms")
    public  void Sendsms(@RequestParam(value = "phone", required = false) String phone)
    {
        if(sms!=null && phone!=null) {
             String content = "您好，您佳惠电子会员登录的验证码是:";
             String con="";
            Random rd=new Random();

            for (int i = 0; i < 6; i++)
            {
                con += rd.nextInt(10);
            }
            smss.InSms(phone,con);
            content+=con;
            logger.info( sms.send(phone,content));
        }
        else
        {
            logger.info(phone+":发送短信失败");
        }
    }


}
