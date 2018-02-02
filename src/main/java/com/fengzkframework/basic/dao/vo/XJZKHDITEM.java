package com.fengzkframework.basic.dao.vo;

import java.math.BigDecimal;

public class XJZKHDITEM extends XJZKHDITEMKey {
    private BigDecimal zkl;

    private Long kcsl;

    private Long grkcsl;

    private Long xssl;
    private BigDecimal maxzkje;

    public BigDecimal getZkl() {
        return zkl;
    }

    public void setZkl(BigDecimal zkl) {
        this.zkl = zkl;
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

	public BigDecimal getMaxzkje() {
		return maxzkje;
	}

	public void setMaxzkje(BigDecimal maxzkje) {
		this.maxzkje = maxzkje;
	}
}