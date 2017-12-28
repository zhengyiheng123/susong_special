package com.xyd.susong.mall;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xyd.susong.R;
import com.xyd.susong.base.BaseFragment;
import com.xyd.susong.promptdialog.PromptDialog;
import com.xyd.susong.store.StoreModel;
import com.xyd.susong.utils.*;
import com.xyd.susong.utils.DividerGridItemDecoration;
import com.xyd.susong.winedetail.WineDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Zheng on 2017/12/19.
 */

public class MallFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener,MallContract.View,BaseQuickAdapter.OnItemChildClickListener{
    @Bind(R.id.tv_local)
    TextView tv_local;
    @Bind(R.id.tv_years)
    TextView tv_years;
    @Bind(R.id.products_rv)
    RecyclerView products_rv;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    private List<StoreModel.DataBean> list;
    private MallAdapter adapter;
    private MallPresenter presenter;
    //页码
    private int page=1;
    //商品类型 1：推荐 2：特产 3：特惠
    private int type = 2;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall;
    }

    @Override
    protected void initView() {
        presenter = new MallPresenter(this);
        presenter.getData(page,type);
        refresh.setColorSchemeColors(getResources().getColor(R.color.theme_color));
        refresh.setOnRefreshListener(this);
        products_rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        initAdapter();
    }

    private void initAdapter() {
        list = new ArrayList<>();
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.empty_no_goods,products_rv,false);
        adapter = new MallAdapter(list,getActivity());
        adapter.setOnLoadMoreListener(this,products_rv);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapte1r, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString(WineDetailActivity.G_ID,adapter.getData().get(position).getG_id()+"");
                startActivity(WineDetailActivity.class,bundle);
            }
        });
        adapter.setEmptyView(view);
        adapter.setOnItemChildClickListener(this);
        products_rv.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        tv_local.setOnClickListener(this);
        tv_years.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            case R.id.tv_local:
                type =2;
                page=1;
                tv_local.setBackgroundResource(R.drawable.bg_mall_title);
                tv_local.setTextColor(getResources().getColor(R.color.theme_color));
                tv_years.setBackgroundColor(getResources().getColor(R.color.material_white));
                tv_years.setTextColor(getResources().getColor(R.color.material_textBlack_text));
                presenter.getData(page,type);
                break;
            case R.id.tv_years:
                type=3;
                tv_years.setTextColor(getResources().getColor(R.color.theme_color));
                tv_local.setBackgroundColor(getResources().getColor(R.color.material_white));
                tv_local.setTextColor(getResources().getColor(R.color.material_textBlack_text));
                tv_years.setBackgroundResource(R.drawable.bg_mall_title);
                presenter.getData(page,type);
                break;
        }
    }

    @Override
    public void onRefresh() {
        page =1;
        presenter.getData(page,type);
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        presenter.getData(page,type);
    }

    @Override
    public void refreshData(StoreModel model) {
        adapter.setNewData(model.getData());
        refresh.setRefreshing(false);
        adapter.setEnableLoadMore(true);
    }

    @Override
    public void loadMoreData(StoreModel model, int type) {
            if (type ==1){
                adapter.addData(model.getData());
                adapter.loadMoreComplete();
            }else {
                adapter.loadMoreEnd();
            }
    }

    @Override
    public void error(String msg) {

    }
    //添加入购物车
    private void addShopChart(String g_id){
        presenter.edit("add",g_id,"");
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter1, View view, int position) {
        switch (view.getId()){
            case R.id.iv_shopchart:
                addShopChart(adapter.getData().get(position).getG_id()+"");
                break;
        }
    }
}
