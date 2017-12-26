package com.xyd.susong.shopchart;

/**
 * Created by Zheng on 2017/12/19.
 */

public class ChartModel {
    private int num;
    private String goodsName;
    private boolean checked;
    private Double price;
    public ChartModel(int num, String goodsName, boolean checked,Double price) {
        this.num = num;
        this.goodsName = goodsName;
        this.checked = checked;
        this.price=price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
