package com.xyd.susong.winedetail;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/14
 * @time: 16:40
 * @description:
 */

public class CustomViewPager extends ViewPager {
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        // 返回false
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        // 返回false
        return false;
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }


    // 防止viewpager跟scrollview冲突
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        boolean ret = super.dispatchTouchEvent(ev);
        if(ret)
        {
            requestDisallowInterceptTouchEvent(true);
        }
        return ret;
    }
}
