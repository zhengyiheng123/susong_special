package com.xyd.susong.commitorder;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.susong.R;
import com.xyd.susong.glide.GlideUtil;
import com.xyd.susong.winedetail.WineModel;

import java.util.List;

/**
 * Created by Zheng on 2017/12/28.
 */

public class COmmitOrderAdapter extends BaseQuickAdapter<CommitModel.GoodsBean,BaseViewHolder> {
    Context context;
    public COmmitOrderAdapter(Context context,@Nullable List<CommitModel.GoodsBean> data) {
        super(R.layout.item_wine_order, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommitModel.GoodsBean item) {
        GlideUtil.getInstance().loadImage(context,(ImageView) helper.getView(R.id.commit_iv),item.getG_img(),false);
        helper.setText(R.id.commit_tv_title,item.getG_name());
        helper.setText(R.id.commit_tv_price,"￥"+item.getG_price());
        helper.setText(R.id.commit_tv_num,"x"+item.getNum());
    }

}
