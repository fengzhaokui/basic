package com.fengzkframework.basic.dao.vo;

public class VERIFYCODE extends VERIFYCODEKey {
    private String code;

    private Long skyid;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getSkyid() {
        return skyid;
    }

    public void setSkyid(Long skyid) {
        this.skyid = skyid;
    }
}