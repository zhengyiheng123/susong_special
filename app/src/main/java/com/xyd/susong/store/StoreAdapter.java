package com.xyd.susong.store;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.susong.R;
import com.xyd.susong.base.PublicStaticData;
import com.xyd.susong.glide.GlideUtil;
import com.xyd.susong.view.SmartImageveiw;

import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/13
 * @time: 14:27
 * @description:
 */

public class StoreAdapter extends BaseQuickAdapter<StoreModel.DataBean,BaseViewHolder>{
    private Context context;
    public StoreAdapter(@Nullable List<StoreModel.DataBean> data, Context context) {
        super(R.layout.item_store, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreModel.DataBean item) {
        GlideUtil.getInstance().loadImage(context,(SmartImageveiw) helper.getView(R.id.store_iv), PublicStaticData.baseUrl+item.getG_img(),true);
        helper.setText(R.id.store_tv_title,item.getG_name());
        helper.setText(R.id.store_tv_title1,item.getG_name());
        helper.setText(R.id.store_tv_price,"￥"+item.getG_price());
        ((SmartImageveiw) helper.getView(R.id.store_iv)).setRatio(2.0f);
    }
}
