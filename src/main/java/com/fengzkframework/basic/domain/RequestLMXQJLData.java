package com.fengzkframework.basic.domain;

import com.fengzkframework.basic.dao.vo.LMXQJL;

public class RequestLMXQJLData extends LMXQJL{
    private  String rq;

    public String getYhqname() {
        return yhqname;
    }

    public void setYhqname(String yhqname) {
        this.yhqname = yhqname;
    }

    private String yhqname;
   // private  String

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }
}
