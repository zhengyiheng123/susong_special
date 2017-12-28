package com.xyd.susong.shopchart;

import com.xyd.susong.api.ShopChartApi;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.BaseObserverLoading;
import com.xyd.susong.base.EmptyModel;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.utils.ToastUtils;

/**
 * Created by Zheng on 2017/12/28.
 */

public class ShopChartPresenter implements ShopChartContract.Presenter  {
    private ShopChartContract.View view;

    public ShopChartPresenter(ShopChartContract.View view) {
        this.view = view;
    }

    @Override
    public void getData() {
        BaseApi.getRetrofit().create(ShopChartApi.class)
                .getDataList()
                .compose(RxSchedulers.<BaseModel<ChartModel>>compose())
                .subscribe(new BaseObserver<ChartModel>() {
                    @Override
                    protected void onHandleSuccess(ChartModel chartModel, String msg, int code) {
                        view.refreshData(chartModel);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        view.error(msg);
                    }
                });
    }
    //编辑购物车操作
    public void edit(final String type, String g_id, String  num){
        BaseApi.getRetrofit().create(ShopChartApi.class)
                .edit(type,g_id,num)
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, String msg, int code) {
//                        ToastUtils.show(msg);
                        view.editSuccess(type);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                    }
                });
    }
}
