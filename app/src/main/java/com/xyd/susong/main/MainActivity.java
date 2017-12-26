package com.xyd.susong.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyd.susong.R;
import com.xyd.susong.base.BaseActivity;
import com.xyd.susong.main.mine.MineFragment;
import com.xyd.susong.main.service.ServiceFragment;
import com.xyd.susong.mall.MallFragment;
import com.xyd.susong.shopchart.ShopChartFragment;
import com.xyd.susong.utils.StatusBarUtil;
import com.xyd.susong.view.MenuPopWindow;

import butterknife.Bind;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/12
 * @time: 14:12
 * @description: 主界面
 */

public class MainActivity extends BaseActivity {
    @Bind(R.id.menu)
    ImageView menu;
    @Bind(R.id.home_frame)
    FrameLayout homeFrame;
    @Bind(R.id.main_fl)
    FrameLayout mainFl;
    @Bind(R.id.main_iv_service)
    ImageView mainIvService;
    @Bind(R.id.main_tv_service)
    TextView mainTvService;
    @Bind(R.id.main_ll_service)
    LinearLayout mainLlService;
    @Bind(R.id.main_iv_home)
    ImageView mainIvHome;
    @Bind(R.id.main_tv_home)
    TextView mainTvHome;
    @Bind(R.id.main_ll_home)
    LinearLayout mainLlHome;
    @Bind(R.id.main_iv_wode)
    ImageView mainIvWode;
    @Bind(R.id.main_tv_wode)
    TextView mainTvWode;
    @Bind(R.id.main_ll_wode)
    LinearLayout mainLlWode;
    @Bind(R.id.main_ll_mall)
    LinearLayout mainLlMall;
    @Bind(R.id.main_iv_mall)
    ImageView mainIvMall;
    @Bind(R.id.main_tv_mall)
    TextView main_tv_mall;
    @Bind(R.id.main_ll_chart)
    LinearLayout main_ll_chart;
    @Bind(R.id.main_iv_chart)
    ImageView main_iv_chart;
    @Bind(R.id.main_tv_chart)
    TextView main_tv_chart;

    private Fragment[] fragments;
    private int index = 0, currentTabIndex = -1;
    private HomeFragmentSuSong home;
    private ServiceFragment service;
    private MineFragment mine;
    private MallFragment mallFragment;
    private ShopChartFragment shopChartFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
    }

    @Override
    protected void initView() {
        home = new HomeFragmentSuSong();
        service = new ServiceFragment();
        mine = new MineFragment();
        shopChartFragment=new ShopChartFragment();
        mallFragment=new MallFragment();

        fragments = new Fragment[]{home,mallFragment, service,shopChartFragment, mine};
        //默认 选中订单
        onTextClicked();
    }

    @Override
    protected void initEvent() {
        mainLlWode.setOnClickListener(this);
        mainLlHome.setOnClickListener(this);
        mainLlService.setOnClickListener(this);
        homeFrame.setOnClickListener(this);
        mainLlMall.setOnClickListener(this);
        main_ll_chart.setOnClickListener(this);
    }
    public void gotoFirstPage(){
        index=1;
        main_tv_mall.setTextColor(getResources().getColor(R.color.theme_color));
        mainIvMall.setImageResource(R.mipmap.mall);
        if (currentTabIndex != index)
            changeColor();

        onTextClicked();
    }
    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.main_ll_home:
                index = 0;
                mainTvHome.setTextColor(getResources().getColor(R.color.theme_color));
                mainIvHome.setImageResource(R.mipmap.home);
                if (currentTabIndex != index)
                    changeColor();
                break;
            case R.id.main_ll_mall:
                index=1;
                main_tv_mall.setTextColor(getResources().getColor(R.color.theme_color));
                mainIvMall.setImageResource(R.mipmap.mall);
                if (currentTabIndex != index)
                    changeColor();
                break;
            case R.id.main_ll_service:
                index = 2;
                mainTvService.setTextColor(getResources().getColor(R.color.theme_color));
                mainIvService.setImageResource(R.mipmap.service);
                if (currentTabIndex != index)
                    changeColor();
                break;
            case R.id.main_ll_chart:
                index=3;
                main_tv_chart.setTextColor(getResources().getColor(R.color.theme_color));
                main_iv_chart.setImageResource(R.mipmap.shop_chart);
                if (currentTabIndex != index)
                    changeColor();
                break;
            case R.id.main_ll_wode:
                index = 4;
                mainTvWode.setTextColor(getResources().getColor(R.color.theme_color));
                mainIvWode.setImageResource(R.mipmap.mine);
                if (currentTabIndex != index)
                    changeColor();

                break;
            case R.id.home_frame:
                MenuPopWindow popWindow = new MenuPopWindow(MainActivity.this);
                popWindow.showAsDropDown(menu, 0, 10);
                break;

        }
        onTextClicked();

    }

    private void onTextClicked() {
        FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
        if (currentTabIndex != index) {
            if (currentTabIndex != -1) {
                trx.hide(fragments[currentTabIndex]);
            }

            if (!fragments[index].isAdded()) {
                trx.add(R.id.main_fl, fragments[index]);
            }
            trx.show(fragments[index]).commit();
            currentTabIndex = index;
        }

    }

    private void changeColor() {
        //setStatusBar();
        switch (currentTabIndex) {
            case 0:
                mainIvHome.setImageResource(R.mipmap.home_hui);
                mainTvHome.setTextColor(getResources().getColor(R.color.material_textBlack_secondaryText));
                break;
            case 1:
               mainIvMall.setImageResource(R.mipmap.mall_hui);
                main_tv_mall.setTextColor(getResources().getColor(R.color.material_textBlack_secondaryText));
                break;
            case 2:
                mainIvService.setImageResource(R.mipmap.service_hui);
                mainTvService.setTextColor(getResources().getColor(R.color.material_textBlack_secondaryText));
                break;
            case 3:
                main_iv_chart.setImageResource(R.mipmap.shop_chart_hui);
                main_tv_chart.setTextColor(getResources().getColor(R.color.material_textBlack_secondaryText));
                break;
            case 4:
                mainIvWode.setImageResource(R.mipmap.mine_hui);
                mainTvWode.setTextColor(getResources().getColor(R.color.material_textBlack_secondaryText));
                break;

        }
    }



}
