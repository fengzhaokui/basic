package com.fengzkframework.basic.dao.vo;

import java.util.Date;

public class XJZKHDITEMKey {
    private Long id;
    
    private Long hdlx;

    private Integer skfs;
    
    private Date datenow;
    private Integer mdid;

    public Integer getMdid() {
		return mdid;
	}

	public void setMdid(Integer mdid) {
		this.mdid = mdid;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSkfs() {
        return skfs;
    }

    public void setSkfs(Integer skfs) {
        this.skfs = skfs;
    }

	public Date getDatenow() {
		return datenow;
	}

	public void setDatenow(Date datenow) {
		this.datenow = datenow;
	}

	public Long getHdlx() {
		return hdlx;
	}

	public void setHdlx(Long hdlx) {
		this.hdlx = hdlx;
	}
}