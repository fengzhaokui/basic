package com.fengzkframework.basic.aes;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.enums.ResultEnum;
import com.fengzkframework.basic.utils.JsonUtils;
import com.fengzkframework.basic.utils.ResultUtil;
import com.fengzkframework.basic.utils.StringUtil;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 微信加密解密；
 */
@Service
public class AESCrypt {
	private static final String AESTYPE ="AES/ECB/PKCS5Padding";
	private static Logger logger= LoggerFactory.getLogger(AESCrypt.class);
	private static   String key;//="FHhPatHL4DF8link";
	@Value("${aeskey}")
	public  void setKey(String key)
	{
		this.key=key;
	}

	public static String encryptAES(String encryptData) {
		return encryptAES(encryptData,key);
	}
	/**
	 * 加密
	 * @param plainText
	 * @param keyStr
	 * @return
	 */
    public static String encryptAES(String plainText, String keyStr) { 
        byte[] encrypt = null; 
        try{ 
            Key key = generateKey(keyStr); 
            //“算法/模式/填充”
            Cipher cipher = Cipher.getInstance(AESTYPE); 
            cipher.init(Cipher.ENCRYPT_MODE, key); 
           // encrypt = cipher.doFinal(plainText.getBytes());
			encrypt = cipher.doFinal(plainText.getBytes("utf-8"));

		}catch(Exception e){
            e.printStackTrace(); 
        }
        return new String(Base64.encodeBase64(encrypt)); 
    }
	public static String decryptAES(String encryptData) {

		StringBuilder sb=new StringBuilder("[解密前：]" + encryptData);
		String str=decryptAES(encryptData,key);
		sb.append("[解密后：]" + str);
		logger.info(sb.toString());
		return str;
	}

	public static ResultData decryptAESResult(String encryptData) {
		ResultData data=new ResultData();
		data.setRetcode(ResultEnum.SUCCESS.getCode());
		StringBuilder sb=new StringBuilder("[解密前：]" + encryptData);
		String str=decryptAES(encryptData,key);
		sb.append("[解密后：]" + str);
		logger.info(sb.toString());
		if(StringUtil.strisnull(str))
		{
			logger.info("解密失败");
			return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
		}
		return data;
	}
	/**
	 * 解密
	 * @param encryptData
	 * @param keyStr
	 * @return
	 */
	public static String decryptAES(String encryptData,String keyStr) {
        byte[] decrypt = null;
		String str="";
        try{ 
            Key key = generateKey(keyStr); 
            Cipher cipher = Cipher.getInstance(AESTYPE); 
            cipher.init(Cipher.DECRYPT_MODE, key); 
            decrypt = cipher.doFinal(Base64.decodeBase64(encryptData));
            str=new String(decrypt).trim();
        }catch(Exception e){ 
            e.printStackTrace(); 
        }

        //str= UncodeUtil.getUTF8XMLString(str);
        return str;
    } 
 
    private static Key generateKey(String key)throws Exception{ 
        try{            
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES"); 
            return keySpec; 
        }catch(Exception e){ 
            e.printStackTrace(); 
            throw e; 
        } 
 
    } 
    
	private static String encryptAESData(String appsecret,
			Map<String, String> dataMap, boolean encode) {
		String data = "";
		if(encode){
			try {
				data = URLEncoder.encode(encryptAES(JsonUtils.object2Json(dataMap),appsecret) , "UTF8" );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				data = encryptAES(JsonUtils.object2Json(dataMap),appsecret);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}
 
    public static void main(String[] args) { 
		
		String openId = "123";
		String appsecret = "FHhPatHL4DF8xPjV";
		String timestamp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String method = "";
		
		StringBuffer bastUrl = new StringBuffer();
		bastUrl.append("http://192.168.28.248:8080/xdInterface/router/rest?");
		//url.append("http://192.168.181.81:8080/xdInterface/router/rest?");
		bastUrl.append("version=2.4").append("&appid=123");
		bastUrl.append("&timestamp=").append(timestamp);
		
		
		
		Map<String,String> dataMap = new HashMap<String,String>();
		
		/*
		 * 发送注册短信
		 */
		StringBuffer smsApplyUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("contractPhone", "15652077080");
		dataMap.put("smsType", "1");
		smsApplyUrl.append(bastUrl.toString());
		smsApplyUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		smsApplyUrl.append("&method=stxfInterface.sms");
		System.out.println("发送注册短信URL:");
		System.out.println(smsApplyUrl);
		
		/*
		 * 注册
		 */
		dataMap = new HashMap<String,String>();
		StringBuffer applyUrl = new StringBuffer();
		dataMap.put("openId", openId);
		dataMap.put("contractName", "赵梓瑛");
		dataMap.put("idCard", "110103197712271215");
		dataMap.put("contractPhone", "15652077080");
		dataMap.put("transPassword", "123456");
		dataMap.put("smsCode", "984135");
		dataMap.put("introducerPhone", "15652077080");
		applyUrl.append(bastUrl.toString());
		applyUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		applyUrl.append("&method=stxfInterface.apply");
		System.out.println("注册URL:");
		System.out.println(applyUrl);
		
		/*
		 * 发送修改密码短信
		 */
		StringBuffer smsTransPwdUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("contractPhone", "15652077080");
		dataMap.put("smsType", "2");
		smsTransPwdUrl.append(bastUrl.toString());
		smsTransPwdUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		smsTransPwdUrl.append("&method=stxfInterface.sms");
		System.out.println("发送修改密码短信URL:");
		System.out.println(smsTransPwdUrl);
		
		/*
		 * 修改密码
		 */
		StringBuffer transPwdUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("contractPhone", "15652077080");
		dataMap.put("smsCode", "106139");
		dataMap.put("transPassword", "111111");
		transPwdUrl.append(bastUrl.toString());
		transPwdUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		transPwdUrl.append("&method=stxfInterface.transpassword.update");
		System.out.println("修改密码URL:");
		System.out.println(transPwdUrl);
		
		/*
		 * 芝麻信用
		 */
		StringBuffer zmUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		zmUrl.append(bastUrl.toString());
		zmUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		zmUrl.append("&method=stxfInterface.zmCredit.check");
		System.out.println("芝麻信用URL:");
		System.out.println(zmUrl);
		
		/*
		 * 运营商
		 */
		StringBuffer operUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("pwd", "123459");
		operUrl.append(bastUrl.toString());
		operUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		operUrl.append("&method=stxfInterface.operator.check");
		System.out.println("运营商URL:");
		System.out.println(operUrl);
		
		/*
		 * jd
		 */
		StringBuffer jdUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("account", "rockyha");
		dataMap.put("pwd", "1q2w3e4r");
		jdUrl.append(bastUrl.toString());
		jdUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		jdUrl.append("&method=stxfInterface.jd.check");
		System.out.println("jd URL:");
		System.out.println(jdUrl);
		
		/*
		 * 信用认证验证码
		 */
		StringBuffer verifyUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("verifyType", "1");//0:芝麻信用 1 京东 2 运营商 3 身份证照片
		dataMap.put("smsCode", "456496");
		dataMap.put("captcha", "");
		verifyUrl.append(bastUrl.toString());
		verifyUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		verifyUrl.append("&method=stxfInterface.verify.check");
		System.out.println("信用认证验证码URL:");
		System.out.println(verifyUrl);
		
		/*
		 * 信用认证验证码刷新
		 */
		StringBuffer verifyFetchUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("verifyType", "1");//0:芝麻信用 1 京东 2 运营商 3 身份证照片
		dataMap.put("fetchVerifyType", "sms");//captcha sms
		verifyFetchUrl.append(bastUrl.toString());
		verifyFetchUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		verifyFetchUrl.append("&method=stxfInterface.verify.fetch");
		System.out.println("信用认证验证码刷新URL:");
		System.out.println(verifyFetchUrl);
		
		/*
		 * 信用认证身份认证
		 */
		StringBuffer idCardUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		idCardUrl.append("http://192.168.28.248:8080/xdInterface/router/authIDcard/check?");
		idCardUrl.append("version=2.1").append("&appid=123");
		idCardUrl.append("&timestamp=").append(timestamp);
		idCardUrl.append("&data=").append(encryptAESData(appsecret, dataMap, false));
		System.out.println("信用认证身份认证URL:");
		System.out.println(idCardUrl);
		
		/*
		 * 认证状态查询
		 */
		StringBuffer verifyStatusUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		verifyStatusUrl.append(bastUrl.toString());
		verifyStatusUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		verifyStatusUrl.append("&method=stxfInterface.auth4.check");
		System.out.println("认证状态查询URL:");
		System.out.println(verifyStatusUrl);
		
		
		/*
		 * 认证结果回调
		 */
		StringBuffer fkResultCallbackUrl = new StringBuffer();
		fkResultCallbackUrl.append("http://192.168.28.248:8080/xdInterface/router/callback/fkResult?");
		fkResultCallbackUrl.append("data={\"uid\":\"110103197712271215\",\"result\":true}");
		System.out.println("认证结果回调URL:");
		System.out.println(fkResultCallbackUrl);
		
		/*
		 * 联系人结果回调
		 */
		StringBuffer contactsCallbackUrl = new StringBuffer();
		contactsCallbackUrl.append("http://192.168.28.248:8080/xdInterface/router/callback/contacts?");
		contactsCallbackUrl.append("data={\"uid\":\"110103197712271215\",\"contacts\":[\"18812341234\",\"18812341235\",\"18812341236\",\"18812341237\",\"18812341238\"]}");
		System.out.println("联系人结果回调URL:");
		System.out.println(contactsCallbackUrl);
		
		/*
		 * 联系人校验
		 */
		StringBuffer contactsCheckUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("emergencyName", "test");
		dataMap.put("emergencyPhone", "18812344567");
		contactsCheckUrl.append(bastUrl.toString());
		contactsCheckUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		contactsCheckUrl.append("&method=stxfInterface.relationship");
		System.out.println("联系人校验URL:");
		System.out.println(contactsCheckUrl);
		
		/*
		 * 个人补全资料
		 */
		StringBuffer supplementUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("cityName", "北京市");
		dataMap.put("region", "海淀区");
		dataMap.put("address", "中关村数码大厦A座25层2515");
		dataMap.put("relationship_A", "R004");
		dataMap.put("emergencyName_A", "张三");
		dataMap.put("emergencyPhone_A", "18812344567");
		dataMap.put("relationship_B", "R003");
		dataMap.put("emergencyName_B", "李四");
		dataMap.put("emergencyPhone_B", "18812344568");
		supplementUrl.append(bastUrl.toString());
		supplementUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		supplementUrl.append("&method=stxfInterface.supplement");
		System.out.println("个人补全资料URL:");
		System.out.println(supplementUrl);
		
		/*
		 * 发送绑定银行卡短信
		 */
		StringBuffer smsBindBankUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("contractPhone", "15652077080");
		dataMap.put("smsType", "3");
		smsBindBankUrl.append(bastUrl.toString());
		smsBindBankUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		smsBindBankUrl.append("&method=stxfInterface.sms");
		System.out.println("发送绑定银行卡短信URL:");
		System.out.println(smsBindBankUrl);
		
		/*
		 * 绑定银行卡
		 */
		StringBuffer bindBankUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("bankCard", "6226660204192724");
		dataMap.put("smsCode", "794156");
		bindBankUrl.append(bastUrl.toString());
		bindBankUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		bindBankUrl.append("&method=stxfInterface.repay.card.setup");
		System.out.println("绑定银行卡URL:");
		System.out.println(bindBankUrl);
		
		/*
		 * 试算
		 */
		StringBuffer calculateUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("amount", "1500.00");
		dataMap.put("loanTerm", "30");
		calculateUrl.append(bastUrl.toString());
		calculateUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		calculateUrl.append("&method=stxfInterface.calculate");
		System.out.println("试算URL:");
		System.out.println(calculateUrl);
		
		/*
		 * 账户查询
		 */
		StringBuffer accountQueryUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		accountQueryUrl.append(bastUrl.toString());
		accountQueryUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		accountQueryUrl.append("&method=stxfInterface.apply.status");
		System.out.println("账户查询URL:");
		System.out.println(accountQueryUrl);
		
		/*
		 * 账户登录接口
		 */
		StringBuffer loginUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("contractPhone", "15652077080");
		dataMap.put("transPassword", "111111");
		loginUrl.append(bastUrl.toString());
		loginUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		loginUrl.append("&method=stxfInterface.Login");
		System.out.println("登录URL:");
		System.out.println(loginUrl);
		
		/*
		 * 账单查询接口
		 */
		StringBuffer billUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("serchDate", "201704");
		billUrl.append(bastUrl.toString());
		billUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		billUrl.append("&method=stxfInterface.bill");
		System.out.println("账单查询URL:");
		System.out.println(billUrl);
		
		/*
		 * 发送提现短信
		 */
		StringBuffer smsCashUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("contractPhone", "15652077080");
		dataMap.put("smsType", "4");
		smsCashUrl.append(bastUrl.toString());
		smsCashUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		smsCashUrl.append("&method=stxfInterface.sms");
		System.out.println("发送提现短信URL:");
		System.out.println(smsCashUrl);
		
		/*
		 * 提现
		 */
		StringBuffer cashUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("amount", "1500.00");
		dataMap.put("loanTerm", "15");
		dataMap.put("smsCode", "654237");
		cashUrl.append(bastUrl.toString());
		cashUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		cashUrl.append("&method=stxfInterface.cash");
		System.out.println("提现URL:");
		System.out.println(cashUrl);
		
		/*
		 * 身份证照片认证
		 */
		StringBuffer idCardCheckUrl = new StringBuffer();
		dataMap = new HashMap<String,String>();
		dataMap.put("openId", openId);
		dataMap.put("photo_A", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492607118&di=be8213fc73a5686a7f3b7f91b7b8d876&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.sznews.com%2Fent%2Fimages%2Fattachement%2Fjpg%2Fsite3%2F20141011%2F4437e629783815a2bce253.jpg");
		dataMap.put("photo_C", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492012334337&di=5feffe8f7943159c6ed85959663a6d7a&imgtype=0&src=http%3A%2F%2Fimgqn.koudaitong.com%2Fupload_files%2F2014%2F09%2F29%2FFp8RLkHrpEDFiXrMSSsDQJIf5OV_.jpg%2521730x0.jpg");
		idCardCheckUrl.append(bastUrl.toString());
		idCardCheckUrl.append("&data=").append(encryptAESData(appsecret, dataMap, true));
		idCardCheckUrl.append("&method=stxfInterface.authIDcard.check");
		System.out.println("身份证照片认证URL:");
		System.out.println(idCardCheckUrl);
         

         
        String encText = encryptAES("{\"openId\":\"123\",\"contractPhone\":\"15652077080\",\"transPassword\":\"12345678\"}", "FHhPatHL4DF8xPjV");
        String decString = decryptAES("EvwDRlkBIuOoS3GeBpMhn+4E+of0hpH/Nvzp1qgO8Rfne+gL0Davxki1q4m6xIVXjl5yK0qXt1ZkQFpK+U2dnYtt9A3jLv0lLyj1QyQRgk8=", "FHhPatHL4DF8xPjV"); 
         
        System.out.println(encText); 
        System.out.println(decString); 
 
    } 
}
