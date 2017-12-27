package com.xyd.susong.mall;

import com.xyd.susong.base.BasePresenter;
import com.xyd.susong.base.BaseView;
import com.xyd.susong.store.StoreModel;

/**
 * Created by Zheng on 2017/12/27.
 */

public interface MallContract {
     interface View{
        void refreshData(StoreModel model);
//         1 有数据 2无更多数据
        void loadMoreData(StoreModel model,int type);
         void error(String msg);
    }
    interface Presenter{
        void getData(int page,int type);
    }
}
