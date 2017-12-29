package com.xyd.susong.commitorder;

import java.util.List;

/**
 * Created by Zheng on 2017/12/29.
 */

public class CommitModel {

    /**
     * goods : [{"g_id":15,"g_name":"香干丝","g_price":"20.00","g_img":"http://shop.cinyida.com/uploads/20171225/82480397afc67ed730c8b86d3ae86d23.png","g_freight":"0.00","num":"2"}]
     * price : 40.00
     * freight : 0.00
     */

    private String price;
    private double freight;
    private List<GoodsBean> goods;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean {
        /**
         * g_id : 15
         * g_name : 香干丝
         * g_price : 20.00
         * g_img : http://shop.cinyida.com/uploads/20171225/82480397afc67ed730c8b86d3ae86d23.png
         * g_freight : 0.00
         * num : 2
         */

        private int g_id;
        private String g_name;
        private String g_price;
        private String g_img;
        private String g_freight;
        private String num;

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

        public String getG_price() {
            return g_price;
        }

        public void setG_price(String g_price) {
            this.g_price = g_price;
        }

        public String getG_img() {
            return g_img;
        }

        public void setG_img(String g_img) {
            this.g_img = g_img;
        }

        public String getG_freight() {
            return g_freight;
        }

        public void setG_freight(String g_freight) {
            this.g_freight = g_freight;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
