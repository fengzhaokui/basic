package com.fengzkframework.basic.dao.vo;

import java.util.Date;

public class TOKEN {
    private String tokenguid;
    
    private String sktno;

    private Integer personId;

    private Date createtime;

    public String getTokenguid() {
        return tokenguid;
    }

    public void setTokenguid(String tokenguid) {
        this.tokenguid = tokenguid == null ? null : tokenguid.trim();
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

	public String getSktno() {
		return sktno;
	}

	public void setSktno(String sktno) {
		this.sktno = sktno;
	}
}