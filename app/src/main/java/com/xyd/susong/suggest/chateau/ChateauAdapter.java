package com.xyd.susong.suggest.chateau;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.susong.R;
import com.xyd.susong.base.PublicStaticData;
import com.xyd.susong.glide.GlideUtil;

import java.util.List;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/14
 * @time: 13:51
 * @description:
 */

public class ChateauAdapter extends BaseQuickAdapter<ChateauModel.ArticlesBean, BaseViewHolder> {
    private Context context;

    public ChateauAdapter(@Nullable List<ChateauModel.ArticlesBean> data, Context context) {
        super(R.layout.item_chateau, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ChateauModel.ArticlesBean item) {
        if (item.getA_img().size() > 0)
            GlideUtil.getInstance()
                    .loadImage(context, (ImageView) helper.getView(R.id.chateau_iv), PublicStaticData.baseUrl + item.getA_img().get(0), true);
        helper.setText(R.id.chateau_tv_title, item.getA_title());
        helper.setText(R.id.chateau_tv_title1, item.getA_stitle());
        helper.setText(R.id.chateau_tv_introduction, item.getA_title());

    }
}
