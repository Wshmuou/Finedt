package com.fine.finedt.baidu.retrofit;

import android.os.Message;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.fine.finedt.baidu.LocationApplication.handler;

/**
 * Created by Administrator on 2017/4/11.
 */

public class retrofitUtils {

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(AppStores.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }


    public static void send(String json, final int type, int eid) {
        AppStores appStores = getRetrofit().create(AppStores.class);
        RequestBody body = null;
        Call<String> call = null;
        if (json != null)
            body = RequestBody.create(MediaType.parse("application/json;utf-8"), json);
        switch (type) {
            case Value.upload:
                if (body != null)
                    call = appStores.postService(type, body);
                break;
            case Value.download:
                call = appStores.getService(type);
                break;
            case Value.delete:
                call = appStores.getService(type,eid);
                break;
            default:
                break;
        }
        if (call != null)
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String json = response.body().toString();
                    Message message = new Message();
                    message.arg1=type;
                    message.what = Value.success;
                    message.obj = json;
                    handler.sendMessage(message);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Message m1 = new Message();
                    m1.what = Value.failure;
                    m1.obj = t.getMessage();
                    handler.sendMessage(m1);
                }
            });

    }
}
