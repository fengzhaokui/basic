package com.fengzkframework.basic.domain;

import com.fengzkframework.basic.dao.vo.LMXQJL;

public class RequestLMXQJLRateData {
    private  String rate;

    public String getYhqname() {
        return yhqname;
    }

    public void setYhqname(String yhqname) {
        this.yhqname = yhqname;
    }

    private String yhqname;
    private String usetype;

    public String getUsetype() {
        return usetype;
    }

    public void setUsetype(String usetype) {
        this.usetype = usetype;
    }

    private  String fqmoney;
    private  String yqmoney;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getFqmoney() {
        return fqmoney;
    }

    public void setFqmoney(String fqmoney) {
        this.fqmoney = fqmoney;
    }

    public String getYqmoney() {
        return yqmoney;
    }

    public void setYqmoney(String yqmoney) {
        this.yqmoney = yqmoney;
    }
}
