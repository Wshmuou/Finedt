package com.fine.finedt.baidu;


import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

/**
 * Created by Administrator on 2017/3/16.
 */

public class MyLocationListenner implements BDLocationListener {

    private BaiduMap mBaiduMap;
    private MapView mMapView;
    private BitmapDescriptor bitmap;
    private boolean isFirstLoc;

    public MyLocationListenner(BaiduMap mBaiduMap, MapView mMapView, BitmapDescriptor bitmap, boolean isFirstLoc) {
        this.mBaiduMap = mBaiduMap;
        this.mMapView = mMapView;
        this.bitmap = bitmap;
        this.isFirstLoc = isFirstLoc;
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        // map view 销毁后不在处理新接收的位置
        if (location == null || mMapView == null) {
            return;
        }
        mBaiduMap.clear();
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);
        LatLng llA=new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bitmap)
                .zIndex(9).draggable(true);
        mBaiduMap.addOverlay(ooA);

        if (isFirstLoc) {
            isFirstLoc = false;
            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }
}
