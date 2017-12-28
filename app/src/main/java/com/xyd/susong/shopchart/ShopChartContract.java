package com.xyd.susong.shopchart;

import com.xyd.susong.base.BasePresenter;

/**
 * Created by Zheng on 2017/12/28.
 */

public interface ShopChartContract {
    interface View{

        void refreshData(ChartModel chartModel);

        void error(String msg);

        void editSuccess(String type);
    }
    interface Presenter{
        void getData();
    }
}
