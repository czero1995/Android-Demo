package com.example.czero.szzj.SZZJModel;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 二手交易实体类
 * @date 2014-9-15
 * @author Stone
 */
@SuppressWarnings("serial")
public class Nsns extends BmobObject {
	private Integer textzan;
	private String test;
	private BmobFile nsnsimg = null;

	public BmobFile getNsnsimg() {
		return nsnsimg;
	}

	public void setNsnsimg(BmobFile nsnsimg) {
		this.nsnsimg = nsnsimg;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Integer getTextzan() {
		return textzan;
	}

	public void setTextzan(Integer textzan) {
		this.textzan = textzan;
	}
}
