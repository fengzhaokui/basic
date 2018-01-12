package com.fengzkframework.basic.service;

import com.fengzkframework.basic.dao.mem_card_accountMapper;
import com.fengzkframework.basic.dao.vo.MEM_BASEINFO;
import com.fengzkframework.basic.dao.vo.mem_card_account;
import com.fengzkframework.basic.dao.vo.memcardmx;
import com.fengzkframework.basic.domain.RequestCardData;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.enums.ResultEnum;
import com.fengzkframework.basic.utils.ResultUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.PanelUI;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class MemCardAccountServiceImpl  {
    Logger logger = LoggerFactory.getLogger(MemCardAccountServiceImpl.class);
    Gson gson = new Gson();
    @Autowired
    mem_card_accountMapper memMapper;

    @Autowired
    private MemBaseInfoServiceImpl ms;
    @Autowired
    MemCardMXServiceImpl mmx;

    public mem_card_account selectByPrimaryKey(Long hyid) {
        // TODO Auto-generated method stub
        return memMapper.selectByPrimaryKey(hyid);
    }
    public int updateByPrimaryKeySelective(mem_card_account record) {
        // TODO Auto-generated method stub
        return memMapper.updateByPrimaryKeySelective(record);
    }
   public int insertSelective(mem_card_account record)
   {
       return  memMapper.insertSelective(record);
   }

    /**
     * 更新账户余额交易,充值,退款；
     * @param indata
     * @return
     */
    @Transactional
   public ResultData cardaccchange(RequestCardData indata)
   {
      // int a=1/0;
       if(indata==null)
       {
           return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg());
       }
       MEM_BASEINFO memBaseinfo2=  ms.selectByopenid(indata.getOpenid());

       List<MEM_BASEINFO> memBaseinfo=  ms.selectByphone(indata.getPhone());
       if(memBaseinfo!=null)
       {
           if(memBaseinfo.size()>1)
           {
               return ResultUtil.error(ResultEnum.MoreHY.getCode(),ResultEnum.MoreHY.getMsg());
           }
           //账户金额变化;
           mem_card_account mcadata=selectByPrimaryKey(memBaseinfo.get(0).getHyid());
           if(mcadata!=null)
           {
               if(indata.getYwlx()<=1) {
                   BigDecimal je = mcadata.getJe().add(indata.getJe());
                   mcadata.setJe(je);
               }
               else {
                   if(indata.getIsrefund()!=null&&indata.getIsrefund().equals("true"))//退款；
                   {
                       BigDecimal je = mcadata.getJe().add(indata.getJe());
                       mcadata.setJe(je);
                   }
                   else {
                       BigDecimal je = mcadata.getJe().subtract(indata.getJe());
                       if (je.compareTo(new BigDecimal(0)) < 0)//余额不足；
                       {
                           return ResultUtil.error(ResultEnum.ZZFAIL.getCode(), ResultEnum.ZZFAIL.getMsg());
                       }
                       mcadata.setJe(je);
                   }

               }
               updateByPrimaryKeySelective(mcadata);
           }
           else
           {
               mcadata=new mem_card_account();
               mcadata.setHyid(memBaseinfo.get(0).getHyid());
               if(indata.getYwlx()<=1) {
                   mcadata.setJe(indata.getJe());
               }
               else {

                       BigDecimal je = mcadata.getJe().subtract(indata.getJe());
                       if (je.compareTo(new BigDecimal(0)) < 0)//余额不足；
                       {
                           return ResultUtil.error(ResultEnum.ZZFAIL.getCode(), ResultEnum.ZZFAIL.getMsg());
                       }
                       mcadata.setJe(je);
                   }

               insertSelective(mcadata);
           }
           memcardmx cardmx=new memcardmx();
           cardmx.setHyid(memBaseinfo.get(0).getHyid().intValue());
           if(indata.getIsrefund()!=null&&indata.getIsrefund().equals("true"))//退货
           {
               cardmx.setJe(indata.getJe().multiply(new BigDecimal(-1)));
               //int oldid=mmx.selectOldid(memBaseinfo.get(0).getHyid(),indata.getSktno(),indata.getJlbh());
               cardmx.setOldid(indata.getOldid());//退货的时候用到的；

           }
           else {
               cardmx.setJe(indata.getJe());
               cardmx.setSktno(indata.getSktno());
               cardmx.setJlbh(indata.getJlbh());
           }
           cardmx.setPhone(indata.getPhone());
           cardmx.setYwlx(indata.getYwlx());
           cardmx.setCzfs(indata.getCzfs());


           if(memBaseinfo2!=null) {
               cardmx.setCzhyid(memBaseinfo2.getHyid().intValue());
           }
           cardmx.setRq(new Date());
           cardmx.setCzje(indata.getCzje());
           mmx.addmx(cardmx);//详细记录；
           return  ResultUtil.success("");
       }
       else {
           return ResultUtil.error(ResultEnum.NOHY.getCode(),ResultEnum.NOHY.getMsg());
       }
   }

    /**
     * 转赠购物卡值
     * @param indata
     * @return
     */
    @Transactional
    public ResultData cardacczz(RequestCardData indata)
    {
        if(indata==null)
        {
            return ResultUtil.error(ResultEnum.UNKONW_ERROR.getCode(),ResultEnum.UNKONW_ERROR.getMsg());
        }
        MEM_BASEINFO memBaseinfo2=  ms.selectByopenid(indata.getOpenid());//转出人；
        List<MEM_BASEINFO> memBaseinfo=  ms.selectByphone(indata.getPhone());//接受人;
        if(memBaseinfo!=null)
        {
            if(memBaseinfo.size()>1)
            {
                return ResultUtil.error(ResultEnum.MoreHY.getCode(),ResultEnum.MoreHY.getMsg());
            }
            //账户金额变化;
            mem_card_account mcadata2=selectByPrimaryKey(memBaseinfo2.getHyid());
            if(mcadata2!=null)
            {
                BigDecimal je = mcadata2.getJe().subtract(indata.getJe());
                mcadata2.setFrozenje(indata.getJe());
                mcadata2.setJe(je);
                updateByPrimaryKeySelective(mcadata2);
            }
            else
            {
                return ResultUtil.error(ResultEnum.ZZFAIL.getCode(),ResultEnum.ZZFAIL.getMsg());
//                mcadata2=new mem_card_account();
//                mcadata2.setHyid(memBaseinfo.get(0).getHyid());
//                mcadata2.setFrozenje(indata.getJe());
//                insertSelective(mcadata2);
            }
            //接受人
            mem_card_account mcadata=selectByPrimaryKey(memBaseinfo.get(0).getHyid());
            if(mcadata!=null)
            {
                // BigDecimal je = mcadata.getJe().add(indata.getJe());
                mcadata.setFrozenje(indata.getJe());
                updateByPrimaryKeySelective(mcadata);
            }
            else
            {
                mcadata=new mem_card_account();
                mcadata.setHyid(memBaseinfo.get(0).getHyid());
                mcadata.setFrozenje(indata.getJe());
                insertSelective(mcadata);
            }
            memcardmx cardmx=new memcardmx();
            cardmx.setHyid(memBaseinfo2.getHyid().intValue());
            cardmx.setJe(indata.getJe());
            cardmx.setPhone(memBaseinfo2.getPhone());
            cardmx.setYwlx(4);
            cardmx.setCzfs(indata.getCzfs());
            cardmx.setCzhyid(memBaseinfo.get(0).getHyid().intValue());
            cardmx.setRq(new Date());
            cardmx.setCzje(indata.getCzje());
            cardmx.setGrantstatus(0);
            mmx.addmx(cardmx);//详细记录；

            memcardmx cardmx1=new memcardmx();
            cardmx1.setHyid(memBaseinfo.get(0).getHyid().intValue());
            cardmx1.setJe(indata.getJe());
            cardmx1.setPhone(indata.getPhone());
            cardmx1.setYwlx(3);
            cardmx1.setCzfs(indata.getCzfs());
            cardmx1.setCzhyid(memBaseinfo2.getHyid().intValue());
            cardmx1.setRq(new Date());
            cardmx1.setZzzcid(cardmx.getId().intValue());
            cardmx1.setCzje(indata.getCzje());
            cardmx1.setGrantstatus(0);
            mmx.addmx(cardmx1);//详细记录接受人；

            return  ResultUtil.success("");
        }
        else {
            return ResultUtil.error(ResultEnum.NOHY.getCode(),ResultEnum.NOHY.getMsg());
        }
    }
    /**
     * 领取转赠购物卡值
     * @param
     * @return
     */
    @Transactional
    public  ResultData Receivecard(long mxid)
    {
        memcardmx aa=  mmx.selectByPrimaryKey(mxid);
        if(aa==null||(!aa.getGrantstatus().equals(0)))
        {
            return ResultUtil.error(ResultEnum.LQCARDFAIL.getCode(), ResultEnum.LQCARDFAIL.getMsg());
        }
        mem_card_account mca= selectByPrimaryKey(Long.valueOf(aa.getHyid()));
        mem_card_account mca2= selectByPrimaryKey(Long.valueOf(aa.getCzhyid()));
        if(mca.getFrozenje().compareTo(aa.getJe())<0 ||mca2.getFrozenje().compareTo(aa.getJe())<0)//冻结金额不够
        {
            return ResultUtil.error(ResultEnum.LQCARDFAIL.getCode(), ResultEnum.LQCARDFAIL.getMsg());
        }
        memcardmx bb=  mmx.selectByPrimaryKey(Long.valueOf( aa.getZzzcid()));
        if(bb==null||(!bb.getGrantstatus().equals(0)))
        {
            return ResultUtil.error(ResultEnum.LQCARDFAIL.getCode(), ResultEnum.LQCARDFAIL.getMsg());
        }
        aa.setGrantstatus(1);
        bb.setGrantstatus(1);
        mmx.updateByPrimaryKeySelective(aa);
        mmx.updateByPrimaryKeySelective(bb);

        mca.setFrozenje(mca.getFrozenje().subtract(aa.getJe()));
        mca.setJe(mca.getJe().add(aa.getJe()));
        mca2.setFrozenje(mca2.getFrozenje().subtract(aa.getJe()));
        updateByPrimaryKeySelective(mca);
        updateByPrimaryKeySelective(mca2);
        return  ResultUtil.success("");
    }
}
