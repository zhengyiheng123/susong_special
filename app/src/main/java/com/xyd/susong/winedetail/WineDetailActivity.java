package com.xyd.susong.winedetail;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xyd.susong.R;
import com.xyd.susong.api.OrderApi;
import com.xyd.susong.api.StoreApi;
import com.xyd.susong.base.BaseActivity;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.BaseObserverLoading;
import com.xyd.susong.base.PublicStaticData;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.commitorder.CommitOrderActivity;
import com.xyd.susong.glide.GlideUtil;
import com.xyd.susong.view.SmartImageveiw;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/14
 * @time: 15:42
 * @description: 小酒
 */

public class WineDetailActivity extends BaseActivity implements ViewPager.OnPageChangeListener,
        RadioGroup.OnCheckedChangeListener {
    public static String G_ID = "g_id";
    public static String G_DATA = "g_data";
    public static String G_NUM = "g_num";
    public static String ORDER_TYPE="from";//1购物车   2商品详情
    @Bind(R.id.base_title_back)
    TextView baseTitleBack;
    @Bind(R.id.base_title_title)
    TextView baseTitleTitle;
    @Bind(R.id.base_title_menu)
    ImageView baseTitleMenu;
    @Bind(R.id.wine_cost)
    TextView wineCost;
    @Bind(R.id.wine_buy)
    TextView wineBuy;
    @Bind(R.id.wine_iv)
    SmartImageveiw wineIv;
    @Bind(R.id.wine_name)
    TextView wineName;
    @Bind(R.id.wine_price)
    TextView winePrice;
    @Bind(R.id.wine_name_bottom)
    TextView wineNameBottom;
    @Bind(R.id.wine_state)
    TextView wineState;
    @Bind(R.id.wine_type)
    TextView wineType;
    @Bind(R.id.wine_detail)
    RadioButton wineDetail;
    @Bind(R.id.wine_evaluate)
    RadioButton wineEvaluate;
    @Bind(R.id.id_stickynavlayout_indicator)
    RadioGroup wineRg;
    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager wineVp;
    @Bind(R.id.wine_jian)
    ImageView wineJian;
    @Bind(R.id.wine_edt_num)
    EditText wineEdtNum;
    @Bind(R.id.wine_add)
    ImageView wineAdd;
    private Fragment[] fragments;
    private int index = 0;
    private WineDetailFragment wineDetailFragment;
    private WineEvaluateFragment wineEvaluateFragment;
    private String g_id;
    private double price;
    private int num = 1;
    private WineModel model;
    List<WineModel.GoodBean> wineList=new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_small_wine;
    }

    @Override
    protected void initView() {
        wineIv.setRatio(1.77f);
        g_id = getIntent().getStringExtra(G_ID);
        Log.e("aaaaaa", g_id);
        baseTitleMenu.setVisibility(View.INVISIBLE);
        wineDetailFragment = new WineDetailFragment();
        wineEvaluateFragment = new WineEvaluateFragment();
        Bundle bundle = new Bundle();
        bundle.putString(G_ID, g_id);
        wineEvaluateFragment.setArguments(bundle);
        fragments = new Fragment[]{wineDetailFragment, wineEvaluateFragment};
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        wineVp.setAdapter(adapter);
        wineVp.setCurrentItem(index);
        wineRg.check(R.id.wine_detail);
        wineEdtNum.setText(num + "");
        getData();


    }


    @Override
    protected void initEvent() {
        baseTitleBack.setOnClickListener(this);
        wineBuy.setOnClickListener(this);
        wineJian.setOnClickListener(this);
        wineAdd.setOnClickListener(this);
        wineRg.setOnCheckedChangeListener(this);
        wineVp.addOnPageChangeListener(this);

    }

    private void getData() {
        BaseApi.getRetrofit()
                .create(StoreApi.class)
                .goods(g_id)
                .compose(RxSchedulers.<BaseModel<WineModel>>compose())
                .subscribe(new BaseObserverLoading<WineModel>(WineDetailActivity.this) {
                    @Override
                    protected void onHandleSuccess(WineModel wineModel, String msg, int code) {
                        model = wineModel;
                        baseTitleTitle.setText(model.getGood().getG_name());
                        GlideUtil.getInstance()
                                .loadCornerImage(WineDetailActivity.this, wineIv, wineModel.getGood().getG_img(), 5);
                        wineName.setText(wineModel.getGood().getG_name());
                        price = wineModel.getGood().getG_price();
                        winePrice.setText("￥" + price);
                        double freignht=Double.valueOf(wineModel.getGood().getG_freight());
                        double goodPrice=price*num;
                        double totalPrice=goodPrice+freignht;
//                        DecimalFormat df=new DecimalFormat("#.##");

                        wineCost.setText("￥" + totalPrice);
                        wineType.setText("库存："+wineModel.getGood().getG_num());
                        if (wineModel.getGood().getG_freight().equals("0.00"))
                            wineState.setText("免运费");
                        else
                            wineState.setText("运费：￥" + wineModel.getGood().getG_freight());

//                        wineNameBottom.setText(wineModel.getGood().getG_sname());
                        wineDetailFragment.setWebView(wineModel.getG_con());

                    }

                    @Override
                    protected void onHandleError(int errorCode, String msg) {

                    }
                });
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.base_title_back:
                finish();
                break;
            case R.id.wine_buy:
                if (model.getGood().getG_num()>0){
//                    model.getGood().setGoods_num(Integer.valueOf(wineEdtNum.getText().toString()));
//                    wineList.add(model.getGood());
                    Bundle bundle = new Bundle();
//                    bundle.putSerializable(G_DATA, (Serializable) wineList);
//                    bundle.putInt(G_NUM, num);
                    bundle.putString(ORDER_TYPE,"2");
                    bundle.putString(G_DATA,queryParam(model.getGood().getG_id(),Integer.valueOf(wineEdtNum.getText().toString())));
                    startActivity(CommitOrderActivity.class, bundle);
                }else {
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setTitle("温馨提示");
                    builder.setMessage("您购买的商品暂时库存不足，请您购买其它商品");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                        }
                    });
                    builder.create().show();

                }

                break;
            case R.id.wine_add:
                if (num < 99) {
                    num++;
                    if (num>model.getGood().getG_num()){
                        AlertDialog.Builder builder=new AlertDialog.Builder(this);
                        builder.setTitle("温馨提示");
                        builder.setMessage("当前商品库存"+model.getGood().getG_num()+"件");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();

                        return;
                    }
                    wineEdtNum.setText(num + "");
                    wineCost.setText("￥" + (price * num + Double.valueOf(model.getGood().getG_freight())));
                }

                break;
            case R.id.wine_jian:
                if (num > 1) {
                    num--;
                    wineEdtNum.setText(num + "");
                    wineCost.setText("￥" + (price * num + Double.valueOf(model.getGood().getG_freight())));
                }
                break;
        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                wineRg.check(R.id.wine_detail);
                break;
            case 1:
                wineRg.check(R.id.wine_evaluate);
                break;

        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.wine_detail:
                index = 0;
                break;
            case R.id.wine_evaluate:
                index = 1;
                break;
        }
        wineVp.setCurrentItem(index, true);
    }

    class MyAdapter extends FragmentStatePagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    //封装请求数据
    private String queryParam(int g_id,int num){
        OrderParam orderParam=new OrderParam(g_id,num);
        List<OrderParam> list=new ArrayList<>();
        list.add(orderParam);
        try {
            return orderParam.toJson(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //封装订单请求参数
    public static class OrderParam{
        public int g_id;
        public int num;

        public OrderParam(int g_id, int num) {
            this.g_id = g_id;
            this.num = num;
        }

        public String toJson(List<OrderParam> list) throws JSONException {
            JSONArray jsonArray=new JSONArray();
            JSONObject jsonObject=new JSONObject();
            JSONObject tempJSONObject=null;
            for (OrderParam param:list){
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
    }
}
