package com.xyd.susong.test;

import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.EmptyModel;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * Created by Zheng on 2017/12/26.
 */

public interface TestService {
    @POST("login/testse1")
    Observable<BaseModel<EmptyModel>> test1();
    @POST("login/testse2")
    Observable<BaseModel<EmptyModel>> test2();
}
