package com.xyd.susong.main.home;

import com.xyd.susong.store.StoreModel;

import java.util.List;

/**
 * @date: 2017/7/27
 * @time: 13:16
 * @description:
 */

public class HomeModelNew {


    private List<LunboBean> lunbo;
    private List<StoreModel.DataBean> tjgoods;
    private List<StoreModel.DataBean> tcgoods;
    private List<StoreModel.DataBean> thgoods;
    public List<LunboBean> getLunbo() {
        return lunbo;
    }

    public void setLunbo(List<LunboBean> lunbo) {
        this.lunbo = lunbo;
    }

    public List<StoreModel.DataBean> getTjgoods() {
        return tjgoods;
    }

    public void setTjgoods(List<StoreModel.DataBean> tjgoods) {
        this.tjgoods = tjgoods;
    }

    public List<StoreModel.DataBean> getTcgoods() {
        return tcgoods;
    }

    public void setTcgoods(List<StoreModel.DataBean> tcgoods) {
        this.tcgoods = tcgoods;
    }

    public List<StoreModel.DataBean> getThgoods() {
        return thgoods;
    }

    public void setThgoods(List<StoreModel.DataBean> thgoods) {
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
}
