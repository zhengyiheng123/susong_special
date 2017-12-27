package com.xyd.susong.main.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xyd.susong.R;
import com.xyd.susong.base.BaseActivity;
import com.xyd.susong.mall.MallAdapter;
import com.xyd.susong.mall.MallContract;
import com.xyd.susong.mall.MallPresenter;
import com.xyd.susong.store.StoreModel;
import com.xyd.susong.winedetail.WineDetailActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Zheng on 2017/12/27.
 */

public class ShangchengActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener,MallContract.View{
    @Bind(R.id.base_title_menu)ImageView base_title_menu;
    @Bind(R.id.base_title_title)TextView base_title_title;
    @Bind(R.id.base_title_back)TextView base_title_back;
    @Bind(R.id.commission_srl)SwipeRefreshLayout commission_srl;
    @Bind(R.id.commission_rv)RecyclerView commission_rv;
    public static String TITLE="title";
    public static String TYPE="type";
    private List<StoreModel.DataBean> list;
    private MallAdapter adapter;
    private MallPresenter presenter;
    //页码
    private int page=1;
    //商品类型 1：推荐 2：特产 3：特惠
    private int type;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_commission_order;
    }

    @Override
    protected void initView() {
        String title=getIntent().getStringExtra(TITLE);
        type= getIntent().getIntExtra(TYPE,0);
        presenter=new MallPresenter(this);
        presenter.getData(page,type);
        base_title_menu.setVisibility(View.INVISIBLE);
        base_title_title.setText(title);
        commission_srl.setColorSchemeColors(getResources().getColor(R.color.theme_color));
        commission_srl.setOnRefreshListener(this);
        commission_rv.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        initAdapter();
    }

    private void initAdapter() {
        list = new ArrayList<>();
        adapter = new MallAdapter(list,getApplicationContext());
        adapter.setOnLoadMoreListener(this,commission_rv);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapte1r, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString(WineDetailActivity.G_ID,adapter.getData().get(position).getG_id()+"");
                startActivity(WineDetailActivity.class,bundle);
            }
        });
        commission_rv.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        base_title_back.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
switch (v.getId()){
    case R.id.base_title_back:
        finish();
        break;
}
    }

    @Override
    public void onRefresh() {
        page=1;
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
        commission_srl.setRefreshing(false);
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
}
