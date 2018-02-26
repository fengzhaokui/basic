
package com.fengzkframework.basic.dao.vo;

import com.fengzkframework.basic.service.MemBaseInfoServiceImpl;
import com.fengzkframework.basic.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
@Service
public class memcardmx {

    private Long id;

    private Date rq;

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    private  String name;
    private Integer hyid;
    private Integer grantstatus;
    private Integer zzzcid;
    private Integer oldid;
    private Integer jlbh;
    private String sktno;

    public String getRealamount() {
        return realamount;
    }

    public void setRealamount(String realamount) {
        this.realamount = realamount;
    }

    private String realamount;

    public Integer getOldid() {
        return oldid;
    }

    public void setOldid(Integer oldid) {
        this.oldid = oldid;
    }

    public Integer getJlbh() {
        return jlbh;
    }

    public void setJlbh(Integer jlbh) {
        this.jlbh = jlbh;
    }

    public String getSktno() {
        return sktno;
    }

    public void setSktno(String sktno) {
        this.sktno = sktno;
    }

    public Integer getGrantstatus() {
        return grantstatus;
    }

    public void setGrantstatus(Integer grantstatus) {
        this.grantstatus = grantstatus;
    }

    public Integer getZzzcid() {
        return zzzcid;
    }

    public void setZzzcid(Integer zzzcid) {
        this.zzzcid = zzzcid;
    }

    public Integer getCzfs() {
        return czfs;
    }

    public void setCzfs(Integer czfs) {
        this.czfs = czfs;
    }

    public Integer getCzhyid() {
        return czhyid;
    }

    public void setCzhyid(Integer czhyid) {
        this.czhyid = czhyid;
    }

    private Integer czfs;
    private  Integer czhyid;

    private String phone;

    private String StrDate;

    public String getStrDate() {
        return CommonUtil.getDateTimeStr(rq);
    }

    public void setStrDate(String strDate) {
        StrDate = strDate;
    }

    private BigDecimal je;

    public BigDecimal getCzje() {
        return czje;
    }

    public void setCzje(BigDecimal czje) {
        this.czje = czje;
    }

    private BigDecimal czje;

    private Integer ywlx;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRq() {
        return rq;

    }

    public void setRq(Date rq) {
        this.rq = rq;
    }

    public Integer getHyid() {
        return hyid;
    }

    public void setHyid(Integer hyid) {
        this.hyid = hyid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public BigDecimal getJe() {
        return je;
    }

    public void setJe(BigDecimal je) {
        this.je = je;
    }

    public Integer getYwlx() {
        return ywlx;
    }

    public void setYwlx(Integer ywlx) {
        this.ywlx = ywlx;
    }
}