package com.xyd.susong.store;

import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/13
 * @time: 14:27
 * @description:
 */

public class StoreModel {


    /**
     * total : 16
     * per_page : 10
     * current_page : 1
     * data : [{"g_id":4,"g_img":"http://shop.cinyida.com/uploads/20171225/945f37e35b277c560b3ca9cc4f9192df.png","g_price":"50.00","g_name":"红烧小鱼干"},{"g_id":24,"g_img":"http://shop.cinyida.com/uploads/20171225/01b66971f238c7cdf25a2773a20cc7c9.png","g_price":"100.00","g_name":"荷叶茶"},{"g_id":5,"g_img":"http://shop.cinyida.com/uploads/20171222/14da4478e6ba2b6c2285a060f6d4a165.png","g_price":"25.00","g_name":"长沙臭豆腐"},{"g_id":6,"g_img":"http://shop.cinyida.com/uploads/20171222/f3f96dced35de02a16ff40941999852c.png","g_price":"200.00","g_name":"鸡蛋卷"},{"g_id":7,"g_img":"http://shop.cinyida.com/uploads/20171222/41e5d8da27cb596bf2f4130b1a884668.png","g_price":"32.00","g_name":"韭菜花"},{"g_id":13,"g_img":"http://shop.cinyida.com/uploads/20171225/ff2be6866e09d5acf970124c83c38887.png","g_price":"36.00","g_name":"草莓"},{"g_id":14,"g_img":"http://shop.cinyida.com/uploads/20171225/8e8a1f16135cbc1f19c7a53e7e5eb622.png","g_price":"10.00","g_name":"芝麻烧饼"},{"g_id":15,"g_img":"http://shop.cinyida.com/uploads/20171225/82480397afc67ed730c8b86d3ae86d23.png","g_price":"20.00","g_name":"香干丝"},{"g_id":16,"g_img":"http://shop.cinyida.com/uploads/20171225/d4c15099bffce99b9046cb42d9d42eac.png","g_price":"200.00","g_name":"春卷"},{"g_id":17,"g_img":"http://shop.cinyida.com/uploads/20171225/fe69293935af5f14dd6aad8a365a9379.png","g_price":"100.00","g_name":"盛开的韭菜花"}]
     */

    private int total;
    private int per_page;
    private String current_page;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * g_id : 4
         * g_img : http://shop.cinyida.com/uploads/20171225/945f37e35b277c560b3ca9cc4f9192df.png
         * g_price : 50.00
         * g_name : 红烧小鱼干
         */

        private int g_id;
        private String g_img;
        private String g_price;
        private String g_name;

        public int getG_id() {
            return g_id;
        }

        public void setG_id(int g_id) {
            this.g_id = g_id;
        }

        public String getG_img() {
            return g_img;
        }

        public void setG_img(String g_img) {
            this.g_img = g_img;
        }

        public String getG_price() {
            return g_price;
        }

        public void setG_price(String g_price) {
            this.g_price = g_price;
        }

        public String getG_name() {
            return g_name;
        }

        public void setG_name(String g_name) {
            this.g_name = g_name;
        }
    }
}
