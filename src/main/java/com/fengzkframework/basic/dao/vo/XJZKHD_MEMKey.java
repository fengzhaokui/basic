package com.fengzkframework.basic.dao.vo;

import java.util.Date;

public class XJZKHD_MEMKey {
    private Long id;

    private Long hyid;

    private Long skfs;
    private Date xfrq;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHyid() {
        return hyid;
    }

    public void setHyid(Long hyid) {
        this.hyid = hyid;
    }

    public Long getSkfs() {
        return skfs;
    }

    public void setSkfs(Long skfs) {
        this.skfs = skfs;
    }

	public Date getXfrq() {
		return xfrq;
	}

	public void setXfrq(Date xfrq) {
		this.xfrq = xfrq;
	}
}