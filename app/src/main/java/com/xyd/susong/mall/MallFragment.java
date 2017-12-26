package com.xyd.susong.mall;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xyd.susong.R;
import com.xyd.susong.base.BaseFragment;
import com.xyd.susong.main.home.GoodsModel;
import com.xyd.susong.store.StoreModel;
import com.xyd.susong.utils.*;
import com.xyd.susong.utils.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Zheng on 2017/12/19.
 */

public class MallFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{
    @Bind(R.id.tv_local)
    TextView tv_local;
    @Bind(R.id.tv_years)
    TextView tv_years;
    @Bind(R.id.products_rv)
    RecyclerView products_rv;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    private List<GoodsModel> list;
    private MallAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mall;
    }

    @Override
    protected void initView() {
        refresh.setColorSchemeColors(getResources().getColor(R.color.theme_color));
        refresh.setOnRefreshListener(this);
        products_rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        initAdapter();
    }

    private void initAdapter() {
        list = new ArrayList<>();
        list.add(new GoodsModel());
        list.add(new GoodsModel());
        list.add(new GoodsModel());
        list.add(new GoodsModel());
        list.add(new GoodsModel());
        list.add(new GoodsModel());
        adapter = new MallAdapter(list,getActivity());
        adapter.setOnLoadMoreListener(this,products_rv);
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
                tv_local.setBackgroundResource(R.drawable.bg_mall_title);
                tv_local.setTextColor(getResources().getColor(R.color.theme_color));
                tv_years.setBackgroundColor(getResources().getColor(R.color.material_white));
                tv_years.setTextColor(getResources().getColor(R.color.material_textBlack_text));
                break;
            case R.id.tv_years:
                tv_years.setTextColor(getResources().getColor(R.color.theme_color));
                tv_local.setBackgroundColor(getResources().getColor(R.color.material_white));
                tv_local.setTextColor(getResources().getColor(R.color.material_textBlack_text));
                tv_years.setBackgroundResource(R.drawable.bg_mall_title);
                break;
        }
    }

    @Override
    public void onRefresh() {
        products_rv.postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(false);
                adapter.loadMoreComplete();
            }
        },2000);
    }

    @Override
    public void onLoadMoreRequested() {
        adapter.loadMoreEnd();
    }
}
