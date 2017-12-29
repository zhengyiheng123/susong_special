package com.xyd.susong.shopchart;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xyd.susong.R;
import com.xyd.susong.base.BaseFragment;
import com.xyd.susong.base.EmptyModel;
import com.xyd.susong.commitorder.CommitOrderActivity;
import com.xyd.susong.main.MainActivity;
import com.xyd.susong.promptdialog.PromptDialog;
import com.xyd.susong.utils.ToastUtils;
import com.xyd.susong.view.SmartImageveiw;
import com.xyd.susong.winedetail.WineDetailActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Zheng on 2017/12/19.
 */

public class ShopChartFragment extends BaseFragment implements
    BaseQuickAdapter.OnItemChildClickListener,ShopChartContract.View,BaseQuickAdapter.OnItemClickListener
{
//    @Bind(R.id.refresh)
//    SwipeRefreshLayout refresh;
    @Bind(R.id.chart_rv)
    RecyclerView chart_rv;
    @Bind(R.id.smart_iv)
    SmartImageveiw smart_iv;
    @Bind(R.id.cb_check_all)
    CheckBox cb_check_all;
    @Bind(R.id.tv_totalmoney)
    TextView tv_totalmoney;
    @Bind(R.id.count_money)
    TextView count_money;
    private List<ChartModel.ContentBean> list;
    private ChartADapter aDapter;
    private TextView tv_shop;
    @Bind(R.id.rl_root)RelativeLayout rl_root;

    //商品总价格
    private Double totalPrice;
    private ShopChartPresenter presenter;
    private PromptDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_chart;
    }

    @Override
    protected void initView() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        dialog = new PromptDialog(getActivity());
        smart_iv.setRatio(13.3f);
//        refresh.setColorSchemeColors(getResources().getColor(R.color.theme_color));
//        refresh.setOnRefreshListener(this);
        chart_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        initAdaper();
        tv_totalmoney.setText("￥"+countPrice());
    }
    //计算选中商品价格
    private Double countPrice(){
        Double totalMoney=0.0;
        for (ChartModel.ContentBean model:aDapter.getData()){
            if (model.isChecked()){
                totalMoney=totalMoney+model.getG_price()*model.getNum();
            }
        }
        return totalMoney;
    }
    private void initAdaper() {
        list = new ArrayList<>();
        presenter = new ShopChartPresenter(this);
        presenter.getData();
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.empty_shopchart,chart_rv,false);
        tv_shop = (TextView) view.findViewById(R.id.tv_shop);
        aDapter= new ChartADapter(list);
//        aDapter.setOnItemClickListener(this);
        aDapter.setEmptyView(view);
        aDapter.setOnItemChildClickListener(this);
        aDapter.loadMoreEnd();
        aDapter.openLoadAnimation(BaseQuickAdapter.HEADER_VIEW);
        chart_rv.setAdapter(aDapter);
    }
    @Override
    protected void initEvent() {
        tv_shop.setOnClickListener(this);
        count_money.setOnClickListener(this);
        cb_check_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    for (ChartModel.ContentBean model:aDapter.getData()){
                        model.setChecked(true);
                    }

                }else {
                    for (ChartModel.ContentBean model:aDapter.getData()){
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
//                ToastUtils.show("去逛逛");
                ((MainActivity)getActivity()).gotoFirstPage();
                break;
            case R.id.count_money:
                if (totalPrice ==0){
                    return;
                }
                Bundle bundle = new Bundle();
//                    bundle.putSerializable(G_DATA, (Serializable) wineList);
//                    bundle.putInt(G_NUM, num);
                try {
                    bundle.putString(WineDetailActivity.G_DATA,queryParam());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                startActivity(CommitOrderActivity.class, bundle);
                break;
            }
    }
    //集合转换成JSON字符串
    public String toJson(List<WineDetailActivity.OrderParam> list) throws JSONException {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        JSONObject tempJSONObject=null;
        for (WineDetailActivity.OrderParam param:list){
            tempJSONObject=new JSONObject();
            tempJSONObject.put("g_id",param.g_id);
            tempJSONObject.put("num",param.num);
            jsonArray.put(tempJSONObject);
            tempJSONObject=null;
        }
        String orderInfo=jsonArray.toString();
//            jsonObject.put("content",orderInfo);
        return orderInfo.toString();
    }
    //封装请求参数
    private String queryParam() throws JSONException {
        List<WineDetailActivity.OrderParam> paramList=new ArrayList<>();
        for (ChartModel.ContentBean model : aDapter.getData()){
            if (model.isChecked()){
                paramList.add(new WineDetailActivity.OrderParam(model.getG_id(),model.getNum()));
            }
        }
        return toJson(paramList);
    }
    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
        switch (view.getId()){
            case R.id.iv_add:
                aDapter.getData().get(position).setNum(aDapter.getData().get(position).getNum()+1);
                presenter.edit("add",aDapter.getData().get(position).getG_id()+"","");
                if (!aDapter.getData().get(position).isChecked()){
                    aDapter.getData().get(position).setChecked(true);
                }
                break;
            case R.id.iv_release:
                if (aDapter.getData().get(position).getNum() == 1){
                    return;
                }
                aDapter.getData().get(position).setNum(aDapter.getData().get(position).getNum()-1);
                presenter.edit("edit",aDapter.getData().get(position).getG_id()+"",aDapter.getData().get(position).getNum()+"");
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
            case R.id.iv_delete:
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setTitle("提示");
                builder.setMessage("确定是删除该商品吗？");
                builder.setNegativeButton("取消",null);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.edit("del",aDapter.getData().get(position).getG_id()+"","");
                    }
                });
                builder.show();
                break;

        }
        aDapter.notifyDataSetChanged();
        totalPrice=countPrice();
        tv_totalmoney.setText("￥"+totalPrice);
    }

    @Override
    public void refreshData(ChartModel chartModel) {
        aDapter.setNewData(chartModel.getContent());
//        refresh.setRefreshing(false);
    }

    @Override
    public void error(String msg) {
        dialog.showError(msg);
//        refresh.setRefreshing(false);
    }
    //购物车加减成功回调
    @Override
    public void editSuccess(String type) {
        if (type.equals("del")){
           presenter.getData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onDataEvent(EmptyModel emptyModel){
        presenter.getData();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (!aDapter.getData().get(position).isChecked()){
            aDapter.getData().get(position).setChecked(true);
        }else {
            aDapter.getData().get(position).setChecked(false);
        }
        aDapter.notifyDataSetChanged();
    }
}
