package com.xyd.susong.main.home;

import java.util.List;

/**
 * @date: 2017/7/27
 * @time: 13:16
 * @description:
 */

public class HomeModelNew {


    private List<LunboBean> lunbo;
    private List<TjgoodsBean> tjgoods;
    private List<TcgoodsBean> tcgoods;
    private List<ThgoodsBean> thgoods;
    public List<LunboBean> getLunbo() {
        return lunbo;
    }

    public void setLunbo(List<LunboBean> lunbo) {
        this.lunbo = lunbo;
    }

    public List<TjgoodsBean> getTjgoods() {
        return tjgoods;
    }

    public void setTjgoods(List<TjgoodsBean> tjgoods) {
        this.tjgoods = tjgoods;
    }

    public List<TcgoodsBean> getTcgoods() {
        return tcgoods;
    }

    public void setTcgoods(List<TcgoodsBean> tcgoods) {
        this.tcgoods = tcgoods;
    }

    public List<ThgoodsBean> getThgoods() {
        return thgoods;
    }

    public void setThgoods(List<ThgoodsBean> thgoods) {
        this.thgoods = thgoods;
    }

    public static class LunboBean {
        /**
         * adv_id : 17
         * adv_imgs : http://shop.cinyida.com/uploads/20171225/ddab2aa799f4aeaeb3ed52fff2a9c822.png
         * adv_url : http://www.taobao.com
         * goods_id : 0
         */
        private int adv_id;
        private String adv_imgs;
        private String adv_url;
        private int goods_id;//当goods_id=0 代表是网页  不等于0代表是商品

        public int getAdv_id() {
            return adv_id;
        }

        public void setAdv_id(int adv_id) {
            this.adv_id = adv_id;
        }

        public String getAdv_imgs() {
            return adv_imgs;
        }

        public void setAdv_imgs(String adv_imgs) {
            this.adv_imgs = adv_imgs;
        }

        public String getAdv_url() {
            return adv_url;
        }

        public void setAdv_url(String adv_url) {
            this.adv_url = adv_url;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }
    }

    public static class TjgoodsBean extends GoodsModel {

    }

    public static class TcgoodsBean extends GoodsModel {

    }

    public static class ThgoodsBean extends GoodsModel {

    }
}
