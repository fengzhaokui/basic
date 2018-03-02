package com.fengzkframework.basic.dao.vo;

import java.math.BigDecimal;
import java.util.Date;

public class MEM_COUPON_DEF {
    private Long yhqid;

    private String yhqmc;

    private Integer type;

    private String title;

    private BigDecimal fee;

    private String content;

    private Date ksrq;

    private Date jsrq;

    private Integer flagIgnoreRule;

    private Long siebelId;

    private String weixinmc;

    private String weixincontent;

    private String photopath;

    private String photopath2;

    private Integer multiFlag;

    private BigDecimal zxrate;

    private Integer zxvalue;
    private Integer shopid;

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getUsetype() {
        return usetype;
    }

    public void setUsetype(Integer usetype) {
        this.usetype = usetype;
    }

    private Integer usetype;

    public Long getYhqid() {
        return yhqid;
    }

    public void setYhqid(Long yhqid) {
        this.yhqid = yhqid;
    }

    public String getYhqmc() {
        return yhqmc;
    }

    public void setYhqmc(String yhqmc) {
        this.yhqmc = yhqmc == null ? null : yhqmc.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getKsrq() {
        return ksrq;
    }

    public void setKsrq(Date ksrq) {
        this.ksrq = ksrq;
    }

    public Date getJsrq() {
        return jsrq;
    }

    public void setJsrq(Date jsrq) {
        this.jsrq = jsrq;
    }

    public Integer getFlagIgnoreRule() {
        return flagIgnoreRule;
    }

    public void setFlagIgnoreRule(Integer flagIgnoreRule) {
        this.flagIgnoreRule = flagIgnoreRule;
    }

    public Long getSiebelId() {
        return siebelId;
    }

    public void setSiebelId(Long siebelId) {
        this.siebelId = siebelId;
    }

    public String getWeixinmc() {
        return weixinmc;
    }

    public void setWeixinmc(String weixinmc) {
        this.weixinmc = weixinmc == null ? null : weixinmc.trim();
    }

    public String getWeixincontent() {
        return weixincontent;
    }

    public void setWeixincontent(String weixincontent) {
        this.weixincontent = weixincontent == null ? null : weixincontent.trim();
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath == null ? null : photopath.trim();
    }

    public String getPhotopath2() {
        return photopath2;
    }

    public void setPhotopath2(String photopath2) {
        this.photopath2 = photopath2 == null ? null : photopath2.trim();
    }

    public Integer getMultiFlag() {
        return multiFlag;
    }

    public void setMultiFlag(Integer multiFlag) {
        this.multiFlag = multiFlag;
    }

    public BigDecimal getZxrate() {
        return zxrate;
    }

    public void setZxrate(BigDecimal zxrate) {
        this.zxrate = zxrate;
    }

    public Integer getZxvalue() {
        return zxvalue;
    }

    public void setZxvalue(Integer zxvalue) {
        this.zxvalue = zxvalue;
    }
}