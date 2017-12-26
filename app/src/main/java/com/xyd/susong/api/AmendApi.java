package com.xyd.susong.api;

import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.EmptyModel;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author: zhaoxiaolei
 * @date: 2017/9/6
 * @time: 10:15
 * @description:
 */

public interface AmendApi {
    /*
    * 修改密码
    * */
    @POST("user/update_key")
    Observable<BaseModel<EmptyModel>> getModification(@Query("old_password") String old_password,@Query("password") String password,@Query("repassword") String repassword);
}
