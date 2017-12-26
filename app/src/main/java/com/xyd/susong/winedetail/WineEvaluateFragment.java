package com.xyd.susong.winedetail;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xyd.susong.R;
import com.xyd.susong.api.StoreApi;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseFragment;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.PublicStaticData;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.view_img.ReviewImageActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/14
 * @time: 15:53
 * @description:
 */

public class WineEvaluateFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener,
        BaseQuickAdapter.RequestLoadMoreListener,BaseQuickAdapter.OnItemChildClickListener {

    @Bind(R.id.wine_evaluate_rg)
    RadioGroup wineEvaluateRg;
    @Bind(R.id.wine_evaluate_rv)
    RecyclerView wineEvaluateRv;
    @Bind(R.id.wine_evaluate_rg_all)
    RadioButton wineEvaluateRgAll;
    @Bind(R.id.wine_evaluate_rg_hao)
    RadioButton wineEvaluateRgHao;
    @Bind(R.id.wine_evaluate_rg_zhong)
    RadioButton wineEvaluateRgZhong;
    @Bind(R.id.wine_evaluate_rg_cha)
    RadioButton wineEvaluateRgCha;
    private String g_id;
    private int page = 1, num = 10, type = 0;
    private List<EvaluateModel.CommentsBean> list;
    private EvaluateAdapter adapter;
    ArrayList<String> imgList=new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wine_evaluate;
    }

    @Override
    protected void initView() {
        g_id = getArguments().getString(WineDetailActivity.G_ID);
        wineEvaluateRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        getData();

    }

    private void getData() {
        BaseApi.getRetrofit()
                .create(StoreApi.class)
                .comments(g_id, type, page, num)
                .compose(RxSchedulers.<BaseModel<EvaluateModel>>compose())
                .subscribe(new BaseObserver<EvaluateModel>() {
                    @Override
                    protected void onHandleSuccess(EvaluateModel evaluateModel, String msg, int code) {
                        adapter.loadMoreComplete();
                    if (page==1){
                        adapter.setNewData(evaluateModel.getComments());

                    }else if (evaluateModel.getComments().size()>0){
                        adapter.addData(evaluateModel.getComments());
                    }else {
                        adapter.loadMoreEnd();
                    }
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        adapter.loadMoreComplete();
                        showToast(msg);
                    }
                });
    }

    @Override
    protected void initEvent() {
        wineEvaluateRg.setOnCheckedChangeListener(this);
        list = new ArrayList<>();
        adapter = new EvaluateAdapter(list, getActivity());
        adapter.setOnLoadMoreListener(this, wineEvaluateRv);
        adapter.setOnItemChildClickListener(this);
//        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        wineEvaluateRv.setAdapter(adapter);
    }

    @Override
    public void widgetClick(View v) {

    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.wine_evaluate_rg_all:
                page=1;
                type=0;
                getData();
                break;
            case R.id.wine_evaluate_rg_hao:
                page=1;
                type=3;
                getData();
                break;
            case R.id.wine_evaluate_rg_zhong:
                page=1;
                type=2;
                getData();
                break;
            case R.id.wine_evaluate_rg_cha:
                page=1;
                type=1;
                getData();
                break;
        }
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        page++;
        getData();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adaptera, View view, int position) {
        switch (view.getId()){
            case R.id.evaluate_ll:
                imgList.clear();
                if (!TextUtils.isEmpty(adapter.getData().get(position).getImg_1())){
                    imgList.add(PublicStaticData.baseUrl+adapter.getData().get(position).getImg_1());
                }
                if (!TextUtils.isEmpty(adapter.getData().get(position).getImg_2())){
                    imgList.add(PublicStaticData.baseUrl+adapter.getData().get(position).getImg_2());
                }
                Intent intent=new Intent(getActivity(), ReviewImageActivity.class);
                intent.putStringArrayListExtra("imgList",imgList);
                startActivity(intent);
                break;
        }
    }
}
