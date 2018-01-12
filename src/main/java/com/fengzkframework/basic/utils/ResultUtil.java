package com.fengzkframework.basic.utils;


import com.fengzkframework.basic.domain.MSPayData;
import com.fengzkframework.basic.domain.ResultData;
import com.fengzkframework.basic.enums.ResultEnum;

public class ResultUtil {

    public static ResultData success(Object object) {
        ResultData result = new ResultData();
        if(object!=null) {
            result.setRetcode(ResultEnum.SUCCESS.getCode());
            result.setRetmsg(ResultEnum.SUCCESS.getMsg());
            result.setData(object);
        }
        else
        {
            result.setRetcode(ResultEnum.NODATA.getCode());
            result.setRetmsg(ResultEnum.NODATA.getMsg());
        }
        return result;
    }

    public static ResultData success() {
        return success(null);
    }

    public static ResultData error(String code, String msg) {
        ResultData result = new ResultData();
        result.setRetcode(code);
        result.setRetmsg(msg);
        return result;
    }
    public static ResultData error(String code, String msg,Object oj) {
        ResultData result = new ResultData();
        result.setRetcode(code);
        result.setRetmsg(msg);
        result.setData(oj);
        return result;
    }

    public static ResultData GetResultData(MSPayData data) {
        ResultData result = new ResultData();
        result.setRetcode(data.getCode());
        result.setRetmsg(data.getMessage());
        result.setData(data.getData());
        return result;
    }
}
