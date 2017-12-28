package com.xyd.susong.winedetail;

import java.io.Serializable;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/20
 * @time: 10:44
 * @description:
 */

public class WineModel implements Serializable{


    /**
     * good : {"g_id":4,"g_name":"红烧小鱼干","g_freight":"5.00","g_price":"50.00","g_num":200,"g_img":"http://shop.cinyida.com/uploads/20171225/945f37e35b277c560b3ca9cc4f9192df.png"}
     * g_con : http://shop.cinyida.com/index/detail/goods/g_id/4
     */

    private GoodBean good;
    private String g_con;

    public GoodBean getGood() {
        return good;
    }

    public void setGood(GoodBean good) {
        this.good = good;
    }

    public String getG_con() {
        return g_con;
    }

    public void setG_con(String g_con) {
        this.g_con = g_con;
    }

    public static class GoodBean implements Serializable{
        /**
         * g_id : 4
         * g_name : 红烧小鱼干
         * g_freight : 5.00
         * g_price : 50.00
         * g_num : 200
         * g_img : http://shop.cinyida.com/uploads/20171225/945f37e35b277c560b3ca9cc4f9192df.png
         */
        private int goods_num;
        private int g_id;
        private String g_name;
        private String g_freight;
        private double g_price;
        private int g_num;
        private String g_img;

        public int getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(int goods_num) {
            this.goods_num = goods_num;
        }

        public int getG_id() {
            return g_id;
        }

        public void setG_id(int g_id) {
            this.g_id = g_id;
        }

        public String getG_name() {
            return g_name;
        }

        public void setG_name(String g_name) {
            this.g_name = g_name;
        }

        public String getG_freight() {
            return g_freight;
        }

        public void setG_freight(String g_freight) {
            this.g_freight = g_freight;
        }

        public double getG_price() {
            return g_price;
        }

        public void setG_price(double g_price) {
            this.g_price = g_price;
        }

        public int getG_num() {
            return g_num;
        }

        public void setG_num(int g_num) {
            this.g_num = g_num;
        }

        public String getG_img() {
            return g_img;
        }

        public void setG_img(String g_img) {
            this.g_img = g_img;
        }
    }
}
