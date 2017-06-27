package com.fine.finedt.baidu.main;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.SpatialRelationUtil;
import com.fine.finedt.R;
import com.fine.finedt.baidu.LocationApplication;
import com.fine.finedt.baidu.bean.Electric;
import com.fine.finedt.baidu.bean.ElectricCacheBean;
import com.fine.finedt.baidu.listener.MyOrientationListener;
import com.fine.finedt.baidu.retrofit.jsonUtils;
import com.fine.finedt.baidu.retrofit.retrofitUtils;
import com.fine.finedt.baidu.view.BaseStripAdapter;
import com.fine.finedt.baidu.view.StripListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//setContentView(R.layout.activity_indoor_location);

public class IndoorLocationActivity extends AppCompatActivity implements BaiduMap.OnMapClickListener, BaiduMap.OnMarkerClickListener {

    // 定位相关
    LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    MyOrientationListener myOrientationListener;
    private String strPoint = "Point";

    //电栏域electricField
    private List<LatLng> myPoint = new ArrayList<>();
    private List<ElectricCacheBean> field = new ArrayList<>();
    private ElectricCacheBean electricCacheBean = null;

    //Polygon
    Map<Long, Overlay> electricField = new HashMap<>();

    MapView mMapView;
    BaiduMap mBaiduMap;

    StripListView stripListView;
    BaseStripAdapter mFloorListAdapter;
    MapBaseIndoorMapInfo mMapBaseIndoorMapInfo = null;
    static Context mContext;
    // UI相关

    Button requestLocButton;
    boolean isFirstLoc = true; // 是否首次定位

    private boolean isRound = false;

    public EditText etName;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ((LocationApplication) getApplicationContext()).getActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        RelativeLayout layout = new RelativeLayout(this);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainview = inflater.inflate(R.layout.activity_indoor_location, null);
        layout.addView(mainview);
        requestLocButton = (Button) mainview.findViewById(R.id.button1);
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        requestLocButton.setVisibility(View.VISIBLE);
        requestLocButton.setText("普通");
        // 地图初始化
        mMapView = (MapView) mainview.findViewById(R.id.bmapView);
        etName = (EditText) mainview.findViewById(R.id.etName);

        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 开启室内图
        mBaiduMap.setIndoorEnable(true);
        //注册点击事件监听接口
        mBaiduMap.setOnMapClickListener(this);
        //覆盖物点击事件
        mBaiduMap.setOnMarkerClickListener(this);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(3000);
        mLocClient.setLocOption(option);

        /*-----------------onStart开启------------------------------*/
        // mLocClient.start();

        stripListView = new StripListView(this);
        layout.addView(stripListView);
        setContentView(layout);
        mFloorListAdapter = new BaseStripAdapter(IndoorLocationActivity.this);

        mBaiduMap.setOnBaseIndoorMapListener(new BaiduMap.OnBaseIndoorMapListener() {
            @Override
            public void onBaseIndoorMapMode(boolean b, MapBaseIndoorMapInfo mapBaseIndoorMapInfo) {
                if (b == false || mapBaseIndoorMapInfo == null) {
                    stripListView.setVisibility(View.INVISIBLE);
                    return;
                }
                mFloorListAdapter.setmFloorList(mapBaseIndoorMapInfo.getFloors());
                stripListView.setVisibility(View.VISIBLE);
                stripListView.setStripAdapter(mFloorListAdapter);
                mMapBaseIndoorMapInfo = mapBaseIndoorMapInfo;
            }
        });
        initOritationListener();
    }

    /**
     * 初始化方向传感器
     */
    private void initOritationListener() {
        myOrientationListener = new MyOrientationListener(
                getApplicationContext());
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                // 构造定位数据
                MyLocationData locData = new MyLocationData.Builder()
                        .accuracy(mCurrentAccracy)
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                        .direction(x)
                        .latitude(mCurrentLantitude)
                        .longitude(mCurrentLongitude)
                        .build();
                // 设置定位数据
                mBaiduMap.setMyLocationData(locData);
                // 设置自定义图标
                BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                        .fromResource(R.mipmap.navi_map_gps_locked);
                MyLocationConfiguration config = new MyLocationConfiguration(
                        mCurrentMode, true, mCurrentMarker);
                mBaiduMap.setMyLocationConfigeration(config);

            }
        });
    }


    public void myOnclick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                switch (mCurrentMode) {
                    case NORMAL:
                        requestLocButton.setText("跟随");
                        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
                        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true,
                                mCurrentMarker));
                        break;
                    case COMPASS:
                        requestLocButton.setText("普通");
                        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
                        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true,
                                mCurrentMarker));
                        break;
                    case FOLLOWING:
                        requestLocButton.setText("罗盘");
                        mCurrentMode = MyLocationConfiguration.LocationMode.COMPASS;
                        mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true,
                                mCurrentMarker));
                        break;
                    default:
                        break;
                }
                break;
            //回来
            case R.id.button2:
                center2myLoc();
                break;
            //取点
            case R.id.button3:
                if (electricCacheBean != null)
                    isRound = true;
                break;
            //清除上一条线路
            case R.id.button4:
                int num = field.size();
                if (num > 0) {
                    electricField.get(num).remove();
                    electricField.remove(num);
                    field.remove(num - 1);
                    Toast.makeText(this, "清除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "还没有创建", Toast.LENGTH_SHORT).show();
                }
                break;
            //保存
            case R.id.button5:
                field.get(0).setNote(etName.getText().toString());
                retrofitUtils.send(jsonUtils.getElectricToJson(field.get(0)), 1, 0);
                break;
            //显示
            case R.id.button6:
                if (field.size() > 0) {
                    addElectricField(field.get(field.size() - 1));
                } else {
                    Toast.makeText(this, "还没有线路", Toast.LENGTH_SHORT).show();
                }
                electricCacheBean = null;
                break;
            //开启一条线路
            case R.id.button7:
                if (electricCacheBean == null) {
                    electricCacheBean = new ElectricCacheBean(field.size() + 1);
                    myPoint.clear();
                } else {
                    Toast.makeText(this, "先结束取点，在开启", Toast.LENGTH_SHORT).show();
                }
                break;
            //结束
            case R.id.endPoint:
                if (electricCacheBean != null) {
                    if (myPoint.size() > 2) {
                        electricCacheBean.setMlatLngs(myPoint);
                        field.add(electricCacheBean);
                        electricCacheBean = null;
                    } else {
                        Toast.makeText(this, "请取两个以上的点", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请先取点", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    protected void onStart() {
        // 开启图层定位
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocClient.isStarted()) {
            mLocClient.start();
        }
        // 开启方向传感器
        myOrientationListener.start();
        String[] str = ((LocationApplication) getApplicationContext()).getStr();
        if (str != null) {
            if (str.length > 0) {
                List<Electric> mlatLngs = LocationApplication.resultDTO.getMlatLngs();
                for (int i = 0; i < mlatLngs.size(); i++) {
                    if (str[((LocationApplication) getApplicationContext()).getPosition()].equals(mlatLngs.get(i).getEname())) {
                        field.clear();
                        field.add(jsonUtils.getElectricToCache(mlatLngs.get(i)));
                        for (int index = 0; index < field.size(); index++) {
                            addElectricField(field.get(index));
                        }
                        break;
                    }
                }
            }
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        // 关闭图层定位
        mBaiduMap.setMyLocationEnabled(false);
        if (mLocClient != null)
            mLocClient.stop();
        // 关闭方向传感器
        myOrientationListener.stop();
        super.onStop();
    }

    private void center2myLoc() {
        LatLng ll = new LatLng(mCurrentLantitude, mCurrentLongitude);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mBaiduMap.animateMapStatus(u);
    }

    private static double mCurrentLantitude;
    private static double mCurrentLongitude;
    private static float mCurrentAccracy;

    private void addMarker(LatLng latLng) {
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.icon_marka);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(latLng)
                .icon(bitmap).title(strPoint);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Log.i("onMapClick", "latitude=" + latLng.latitude + "------------------latitude=" + latLng.longitude);
        if (isRound) {
            myPoint.add(latLng);
            addMarker(latLng);
            isRound = false;
        }
    }


    private void addElectricField(ElectricCacheBean electricCacheBean) {


        //构建用户绘制多边形的Option对象
        OverlayOptions polygonOption = new PolygonOptions()
                .points(electricCacheBean.getMlatLngs())
                .stroke(new Stroke(5, 0xAA00FF00))
                .fillColor(0xAAFFFF00);
        //在地图上添加多边形Option，用于显示
        Overlay overlay = mBaiduMap.addOverlay(polygonOption);
        electricField.put(electricCacheBean.getNoId(), overlay);
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    //覆盖物点击事件接口
    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.getTitle().equals(strPoint)) {
            marker.remove();
            for (int f = 0; f < field.size(); f++) {
                for (int k = 0; k < field.get(f).getMlatLngs().size(); k++) {
                    if (field.get(f).getMlatLngs().get(k) == marker.getPosition()) {
                        field.get(f).getMlatLngs().remove(k);
                    }
                }
            }
        }
        return false;
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        private String lastFloor = null;

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLantitude = location.getLatitude();
            mCurrentLongitude = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            String bid = location.getBuildingID();
            LatLng latLng = new LatLng(mCurrentLantitude, mCurrentLongitude);

            //判断是否在区域内
            if (field.size() > 0) {
                for (int j = 0; j < field.size(); j++) {
                    if (SpatialRelationUtil.isPolygonContainsPoint(field.get(j).getMlatLngs(), latLng)) {
                        Toast.makeText(IndoorLocationActivity.this, "在区域内", Toast.LENGTH_SHORT).show();
                        break;
                    } else {
                        Toast.makeText(IndoorLocationActivity.this, "不在区域内", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            if (bid != null && mMapBaseIndoorMapInfo != null) {
                Log.i("indoor", "bid = " + bid + " mid = " + mMapBaseIndoorMapInfo.getID());
                if (bid.equals(mMapBaseIndoorMapInfo.getID())) {// 校验是否满足室内定位模式开启条件
                    // Log.i("indoor","bid = mMapBaseIndoorMapInfo.getID()");
                    String floor = location.getFloor().toUpperCase();// 楼层
                    Log.i("indoor", "floor = " + floor + " position = " + mFloorListAdapter.getPosition(floor));
                    Log.i("indoor", "radius = " + location.getRadius() + " type = " + location.getNetworkLocationType());
                    boolean needUpdateFloor = true;
                    if (lastFloor == null) {
                        lastFloor = floor;
                    } else {
                        if (lastFloor.equals(floor)) {
                            needUpdateFloor = false;
                        } else {
                            lastFloor = floor;
                        }
                    }
                    if (needUpdateFloor) {// 切换楼层
                        mBaiduMap.switchBaseIndoorMapFloor(floor, mMapBaseIndoorMapInfo.getID());
                        mFloorListAdapter.setSelectedPostion(mFloorListAdapter.getPosition(floor));
                        mFloorListAdapter.notifyDataSetInvalidated();
                    }
                    if (!location.isIndoorLocMode()) {
                        mLocClient.startIndoorMode();// 开启室内定位模式，只有支持室内定位功能的定位SDK版本才能调用该接口
                        Log.i("indoor", "start indoormod");
                    }
                }
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100)
                    .latitude(mCurrentLantitude)
                    .longitude(mCurrentLongitude)
                    .build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(mCurrentLantitude, mCurrentLongitude);
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }

        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

/*    //
    private void isContainListener(boolean isContains) {
       *//* if (isContains){
            Log.i("TAG",true+"=======================================================");
        }else {
            Log.i("TAG",false+"=======================================================");
        }*//*
        Log.i("TAG", isContains + "=======================================================");
    }*/

    @Override
    protected void onPause() {
        if (mMapView != null)
            mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mMapView != null)
            mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        if (mLocClient != null)
            mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        if (mMapView != null)
            mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }
}