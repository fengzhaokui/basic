package com.fengzkframework.basic.utils;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;
@Service
public class ALDYSmsUtils {

    private  org.slf4j.Logger logger= LoggerFactory.getLogger(ALDYSmsUtils.class);
  //  Logger logger= LoggerFactory.getLogger(ALDYSmsUtils.class);
  @Value("${dyaccessKeyId}")
  private String accessKeyId;
    @Value("${dyaccessKeySecret}")
    private String accessKeySecret;
    @Value("${dyurl}")
    private String url;
    @Value("${dySmsTemplateCode}")
    private String dySmsTemplateCode;
    // final String accessKeyId = "23765252";
//     final String accessKeySecret = "0463628d1cf98086c51ba8d3d8e49eca";
//     String url="http://gw.api.taobao.com/router/rest";
    public   void Sendsms(String phone,String code)  {
        try {
            TaobaoClient client = new DefaultTaobaoClient(url, accessKeyId, accessKeySecret);
            AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
            req.setExtend("");
            req.setSmsType("normal");
            req.setSmsFreeSignName("佳惠");
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("{NUMBERID:'");
            stringBuilder.append(code);
            stringBuilder.append("'}");
            req.setSmsParamString(stringBuilder.toString());
            req.setRecNum(phone);
            req.setSmsTemplateCode(dySmsTemplateCode);
            AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
            logger.info(rsp.getBody());
            //System.out.println(rsp.getBody());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            //System.out.println("验证码错误;"+ex.getMessage());
        }
    }


        public static void main(String[] args) throws  InterruptedException {

          //  Sendsms("15010429416","123456");
//            //发短信
//            SendSmsResponse response = sendSms();
//            System.out.println("短信接口返回的数据----------------");
//            System.out.println("Code=" + response.getCode());
//            System.out.println("Message=" + response.getMessage());
//            System.out.println("RequestId=" + response.getRequestId());
//            System.out.println("BizId=" + response.getBizId());

           // Thread.sleep(3000L);

        }

}
