package com.xyd.susong.forgetpassword;

import com.xyd.susong.base.BasePresenter;
import com.xyd.susong.base.BaseView;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/19
 * @time: 16:55
 * @description:
 */

public interface ForgetPasswordContract {

    interface View extends BaseView<Presenter>{
        void error(String msg);
        void success();
        void showDialog();
        void closeDialog();
        void downTime();
    }

    interface Presenter extends BasePresenter{
        void code(String phone);
        void editPassword(String phone,String password,String code);
    }
}
