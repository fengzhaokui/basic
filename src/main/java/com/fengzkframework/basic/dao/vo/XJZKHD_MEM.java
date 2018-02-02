package com.fengzkframework.basic.dao.vo;

import java.math.BigDecimal;
import java.util.Date;

public class XJZKHD_MEM extends XJZKHD_MEMKey {
    private BigDecimal zkl;

    private Long xssl;
    
    public BigDecimal getZkl() {
        return zkl;
    }

    public void setZkl(BigDecimal zkl) {
        this.zkl = zkl;
    }

    public Long getXssl() {
        return xssl;
    }

    public void setXssl(Long xssl) {
        this.xssl = xssl;
    }

	
}