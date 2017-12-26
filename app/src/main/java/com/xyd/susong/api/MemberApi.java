package com.xyd.susong.api;

import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.EmptyModel;
import com.xyd.susong.member.MemberModel;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/16
 * @time: 11:41
 * @description:
 */

public interface MemberApi {


    @POST("exchange/add")
    Observable<BaseModel<EmptyModel>> add(@Query("u_id") int u_id,
                                          @Query("pid") int pid,
                                          @Query("e_comment") String e_comment);

    @POST("exchange/del")
    Observable<BaseModel<EmptyModel>> del(@Query("u_id") int u_id,
                                          @Query("pid") int pid);

    @POST("exchange/index")
    Observable<BaseModel<MemberModel>> exchange(@Query("u_id") int u_id,
                                                @Query("pid") int pid,
                                                @Query("page") int page,
                                                @Query("num") int num);

}
