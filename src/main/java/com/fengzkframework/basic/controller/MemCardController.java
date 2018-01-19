package com.fengzkframework.basic.controller;

import com.fengzkframework.basic.aes.AESCrypt;
import com.fengzkframework.basic.dao.CARDZKLMapper;
import com.fengzkframework.basic.dao.vo.MEM_BASEINFO;
import com.fengzkframework.basic.dao.vo.mem_card_account;
import com.fengzkframework.basic.dao.vo.memcardmx;
import com.fengzkframework.basic.domain.*;
import com.fengzkframework.basic.enums.ResultEnum;
import com.fengzkframework.basic.service.*;
import com.fengzkframework.basic.utils.JsonUtils;
import com.fengzkframework.basic.utils.ResultUtil;
import com.fengzkframework.basic.utils.StringUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/weixin")
public class MemCardController {
    Logger logger = LoggerFactory.getLogger(MemCardController.class);
    Gson gson = new Gson();

    @Autowired
    //@Qualifier("MemBaseInfoServiceImpl")
    private MemBaseInfoServiceImpl ms;
    @Autowired
    private MemCardAccountServiceImpl mca;
    @Autowired
    MemCardMXServiceImpl mmx;
    @Autowired
    CardZKLServiceImpl zkls;
    @Autowired
    PayService payService;

    /**
     * 获取储值卡信息
     *
     * @param rdata
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/gethyczkxx", produces = "application/json;charset=UTF-8")
    public ResultData gethyczkxx(@RequestBody RequestData rdata) throws Exception {
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
            MEM_BASEINFO memBaseinfo = ms.selectByopenid(openid);
            if (memBaseinfo != null) {
                MEMCARDData result = new MEMCARDData();
                result.setPhone(memBaseinfo.getPhone());
                mem_card_account mcadata = mca.selectByPrimaryKey(memBaseinfo.getHyid());
                if (mcadata != null) {
                    result.setJe(mcadata.getJe().toString());
                } else {
                    result.setJe("0");
                }
                return ResultUtil.success(result);
            } else {
                return ResultUtil.error(ResultEnum.NOHY.getCode(), ResultEnum.NOHY.getMsg());
            }
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    /**
     * 支付并且更改会员卡账户余额；
     *0 本人充值  1 给他人充值
     * @param rdata
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/payhycardchange", produces = "application/json;charset=UTF-8")
    public ResultData PayHYCardChange(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data= AESCrypt.decryptAES( data);
            if (StringUtil.strisnull(data)) {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(), ResultEnum.AESFAIL.getMsg());
            }
            RequestCardData indata = gson.fromJson(data, RequestCardData.class);
            if(indata.getYwlx().equals(0)||indata.getYwlx().equals(1))
            {

            }
            else//不支持的业务类型；
            {
                return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
            }
            indata.setOrderid(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ StringUtil.randomString(6));
           int paytype=indata.getCzfs()+1;
            indata.setPaytype(String.valueOf(paytype));
            indata.setGoodsname("购物卡");
            ResultData paydata = payService.cardpay(indata);
            if (paydata.getRetcode().equals("0")) {
                String paymsdata = AESCrypt.decryptAES(paydata.getData().toString());
                Map<String, Object> map = JsonUtils.json2Map(paymsdata);
                if (map.get("status").equals("4")) {
                    ResultData mrd=null;
                    try {
                         mrd= mca.cardaccchange(indata);
                    }
                   finally {
                        if(mrd==null||(!mrd.getRetcode().equals("00")))//退款；
                        {
                            ResultData funddata = payService.cardfund(indata);
                           if (funddata.getRetcode().equals("0")) {
                           return  ResultUtil.error(ResultEnum.REFUNDSUC.getCode(), ResultEnum.REFUNDSUC.getMsg(), paydata.getRetmsg());
                           }
                           else {
                               return funddata;
                           }
                        }
                        else
                            return  mrd;
                    }

                } else if (map.get("status").equals("0")){
                    return ResultUtil.error(ResultEnum.SRYZM.getCode(), ResultEnum.SRYZM.getMsg(), indata.getOrderid());

                } else {
                    return ResultUtil.error(ResultEnum.PAYFAIL.getCode(), ResultEnum.PAYFAIL.getMsg(), paydata.getRetmsg());
                }

            } else {
                return ResultUtil.error(ResultEnum.PAYFAIL.getCode(), ResultEnum.PAYFAIL.getMsg(), paydata.getRetmsg());
            }
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    /*
    只充值购物卡，没有金额；；
     */
    @PostMapping(value = "/rechargecard", produces = "application/json;charset=UTF-8")
    public ResultData RechargeCard(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data= AESCrypt.decryptAES( data);
            if (StringUtil.strisnull(data)) {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(), ResultEnum.AESFAIL.getMsg());
            }
            RequestCardData indata = gson.fromJson(data, RequestCardData.class);
            if(indata.getYwlx().equals(0)||indata.getYwlx().equals(1))
            {
                return mca.cardaccchange(indata);
            }
            else//不支持的业务类型；
            {
                return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
            }
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    /*
    购物卡消费； ywlx=2
    phone je ywlx 必传
     */
    @PostMapping(value = "/payhycard", produces = "application/json;charset=UTF-8")
   public ResultData PayCard(@Valid RequestCardData indata) throws Exception {
//    public ResultData PayCard(@RequestParam(value = "phone", required = false) String phone,@RequestParam(value = "ywlx", required = false) String ywlx,
//    @RequestParam(value = "je", required = false) String je) throws Exception {
//        RequestCardData indata=new RequestCardData();
//        indata.setPhone(phone);
//        indata.setJe(new BigDecimal(je));
//        indata.setYwlx(Integer.valueOf(ywlx));
        if (indata != null) {
            if(indata.getYwlx().equals(2))
            {
                return mca.cardaccchange(indata);
            }
            else//不支持的业务类型；
            {
                return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
            }

        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }
/*
购物卡退货，撤销； ywlx一样是2 金额为负数；
支持批量退
sktno,jlbh,je
 */
    @Transactional
    @PostMapping(value = "/refundhycard", produces = "application/json;charset=UTF-8")
    public ResultData RefundCard(@Valid RequestCardData indata) throws Exception {
        if (indata != null) {
           // if(indata.getYwlx().equals(2))
            {
                indata.setIsrefund("true");
                List<memcardmx> oldmx=mmx.selectOldmx(indata.getSktno(),indata.getJlbh());
                if(oldmx==null||oldmx.size()==0)
                {
                      return ResultUtil.error(ResultEnum.UNOLDPAY.getCode(), ResultEnum.UNOLDPAY.getMsg());
                }
                BigDecimal je=new BigDecimal(0);
                for(memcardmx item:oldmx) {
                je=je.add(item.getJe());
                }
                if(je.compareTo(indata.getJe())!=0)
                {
                    return ResultUtil.error(ResultEnum.CARDREFUNDNOEQUAL.getCode(), ResultEnum.CARDREFUNDNOEQUAL.getMsg());
                }
                for(memcardmx item:oldmx)
                {
                    item.setOldid(-1);
                    mmx.updateByPrimaryKeySelective(item);
                    indata.setYwlx(2);
                    indata.setOldid(item.getId().intValue());
                    indata.setJe(item.getJe());
                    indata.setPhone(item.getPhone());
                    ResultData itemdata=  mca.cardaccchange(indata);
                   if(itemdata.getRetcode()!="00")
                   {
                       return ResultUtil.error(ResultEnum.CARDREFUNDFAIL.getCode(), ResultEnum.CARDREFUNDFAIL.getMsg());
                   }
                }
                return ResultUtil.success("");
//                indata.setYwlx(2);
//                indata.setOldid(oldmx.getId().intValue());
//                indata.setJe(oldmx.getJe());
//               indata.setPhone(oldmx.getPhone());
//                return mca.cardaccchange(indata);
            }
//            else//不支持的业务类型；
//            {
//                return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
//            }

        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }


    /*
   转赠购物卡值；
  3 发起转赠  4.转赠某某
    */
    // @Transactional
    @PostMapping(value = "/hycardzz", produces = "application/json;charset=UTF-8")
    public ResultData HYCardZZ(@RequestBody RequestData rdata) throws Exception {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data = AESCrypt.decryptAES(data);
            if (StringUtil.strisnull(data)) {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(), ResultEnum.AESFAIL.getMsg());
            }
            RequestCardData indata = gson.fromJson(data, RequestCardData.class);
            if(indata.getYwlx().equals(3)||indata.getYwlx().equals(4))
            {

            }
            else//不支持的业务类型；
            {
                return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
            }
            return mca.cardacczz(indata);
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    /*
 获取会员余额变动列表；
 分页；
  */
    @PostMapping(value = "/gethycardmxlist", produces = "application/json;charset=UTF-8")
    public ResultData gethycardmxlist(@RequestBody RequestData rdata) throws Exception {
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
            int start=1;
            int end=10000;
            if(map.containsKey("start"))//行号从1开始
            {
                 start= Integer.valueOf( map.get("start"))+1;
            }
            if(map.containsKey("end"))
            {
                end= Integer.valueOf( map.get("end"))+1;
            }

            MEM_BASEINFO memBaseinfo = ms.selectByopenid(openid);
            if (memBaseinfo != null) {
                List<memcardmx> list = mmx.selectByhyidpage(memBaseinfo.getHyid(),start,end);//mmx.Getcardmx(memBaseinfo.getHyid());
                if (list != null && list.size() > 0) {
                    for(memcardmx item :list)
                    {
                        if(item.getYwlx()==0||item.getYwlx()==1)
                        {
                            if(item.getCzfs()==0)
                            {
                                item.setName("购物卡充值(佳贝信用)");
                            }
                            else if(item.getCzfs()==1)
                            {
                                item.setName("购物卡充值(佳贝支付)");
                            }
                            else if(item.getCzfs()==2)
                            {
                                item.setName("购物卡充值(微信支付)");
                            }
                        }
                        else if(item.getYwlx()==3)
                        {
                            String mn= ms.selectNameByPrimaryKey(Long.valueOf(item.getCzhyid()) );
                            if(item.getGrantstatus()!=null &&item.getGrantstatus()==0) {

                                item.setName(mn+"转赠充值(未领取)");
                            }
                            else {
                                item.setName ( mn+"他人转赠充值(已领取)");
                            }
                        }
                        else if(item.getYwlx()==4)
                        {
                            String mn= ms.selectNameByPrimaryKey(Long.valueOf(item.getCzhyid()) );
                            if(item.getGrantstatus()!=null && item.getGrantstatus()==0) {
                                item.setName ("转赠"+mn+"(未领取)");
                            }
                            else {
                                item.setName ( "转赠"+mn+"(已领取)");
                            }
                        }
                        else  if(item.getYwlx()==2)
                        {
                            item.setName("购物卡消费");
                        }
                    }
                    OutHyCardMX outdata = new OutHyCardMX();
                    outdata.setMxlist(list);
                    if (map.containsKey("type")) {
                        String type = map.get("type");
                        if (type.equals("1"))//计算总的账户信息；
                        {
                            List<String> addlist = new ArrayList<String>();
                            addlist.add("0");
                            addlist.add("1");
                             BigDecimal addsum = mmx.sumByhyidywlx(addlist,memBaseinfo.getHyid());
                            if(addsum==null)
                                addsum=new BigDecimal(0);
                             outdata.setAddje(addsum.toString());
                            mem_card_account zh= mca.selectByPrimaryKey(memBaseinfo.getHyid());
                            outdata.setCurrentye(zh.getJe().toString());
                            List<String> consumelist = new ArrayList<String>();
                            consumelist.add("2");
                            BigDecimal consum =mmx.sumByhyidywlx(consumelist,memBaseinfo.getHyid());
                            if(consum==null)
                                consum=new BigDecimal(0);
                                    outdata.setConsumeje(consum.toString());
                        }
                    }
                    return ResultUtil.success(outdata);
                } else {
                    return ResultUtil.error(ResultEnum.NODATA.getCode(), ResultEnum.NODATA.getMsg());
                }
            } else {
                return ResultUtil.error(ResultEnum.NOHY.getCode(), ResultEnum.NOHY.getMsg());
            }
        } else {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }
    }

    /**
     * 领取他人赠送购物卡
     * @param rdata
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/receivecard", produces = "application/json;charset=UTF-8")
    public  ResultData Receivecard(@RequestBody RequestData rdata) throws Exception
    {
        if (rdata != null) {
            String data = rdata.getData();
            logger.info("收到：" + data);
            data= AESCrypt.decryptAES( data);
            if (StringUtil.strisnull(data)) {
                logger.info("解密失败");
                return ResultUtil.error(ResultEnum.AESFAIL.getCode(), ResultEnum.AESFAIL.getMsg());
            }
            Map<String, String> map = gson.fromJson(data, HashMap.class);
            String mxid = map.get("id");

          return   mca.Receivecard(Long.valueOf(mxid));

        }
        else
        {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getMsg());
        }

    }

    @PostMapping(value = "/gethycardzkl", produces = "application/json;charset=UTF-8")
    public ResultData gethycardzkl() throws Exception {
        return ResultUtil.success(zkls.selectall());
    }
}
