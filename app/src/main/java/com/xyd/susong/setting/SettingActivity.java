package com.xyd.susong.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.xyd.susong.R;
import com.xyd.susong.base.BaseActivity;
import com.xyd.susong.cookie.PersistentCookieStore;
import com.xyd.susong.login.LoginActivity;
import com.xyd.susong.modification.ModificationActivity;
import com.xyd.susong.permissions.PermissionsManager;
import com.xyd.susong.utils.AppUtils;
import com.xyd.susong.utils.CacheUtil;
import com.xyd.susong.utils.ToastUtils;

import butterknife.Bind;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/7
 * @time: 16:26
 * @description: 设置界面
 */

public class SettingActivity extends BaseActivity {
    @Bind(R.id.base_title_back)
    TextView baseTitleBack;
    @Bind(R.id.base_title_title)
    TextView baseTitleTitle;
    @Bind(R.id.base_title_menu)
    ImageView menu;
    @Bind(R.id.setting_tv_push)
    TextView settingTvPush;
    @Bind(R.id.setting_switch_push)
    Switch settingSwitchPush;
    @Bind(R.id.setting_tv_cache)
    TextView settingTvCache;
    @Bind(R.id.setting_switch_cache)
    TextView settingSwitchCache;
    @Bind(R.id.setting_tv_updata)
    TextView settingTvUpdata;
    @Bind(R.id.setting_tv_quit)
    TextView settingTvQuit;
    @Bind(R.id.setting_tv_code)
    TextView settingTvCode;
    @Bind(R.id.setting_tv_modification)
    TextView settingTvModificaation;
    private String cache;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


    @Override
    protected void initView() {
        menu.setVisibility(View.INVISIBLE);
        baseTitleTitle.setText("设置");
        AppUtils.getAppVersionCode(this);
        settingTvCode.setText("v" + AppUtils.getAppVersionName(this));
        try {
            cache = CacheUtil.getTotalCacheSize(getApplicationContext());
            settingSwitchCache.setText(cache);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initEvent() {
        settingSwitchCache.setOnClickListener(this);
        baseTitleBack.setOnClickListener(this);
        settingTvUpdata.setOnClickListener(this);
        settingTvQuit.setOnClickListener(this);
        settingTvModificaation.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.base_title_back:
                finish();
                break;
            case R.id.setting_tv_updata:
//                PermissionUtils.storage(this, new PermissionUtils.OnPermissionResult() {
//                    @Override
//                    public void onGranted() {
//                        VersionUpdateHelper helper = new VersionUpdateHelper(SettingActivity.this);
//                        helper.setShowDialogOnStart(true);
//                        helper.setToastInfo(true);
//                        VersionUpdateHelper.resetCancelFlag();
//                        helper.startUpdateVersion();
//                    }
//                });

                break;
            case R.id.setting_switch_cache:
                CacheUtil.clearAllCache(getApplicationContext());
                ToastUtils.show("缓存已清理");
                settingSwitchCache.setText(0+"k");
                break;
            case R.id.setting_tv_quit:
                // showTestToast("退出登陆");
                PersistentCookieStore store = new PersistentCookieStore(this);
                store.removeAll();
                closeApp();
                startActivity(LoginActivity.class);
                break;
            case R.id.setting_tv_modification:
                startActivity(ModificationActivity.class);
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsManager.getInstance().notifyPermissionsChange(permissions,grantResults);
    }
}
