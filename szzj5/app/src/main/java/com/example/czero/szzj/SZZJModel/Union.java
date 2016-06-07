package com.example.czero.szzj.SZZJModel;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 二手交易实体类
 * @date 2014-9-15
 * @author Stone
 */
@SuppressWarnings("serial")
public class Union extends BmobObject {


	private String name = ""; //
	private String type = "";
	private String contact = "";
	private String bannar = "";
	private BmobFile picUnion = null;


	public String getBannar() {
		return bannar;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setBannar(String bannar) {
		this.bannar = bannar;
	}

	public String getContact() {

		return contact;
	}


	public void setPicUnion(BmobFile picUnion) {
		this.picUnion = picUnion;
	}

	public BmobFile getPicUnion() {

		return picUnion;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {

		return time;
	}

	private String time;// 联系电话





	public String getType() {
		return type;
	}





	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;

	}

	public void setType(String type) {
		this.type = type;
	}



}
