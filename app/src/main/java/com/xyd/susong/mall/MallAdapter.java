package com.xyd.susong.mall;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.susong.R;
import com.xyd.susong.glide.GlideUtil;
import com.xyd.susong.main.home.HomeModelNew;
import com.xyd.susong.store.StoreModel;
import com.xyd.susong.view.SmartImageveiw;

import java.util.List;

/**
 * Created by Zheng on 2017/12/19.
 */

public class MallAdapter extends BaseQuickAdapter<StoreModel.DataBean,BaseViewHolder> {
    private Context context;
    public MallAdapter(@Nullable List<StoreModel.DataBean> data, Context context) {
        super(R.layout.item_mall, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreModel.DataBean item) {
        ((SmartImageveiw) helper.getView(R.id.goods_img)).setRatio(1.7f);
        GlideUtil.getInstance().loadImage(context,(SmartImageveiw)helper.getView(R.id.goods_img),item.getG_img(),true);
        helper.setText(R.id.tv_goods_name,item.getG_name());
        helper.setText(R.id.tv_price,"￥"+item.getG_price());
        helper.addOnClickListener(R.id.iv_shopchart);
    }
}
