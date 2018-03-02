package com.fengzkframework.basic.dao.vo;

import java.math.BigDecimal;

public class MEM_COUPON_ACCOUNT extends MEM_COUPON_ACCOUNTKey {
    private BigDecimal je;

    private String shorturl;

    private Integer mallid;

    private Integer cxtype;

    private Long hdid;

    private Integer status;

    private BigDecimal amt;

    public BigDecimal getJe() {
        return je;
    }

    public void setJe(BigDecimal je) {
        this.je = je;
    }

    public String getShorturl() {
        return shorturl;
    }

    public void setShorturl(String shorturl) {
        this.shorturl = shorturl == null ? null : shorturl.trim();
    }

    public Integer getMallid() {
        return mallid;
    }

    public void setMallid(Integer mallid) {
        this.mallid = mallid;
    }

    public Integer getCxtype() {
        return cxtype;
    }

    public void setCxtype(Integer cxtype) {
        this.cxtype = cxtype;
    }

    public Long getHdid() {
        return hdid;
    }

    public void setHdid(Long hdid) {
        this.hdid = hdid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
}