package com.xyd.susong.main.mine;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.xyd.susong.R;
import com.xyd.susong.api.GeneralizeApi;
import com.xyd.susong.api.MineApi;
import com.xyd.susong.balance.BalanceActivity;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseFragment;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.PublicStaticData;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.collect.CollectActivity;
import com.xyd.susong.commissionorder.CommissionOrderActivity;
import com.xyd.susong.generalize.GeneralizeActivity;
import com.xyd.susong.generalize.GeneralizeModel;
import com.xyd.susong.member.EarningActivity;
import com.xyd.susong.glide.GlideUtil;
import com.xyd.susong.kefu.ChatActivity;
import com.xyd.susong.message.MessageActivity;
import com.xyd.susong.order.OrderActivity;
import com.xyd.susong.payments.PaymentsActivity;
import com.xyd.susong.permissions.PermissionsManager;
import com.xyd.susong.personinformation.InformationMessage;
import com.xyd.susong.personinformation.InfromationActivity;
import com.xyd.susong.personinformation.InfromationModel;
import com.xyd.susong.promptdialog.PromptDialog;
import com.xyd.susong.rank.RankActivity;
import com.xyd.susong.setting.SettingActivity;
import com.xyd.susong.utils.LogUtil;
import com.xyd.susong.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;

/**
 * @author: zhaoxiaolei
 * @date: 2017/6/12
 * @time: 15:06
 * @description: 我的
 */

public class MineFragment extends BaseFragment {

    @Bind(R.id.menu)
    ImageView menu;
    @Bind(R.id.mine_tv_name)
    TextView mineTvName;
    @Bind(R.id.mine_tv_id)
    TextView mintv_id;
    @Bind(R.id.mine_tv_order)
    TextView mineTvOrder;
    @Bind(R.id.mine_tv_commission_order)
    TextView mineTvCommissionOrder;
    @Bind(R.id.mine_tv_rank)
    TextView mineTvRank;
    @Bind(R.id.mine_tv_collection)
    TextView mineTvCollection;
    @Bind(R.id.mine_tv_setting)
    TextView mineTvSetting;
    @Bind(R.id.mine_iv_head)
    ImageView mineIvHead;
    @Bind(R.id.mine_iv_information)
    ImageView mineIvInformation;
    @Bind(R.id.mine_iv_service)
    ImageView mineIvService;
    @Bind(R.id.mine_tv_gongyi)
    TextView mineTvGongyi;
    @Bind(R.id.mine_tv_balance)
    TextView mineTvBalance;
    @Bind(R.id.mine_tv_earnings)
    TextView mineTvEarnings;
    @Bind(R.id.mine_tv_code)
    TextView mineTvCode;
    @Bind(R.id.view_circle)
    View view_circle;
    @Bind(R.id.mine_tv_message)
    RelativeLayout mineTvMessage;
    private PromptDialog promptDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        promptDialog = new PromptDialog(getActivity());
        EventBus.getDefault().register(this);
        menu.setVisibility(View.INVISIBLE);
//        GlideUtil.getInstance()
//                .loadCircleImage(getActivity(), mineIvHead, PublicStaticData.baseUrl + PublicStaticData.sharedPreferences.getString("head", ""));
        mineTvName.setText(PublicStaticData.sharedPreferences.getString("nickname", ""));

//        mintv_id.setText("ID："+PublicStaticData.sharedPreferences.getInt("id", 0));
    }

    @Override
    public void onResume() {
        super.onResume();
        queryMessage();
        getData();
    }

    @Override
    protected void initEvent() {
        menu.setOnClickListener(this);
        mineTvCollection.setOnClickListener(this);
        mineTvCommissionOrder.setOnClickListener(this);
        mineTvOrder.setOnClickListener(this);
        mineTvRank.setOnClickListener(this);
        mineTvSetting.setOnClickListener(this);
        mineIvInformation.setOnClickListener(this);
        mineIvService.setOnClickListener(this);
        mineTvGongyi.setOnClickListener(this);
        mineTvBalance.setOnClickListener(this);
        mineTvEarnings.setOnClickListener(this);
        mineTvCode.setOnClickListener(this);
        mineTvMessage.setOnClickListener(this);

    }

    @Subscribe
    public void onEventBus(InformationMessage message) {
        GlideUtil.getInstance()
                .loadCircleImage(getActivity(), mineIvHead, PublicStaticData.baseUrl + PublicStaticData.sharedPreferences.getString("head", ""));
//        mintv_id.setText("ID："+PublicStaticData.sharedPreferences.getInt("id", 0));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.menu:
                showTestToast("menu");
                break;
            case R.id.mine_tv_collection:
                startActivity(CollectActivity.class);
                break;
            case R.id.mine_tv_commission_order:
                startActivity(CommissionOrderActivity.class);
                break;
            case R.id.mine_tv_order:
                startActivity(OrderActivity.class);
                break;
            case R.id.mine_tv_rank:
                startActivity(RankActivity.class);
                break;
            case R.id.mine_tv_setting:
                startActivity(SettingActivity.class);
                break;
            case R.id.mine_iv_information:
                startActivity(InfromationActivity.class);
                break;
            case R.id.mine_iv_service:

                if (ChatClient.getInstance().isLoggedInBefore()){
                    startActivity(ChatActivity.class);
                }
                else{
                loginHx("qiaozhijinhan"+PublicStaticData.sharedPreferences.getInt("id",0),"123456");

            }

                break;
            case R.id.mine_tv_gongyi:
                startActivity(PaymentsActivity.class);
                break;
            case R.id.mine_tv_balance:
                startActivity(BalanceActivity.class);
                break;
            case R.id.mine_tv_earnings:
                startActivity(EarningActivity.class);
                break;
            case R.id.mine_tv_message:
               startActivity(MessageActivity.class);
                break;
            case R.id.mine_tv_code:
                goGeneralize();
                break;

        }

    }
    /**
     * 购买商品后才能分享
     */
    private void goGeneralize() {
        BaseApi.getRetrofit()
                .create(GeneralizeApi.class)
                .generalize()
                .compose(RxSchedulers.<BaseModel<GeneralizeModel>>compose())
                .subscribe(new BaseObserver<GeneralizeModel>() {
                    @Override
                    protected void onHandleSuccess(GeneralizeModel generalizeModel, String msg, int code) {
                        startActivity(GeneralizeActivity.class);
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setMessage("您需购买商品后才能拥有分享二维码！");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    }
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }

    private void loginHx(final String uname, final String upwd) {

        // login huanxin server
        ChatClient.getInstance().login(uname, upwd, new Callback() {
            @Override
            public void onSuccess() {
                startActivity(ChatActivity.class);
            }

            @Override
            public void onError(int code, String error) {
                LogUtil.e(code+error);
                ToastUtils.show("环信登录失败："+error);
               // loginHx("qiaozhijinhan"+PublicStaticData.sharedPreferences.getInt("id",0),"123456");
            }

            @Override
            public void onProgress(int progress, String status) {
                LogUtil.e(progress+status);
              //  loginHx("qiaozhijinhan"+PublicStaticData.sharedPreferences.getInt("id",0),"123456");
            }
        });
    }

    //查询是否有未读消息
    private void queryMessage(){
        BaseApi.getRetrofit().create(MineApi.class)
                .s_index()
                .compose(RxSchedulers.<BaseModel<Is_Read>>compose())
                .subscribe(new BaseObserver<Is_Read>() {
                    @Override
                    protected void onHandleSuccess(Is_Read is_read, String msg, int code) {
                        if (is_read.getIs_read() == 0){
                            view_circle.setVisibility(View.VISIBLE);
                        }else {
                            view_circle.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    protected void onHandleError(String msg) {

                    }
                });
    }
    public class Is_Read{
        private int is_read;

        public int getIs_read() {
            return is_read;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }
    }

    /**
     * 获取个人信息
     */
    private void getData() {
        BaseApi.getRetrofit()
                .create(MineApi.class)
                .information()
                .compose(RxSchedulers.<BaseModel<InfromationModel>>compose())
                .subscribe(new BaseObserver<InfromationModel>() {
                    @Override
                    protected void onHandleSuccess(InfromationModel infromationModel, String msg, int code) {
                        mineTvName.setText(infromationModel.getNickname());
                        GlideUtil.getInstance().loadCircleImage(getActivity(),mineIvHead,PublicStaticData.baseUrl+infromationModel.getHead_img());
                        if (infromationModel.getIs_buyed() == 1){
                            mintv_id.setText("ID："+PublicStaticData.sharedPreferences.getInt("id", 0));
                        }else {
                            mintv_id.setText("");
                        }

                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showTestToast(msg);

                    }
                });


    }
}
