package com.example.czero.szzj.SZZJModel;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;


public class PicText extends BmobObject {


	private String content = ""; // 价格
	private String name = "";
	private String time = "";

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {

		return time;
	}


	// 联系电话
	private BmobFile pictextimg = null; // 物品主图

	public String getContent() {
		return content;
	}

	public String getName() {
		return name;
	}

	public BmobFile getPictextimg() {
		return pictextimg;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPictextimg(BmobFile pictextimg) {
		this.pictextimg = pictextimg;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
