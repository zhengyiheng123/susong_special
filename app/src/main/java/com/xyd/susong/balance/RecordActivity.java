package com.xyd.susong.balance;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xyd.susong.R;
import com.xyd.susong.api.MineApi;
import com.xyd.susong.base.BaseActivity;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


/**
 * @author: zhaoxiaolei
 * @date: 2017/8/14
 * @time: 17:08
 * @description: 充值  提现记录
 */

public class RecordActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{
    @Bind(R.id.base_title_back)
    TextView baseTitleBack;
    @Bind(R.id.base_title_title)
    TextView baseTitleTitle;
    @Bind(R.id.base_title_menu)
    ImageView baseTitleMenu;
    @Bind(R.id.base_title_right)
    TextView baseTitleRight;
    @Bind(R.id.record_Rv)
    RecyclerView recordRv;
    @Bind(R.id.record_srl)
    SwipeRefreshLayout recordSrl;
    private RecordAdapter adapter;
    private List<CashValueModel.CashValueBean> list;
    private int page=1,num=10;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_record;

    }

    @Override
    protected void initView() {
        baseTitleTitle.setText("记录");
        baseTitleMenu.setVisibility(View.INVISIBLE);
        recordSrl.setColorSchemeColors(getResources().getColor(R.color.theme_color));
        recordRv.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        getData();
    }

    private void getData() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .cash_value(page,num)
                .compose(RxSchedulers.<BaseModel<CashValueModel>>compose())
                .subscribe(new BaseObserver<CashValueModel>() {
                    @Override
                    protected void onHandleSuccess(CashValueModel cashValueModel, String msg, int code) {
                        adapter.loadMoreComplete();
                        recordSrl.setRefreshing(false);
                        if (page == 1) {
                            adapter.setNewData(cashValueModel.getCash_value());
                        } else if (cashValueModel.getCash_value().size() > 0) {
                            adapter.addData(cashValueModel.getCash_value());

                        } else {
                            adapter.loadMoreEnd();
                        }
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        adapter.loadMoreComplete();
                        recordSrl.setRefreshing(false);
                        showToast(msg);
                    }
                });

    }

    private void initAdapter() {

        list = new ArrayList<>();

        adapter = new RecordAdapter(list, this);
        View view= LayoutInflater.from(this).inflate(R.layout.empty_view,null);
        adapter.setEmptyView(view);
        adapter.setOnLoadMoreListener(this, recordRv);
//        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recordRv.setAdapter(adapter);

    }

    @Override
    protected void initEvent() {
        baseTitleBack.setOnClickListener(this);
        recordSrl.setOnRefreshListener(this);

    }

    @Override
    public void widgetClick(View v) {
        finish();

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
}
