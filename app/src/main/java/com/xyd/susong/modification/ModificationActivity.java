package com.xyd.susong.modification;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyd.susong.R;
import com.xyd.susong.api.LoginApi;
import com.xyd.susong.base.BaseActivity;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.login.ChangePasswodModel;
import com.xyd.susong.utils.ToastUtils;

import butterknife.Bind;

public class ModificationActivity extends BaseActivity {
    @Bind(R.id.oldet)
    EditText mOldET;
    @Bind(R.id.newet)
    EditText mNewET;
    @Bind(R.id.defineet)
    EditText mDefineET;
    @Bind(R.id.countersigntv)
    TextView mCountersignTV;
    @Bind(R.id.base_title_back)
    TextView mBaseTitileBack;
    @Bind(R.id.base_title_title)
    TextView mBaseTitle;
    @Bind(R.id.base_title_menu)
    ImageView mBaseTitleMenu;
    private String mOldString;
    private String mNewString;
    private String mDefineString;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modification;
    }

    @Override
    protected void initView() {
        mBaseTitle.setText("修改密码");
        mBaseTitleMenu.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initEvent() {
        mCountersignTV.setOnClickListener(this);
        mBaseTitileBack.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.countersigntv:
                getData();
                break;
            case R.id.base_title_back:
                finish();
                break;
        }
    }
    public void getData(){
        mOldString = mOldET.getText().toString();
        mNewString = mNewET.getText().toString();
        mDefineString = mDefineET.getText().toString();
        if (mOldString.length()<6) {
            ToastUtils.show("密码不能少于六位");
            return;
        }
        if (mNewString.length()<6) {
            ToastUtils.show("新密码不能少于六位");
            return;
        }
        if (mDefineString.length()<6) {
            ToastUtils.show("新密码不能少于六位");
            return;
        }
        if (mNewString.equals(mDefineString)) {
            ToastUtils.show("两次新密码输入不一致");
            return;
        }
        if (mOldString.equals(mNewString)) {
            ToastUtils.show("新密码与旧密码不能相同");
        }
        BaseApi.getRetrofit()
                .create(LoginApi.class)
                .getModification(mOldString,mNewString,mDefineString)
                .compose(RxSchedulers.<BaseModel<ChangePasswodModel>>compose())
                .subscribe(new BaseObserver<ChangePasswodModel>() {
                    @Override
                    protected void onHandleSuccess(ChangePasswodModel emptyModel, String msg, int code) {
                        ToastUtils.show(msg);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                    }
                });
    }
}
