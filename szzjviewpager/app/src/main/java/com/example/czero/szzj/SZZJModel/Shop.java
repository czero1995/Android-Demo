package com.example.czero.szzj.SZZJModel;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * 店铺实体类， 实现序列化, Activity之间实现传递
 * @date 2014-4-24
 * @author Stone
 */
public class Shop extends BmobObject implements Serializable {

	private String userID; 		// 主人
	private String type; 		// 类型(11代表第一个GridView中的第一个)
	private String name; 		// 店名
	private String location; 	// 地理位置
	private String phone1;
	private String phone2;
	private String phone3;	// 联系电话
	private String info; 		// 简介
	private String sale;
	private String waimaimenu1;// 促销信息
	private String waimaimenu2;
	private String waimaimenu3;
	private String waimaimenu4;
	private String waimaimenu5;
	private String waimaimenu6;
	private String waimaimenu7;
	private String waimaimenu8;
	private String waimaimenu9;
	private String waimaimenu10;
	private String waimaiprice1;
	private String waimaiprice2;
	private String waimaiprice3;
	private String waimaiprice4;
	private String waimaiprice5;
	private String waimaiprice6;

	public String getWaimaimenu1() {
		return waimaimenu1;
	}

	public String getWaimaimenu2() {
		return waimaimenu2;
	}

	public String getWaimaimenu3() {
		return waimaimenu3;
	}

	public String getWaimaimenu4() {
		return waimaimenu4;
	}

	public String getWaimaimenu5() {
		return waimaimenu5;
	}

	public String getWaimaimenu6() {
		return waimaimenu6;
	}

	public String getWaimaimenu7() {
		return waimaimenu7;
	}

	public String getWaimaimenu8() {
		return waimaimenu8;
	}

	public String getWaimaimenu10() {
		return waimaimenu10;
	}

	public String getWaimaimenu9() {
		return waimaimenu9;
	}

	public String getWaimaiprice1() {
		return waimaiprice1;
	}

	public String getWaimaiprice5() {
		return waimaiprice5;
	}

	public String getWaimaiprice2() {
		return waimaiprice2;
	}

	public String getWaimaiprice3() {
		return waimaiprice3;
	}

	public String getWaimaiprice4() {
		return waimaiprice4;
	}

	public String getWaimaiprice6() {
		return waimaiprice6;
	}

	public String getWaimaiprice7() {
		return waimaiprice7;
	}

	public String getWaimaiprice8() {
		return waimaiprice8;
	}

	public String getWaimaiprice9() {
		return waimaiprice9;
	}

	public String getWaimaiprice10() {
		return waimaiprice10;
	}

	private String waimaiprice7;
	private String waimaiprice8;
	private String waimaiprice9;
	private String waimaiprice10;
	// 促销信息// 促销信息
	@SuppressWarnings("unused")
	private BmobFile picShop; 	// 商店主图

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone(String phone) {
		this.phone1 = phone;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}
	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getPhone3() {
		return phone3;
	}
}
