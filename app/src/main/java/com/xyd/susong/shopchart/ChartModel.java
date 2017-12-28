package com.xyd.susong.shopchart;

import java.util.List;

/**
 * Created by Zheng on 2017/12/19.
 */

public class ChartModel {
    /**
     * content : [{"g_id":4,"g_name":"红烧小鱼干","g_price":"50.00","g_img":"http://shop.cinyida.com/uploads/20171225/945f37e35b277c560b3ca9cc4f9192df.png","num":1},{"g_id":7,"g_name":"韭菜花","g_price":"32.00","g_img":"http://shop.cinyida.com/uploads/20171222/41e5d8da27cb596bf2f4130b1a884668.png","num":3}]
     * total_num : 4
     * total_price : 146.00
     */

    private int total_num;
    private String total_price;
    private List<ContentBean> content;

    public int getTotal_num() {
        return total_num;
    }

    public void setTotal_num(int total_num) {
        this.total_num = total_num;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * g_id : 4
         * g_name : 红烧小鱼干
         * g_price : 50.00
         * g_img : http://shop.cinyida.com/uploads/20171225/945f37e35b277c560b3ca9cc4f9192df.png
         * num : 1
         */
        private boolean checked;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }
        private int g_id;
        private String g_name;
        private double g_price;
        private String g_img;
        private int num;

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

        public double getG_price() {
            return g_price;
        }

        public void setG_price(double g_price) {
            this.g_price = g_price;
        }

        public String getG_img() {
            return g_img;
        }

        public void setG_img(String g_img) {
            this.g_img = g_img;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
