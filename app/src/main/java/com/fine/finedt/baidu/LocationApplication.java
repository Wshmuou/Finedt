package com.fine.finedt.baidu;


import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baidu.mapapi.SDKInitializer;
import com.fine.finedt.baidu.retrofit.ResultDTO;
import com.fine.finedt.baidu.retrofit.Value;
import com.fine.finedt.baidu.service.LocationService;

/**
 * 主Application，所有百度定位SDK的接口说明请参考线上文档：http://developer.baidu.com/map/loc_refer/index.html
 * <p>
 * 百度定位SDK官方网站：http://developer.baidu.com/map/index.php?title=android-locsdk
 * <p>
 * 直接拷贝com.baidu.location.service包到自己的工程下，简单配置即可获取定位结果，也可以根据demo内容自行封装
 */
public class LocationApplication extends Application {
    public LocationService locationService;
    public Vibrator mVibrator;
    private static Context mContext = null;
    private static ListView lv;
    private int position=0;

    public static  ResultDTO resultDTO=null;
    private static String[] str=null;

    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());
    }

    public void getActivity(Context mContext) {
        this.mContext = mContext;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public int getPosition(){
        return position;
    }



    public void getListView(ListView lv){
        this.lv=lv;
    }

    public String[] getStr(){
        return str;
    }


    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Value.success:
                    switch (msg.arg1){
                        case Value.upload:
                        case Value.delete:
                        case Value.download:
                            resultDTO = JSONObject.parseObject(msg.obj.toString(),ResultDTO.class, Feature.IgnoreAutoType);;
                            break;
                    }
                    Toast.makeText(mContext, resultDTO.getMsg(), Toast.LENGTH_SHORT).show();
                    if (resultDTO.getMlatLngs()!=null)
                        if (resultDTO.getMlatLngs().size()>0){
                            str=new String[resultDTO.getMlatLngs().size()];
                            for (int i=0;i<resultDTO.getMlatLngs().size();i++){
                                str[i]=resultDTO.getMlatLngs().get(i).getEname();
                            }
                            lv.setAdapter(new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,str));
                        }
                    break;
                case Value.failure:
                    Toast.makeText(mContext, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
