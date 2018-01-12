package com.fengzkframework.basic.dao.vo;

import java.math.BigDecimal;

public class mem_card_account {
    private Long hyid;

    private BigDecimal je;
    private  BigDecimal frozenje;

    public BigDecimal getFrozenje() {
        return frozenje;
    }

    public void setFrozenje(BigDecimal frozenje) {
        this.frozenje = frozenje;
    }

    public Long getHyid() {
        return hyid;
    }

    public void setHyid(Long hyid) {
        this.hyid = hyid;
    }

    public BigDecimal getJe() {
        return je;
    }

    public void setJe(BigDecimal je) {
        this.je = je;
    }
}