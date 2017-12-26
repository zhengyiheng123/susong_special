package com.xyd.susong.shopchart;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.susong.R;

import java.util.List;

/**
 * Created by Zheng on 2017/12/19.
 */

public class ChartADapter extends BaseQuickAdapter<ChartModel, BaseViewHolder>{


    public ChartADapter(@Nullable List<ChartModel> data) {
        super(R.layout.item_shop_chart, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChartModel item) {
        helper.addOnClickListener(R.id.iv_release);
        helper.addOnClickListener(R.id.iv_add);
        helper.addOnClickListener(R.id.cb_check);
        helper.setChecked(R.id.cb_check,item.isChecked() ? true : false);
        helper.setText(R.id.et_num,item.getNum()+"");
        helper.setText(R.id.tv_price,"￥"+item.getPrice());
        helper.setText(R.id.tv_title,item.getGoodsName());
    }
}
