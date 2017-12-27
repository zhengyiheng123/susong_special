package com.xyd.susong.main.service;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.xyd.susong.R;
import com.xyd.susong.activity.ActivityActivity;
import com.xyd.susong.api.GeneralizeApi;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseFragment;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.generalize.GeneralizeActivity;
import com.xyd.susong.generalize.GeneralizeModel;
import com.xyd.susong.main.home.ShangchengActivity;
import com.xyd.susong.promptdialog.PromptDialog;
import com.xyd.susong.store.StoreActivity;
import com.xyd.susong.suggest.SuggestActivity;
import com.xyd.susong.winesteward.StewardActivity;

import butterknife.Bind;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/12
 * @time: 15:06
 * @description: 服务
 */

public class ServiceFragment extends BaseFragment {

    @Bind(R.id.service_iv_steward)
    ImageView serviceIvSteward;
    @Bind(R.id.service_iv_generalize)
    ImageView serviceIvGeneralize;
    @Bind(R.id.service_iv_market)
    ImageView serviceIvMarket;
    @Bind(R.id.service_iv_service)
    ImageView serviceIvService;
//    @Bind(R.id.service_iv_primeur)
//    ImageView serviceIvPrimeur;
    @Bind(R.id.service_iv_activity)
    ImageView serviceIvActivity;
    private PromptDialog dialog;
    private int screenWidth;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service;
    }

    @Override
    protected void initView() {
        dialog = new PromptDialog(getActivity());
//        DisplayMetrics dm = getResources().getDisplayMetrics();
//        screenWidth = dm.widthPixels;
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth, (int) (screenWidth * 0.32219251));
//        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(screenWidth / 2, (int) (screenWidth * 0.32219251));
//        params.topMargin = -24;
//        serviceIvSteward.setLayoutParams(params);
//        serviceIvGeneralize.setLayoutParams(params);
//        serviceIvMarket.setLayoutParams(params);
//        serviceIvService.setLayoutParams(params);
//        serviceIvActivity.setLayoutParams(params1);
//        serviceIvPrimeur.setLayoutParams(params1);


    }

    @Override
    protected void initEvent() {
        serviceIvActivity.setOnClickListener(this);
        serviceIvSteward.setOnClickListener(this);
        serviceIvGeneralize.setOnClickListener(this);
        serviceIvMarket.setOnClickListener(this);
        serviceIvService.setOnClickListener(this);
//        serviceIvPrimeur.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.service_iv_steward:
                startActivity(StewardActivity.class);
                break;
            case R.id.service_iv_generalize:
                dialog.showLoading("");
                getData();
                break;
            case R.id.service_iv_market:
                Bundle bundle=new Bundle();
                bundle.putString(ShangchengActivity.TITLE,"商城");
                bundle.putInt(ShangchengActivity.TYPE,0);
                startActivity(ShangchengActivity.class,bundle);
                break;
            case R.id.service_iv_service:
                startActivity(SuggestActivity.class);
                break;
//            case R.id.service_iv_primeur:
//                startActivity(PrimeurActivity.class);
//                break;
            case R.id.service_iv_activity:
                startActivity(ActivityActivity.class);
                break;
        }

    }

    /**
     * 购买商品后才能分享
     */
    private void getData() {
        BaseApi.getRetrofit()
                .create(GeneralizeApi.class)
                .generalize()
                .compose(RxSchedulers.<BaseModel<GeneralizeModel>>compose())
                .subscribe(new BaseObserver<GeneralizeModel>() {
                    @Override
                    protected void onHandleSuccess(GeneralizeModel generalizeModel, String msg, int code) {
                        dialog.dismissImmediately();
                        startActivity(GeneralizeActivity.class);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        dialog.dismissImmediately();
                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setMessage("您需购买商品后才能拥有分享二维码！");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    }
                });
    }
}
