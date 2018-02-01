package com.fengzkframework.basic.dao.vo;

import java.util.Date;

public class MALLDEF {
    private Integer id;

    private String code;

    private String name;

    private Long lastmodifierid;

    private String lastmodifiername;

    private Date lastmodifydate;

    private String xtcode;

    private Long tm;

    private String oldMdid;

    private String mdidPark;

    private String mdaddress;

    private String mdphone;

    private Integer bjAutoreg;
    private Integer yttype;
    private Integer cityid;

    private String cityname;
    private  String yeepaysubno;

    public String getYeepaysubno() {
        return yeepaysubno;
    }

    public void setYeepaysubno(String yeepaysubno) {
        this.yeepaysubno = yeepaysubno;
    }

    public Integer getYttype() {
        return yttype;
    }

    public void setYttype(Integer yttype) {
        this.yttype = yttype;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getLastmodifierid() {
        return lastmodifierid;
    }

    public void setLastmodifierid(Long lastmodifierid) {
        this.lastmodifierid = lastmodifierid;
    }

    public String getLastmodifiername() {
        return lastmodifiername;
    }

    public void setLastmodifiername(String lastmodifiername) {
        this.lastmodifiername = lastmodifiername == null ? null : lastmodifiername.trim();
    }

    public Date getLastmodifydate() {
        return lastmodifydate;
    }

    public void setLastmodifydate(Date lastmodifydate) {
        this.lastmodifydate = lastmodifydate;
    }

    public String getXtcode() {
        return xtcode;
    }

    public void setXtcode(String xtcode) {
        this.xtcode = xtcode == null ? null : xtcode.trim();
    }

    public Long getTm() {
        return tm;
    }

    public void setTm(Long tm) {
        this.tm = tm;
    }

    public String getOldMdid() {
        return oldMdid;
    }

    public void setOldMdid(String oldMdid) {
        this.oldMdid = oldMdid == null ? null : oldMdid.trim();
    }

    public String getMdidPark() {
        return mdidPark;
    }

    public void setMdidPark(String mdidPark) {
        this.mdidPark = mdidPark == null ? null : mdidPark.trim();
    }

    public String getMdaddress() {
        return mdaddress;
    }

    public void setMdaddress(String mdaddress) {
        this.mdaddress = mdaddress == null ? null : mdaddress.trim();
    }

    public String getMdphone() {
        return mdphone;
    }

    public void setMdphone(String mdphone) {
        this.mdphone = mdphone == null ? null : mdphone.trim();
    }

    public Integer getBjAutoreg() {
        return bjAutoreg;
    }

    public void setBjAutoreg(Integer bjAutoreg) {
        this.bjAutoreg = bjAutoreg;
    }
}