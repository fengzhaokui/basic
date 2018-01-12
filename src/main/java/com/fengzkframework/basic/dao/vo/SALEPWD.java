package com.fengzkframework.basic.dao.vo;

public class SALEPWD {
    private Long hyid;

    private String password;

    private Integer mspasswordbj;

    public Long getHyid() {
        return hyid;
    }

    public void setHyid(Long hyid) {
        this.hyid = hyid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getMspasswordbj() {
        return mspasswordbj;
    }

    public void setMspasswordbj(Integer mspasswordbj) {
        this.mspasswordbj = mspasswordbj;
    }
}