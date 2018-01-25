package com.fengzkframework.basic.dao.vo;

public class WXAD {
    private Integer id;

    private String bigaddress;

    private String smalladdress;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBigaddress() {
        return bigaddress;
    }

    public void setBigaddress(String bigaddress) {
        this.bigaddress = bigaddress == null ? null : bigaddress.trim();
    }

    public String getSmalladdress() {
        return smalladdress;
    }

    public void setSmalladdress(String smalladdress) {
        this.smalladdress = smalladdress == null ? null : smalladdress.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}