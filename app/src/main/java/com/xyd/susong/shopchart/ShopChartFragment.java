package com.xyd.susong.shopchart;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xyd.susong.R;
import com.xyd.susong.base.BaseFragment;
import com.xyd.susong.main.MainActivity;
import com.xyd.susong.utils.ToastUtils;
import com.xyd.susong.view.SmartImageveiw;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Zheng on 2017/12/19.
 */

public class ShopChartFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener
    ,BaseQuickAdapter.OnItemChildClickListener
{
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    @Bind(R.id.chart_rv)
    RecyclerView chart_rv;
    @Bind(R.id.smart_iv)
    SmartImageveiw smart_iv;
    @Bind(R.id.cb_check_all)
    CheckBox cb_check_all;
    @Bind(R.id.tv_totalmoney)
    TextView tv_totalmoney;
    private List<ChartModel> list;
    private ChartADapter aDapter;
    private TextView tv_shop;
    //商品总价格
    private Double totalPrice;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_chart;
    }

    @Override
    protected void initView() {
        smart_iv.setRatio(13.3f);
        refresh.setOnRefreshListener(this);
        refresh.setColorSchemeColors(getResources().getColor(R.color.theme_color));
        refresh.setOnRefreshListener(this);
        chart_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        initAdaper();
        tv_totalmoney.setText("￥"+countPrice());
    }
    //计算选中商品价格
    private Double countPrice(){
        Double totalMoney=0.0;
        for (ChartModel model:aDapter.getData()){
            if (model.isChecked()){
                totalMoney=totalMoney+model.getPrice()*model.getNum();
            }
        }
        return totalMoney;
    }
    private void initAdaper() {
        list = new ArrayList<>();
        list.add(new ChartModel(3,"小黄鱼",true,40.00));
        list.add(new ChartModel(2,"大黄鱼",false,38.00));
        list.add(new ChartModel(1,"大葱",false,49.00));
        list.add(new ChartModel(3,"小黄鱼",false,40.00));
        list.add(new ChartModel(2,"大黄鱼",false,38.00));
        list.add(new ChartModel(1,"大葱",false,49.00));
        list.add(new ChartModel(3,"小黄鱼",false,40.00));
        list.add(new ChartModel(2,"大黄鱼",false,38.00));
        list.add(new ChartModel(1,"大葱",false,49.00));
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.empty_shopchart,chart_rv,false);
        tv_shop = (TextView) view.findViewById(R.id.tv_shop);
        aDapter= new ChartADapter(list);
        aDapter.setEmptyView(view);
        aDapter.setOnItemChildClickListener(this);
        aDapter.setOnLoadMoreListener(this,chart_rv);
        aDapter.loadMoreEnd();
        chart_rv.setAdapter(aDapter);
    }
    @Override
    protected void initEvent() {
        tv_shop.setOnClickListener(this);
        cb_check_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    for (ChartModel model:aDapter.getData()){
                        model.setChecked(true);
                    }

                }else {
                    for (ChartModel model:aDapter.getData()){
                        model.setChecked(false);
                    }
                }
                aDapter.notifyDataSetChanged();
                totalPrice=countPrice();
                tv_totalmoney.setText("￥"+totalPrice);
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.tv_shop:
                ToastUtils.show("去逛逛");
                ((MainActivity)getActivity()).gotoFirstPage();
                break;
            }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMoreRequested() {
        aDapter.loadMoreComplete();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()){
            case R.id.iv_add:
                aDapter.getData().get(position).setNum(aDapter.getData().get(position).getNum()+1);
                if (!aDapter.getData().get(position).isChecked()){
                    aDapter.getData().get(position).setChecked(true);
                }
                break;
            case R.id.iv_release:
                if (aDapter.getData().get(position).getNum() == 1){
                    return;
                }
                aDapter.getData().get(position).setNum(aDapter.getData().get(position).getNum()-1);
                if (!aDapter.getData().get(position).isChecked()){
                    aDapter.getData().get(position).setChecked(true);
                }
                break;
            case R.id.cb_check:
                if (aDapter.getData().get(position).isChecked()){
                    aDapter.getData().get(position).setChecked(false);
                }else {
                    aDapter.getData().get(position).setChecked(true);
                }
                break;
        }
        aDapter.notifyDataSetChanged();
        totalPrice=countPrice();
        tv_totalmoney.setText("￥"+totalPrice);
    }
}
