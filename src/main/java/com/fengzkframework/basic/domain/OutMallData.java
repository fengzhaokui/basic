package com.fengzkframework.basic.domain;

import com.fengzkframework.basic.utils.StringUtil;

public class OutMallData {
    private String mallcode;
    private String mallname;
    private String  longtitude;
    private String  latitude;

    private String photourl;

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

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
