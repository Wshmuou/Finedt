package com.fine.finedt.baidu.bean;

import com.baidu.mapapi.model.LatLng;

import java.util.List;

/**
 * Created by Administrator on 2017/4/5.
 */

public class ElectricCacheBean {
    private String note;
    private List<LatLng> mlatLngs;
    private long noId;

    public ElectricCacheBean(long noId, String note) {
        this.noId = noId;
        this.note = note;
    }

    public ElectricCacheBean(long noId) {
        this.noId = noId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<LatLng> getMlatLngs() {
        return mlatLngs;
    }

    public void setMlatLngs(List<LatLng> mlatLngs) {
        this.mlatLngs = mlatLngs;
    }

    public long getNoId() {
        return noId;
    }

    public void setNoId(long noId) {
        this.noId = noId;
    }
}
