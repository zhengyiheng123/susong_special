package com.xyd.susong.commitorder;

import com.google.gson.annotations.SerializedName;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/2
 * @time: 14:03
 * @description:
 */

public class WxModel {


    /**
     * appid : wxedf9efa84a68a388
     * partnerid : 1495125652
     * prepayid : wx20180104173254f0d9ee629b0711148068
     * noncestr : 5a4df4c6e5caa
     * timestamp : 1515058374
     * package : Sign=WXPay
     * sign : 5FD0A171A395C600A24C3CAF0252A6F7
     */

    private String appid;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private int timestamp;
    private String s_package;
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getS_package() {
        return s_package;
    }

    public void setS_package(String s_package) {
        this.s_package = s_package;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
