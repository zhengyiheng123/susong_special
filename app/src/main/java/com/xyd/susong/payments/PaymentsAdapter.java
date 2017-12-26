package com.xyd.susong.payments;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.susong.R;
import com.xyd.susong.base.PublicStaticData;
import com.xyd.susong.glide.GlideUtil;
import com.xyd.susong.utils.TimeUtils;

import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/11
 * @time: 10:30
 * @description:
 */

public class PaymentsAdapter extends BaseQuickAdapter<PaymentsModel.WelfareListBean, BaseViewHolder> {
    private Context context;
    public PaymentsAdapter(@Nullable List<PaymentsModel.WelfareListBean> data, Context context) {
        super(R.layout.item_payments, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PaymentsModel.WelfareListBean item) {
        GlideUtil.getInstance().loadImage(context, (ImageView) helper.getView(R.id.payments_iv),
                PublicStaticData.baseUrl+item.getG_img(),false);
        helper.setText(R.id.payments_item_tv_price,"+"+item.getG_price()+"x1%");
        helper.setText(R.id.payments_tv_title,item.getG_name());
        helper.setText(R.id.payments_tv_title1,item.getG_sname());
        helper.setText(R.id.payments_tv_add,"+"+item.getDonate_money());
        long  time= Long.parseLong(item.getCreate_time());
        helper.setText(R.id.payments_tv_time, TimeUtils.millis2String(time*1000,"yyyy年MM月dd日"));
    }
}
