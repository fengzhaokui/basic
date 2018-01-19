package com.fengzkframework.basic.service;

import com.fengzkframework.basic.aes.AESCrypt;
import com.fengzkframework.basic.domain.RequestCardData;
import com.fengzkframework.basic.domain.ResultData;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WXServiceImpl {
    @Autowired
    HttpService hs;
    Gson gson=new Gson();
    Logger logger= LoggerFactory.getLogger(WXServiceImpl.class);
    /**
     * 验证码支付结果检查
     * @param
     * @return
     */
    public ResultData wetpayconfirm (String openid,String qrcode)
    {
        String url="wetpaytradequery.do";
        Map<String,String> map=new HashMap<String,String>();
        map.put("openId",openid);
        map.put("qrcode",qrcode);
        String json=gson.toJson(map);
        logger.info("验证码支付结果检查:"+json);
        json= AESCrypt.encryptAES(json);
        ResultData rd= hs.GetMSService(url,json);
        if(rd.getRetcode().equals("0"))
        {
            rd.setRetcode("00");
        }
        if(rd.getData()!=null) {
            String str = AESCrypt.decryptAES(rd.getData().toString());
            msrequestdata data = gson.fromJson(str, msrequestdata.class);
            rd.setData(data);
        }
        return  rd;
    }
    public  class msrequestdata
    {
        private String money;
        private String realmoney;
        private String status;
        private String orderid;

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getRealmoney() {
            return realmoney;
        }

        public void setRealmoney(String realmoney) {
            this.realmoney = realmoney;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
