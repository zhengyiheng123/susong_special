package com.xyd.susong.view_img;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xyd.susong.R;
import com.xyd.susong.glide.GlideUtil;

import java.util.List;

/**
 * Created by Zheng on 2017/9/19.
 */

public class ImageViewAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private Context context;
    public ImageViewAdapter(@Nullable List<String> data, Context context) {
        super(R.layout.item_img, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        GlideUtil.getInstance().loadImage(context,(ImageView) helper.getView(R.id.iv_item), item,true);
    }
}
