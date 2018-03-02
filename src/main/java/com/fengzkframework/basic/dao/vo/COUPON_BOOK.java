package com.fengzkframework.basic.dao.vo;

import java.math.BigDecimal;
import java.util.Date;

public class COUPON_BOOK {
    private BigDecimal serialid;

    private Short sheettype;

    private String sheettypename;

    private Date jzrq;

    private Integer mallid;

    private String location;

    private String sheetid;

    private Long fqshopid;

    private Long yqshopid;

    private Short coupontypeid;

    private Long yhqid;

    private String code;

    private Integer direction;

    private BigDecimal value;

    private BigDecimal xjvalue;

    private BigDecimal bfje;

    private BigDecimal je;

    private Short status;

    public BigDecimal getSerialid() {
        return serialid;
    }

    public void setSerialid(BigDecimal serialid) {
        this.serialid = serialid;
    }

    public Short getSheettype() {
        return sheettype;
    }

    public void setSheettype(Short sheettype) {
        this.sheettype = sheettype;
    }

    public String getSheettypename() {
        return sheettypename;
    }

    public void setSheettypename(String sheettypename) {
        this.sheettypename = sheettypename == null ? null : sheettypename.trim();
    }

    public Date getJzrq() {
        return jzrq;
    }

    public void setJzrq(Date jzrq) {
        this.jzrq = jzrq;
    }

    public Integer getMallid() {
        return mallid;
    }

    public void setMallid(Integer mallid) {
        this.mallid = mallid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getSheetid() {
        return sheetid;
    }

    public void setSheetid(String sheetid) {
        this.sheetid = sheetid == null ? null : sheetid.trim();
    }

    public Long getFqshopid() {
        return fqshopid;
    }

    public void setFqshopid(Long fqshopid) {
        this.fqshopid = fqshopid;
    }

    public Long getYqshopid() {
        return yqshopid;
    }

    public void setYqshopid(Long yqshopid) {
        this.yqshopid = yqshopid;
    }

    public Short getCoupontypeid() {
        return coupontypeid;
    }

    public void setCoupontypeid(Short coupontypeid) {
        this.coupontypeid = coupontypeid;
    }

    public Long getYhqid() {
        return yhqid;
    }

    public void setYhqid(Long yhqid) {
        this.yhqid = yhqid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getXjvalue() {
        return xjvalue;
    }

    public void setXjvalue(BigDecimal xjvalue) {
        this.xjvalue = xjvalue;
    }

    public BigDecimal getBfje() {
        return bfje;
    }

    public void setBfje(BigDecimal bfje) {
        this.bfje = bfje;
    }

    public BigDecimal getJe() {
        return je;
    }

    public void setJe(BigDecimal je) {
        this.je = je;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}