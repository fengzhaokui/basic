package com.fengzkframework.basic.dao.vo;

public class PERSONINFO {
    private Long personId;

    private String personName;

    private String rydm;

    private Long deptid;

    private Integer workType;

    private Integer status;

    private String wxopenid;

    private String mobile;

    private Integer superadmin;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName == null ? null : personName.trim();
    }

    public String getRydm() {
        return rydm;
    }

    public void setRydm(String rydm) {
        this.rydm = rydm == null ? null : rydm.trim();
    }

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid == null ? null : wxopenid.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getSuperadmin() {
        return superadmin;
    }

    public void setSuperadmin(Integer superadmin) {
        this.superadmin = superadmin;
    }
}