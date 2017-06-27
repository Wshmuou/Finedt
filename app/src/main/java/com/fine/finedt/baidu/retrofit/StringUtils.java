package com.fine.finedt.baidu.retrofit;


public class StringUtils {

	public static Boolean isEmpty(String value){
		if(value==null||value.trim().isEmpty()){
			return true;
		}
		return false;
	}
}
