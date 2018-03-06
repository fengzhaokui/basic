package com.fengzkframework.basic.dao.vo;

public class PERSONOPENID extends PERSONOPENIDKey {
    private Integer status;

    private Integer bj_login;

    public Integer getBj_login() {
        return bj_login;
    }

    public void setBj_login(Integer bj_login) {
        this.bj_login = bj_login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}