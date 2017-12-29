package com.xyd.susong.api;

import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.EmptyModel;
import com.xyd.susong.commissionorder.CommissionOrderModel;
import com.xyd.susong.commitorder.AliModel;
import com.xyd.susong.commitorder.CommitModel;
import com.xyd.susong.commitorder.PayFinishActivity;
import com.xyd.susong.commitorder.WxModel;
import com.xyd.susong.logistics.LogisticsModel;
import com.xyd.susong.order.OrderModel;
import com.xyd.susong.orderdetail.OrderDetailModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/20
 * @time: 10:47
 * @description:
 */

public interface OrderApi {

    /**
     * 查看物流信息
     *
     * @param order_num
     * @return
     */
    @POST("orders/check_logistics")
    Observable<BaseModel<LogisticsModel>> logistics(@Query("order_num") String order_num);

    /**
     * 立即购买
     *
     * @param a_id    收货地址ID
     * @param g_id    产品id
     * @param num     购买数量
     * @param price   付款金额
     * @param content 留言（可以为空）
     * @return
     */
    @POST("wechat_pay/wechat_pay")
    Observable<BaseModel<WxModel>> buy(@Query("a_id") String a_id,
                                       @Query("g_id") String g_id,
                                       @Query("num") String num,
                                       @Query("price") String price,
                                       @Query("content") String content);

    @POST("alipay/ali_pay")
    Observable<BaseModel<AliModel>> buyAliPay(@Query("a_id") String a_id,
                                              @Query("g_id") String g_id,
                                              @Query("num") String num,
                                              @Query("price") String price,
                                              @Query("content") String content);
    /**
     * 确认收货
     *
     * @param order_num
     * @return
     */
    @POST("orders/edit_status")
    Observable<BaseModel<EmptyModel>> edit_status(@Query("order_num") String order_num);

    /**
     * 我的订单
     *
     * @param state 0全部  1待收货  2全部
     * @return
     */
    @POST("orders/index")
    Observable<BaseModel<OrderModel>> orders(@Query("state") String state,
                                             @Query("page") int page,
                                             @Query("num") int num);

    /**
     * 提成订单
     *
     * @return
     */
    @POST("orders/deduct_order")
    Observable<BaseModel<CommissionOrderModel>> deduct_order(@Query("page") int page,
                                                             @Query("num") int num);
    /**
     * 支付成功回调信息
     */
    @POST("sundry/pay_msg")
    Observable<BaseModel<PayFinishActivity.PayMsg>> payMsg();

    /**
     * 订单详情
     */
    @POST("orders/order_detail")
    Observable<BaseModel<OrderDetailModel>> orderDetail(@Query("order_num") String order_num);

    //下单商品详情
    @FormUrlEncoded
    @POST("orders/goodsinfo")
    Observable<BaseModel<CommitModel>> goodsInfo(@Field("content") String content);
}
