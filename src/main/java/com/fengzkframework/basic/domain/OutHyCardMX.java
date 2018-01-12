package com.fengzkframework.basic.domain;

import com.fengzkframework.basic.dao.vo.memcardmx;

import java.util.List;

public class OutHyCardMX {
    private  String currentye;
    private  String addje;
    private String consumeje;

    public List<memcardmx> getMxlist() {
        return mxlist;
    }

    public void setMxlist(List<memcardmx> mxlist) {
        this.mxlist = mxlist;
    }

    private List<memcardmx> mxlist;

    public String getCurrentye() {
        return currentye;
    }

    public void setCurrentye(String currentye) {
        this.currentye = currentye;
    }

    public String getAddje() {
        return addje;
    }

    public void setAddje(String addje) {
        this.addje = addje;
    }

    public String getConsumeje() {
        return consumeje;
    }

    public void setConsumeje(String consumeje) {
        this.consumeje = consumeje;
    }
}
