package com.fengzkframework.basic.enums;
public enum ResultEnum {
    UNKONW_ERROR("-1", "参数错误"),
    SUCCESS("00", "成功"),
    SENDSMSFAIL("01","发送短信失败"),
    AESFAIL("02", "解密失败"),
    NOHY("03", "该会员不存在"),
    MoreHY("04", "该手机号有多个会员，请联系客服"),
    PAYFAIL("05", "支付失败"),
    ZZFAIL("06", "账户余额不足"),
    LQCARDFAIL("07", "领取购物卡失败"),
    REFUNDSUC("08", "购买失败，退款成功"),
    SRYZM("09", "请输入验证码"),
    UNOLDPAY("10", "原交易记录不存在,无法退款"),
    CARDREFUNDFAIL("11", "购物卡退款失败"),
    CARDREFUNDNOEQUAL("12", "购物卡退款金额不匹配,无法退款"),
    PWDISEXIST("13", "密码已存在"),

    SETPWDFAIL("14", "密码设置失败"),
    PWDISNOEXIST("15", "密码不存在,无法重置密码"),
    PWDISERROR("16", "旧密码错误,无法重置密码"),
    UPPWDFAIL("17", "修改密码失败"),
    PWDCW("18", "密码错误,请重新输入"),
    SHHYOPENIDCW("19", "请重新登录"),
    COUPONNOTEXIST("20", "优惠券不可用"),
    COUPONNOMONEY("21", "优惠券余额不足"),
    COUPONNOCANUSE("21", "优惠券不是该店铺不能使用"),
    USEDCOUPONFAIL("22", "用券失败"),
    SETSHUSERNAMEFAIL("23", "设置用户名失败"),
    SHUSERSTATUSFAIL("24", "该用户不许消券"),
    UNKNOWERROR("100", "未知错误"),
    NODATA("99", "没有数据"),

    ;
    private String code;
    private String msg;
    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
