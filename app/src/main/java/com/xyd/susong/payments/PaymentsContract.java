package com.xyd.susong.payments;

import com.xyd.susong.base.BasePresenter;
import com.xyd.susong.base.BaseView;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/20
 * @time: 15:39
 * @description:
 */

public interface PaymentsContract {

    interface View extends BaseView<Presenter>{
        void refreshData(PaymentsModel model);

        /**
         *
         * @param model
         * @param type  1 有数据 2无更多数据
         */
        void loadMoreData(PaymentsModel model,int type);
        void error(String msg);


    }


    interface Presenter extends BasePresenter{
        void getData(int page,int num);
    }



}
