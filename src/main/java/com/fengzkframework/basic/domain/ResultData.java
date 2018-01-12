package com.fengzkframework.basic.domain;

public class ResultData<T> {

    /** 错误码. */
    private String retcode;

    /** 提示信息. */
    private String retmsg;

    /** 具体的内容. */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }
}
