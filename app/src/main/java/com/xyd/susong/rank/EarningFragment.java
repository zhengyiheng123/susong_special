package com.xyd.susong.rank;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xyd.susong.R;
import com.xyd.susong.api.MineApi;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseFragment;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/13
 * @time: 11:56
 * @description: 收益排行榜
 */

public class EarningFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.base_rv)
    RecyclerView baseRv;
    @Bind(R.id.base_srl)
    SwipeRefreshLayout baseSrl;
    private EarningAdapter adapter;
    private List<RankModel.RevenueBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.base_srl_rv;
    }

    @Override
    protected void initView() {

        baseSrl.setColorSchemeColors(getResources().getColor(R.color.theme_color));
        baseRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        initAdapter();
        getData();

    }

    private void getData() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .rank()
                .compose(RxSchedulers.<BaseModel<RankModel>>compose())
                .subscribe(new BaseObserver<RankModel>() {
                    @Override
                    protected void onHandleSuccess(RankModel rankModel, String msg, int code) {
                        adapter.setNewData(rankModel.getRevenue());
                        baseSrl.setRefreshing(false);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        baseSrl.setRefreshing(false);
                        showToast(msg);
                    }
                });
    }
    private void initAdapter() {

        list = new ArrayList<>();
        adapter = new EarningAdapter(list, getActivity());
//        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        baseRv.setAdapter(adapter);
        adapter.setEnableLoadMore(true);
    }

    @Override
    protected void initEvent() {
        baseSrl.setOnRefreshListener(this);
    }

    @Override
    public void widgetClick(View v) {

    }

    /**
     * SwipeRefreshLayout
     */
    @Override
    public void onRefresh() {
        getData();
    }


}
