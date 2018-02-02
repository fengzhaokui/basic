package com.fengzkframework.basic.dao.vo;

import java.util.Date;

public class XJZKHD {
    private Long id;

    private Date ksrq;

    private Date jsrq;

    private Long djr;

    private String djrmc;

    private Date djsj;

    private Long kcsl;

    private Long grkcsl;

    private Long xssl;

    private Integer kcslFlag;

    private Integer grkcslFlag;

    private Integer status;

    private Long zxr;

    private String zxrmc;

    private Date zxrq;

    private Long gzid;
    
    private Long hdlx;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getDjr() {
        return djr;
    }

    public void setDjr(Long djr) {
        this.djr = djr;
    }

    public String getDjrmc() {
        return djrmc;
    }

    public void setDjrmc(String djrmc) {
        this.djrmc = djrmc == null ? null : djrmc.trim();
    }

    public Date getDjsj() {
        return djsj;
    }

    public void setDjsj(Date djsj) {
        this.djsj = djsj;
    }

    public Long getKcsl() {
        return kcsl;
    }

    public void setKcsl(Long kcsl) {
        this.kcsl = kcsl;
    }

    public Long getGrkcsl() {
        return grkcsl;
    }

    public void setGrkcsl(Long grkcsl) {
        this.grkcsl = grkcsl;
    }

    public Long getXssl() {
        return xssl;
    }

    public void setXssl(Long xssl) {
        this.xssl = xssl;
    }

    public Integer getKcslFlag() {
        return kcslFlag;
    }

    public void setKcslFlag(Integer kcslFlag) {
        this.kcslFlag = kcslFlag;
    }

    public Integer getGrkcslFlag() {
        return grkcslFlag;
    }

    public void setGrkcslFlag(Integer grkcslFlag) {
        this.grkcslFlag = grkcslFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getZxr() {
        return zxr;
    }

    public void setZxr(Long zxr) {
        this.zxr = zxr;
    }

    public String getZxrmc() {
        return zxrmc;
    }

    public void setZxrmc(String zxrmc) {
        this.zxrmc = zxrmc == null ? null : zxrmc.trim();
    }

    public Date getZxrq() {
        return zxrq;
    }

    public void setZxrq(Date zxrq) {
        this.zxrq = zxrq;
    }

    public Long getGzid() {
        return gzid;
    }

    public void setGzid(Long gzid) {
        this.gzid = gzid;
    }

	public Long getHdlx() {
		return hdlx;
	}

	public void setHdlx(Long hdlx) {
		this.hdlx = hdlx;
	}
}