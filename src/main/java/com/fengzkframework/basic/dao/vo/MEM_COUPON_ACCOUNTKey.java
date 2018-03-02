package com.fengzkframework.basic.dao.vo;

import java.util.Date;

public class MEM_COUPON_ACCOUNTKey {
    private Long hyid;

    private Long yhqid;

    private Date jsrq;

    private String code;

    public Long getHyid() {
        return hyid;
    }

    public void setHyid(Long hyid) {
        this.hyid = hyid;
    }

    public Long getYhqid() {
        return yhqid;
    }

    public void setYhqid(Long yhqid) {
        this.yhqid = yhqid;
    }

    public Date getJsrq() {
        return jsrq;
    }

    public void setJsrq(Date jsrq) {
        this.jsrq = jsrq;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}