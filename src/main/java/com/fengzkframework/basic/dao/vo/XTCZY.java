package com.fengzkframework.basic.dao.vo;

public class XTCZY {
    private Long personId;

    private String operStation;

    private String loginPassword;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getOperStation() {
        return operStation;
    }

    public void setOperStation(String operStation) {
        this.operStation = operStation == null ? null : operStation.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
}