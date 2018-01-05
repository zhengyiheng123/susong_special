package com.xyd.susong.api;

import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.EmptyModel;
import com.xyd.susong.login.ChangePasswodModel;
import com.xyd.susong.login.LoginModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/19
 * @time: 14:51
 * @description:
 */

public interface LoginApi {
    /**
     * 登录
     *
     * @param phone
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("login/index")
    Observable<BaseModel<LoginModel>> login(@Field("phone") String phone,
                                            @Field("password") String password);

    /**
     * 三方登录
     * @param name
     * @param gender
     * @param uid
     * @return
     */
    @FormUrlEncoded
    @POST("tripartite_login/index")
    Observable<BaseModel<LoginModel>>  tripartite_login(@Field("name") String name,
                                                        @Field("gender") String gender,
                                                        @Field("uid") String uid);
    /**
     * 注册
     *
     * @param phone
     * @param password
     * @param repassword
     * @param referral_code 推荐码（可为空）
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("login/reg")
    Observable<BaseModel<EmptyModel>> register(@Field("phone") String phone,
                                    @Field("password") String password,
                                    @Field("repassword") String repassword,
                                    @Field("referral_code") String referral_code,
                                    @Field("code") String code);

    /**
     * @param phone
     * @param scene 发动场景1注册2忘记密码
     * @return
     */
    @FormUrlEncoded
    @POST("login/send_code")
    Observable<BaseModel<EmptyModel>> sendCode(@Field("phone") String phone,
                                    @Field("scene") int scene
    );

    /**
     * @param code
     * @param password
     * @param repassword   确认密码
     * @return
     */
    @FormUrlEncoded
    @POST("login/edit")
    Observable<BaseModel<EmptyModel>> forget(@Field("code") String code,
                                            @Field("password") String password,
                                            @Field("repassword") String repassword

    );
    /**
     * 品味宿松注册协议
     */
    @GET("user_agreement/index")
    Observable<BaseModel> xieyi();

    /*
    * 修改密码
    * */
    @FormUrlEncoded
    @POST("user/update_key")
    Observable<BaseModel<ChangePasswodModel>> getModification(@Field("old_password") String old_password,
                                                              @Field("password") String password,
                                                              @Field("repassword") String repassword);
}
