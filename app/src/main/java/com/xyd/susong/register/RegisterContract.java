package com.xyd.susong.register;

import com.xyd.susong.base.BasePresenter;
import com.xyd.susong.base.BaseView;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/18
 * @time: 17:01
 * @description:
 */

public interface RegisterContract {

    interface View extends BaseView<Presenter> {
        void showError(String msg);
        void success();
        void showDialog();
        void closeDialog();
        void downTime();
    }

    interface Presenter extends BasePresenter {
        void getCode(String phone);
        void register(String phone, String password, String commit, String recommendCode, String code, boolean agreement);

    }
}
