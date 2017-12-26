package com.xyd.susong.suggest.image;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xyd.susong.R;
import com.xyd.susong.api.SuggestApi;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseFragment;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.newsdetail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/14
 * @time: 9:58
 * @description: 资讯——图文
 */

public class ImageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemClickListener {
    @Bind(R.id.base_rv)
    RecyclerView baseRv;
    @Bind(R.id.base_srl)
    SwipeRefreshLayout baseSrl;
    private int page = 1, num = 10;
    private ImageAdapter adapter;
    private List<ImageModel.ArticlesBean> list;

    @Override
    protected int getLayoutId() {
        return R.layout.base_srl_rv;
    }

    @Override
    protected void initView() {
        baseSrl.setOnRefreshListener(this);
        baseSrl.setColorSchemeColors(getResources().getColor(R.color.theme_color));
        baseRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        initAdapter();
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    private void getData() {
        BaseApi.getRetrofit()
                .create(SuggestApi.class)
                .photo_msg(page, num)
                .compose(RxSchedulers.<BaseModel<ImageModel>>compose())
                .subscribe(new BaseObserver<ImageModel>() {
                    @Override
                    protected void onHandleSuccess(ImageModel imageModel, String msg, int code) {

                        baseSrl.setRefreshing(false);
                        adapter.loadMoreComplete();
                        if (page==1){
                            adapter.setNewData(imageModel.getArticles());
                        }else if (imageModel.getArticles().size()>0){
                            adapter.addData(imageModel.getArticles());
                        }else {
                            adapter.loadMoreEnd();
                        }

                    }

                    @Override
                    protected void onHandleError(String msg) {
                        baseSrl.setRefreshing(false);
                        adapter.loadMoreComplete();
                        showToast(msg);
                    }
                });
    }

    private void initAdapter() {
        list = new ArrayList<>();
        adapter = new ImageAdapter(list, getActivity());
        adapter.setOnLoadMoreListener(this, baseRv);
        adapter.setOnItemClickListener(this);
//        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        baseRv.setAdapter(adapter);
        adapter.setEnableLoadMore(true);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void onRefresh() {
        page=1;
        getData();

    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        getData();

    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter1, View view, int position) {
        Bundle b = new Bundle();
        b.putInt(DetailActivity.NEWS_ID, adapter.getData().get(position).getA_id());
        b.putInt(DetailActivity.COLLECT, adapter.getData().get(position).getCollect());
        b.putString(DetailActivity.NEWS_URL,adapter.getData().get(position).getA_content());
        b.putString(DetailActivity.TITLE,adapter.getData().get(position).getA_title());
        startActivity(DetailActivity.class,b);

    }
}
