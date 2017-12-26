package com.xyd.susong.activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.susong.R;
import com.xyd.susong.base.PublicStaticData;
import com.xyd.susong.glide.GlideUtil;
import com.xyd.susong.view.ProgressView;

import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/13
 * @time: 12:00
 * @description:
 */

public class BenefitAdapter extends BaseQuickAdapter<BenefitModel.ActiveBean, BaseViewHolder> {
    private Context context;

    public BenefitAdapter(@Nullable List<BenefitModel.ActiveBean> data, Context context) {
        super(R.layout.item_activity, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BenefitModel.ActiveBean item) {
        GlideUtil.getInstance()
                .loadImage(context, (ImageView) helper.getView(R.id.item_activity_iv), PublicStaticData.baseUrl + item.getAc_img(), true);
        helper.setText(R.id.item_activity_title, item.getAc_title());
        helper.setText(R.id.item_activity_content, item.getAc_slogan());
        helper.setText(R.id.item_activity_tv_progress, "目前筹集" + item.getNow_num());
        helper.setText(R.id.item_activity_tv_num, item.getHave_support() + "人");
        helper.setText(R.id.item_activity_tv_num1, item.getSupport_num() + "人");
        if (item.getAc_state() == 1)
            helper.setText(R.id.item_activity_tv_state, "成功");
        else
            helper.setText(R.id.item_activity_tv_state, "筹集中");
        ProgressView view = (ProgressView) (helper.getView(R.id.item_activity_progress));
        if (item.getNow_num() > item.getAll_num())
            view.setProgress((float) 1);
        else
            view.setProgress((float) (item.getNow_num() / item.getAll_num()));

    }
}
