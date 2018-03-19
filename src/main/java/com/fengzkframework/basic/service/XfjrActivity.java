package com.fengzkframework.basic.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fengzkframework.basic.dao.vo.*;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.enums.ResultEnum;
import com.fengzkframework.basic.utils.ResultUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fengzkframework.basic.dao.SKTMapper;
import com.fengzkframework.basic.dao.XJZKHDITEMMapper;
import com.fengzkframework.basic.dao.XJZKHDMapper;
import com.fengzkframework.basic.dao.XJZKHD_MEMMapper;
//import com.symboltech.msxfInterface.util.JsonUtils;

/*
 * 消费金融收款方式优惠金额处理；
 *
 */
@Service
public class XfjrActivity {

    @Autowired
    XJZKHDMapper xjzkhdMapper;
    @Autowired
    XJZKHDITEMMapper xjzkhditemMapper;
    @Autowired
    XJZKHD_MEMMapper xjzkhdmemMapper;
    @Autowired
    SKTMapper sktMapper;
    @Autowired
    MallDefServiceImpl mallDefService;
    @Autowired
    MemBaseInfoServiceImpl memBaseInfoService;

    Logger logger = Logger.getLogger(XfjrActivity.class);

    /**
     * 获取某个会员在某个门店的支付折扣
     * @param money
     * @param mallcode
     * @param openid
     * @return
     */
    public ResultData GetRealMoney(String money, String mallcode, String openid) {
        List<Realmoneydata> list=null;
        MEM_BASEINFO mem_baseinfo = memBaseInfoService.selectByopenid(openid);
        if (mem_baseinfo != null) {
            list=new ArrayList<Realmoneydata>();
//            Realmoneydata data=new Realmoneydata();
//            data.setSkfs("15");
//            data.setRealmoney(GetRealMoney(new BigDecimal(money) ,1,Integer.valueOf(mallcode),mem_baseinfo.getHyid().intValue()).toString());
//            list.add(data);
//            Realmoneydata data1=new Realmoneydata();
//            data1.setSkfs("23");
//            data1.setRealmoney(GetRealMoney(new BigDecimal(money),2,Integer.valueOf(mallcode),mem_baseinfo.getHyid().intValue()).toString());
//            list.add(data1);
            list.add(GetRealMoney(new BigDecimal(money) ,1,Integer.valueOf(mallcode),mem_baseinfo.getHyid().intValue()));
            list.add(GetRealMoney(new BigDecimal(money) ,2,Integer.valueOf(mallcode),mem_baseinfo.getHyid().intValue()));
            return ResultUtil.success(list);
        }
        return ResultUtil.error(ResultEnum.NOHY.getCode(),ResultEnum.NOHY.getMsg());
    }
public  class  Realmoneydata
{
    private  String skfs;

    public String getSkfs() {
        return skfs;
    }

    public void setSkfs(String skfs) {
        this.skfs = skfs;
    }

    public String getRealmoney() {
        return realmoney;
    }

    public void setRealmoney(String realmoney) {
        this.realmoney = realmoney;
    }

    private  String realmoney;

    public String getZkl() {
        return zkl;
    }

    public void setZkl(String zkl) {
        this.zkl = zkl;
    }

    private  String zkl;
}

    public Realmoneydata GetRealMoney(BigDecimal money, int skfstype, int mallcode, int hyid) {
        Realmoneydata data=new Realmoneydata();
        try {

            BigDecimal realmoney = new BigDecimal(0);
            XJZKHDITEMKey key = new XJZKHDITEMKey();
            int skfs = 0;
            if (skfstype == 1)
                skfs = 15;
            else if (skfstype == 2)
                skfs = 23;
            else {
                data.setSkfs("-1");
                data.setZkl("100");
                data.setRealmoney(money.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                return data;
            }
            data.setSkfs(String.valueOf(skfs));
            key.setSkfs(skfs);
            key.setDatenow(new Date());
            MALLDEF malldef = mallDefService.selectBycode(String.valueOf(mallcode));
            //SKT skt = sktMapper.selectByPrimaryKey(sktno);
            if (malldef != null) {
                key.setMdid(malldef.getId());
            }
            key.setHdlx(1l);// 查询优先级的活动
            XJZKHD hd = xjzkhdMapper.selectOneHD(key);
            if (hd != null) {
                key.setId(hd.getId());

                XJZKHDITEM item = xjzkhditemMapper.selectByPrimaryKey(key);
                if (item != null) {
                    // 限制数量
                    if (item.getGrkcsl() != null) {
                        XJZKHD_MEMKey memkey = new XJZKHD_MEMKey();
                        memkey.setHyid(Long.valueOf(hyid));
                        memkey.setId(item.getId());
                        memkey.setSkfs(Long.valueOf(item.getSkfs()));
                        memkey.setXfrq(new Date());
                        XJZKHD_MEM mem = xjzkhdmemMapper.selectByPrimaryKey(memkey);// 获取已经有的记录
                        if (mem != null) {
                            if (item.getGrkcsl() <= mem.getXssl()) {
                                data.setZkl("100");
                                data.setRealmoney(money.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                return data;
                            }
                        }
                    }
                    BigDecimal zkl2 = new BigDecimal(1).subtract(item.getZkl());
                    BigDecimal zkmoney = zkl2.multiply(money).setScale(2, BigDecimal.ROUND_HALF_UP);
                    if (item.getMaxzkje() != null) {
                        if (zkmoney.compareTo(item.getMaxzkje()) == 1) {
                            zkmoney = item.getMaxzkje();
                        }
                    }
                    realmoney = money.subtract(zkmoney).setScale(2, BigDecimal.ROUND_HALF_UP);
                    data.setZkl(String.valueOf(item.getZkl()));
                    data.setRealmoney(realmoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                    return data;
                }
            } else {
                key.setHdlx(0l);// 普通活动
                hd = xjzkhdMapper.selectOneHD(key);
                if (hd != null) {
                    key.setId(hd.getId());
                    XJZKHDITEM item = xjzkhditemMapper.selectByPrimaryKey(key);
                    if (item != null) {
                        // 限制数量
                        if (item.getGrkcsl() != null) {
                            XJZKHD_MEMKey memkey = new XJZKHD_MEMKey();
                            memkey.setHyid(Long.valueOf(hyid));
                            memkey.setId(item.getId());
                            memkey.setSkfs(Long.valueOf(item.getSkfs()));
                            memkey.setXfrq(new Date());
                            XJZKHD_MEM mem = xjzkhdmemMapper.selectByPrimaryKey(memkey);// 获取已经有的记录
                            if (mem != null) {
                                if (item.getGrkcsl() <= mem.getXssl()) {
                                    data.setZkl("100");
                                    data.setRealmoney(money.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                                    return data;
                                }
                            }
                        }
                        BigDecimal zkl2 = new BigDecimal(1).subtract(item.getZkl());

                        BigDecimal zkmoney = zkl2.multiply(money).setScale(2, BigDecimal.ROUND_HALF_UP);
                        if (zkmoney.compareTo(item.getMaxzkje()) == 1) {
                            zkmoney = item.getMaxzkje();
                        }
                        realmoney = money.subtract(zkmoney).setScale(2, BigDecimal.ROUND_HALF_UP);
                        data.setZkl(String.valueOf(item.getZkl()));
                        data.setRealmoney(realmoney.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                        return data;
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
        data.setZkl("100");
        data.setSkfs("-1");
        data.setRealmoney(money.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        return data;//money.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /*
     * skfstype: 1 马上消费 (15)；2易宝 (23)
     */
    public BigDecimal GetRealMoney(BigDecimal money, int skfstype, String sktno, int hyid) {
        try {
            logger.info("GetRealMoney;money:" + money + "skfstype:" + skfstype + ";sktno:" + sktno);
            BigDecimal realmoney = new BigDecimal(0);
            XJZKHDITEMKey key = new XJZKHDITEMKey();
            int skfs = 0;
            if (skfstype == 1)
                skfs = 15;
            else if (skfstype == 2)
                skfs = 23;
            else
                return money.setScale(2, BigDecimal.ROUND_HALF_UP);
            key.setSkfs(skfs);

            key.setDatenow(new Date());
            SKT skt = sktMapper.selectByPrimaryKey(sktno);
            if (skt != null) {
                key.setMdid(skt.getMdid());
            }
            key.setHdlx(1l);// 查询优先级的活动
            XJZKHD hd = xjzkhdMapper.selectOneHD(key);
            if (hd != null) {
                key.setId(hd.getId());

                XJZKHDITEM item = xjzkhditemMapper.selectByPrimaryKey(key);
                if (item != null) {
                    // 限制数量
                    if (item.getGrkcsl() != null) {
                        XJZKHD_MEMKey memkey = new XJZKHD_MEMKey();
                        memkey.setHyid(Long.valueOf(hyid));
                        memkey.setId(item.getId());
                        memkey.setSkfs(Long.valueOf(item.getSkfs()));
                        memkey.setXfrq(new Date());
                        XJZKHD_MEM mem = xjzkhdmemMapper.selectByPrimaryKey(memkey);// 获取已经有的记录
                        if (mem != null) {
                            if (item.getGrkcsl() <= mem.getXssl()) {
                                return money;
                            }
                        }
                    }
                    BigDecimal zkl2 = new BigDecimal(1).subtract(item.getZkl());
                    BigDecimal zkmoney = zkl2.multiply(money).setScale(2, BigDecimal.ROUND_HALF_UP);
                    // System.out.println("限额：" + item.getMaxzkje());
                    if (item.getMaxzkje() != null) {
                        if (zkmoney.compareTo(item.getMaxzkje()) == 1) {
                            zkmoney = item.getMaxzkje();
                        }
                    }
                    realmoney = money.subtract(zkmoney).setScale(2, BigDecimal.ROUND_HALF_UP);
                    return realmoney;
                }
            } else {
                key.setHdlx(0l);// 普通活动
                hd = xjzkhdMapper.selectOneHD(key);
                if (hd != null) {
                    key.setId(hd.getId());
                    XJZKHDITEM item = xjzkhditemMapper.selectByPrimaryKey(key);
                    if (item != null) {
                        // 限制数量
                        if (item.getGrkcsl() != null) {
                            XJZKHD_MEMKey memkey = new XJZKHD_MEMKey();
                            memkey.setHyid(Long.valueOf(hyid));
                            memkey.setId(item.getId());
                            memkey.setSkfs(Long.valueOf(item.getSkfs()));
                            memkey.setXfrq(new Date());
                            XJZKHD_MEM mem = xjzkhdmemMapper.selectByPrimaryKey(memkey);// 获取已经有的记录
                            if (mem != null) {
                                if (item.getGrkcsl() <= mem.getXssl()) {
                                    return money;
                                }
                            }
                        }
                        BigDecimal zkl2 = new BigDecimal(1).subtract(item.getZkl());

                        BigDecimal zkmoney = zkl2.multiply(money).setScale(2, BigDecimal.ROUND_HALF_UP);
                        if (zkmoney.compareTo(item.getMaxzkje()) == 1) {
                            zkmoney = item.getMaxzkje();
                        }
                        realmoney = money.subtract(zkmoney).setScale(2, BigDecimal.ROUND_HALF_UP);
                        return realmoney;
                        // realmoney = (item.getZkl().setScale(2,
                        // BigDecimal.ROUND_HALF_UP)
                        // .multiply(money.setScale(2,
                        // BigDecimal.ROUND_HALF_UP))).setScale(2,
                        // BigDecimal.ROUND_HALF_UP);
                        // return realmoney;
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
        return money.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /*
     * hyid 会员id； direction 1：交易； -1 ：退货
     */
    public void UpXJZKMEM(Long hyid, int skfstype, String sktno, int direction) {
        try {
            logger.info("UpXJZKMEM;" + hyid + ";" + skfstype + ";" + sktno + ";" + direction);
            int skfs = 0;
            if (skfstype == 1)
                skfs = 15;
            else if (skfstype == 2)
                skfs = 23;
            long hdid = Gethdid(skfs, sktno);
            // 有活动才存数据；
            if (hdid > 0) {

                XJZKHD_MEM mem = new XJZKHD_MEM();
                mem.setHyid(hyid);
                mem.setSkfs((long) skfs);
                mem.setXfrq(new Date());
                mem.setId(hdid);
                XJZKHD_MEM mem2 = xjzkhdmemMapper.selectByPrimaryKey(mem);
                if (mem2 == null && direction > 0) {

                    mem.setXssl(1l);
                    xjzkhdmemMapper.insert(mem);
                    //logger.info("xjzkhdmem表插入数据："+JsonUtils.object2Json(mem));
                } else {
                    if (direction > 0) {
                        mem.setXssl(mem2.getXssl() + 1);
                    } else {
                        mem.setXssl(mem2.getXssl() - 1);
                    }
                    //logger.info("xjzkhdmem表更新数据："+JsonUtils.object2Json(mem));
                    xjzkhdmemMapper.updateByPrimaryKeySelective(mem);
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    /*
     * 获取当前活动id 如果返回-1为没有活动；
     */
    private long Gethdid(int skfs, String sktno) {
        XJZKHDITEMKey key = new XJZKHDITEMKey();
        key.setSkfs(skfs);
        key.setDatenow(new Date());
        SKT skt = sktMapper.selectByPrimaryKey(sktno);
        if (skt != null) {
            key.setMdid(skt.getMdid());
        }
        key.setHdlx(1l);// 查询优先级的活动
        XJZKHD hd = xjzkhdMapper.selectOneHD(key);
        if (hd != null) {
            return hd.getId();
        } else {
            key.setHdlx(0l);// 普通活动
            hd = xjzkhdMapper.selectOneHD(key);
            if (hd != null) {
                return hd.getId();
            }
        }
        return -1l;

    }

    // /*
    // * 获取当天日期
    // */
    // private Date GetDate() throws ParseException {
    // Date currentTime = new Date();
    // SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    // String dateString = formatter.format(currentTime);
    // String pattern = "yyyy-MM-dd";
    // return new SimpleDateFormat(pattern).parse(dateString);
    // }
}
