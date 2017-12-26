package com.xyd.susong.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Zheng on 2017/12/21.
 */

public class MyRecycle extends RecyclerView {
    public MyRecycle(Context context) {
        super(context);
    }

    public MyRecycle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecycle(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
