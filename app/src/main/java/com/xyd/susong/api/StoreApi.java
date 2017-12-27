package com.xyd.susong.api;

import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.EmptyModel;
import com.xyd.susong.store.StoreModel;
import com.xyd.susong.winedetail.EvaluateModel;
import com.xyd.susong.winedetail.WineModel;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/20
 * @time: 10:36
 * @description:
 */

public interface StoreApi {
    /**
     * 商城列表
     * @return
     */
    @POST("goods/index")
    Observable<BaseModel<StoreModel>>    goodsList(@Query("page") int page,
                                                   @Query("type")int type);

    @POST("goods/detail")
    Observable<BaseModel<WineModel>>    goods(@Query("g_id") String g_id);


    /**
     *  商品评价列表
     * @param g_id
     * @param type  0全部3好评2中评1差评
     * @return
     */
    @POST("comment/index")
    Observable<BaseModel<EvaluateModel>>  comments(@Query("g_id") String g_id,
                                                   @Query("type") int type,
                                                   @Query("page") int page,
                                                   @Query("num")int num);

    /**
     * 评论
     * @param body
     * @return
     */
    @POST("comment/add")
    Observable<BaseModel<EmptyModel> >  addComment(@Body RequestBody body);



}
