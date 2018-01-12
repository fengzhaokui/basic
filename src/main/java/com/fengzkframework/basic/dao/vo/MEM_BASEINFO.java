package com.fengzkframework.basic.dao.vo;

import java.util.Date;

public class MEM_BASEINFO {
    private Long hyid;

    private Long hyktype;

    private String hykno;

    private String cdnr;

    private String hyname;

    private String phone;

    private Date validdate;

    private String password;

    private Integer status;

    private String exterhyid;

    private Date birthdate;

    private Integer sex;

    private String idcard;

    private String passport;

    private String teacherCard;

    private String soldierCard;

    private String openid;

    private String thirdhyid;

    private String phoneWxtj;

    private String phoneCftj;

    private Integer bjKtxj;

    private Date createtime;

    public Long getHyid() {
        return hyid;
    }

    public void setHyid(Long hyid) {
        this.hyid = hyid;
    }

    public Long getHyktype() {
        return hyktype;
    }

    public void setHyktype(Long hyktype) {
        this.hyktype = hyktype;
    }

    public String getHykno() {
        return hykno;
    }

    public void setHykno(String hykno) {
        this.hykno = hykno == null ? null : hykno.trim();
    }

    public String getCdnr() {
        return cdnr;
    }

    public void setCdnr(String cdnr) {
        this.cdnr = cdnr == null ? null : cdnr.trim();
    }

    public String getHyname() {
        return hyname;
    }

    public void setHyname(String hyname) {
        this.hyname = hyname == null ? null : hyname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getValiddate() {
        return validdate;
    }

    public void setValiddate(Date validdate) {
        this.validdate = validdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExterhyid() {
        return exterhyid;
    }

    public void setExterhyid(String exterhyid) {
        this.exterhyid = exterhyid == null ? null : exterhyid.trim();
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport == null ? null : passport.trim();
    }

    public String getTeacherCard() {
        return teacherCard;
    }

    public void setTeacherCard(String teacherCard) {
        this.teacherCard = teacherCard == null ? null : teacherCard.trim();
    }

    public String getSoldierCard() {
        return soldierCard;
    }

    public void setSoldierCard(String soldierCard) {
        this.soldierCard = soldierCard == null ? null : soldierCard.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getThirdhyid() {
        return thirdhyid;
    }

    public void setThirdhyid(String thirdhyid) {
        this.thirdhyid = thirdhyid == null ? null : thirdhyid.trim();
    }

    public String getPhoneWxtj() {
        return phoneWxtj;
    }

    public void setPhoneWxtj(String phoneWxtj) {
        this.phoneWxtj = phoneWxtj == null ? null : phoneWxtj.trim();
    }

    public String getPhoneCftj() {
        return phoneCftj;
    }

    public void setPhoneCftj(String phoneCftj) {
        this.phoneCftj = phoneCftj == null ? null : phoneCftj.trim();
    }

    public Integer getBjKtxj() {
        return bjKtxj;
    }

    public void setBjKtxj(Integer bjKtxj) {
        this.bjKtxj = bjKtxj;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}