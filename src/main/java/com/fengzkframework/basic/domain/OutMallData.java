package com.fengzkframework.basic.domain;

import com.fengzkframework.basic.utils.StringUtil;

public class OutMallData {
    private String mallcode;
    private String mallname;

    public String getMallcode() {
        return mallcode;
    }

    public void setMallcode(String mallcode) {
        this.mallcode = mallcode;
    }

    public String getMallname() {
        return mallname;
    }

    public void setMallname(String mallname) {
        this.mallname = mallname;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    private String cityname;
    private String cityid;
}
