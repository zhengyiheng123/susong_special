package com.xyd.susong.api;

import com.xyd.susong.base.BaseModel;
import com.xyd.susong.generalize.GeneralizeModel;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/26
 * @time: 17:54
 * @description:
 */

public interface GeneralizeApi {

    @POST("expand/index")
    Observable<BaseModel<GeneralizeModel>>  generalize();

}
