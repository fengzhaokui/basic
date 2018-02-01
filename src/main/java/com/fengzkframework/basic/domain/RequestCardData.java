package com.fengzkframework.basic.domain;

import com.fengzkframework.basic.dao.vo.memcardmx;

public class RequestCardData extends  memcardmx {
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    private  String openid;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    private  String orderid;
    private  String isrefund;
    private  String goodsname;
    private String mallcode;

    public String getMallcode() {
        return mallcode;
    }

    public void setMallcode(String mallcode) {
        this.mallcode = mallcode;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getValidatecode() {
        return validatecode;
    }

    public void setValidatecode(String validatecode) {
        this.validatecode = validatecode;
    }

    private  String validatecode;

    public String getIsrefund() {
        return isrefund;
    }

    public void setIsrefund(String isrefund) {
        this.isrefund = isrefund;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    private  String paytype;
    public String getOpenid() {
        return openid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private  String password;

}
