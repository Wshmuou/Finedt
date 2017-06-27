package com.fine.finedt.baidu.retrofit;

import com.fine.finedt.baidu.bean.Electric;

import java.util.List;


public class ResultDTO {

	private Integer success = 0;
	private String msg = "OK";
	
	private int size=0;
	private List<Electric> mlatLngs;
	
	public ResultDTO(){
	}
	public Integer getSuccess() {
		return success;
	}
	public void setSuccess(Integer success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<Electric> getMlatLngs() {
		return mlatLngs;
	}
	public void setMlatLngs(List<Electric> mlatLngs) {
		this.mlatLngs = mlatLngs;
	}
}
