package com.xyd.susong.shopchart;


import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.susong.MyApplication;
import com.xyd.susong.R;
import com.xyd.susong.glide.GlideUtil;
import com.xyd.susong.view.SmartImageveiw;

import java.util.List;

/**
 * Created by Zheng on 2017/12/19.
 */

public class ChartADapter extends BaseQuickAdapter<ChartModel.ContentBean, BaseViewHolder>{


    public ChartADapter(@Nullable List<ChartModel.ContentBean> data) {
        super(R.layout.item_shop_chart, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChartModel.ContentBean item) {
        helper.addOnClickListener(R.id.iv_release);
        helper.addOnClickListener(R.id.iv_add);
        helper.addOnClickListener(R.id.cb_check);
        helper.setChecked(R.id.cb_check,item.isChecked() ? true : false);
        helper.setText(R.id.et_num,item.getNum()+"");
        helper.setText(R.id.tv_price,"￥"+item.getG_price());
        helper.setText(R.id.tv_title,item.getG_name());
        GlideUtil.getInstance().loadImage(MyApplication.getContext(),(ImageView)helper.getView(R.id.iv_img),item.getG_img(),false);
        helper.addOnClickListener(R.id.iv_delete);
    }
}
