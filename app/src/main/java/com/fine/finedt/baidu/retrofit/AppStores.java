package com.fine.finedt.baidu.retrofit;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/4/11.
 */

public interface AppStores {
//    http://localhost:8080/InspTrack/PostService/postResponse?type=1
//    http://localhost:8080/InspTrack/getResponse?type=2

    public final  static  String URL="http://192.168.1.115:8080/InspTrack/";

//   ------------------------------------------post-------------------------------------------------
    /**
     *     上传点位
     * @param type 请求类型
     * @param body json数据
     * @return
     */
    @POST("PostService/postResponse")
    @Headers({"Content-Type:application/json;charset=utf-8","Accept:application/json"})
    public Call<String> postService(@Query("type") int type, @Body RequestBody body);


//    ------------------------------------------get-------------------------------------------------

    /**
     *  下载点位
     * @param type
     * @return
     */
    @GET("getResponse")
    @Headers({"Content-Type:application/json;charset=utf-8","Accept:application/json"})
    public Call<String> getService(@Query("type") int type);

    /**
     *   删除 电子域
     * @param type
     * @param eid eid
     * @return
     */

    @GET("/getResponse")
    @Headers({"Content-Type:application/json;charset=utf-8","Accept:application/json"})
    public Call<String> getService(@Query("type") int type,@Query("eid")int eid);

//  ------------------------------------------------------------------------------------------------

}
