package com.fengzkframework.basic.controller;

import com.fengzkframework.basic.aes.AESCrypt;
import com.fengzkframework.basic.dao.MALLDEFMapper;
import com.fengzkframework.basic.dao.MEM_BASEINFOMapper;
import com.fengzkframework.basic.dao.vo.MALLDEF;
import com.fengzkframework.basic.domain.RequestCardData;
import com.fengzkframework.basic.domain.RequestData;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.enums.ResultEnum;
import com.fengzkframework.basic.service.*;
import com.fengzkframework.basic.utils.ResultUtil;
import com.fengzkframework.basic.utils.StringUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 佳惠微信项目
 */
@RestController
@RequestMapping("/weixin")
public class JHWXController {

    @Autowired
    private HttpService hs;
    @Autowired
    private SendSmsService smss;
    @Autowired
    CityServiceImpl cs;
    @Autowired
    MemBaseInfoServiceImpl mems;
   @Autowired
    MallDefServiceImpl mallDefService;
    Logger logger = LoggerFactory.getLogger(JHWXController.class);
    Gson gson = new Gson();
    /**
     * 发送手机验证码
     * @param phone
     */
    @GetMapping("/sendsms")
    public  ResultData Sendsms(@RequestParam(value = "data", required = false) String phone)
    {
        if(phone!=null) {
            phone = AESCrypt.decryptAES(phone);
            if(StringUtil.strisnull(phone))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            return smss.Sendwxsms(phone);
        }
        else
        {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

/*
获取城市门店列表；
 */
    @PostMapping(value = "/getcity",produces = "application/json")
    public  ResultData getcity() throws Exception {
        return  cs.GetallCity();
    }
   /*
   该会员是否已经设置密码
    */
    @PostMapping(value = "/existpwd",produces = "application/json")
    public  ResultData ExistPWD(@RequestBody RequestData rdata) throws Exception {
        if(rdata!=null) {
            String data=rdata.getData();
            logger.info("收到："+data);
            data=AESCrypt.decryptAES( data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            return mems.ExistPWD(map);
        }
        else
        {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg());
        }

    }
    /**
     * 设置会员密码
     * @param rdata
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/sethypwd",produces = "application/json;charset=UTF-8")
    public  ResultData sethypwd(@RequestBody RequestData rdata) throws Exception
    {
        if(rdata!=null) {
            String data=rdata.getData();
            logger.info("收到："+data);
            data=AESCrypt.decryptAES( data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            return mems.sethypwd(map);
        }
        else
        {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    /**
     * 修改会员密码
     * @param rdata
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/updatehypwd",produces = "application/json;charset=UTF-8")
    public  ResultData updatehypwd(@RequestBody RequestData rdata) throws Exception
    {
        if(rdata!=null) {
            String data=rdata.getData();
            logger.info("收到："+data);
            data=AESCrypt.decryptAES( data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            return mems.updatehypwd(map);
        }
        else
        {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg());
        }
    }


    /***
     * 获取会员券变动详情
     * @param rdata
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/gethycouponchange",produces = "application/json;charset=UTF-8")
    public  ResultData gethycouponchange(@RequestBody RequestData rdata) throws Exception
    {
        if(rdata!=null) {
            String data=rdata.getData();
            logger.info("收到："+data);
            data=AESCrypt.decryptAES( data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            return hs.PostService("gethycouponchange",map);
        }
        else
        {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg());
        }
    }
    /***
     * 判断会员状态
     * @param rdata
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/gethystatus",produces = "application/json;charset=UTF-8")
    public  ResultData gethystatus(@RequestBody RequestData rdata) throws Exception
    {
        if(rdata!=null) {
            String data=rdata.getData();
            logger.info("收到："+data);
            data=AESCrypt.decryptAES( data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            return hs.PostService("gethystatus",map);
        }
        else
        {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    /**
     * 微信登陆接口
     * @param rdata
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/wxlogin",produces = "application/json;charset=UTF-8")
    public  ResultData wxlogin(@RequestBody RequestData rdata) throws Exception
    {
    if(rdata!=null) {
        String data=rdata.getData();
        logger.info("收到："+data);
        data=AESCrypt.decryptAES( data);
        if(StringUtil.strisnull(data))
        {
            logger.info("解密失败");
            return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
        }
        Map<String, String> map = gson.fromJson(data, HashMap.class);
        return hs.PostService("wxlogin",map);
    }
    else
    {
        return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg());
    }
    }

    /**
     * 微信注册
     * @param rdata
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/registermember",produces = "application/json;charset=UTF-8")
    public  ResultData registermember(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }

            return hs.PostService("registermember",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    /**
     * 绑卡
     * @param rdata
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/bindwechat",produces = "application/json")
    public  ResultData bindwechat(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
//            logger.info("解密后：" + data);
            return hs.PostService("bindwechat",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/activemember",produces = "application/json")
    public  ResultData activemember(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("activemember",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/gethymain",produces = "application/json")
    public  ResultData gethymain(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("gethymain",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/gethymallpoint",produces = "application/json")
    public  ResultData gethymallpoint(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("gethymallpoint",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/gethymalldaypoint",produces = "application/json")
    public  ResultData gethymalldaypoint(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("gethymalldaypoint",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/gethymallcoupon",produces = "application/json")
    public  ResultData gethymallcoupon(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("gethymallcoupon",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/gethymallcouponlist",produces = "application/json")
    public  ResultData gethymallcouponlist(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("gethymallcouponlist",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/pointexchangecoupon",produces = "application/json")
    public  ResultData pointexchangecoupon(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("pointexchangecoupon",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/getcashbuycouponlist",produces = "application/json")
    public  ResultData getcashbuycouponlist(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("getcashbuycouponlist",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/cashbuycoupon",produces = "application/json")
    public  ResultData cashbuycoupon(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("cashbuycoupon",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/getmall",produces = "application/json")
    public  ResultData getmall(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            MALLDEF def = gson.fromJson(data, MALLDEF.class);
            return ResultUtil.success( mallDefService.selectByytandcity2(def));
           // return hs.PostService("getmall",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/reopenid",produces = "application/json")
    public  ResultData reopenid(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("reopenid",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/getpointexchangecouponlist",produces = "application/json")
    public  ResultData getpointexchangecouponlist(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("getpointexchangecouponlist",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/addpoint",produces = "application/json")
    public  ResultData addpoint(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("addpoint",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/wxticklist",produces = "application/json")
    public  ResultData wxticklist(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("wxticklist",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/wxtickdetail",produces = "application/json")
    public  ResultData wxtickdetail(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("wxtickdetail",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/subtractpoint",produces = "application/json")
    public  ResultData subtractpoint(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("subtractpoint",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/gethycoupondetail",produces = "application/json")
    public  ResultData gethycoupondetail(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("gethycoupondetail",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    @PostMapping(value = "/sendcoupon",produces = "application/json")
    public  ResultData sendcoupon(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if(StringUtil.strisnull(data))
            {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(),ResultEnum.AESFAIL.getMsg());
            }
            logger.info("解密后：" + data);
            return hs.PostService("sendcoupon",data);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

}
