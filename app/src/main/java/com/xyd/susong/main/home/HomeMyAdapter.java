package com.xyd.susong.main.home;

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
 * @date: 2017/9/7
 * @time: 10:36
 * @description:
 */

public class HomeMyAdapter extends BaseQuickAdapter<HomeModelNew.TcgoodsBean,BaseViewHolder>{
    private Context context;

    public HomeMyAdapter(@Nullable List<HomeModelNew.TcgoodsBean> data, Context context) {
        super(R.layout.home_mylistview_item, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeModelNew.TcgoodsBean item) {
        GlideUtil.getInstance()
                .loadImage(context, (SmartImageveiw) helper.getView(R.id.home_my_iv), PublicStaticData.baseUrl + item.getG_img(), true);
//        helper.setText(R.id.home_my_name_tv,item.getG_name());
//        helper.setText(R.id.home_my_place_tv,item.getG_sname());
//        helper.setText(R.id.home_my_content_tv,item.getG_price()+"元");
//        helper.setText(R.id.home_my_concentration_tv,item.getG_kind());
        ((SmartImageveiw) helper.getView(R.id.home_my_iv)).setRatio(2.0f);
    }
}
