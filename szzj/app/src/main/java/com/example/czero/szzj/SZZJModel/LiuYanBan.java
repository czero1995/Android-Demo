package com.example.czero.szzj.SZZJModel;

import cn.bmob.v3.BmobObject;

/**
 * 二手交易实体类
 * @date 2014-9-15
 * @author Stone
 */
@SuppressWarnings("serial")
public class LiuYanBan extends BmobObject {


	public String getAmusecontent() {
		return amusecontent;
	}

	public void setAmusecontent(String amusecontent) {
		this.amusecontent = amusecontent;
	}

	private String amusecontent = ""; // 描述



}
