package com.xyd.susong.api;

import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.EmptyModel;
import com.xyd.susong.message.MessageModel;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author: zhaoxiaolei
 * @date: 2017/8/16
 * @time: 9:47
 * @description:
 */

public interface MessageApi {
    /**
     * 0或1 0未读1已读
     *
     * @param is_read
     * @return
     */
    @POST("remind/index")
    Observable<BaseModel<MessageModel>> remind(@Query("is_read") int is_read,
                                               @Query("page") int page,
                                               @Query("num") int num);
    /**
     * @param r_id
     * @return
     */
    @POST("remind/do_read")
    Observable<BaseModel<EmptyModel>> do_read(@Query("r_id") int r_id
                                               );
}
