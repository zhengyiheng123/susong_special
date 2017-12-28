package com.xyd.susong.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.recker.flybanner.FlyBanner;
import com.xyd.susong.R;
import com.xyd.susong.api.HomeApi;
import com.xyd.susong.api.ShopChartApi;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseFragment;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.BaseObserverLoading;
import com.xyd.susong.base.EmptyModel;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.glide.GlideUtil;
import com.xyd.susong.main.home.HomeModelNew;
import com.xyd.susong.main.home.ShangchengActivity;
import com.xyd.susong.main.home.WebViewActivity;
import com.xyd.susong.mall.MallAdapter;
import com.xyd.susong.store.StoreActivity;
import com.xyd.susong.store.StoreModel;
import com.xyd.susong.utils.ToastUtils;
import com.xyd.susong.view.MyRecycle;
import com.xyd.susong.view.SmartImageveiw;
import com.xyd.susong.winedetail.WineDetailActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @date: 2017/6/12
 * @time: 15:06
 * @description: 首页
 */

public class HomeFragmentSuSong extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener ,FlyBanner.OnItemClickListener {

    @Bind(R.id.banner)
    FlyBanner banner;
    @Bind(R.id.local_rv)
    MyRecycle local_rv;
    @Bind(R.id.goods_img)
    SmartImageveiw goods_img;
    @Bind(R.id.goods_img2)
    SmartImageveiw goods_img2;
    @Bind(R.id.scroll_view)
    ScrollView scroll_view;
    @Bind(R.id.gift_rv)
    MyRecycle gift_rv;
    @Bind(R.id.tv_goods_name)
    TextView tv_goods_name;
    @Bind(R.id.tv_goodsprice)
    TextView tv_goodsprice;
    @Bind(R.id.tv_goods_name2)TextView tv_goods_name2;
    @Bind(R.id.tv_goodsprice2)TextView tv_goodsprice2;
    @Bind(R.id.rl_goods2)RelativeLayout rl_goods2;
    @Bind(R.id.rl_goods1)RelativeLayout rl_goods1;
    @Bind(R.id.home_srl)SwipeRefreshLayout home_srl;
    @Bind(R.id.iv_add_chart1)ImageView iv_add_chart1;
    @Bind(R.id.iv_add_chart2)ImageView iv_add_chart2;
    @Bind(R.id.tv_more_tuijian)TextView tv_more_tuijian;
    @Bind(R.id.tv_more_local)TextView tv_more_local;
    @Bind(R.id.tv_more_gift)TextView tv_more_gift;
    private int width;
    //新春特惠
    private List<StoreModel.DataBean> thgoodslist;
    //特产集合
    private List<StoreModel.DataBean> tcgoodsList;
    //推荐
    private List<StoreModel.DataBean> tjgoodsList=new ArrayList<>();
    //轮播
    private List<HomeModelNew.LunboBean> lunboBeenList=new ArrayList<>();
    private MallAdapter localAdapter;
    private MallAdapter xcAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemClick(int position) {
        if (lunboBeenList.get(position).getGoods_id() == 0){
            ToastUtils.show("轮播类型为商品");
        }else {
            ToastUtils.show("轮播类型为资讯");
        }
    }

    //本地特产子条目点击事件
    private class TCOnitemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener{

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//            ToastUtils.show(localAdapter.getData().get(position).getG_id()+"");
            edit("add",localAdapter.getData().get(position).getG_id()+"","");
        }
    }
    //送礼精品子条目点击事件
    private class GiftOnitemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener{

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//            ToastUtils.show(xcAdapter.getData().get(position).getG_id()+"");
            edit("add",xcAdapter.getData().get(position).getG_id()+"","");
        }
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_susong;
    }

    @Override
    protected void initView() {
        home_srl.setColorSchemeColors(getResources().getColor(R.color.theme_color));
        home_srl.setOnRefreshListener(this);
        goods_img.setRatio(1.7f);
        goods_img2.setRatio(1.7f);
        initBanner();
        //初始化本地特产
        initLocalRv();

        initGiftRv();
        scroll_view.scrollTo(0,0);
        onRefresh();
    }
            //送礼精品
    private void initGiftRv() {
        thgoodslist=new ArrayList<>();
        xcAdapter = new MallAdapter(thgoodslist,getActivity());
        xcAdapter.setOnItemChildClickListener(new GiftOnitemChildClickListener());
        gift_rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        gift_rv.setAdapter(xcAdapter);
    }

    //初始化banner
    private void initBanner() {
        ViewGroup.LayoutParams params=banner.getLayoutParams();
        params.width= ViewGroup.LayoutParams.MATCH_PARENT;
        params.height=(int )(width/1.7f);
        banner.setLayoutParams(params);
    }
        //本地特产集合
    private void initLocalRv() {
        tcgoodsList=new ArrayList<>();
        localAdapter = new MallAdapter(tcgoodsList,getActivity());
        localAdapter.setOnItemChildClickListener(new TCOnitemChildClickListener());
        local_rv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        local_rv.setAdapter(localAdapter);
    }

    @Override
    protected void initEvent() {
        tv_more_gift.setOnClickListener(this);
        tv_more_local.setOnClickListener(this);
        tv_more_tuijian.setOnClickListener(this);
        banner.setOnItemClickListener(this);
        rl_goods1.setOnClickListener(this);
        rl_goods2.setOnClickListener(this);
        iv_add_chart2.setOnClickListener(this);
        iv_add_chart1.setOnClickListener(this);
        localAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString(WineDetailActivity.G_ID,localAdapter.getData().get(position).getG_id()+"");
                startActivity(WineDetailActivity.class,bundle);
            }
        });
        xcAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle=new Bundle();
                bundle.putString(WineDetailActivity.G_ID,xcAdapter.getData().get(position).getG_id()+"");
//                Log.e("position",position+"");
                startActivity(WineDetailActivity.class,bundle);
            }
        });
        banner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (null!=lunboBeenList && lunboBeenList.size()>0){
                    if (lunboBeenList.get(position).getGoods_id() == 0){
                        Bundle bundle=new Bundle();
                        bundle.putString(WebViewActivity.TITLE,"");
                        bundle.putString(WebViewActivity.URL,lunboBeenList.get(position).getAdv_url());
                        startActivity(WebViewActivity.class,bundle);
                    }else {
                        Bundle bundle=new Bundle();
                        bundle.putString(WineDetailActivity.G_ID,localAdapter.getData().get(position).getG_id()+"");
                        startActivity(WineDetailActivity.class,bundle);
                    }
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        Bundle bundle=null;
        switch (v.getId()){
            case R.id.tv_more_gift:
                ((MainActivity)getActivity()).gotoFirstPage();
                break;
            case R.id.tv_more_local:
                ((MainActivity)getActivity()).gotoFirstPage();
                break;
            case R.id.tv_more_tuijian:
                bundle=new Bundle();
                bundle.putString(ShangchengActivity.TITLE,"推荐");
                bundle.putInt(ShangchengActivity.TYPE,1);
                startActivity(ShangchengActivity.class,bundle);
                break;
            case R.id.rl_goods1:
                bundle=new Bundle();
                bundle.putString(WineDetailActivity.G_ID,tjgoodsList.get(0).getG_id()+"");
                startActivity(WineDetailActivity.class,bundle);
                break;
            case R.id.rl_goods2:
                bundle=new Bundle();
                bundle.putString(WineDetailActivity.G_ID,tjgoodsList.get(1).getG_id()+"");
                startActivity(WineDetailActivity.class,bundle);
                break;
            case R.id.iv_add_chart2:
                edit("add",tjgoodsList.get(1).getG_id()+"","");
                break;
            case R.id.iv_add_chart1:
                edit("add",tjgoodsList.get(0).getG_id()+"","");
                break;
        }
    }
    private void getData(){
        BaseApi.getRetrofit()
                .create(HomeApi.class)
                .home()
                .compose(RxSchedulers.<BaseModel<HomeModelNew>>compose())
                .subscribe(new BaseObserverLoading<HomeModelNew>(getActivity()) {
                    @Override
                    protected void onHandleSuccess(HomeModelNew homeModel, String msg, int code) {
                        thgoodslist.clear();
                        thgoodslist.addAll(homeModel.getThgoods());
                        tcgoodsList.clear();
                        tcgoodsList.addAll(homeModel.getTcgoods());
                        tjgoodsList.clear();
                        tjgoodsList.addAll(homeModel.getTjgoods());
//                        tjgoodsList.clear();
//                        tjgoodsList.addAll(homeModel.getTjgoods());
                        lunboBeenList.clear();
                        lunboBeenList.addAll(homeModel.getLunbo());
                        List<String> imgList=new ArrayList<String>();
                        for (HomeModelNew.LunboBean lunboBean:homeModel.getLunbo()){
                            imgList.add(lunboBean.getAdv_imgs());
                        }
                        //加载轮播图
                        if (imgList.size()>0){
                            banner.setImagesUrl(imgList);
                            banner.startAutoPlay();
                        }

                        //新春推荐
                        thgoodslist.clear();
                        for (StoreModel.DataBean goodsModel:homeModel.getThgoods()){
                            thgoodslist.add(goodsModel);
                        }
                        xcAdapter.setNewData(thgoodslist);

                        //本地特产
                        tcgoodsList.clear();
                        for (StoreModel.DataBean goodsModel:homeModel.getTcgoods()){
                            tcgoodsList.add(goodsModel);
                        }
                        localAdapter.setNewData(tcgoodsList);

                        //推荐商品
                        if (homeModel.getTjgoods().size()>=2){
                            GlideUtil.getInstance().loadImage(getActivity(),goods_img,homeModel.getTjgoods().get(0).getG_img(),false);
                            GlideUtil.getInstance().loadImage(getActivity(),goods_img2,homeModel.getTjgoods().get(1).getG_img(),false);
                            tv_goods_name.setText(homeModel.getTjgoods().get(0).getG_name());
                            tv_goods_name2.setText(homeModel.getTjgoods().get(1).getG_name());
                            tv_goodsprice.setText("￥"+homeModel.getTjgoods().get(0).getG_price());
                            tv_goodsprice2.setText("￥"+homeModel.getTjgoods().get(1).getG_price());
                            home_srl.setRefreshing(false);
                        }
                    }

                    @Override
                    protected void onHandleError(int errorCode, String msg) {
                        home_srl.setRefreshing(false);
                    }
                });
    }

    @Override
    public void onRefresh() {
        getData();
    }
    //编辑购物车操作
    public void edit(String type,String g_id,String  num){
        BaseApi.getRetrofit().create(ShopChartApi.class)
                .edit(type,g_id,num)
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, String msg, int code) {
                        ToastUtils.show(msg);
                        EventBus.getDefault().postSticky(emptyModel);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        ToastUtils.show(msg);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeStickyEvent(new EmptyModel());
    }
}
