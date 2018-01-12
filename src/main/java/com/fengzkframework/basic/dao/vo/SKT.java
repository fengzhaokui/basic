package com.fengzkframework.basic.dao.vo;

import java.util.Date;

public class SKT {
    private String sktno;

    private String databasename;

    private Integer qsfs;

    private Long maxjlbh;

    private Date gxsj;

    private Long sky;

    private Short canZk;

    private Short canTh;

    private Short canBb;

    private Short canGz;

    private Short canBar;

    private Integer xsddid;

    private String machinecode;

    private Integer shopid;

    private Integer status;

    private String ip;
    private String thirdpayuser;
    private String thirdpaypwd;


    private String mac1;

    private String mac2;

    private String linkurl;

    private Integer monitorStatus;

    private String weixin;

    private String alipay;

    private Integer bjTyzf;

	private Integer mdid;
    private Integer dyfa;

    public String getSktno() {
        return sktno;
    }

    public void setSktno(String sktno) {
        this.sktno = sktno == null ? null : sktno.trim();
    }

    public String getDatabasename() {
        return databasename;
    }

    public void setDatabasename(String databasename) {
        this.databasename = databasename == null ? null : databasename.trim();
    }

    public Integer getQsfs() {
        return qsfs;
    }

    public void setQsfs(Integer qsfs) {
        this.qsfs = qsfs;
    }

    public Long getMaxjlbh() {
        return maxjlbh;
    }

    public void setMaxjlbh(Long maxjlbh) {
        this.maxjlbh = maxjlbh;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
    }

    public Long getSky() {
        return sky;
    }

    public void setSky(Long sky) {
        this.sky = sky;
    }

    public Short getCanZk() {
        return canZk;
    }

    public void setCanZk(Short canZk) {
        this.canZk = canZk;
    }

    public Short getCanTh() {
        return canTh;
    }

    public void setCanTh(Short canTh) {
        this.canTh = canTh;
    }

    public Short getCanBb() {
        return canBb;
    }

    public void setCanBb(Short canBb) {
        this.canBb = canBb;
    }

    public Short getCanGz() {
        return canGz;
    }

    public void setCanGz(Short canGz) {
        this.canGz = canGz;
    }

    public Short getCanBar() {
        return canBar;
    }

    public void setCanBar(Short canBar) {
        this.canBar = canBar;
    }

    

    public Integer getXsddid() {
        return xsddid;
    }

    public void setXsddid(Integer xsddid) {
        this.xsddid = xsddid;
    }

    public String getMachinecode() {
        return machinecode;
    }

    public void setMachinecode(String machinecode) {
        this.machinecode = machinecode == null ? null : machinecode.trim();
    }

    public Integer getShopid() {
        return shopid;
    }

    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getMac1() {
        return mac1;
    }

    public void setMac1(String mac1) {
        this.mac1 = mac1 == null ? null : mac1.trim();
    }

    public String getMac2() {
        return mac2;
    }

    public void setMac2(String mac2) {
        this.mac2 = mac2 == null ? null : mac2.trim();
    }

    public String getLinkurl() {
        return linkurl;
    }

    public void setLinkurl(String linkurl) {
        this.linkurl = linkurl == null ? null : linkurl.trim();
    }

    public Integer getMonitorStatus() {
        return monitorStatus;
    }

    public void setMonitorStatus(Integer monitorStatus) {
        this.monitorStatus = monitorStatus;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay == null ? null : alipay.trim();
    }

    public Integer getBjTyzf() {
        return bjTyzf;
    }

    public void setBjTyzf(Integer bjTyzf) {
        this.bjTyzf = bjTyzf;
    }

    

    public Integer getDyfa() {
        return dyfa;
    }

    public void setDyfa(Integer dyfa) {
        this.dyfa = dyfa;
    }

	public String getThirdpayuser() {
		return thirdpayuser;
	}

	public void setThirdpayuser(String thirdpayuser) {
		this.thirdpayuser = thirdpayuser;
	}

	public String getThirdpaypwd() {
		return thirdpaypwd;
	}

	public void setThirdpaypwd(String thirdpaypwd) {
		this.thirdpaypwd = thirdpaypwd;
	}

	public Integer getMdid() {
		return mdid;
	}

	public void setMdid(Integer mdid) {
		this.mdid = mdid;
	}
}