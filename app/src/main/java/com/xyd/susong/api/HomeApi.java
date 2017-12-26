package com.xyd.susong.api;

import com.xyd.susong.base.BaseModel;
import com.xyd.susong.main.home.HomeModel;
import com.xyd.susong.main.home.HomeModelNew;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/27
 * @time: 13:44
 * @description:
 */

public interface HomeApi {

    @POST("index/index")
    Observable<BaseModel<HomeModelNew>>  home();

}
