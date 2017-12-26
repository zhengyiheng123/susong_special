package com.xyd.susong.order;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xyd.susong.R;
import com.xyd.susong.api.OrderApi;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseFragment;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.evaluate.EvaluateActivity;
import com.xyd.susong.orderdetail.OrderDetailActivity;
import com.xyd.susong.winedetail.WineDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/13
 * @time: 15:34
 * @description: 订单  完成
 */

public class OrderFinishFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener,
BaseQuickAdapter.OnItemClickListener{
    @Bind(R.id.order_rv)
    RecyclerView orderRv;
    @Bind(R.id.order_srl)
    SwipeRefreshLayout orderSrl;

    private List<OrderModel.MyOrderBean> list;
    private OrderAdapter adapter;
    private int page = 1, num = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }
    @Override
    protected void initView() {

        orderSrl.setColorSchemeColors(getResources().getColor(R.color.theme_color));
        orderRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        initAdapter();
        getData();

    }

    /**
     * 数据获取
     */
    private void getData() {

        BaseApi.getRetrofit()
                .create(OrderApi.class)
                .orders("2", page, num)
                .compose(RxSchedulers.<BaseModel<OrderModel>>compose())
                .subscribe(new BaseObserver<OrderModel>() {
                    @Override
                    protected void onHandleSuccess(OrderModel orderModel, String msg, int code) {
                        adapter.loadMoreComplete();
                        orderSrl.setRefreshing(false);
                        if (page == 1) {
                            adapter.setNewData(orderModel.getMy_order());
                        } else if (orderModel.getMy_order().size() > 0) {
                            adapter.addData(orderModel.getMy_order());
                        } else {
                            adapter.loadMoreEnd();
                        }

                    }

                    @Override
                    protected void onHandleError(String msg) {
                        orderSrl.setRefreshing(false);
                        adapter.loadMoreComplete();
                        showToast(msg);
                    }
                });
    }

    private void initAdapter() {

        list = new ArrayList<>();

        adapter = new OrderAdapter(list, getActivity());
        adapter.setOnLoadMoreListener(this, orderRv);
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.empty_view,null);
        adapter.setEmptyView(view);
//        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        orderRv.setAdapter(adapter);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnItemClickListener(this);
        adapter.setEnableLoadMore(true);
    }

    @Override
    protected void initEvent() {
        orderSrl.setOnRefreshListener(this);
    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onRefresh() {
        page = 1;
        getData();

    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        getData();

    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter1, View view, int position) {
        switch (view.getId()) {
            case R.id.order_tv_left:
                Bundle bundle=new Bundle();
                bundle.putSerializable(EvaluateActivity.EVALUATE, adapter.getData().get(position));
                startActivity(EvaluateActivity.class,bundle);
                break;
            case R.id.order_tv_right:
                Bundle bundle1=new Bundle();
                bundle1.putString(WineDetailActivity.G_ID,adapter.getData().get(position).getG_id()+"");
                startActivity(WineDetailActivity.class,bundle1);
                break;
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
        Bundle bundle=new Bundle();
        bundle.putString(OrderDetailActivity.ORDER_NUM,adapter.getData().get(position).getOrder_num());
        startActivity(OrderDetailActivity.class,bundle);
    }
}
