package com.xyd.susong.api;

import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.EmptyModel;
import com.xyd.susong.shopchart.ChartModel;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Zheng on 2017/12/28.
 */

public interface ShopChartApi {
    @POST("good_cart/index")
    Observable<BaseModel<ChartModel>> getDataList();
    @POST("good_cart/edit")
//    type  add 增加 edit 编辑 del 删除
//    g_id   商品ID
//    num    商品数量/编辑必传、增加删除不传
    Observable<BaseModel<EmptyModel>> edit(@Query("type")String type,@Query("g_id")String g_id,@Query("num")String num);
}
