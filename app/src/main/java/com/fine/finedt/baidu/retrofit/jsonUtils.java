package com.fine.finedt.baidu.retrofit;

import com.alibaba.fastjson.JSONObject;
import com.baidu.mapapi.model.LatLng;
import com.fine.finedt.baidu.bean.Electric;
import com.fine.finedt.baidu.bean.ElectricCacheBean;
import com.fine.finedt.baidu.bean.Latlngs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/11.
 */

public class jsonUtils {
    public static String getElectricToJson(ElectricCacheBean bean) {
        if (!StringUtils.isEmpty(bean.getNote())) {
            Electric electric = new Electric();
            electric.setEname(bean.getNote());
            Set<Latlngs> latlngsSet = new HashSet<Latlngs>();
            for (int j = 0; j < bean.getMlatLngs().size(); j++) {
                Latlngs latlngs = new Latlngs();
                latlngs.setMlatitude(bean.getMlatLngs().get(j).latitude);
                latlngs.setMlongitude(bean.getMlatLngs().get(j).longitude);
                latlngs.setMposition(j);
                latlngsSet.add(latlngs);
            }
            electric.setLatlngses(latlngsSet);
            return JSONObject.toJSONString(electric);
        }
        return null;
    }

    public static ElectricCacheBean getElectricToCache(Electric electric) {
        ElectricCacheBean cacheBean = new ElectricCacheBean(electric.getEid(), electric.getEname());
        List<LatLng> mlatLngs = new ArrayList<>();
        Map<Integer, LatLng> map = new HashMap<>();
        Iterator<Latlngs> iterator = electric.getLatlngses().iterator();
        while (iterator.hasNext()) {
            Latlngs latlngs = iterator.next();
            LatLng latLng = new LatLng(latlngs.getMlatitude(), latlngs.getMlongitude());
            map.put(latlngs.getMposition(), latLng);
        }
        for (int i = 0; i < map.size(); i++) {
            mlatLngs.add(map.get(i));
        }
        cacheBean.setMlatLngs(mlatLngs);
        return cacheBean;
    }

    public static List<ElectricCacheBean> getListCache(List<Electric> electrics) {
        List<ElectricCacheBean> list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list.add(getElectricToCache(electrics.get(i)));
        }
        return list.size() == 0 ? null : list;
    }
}
