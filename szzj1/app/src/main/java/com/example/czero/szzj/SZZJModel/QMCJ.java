package com.example.czero.szzj.SZZJModel;

import cn.bmob.v3.BmobObject;

/**
 * 二手交易实体类
 * @date 2014-9-15
 * @author Stone
 */
@SuppressWarnings("serial")
public class QMCJ extends BmobObject {

	private String number;
	private String password;
	private String name;
	private String xi;
	private String zhuanye;
	private String cj;

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumber() {
		return number;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setXi(String xi) {
		this.xi = xi;
	}

	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}

	public void setCj(String cj) {
		this.cj = cj;
	}

	public String getName() {
		return name;
	}

	public String getXi() {
		return xi;
	}

	public String getCj() {
		return cj;
	}

	public String getZhuanye() {
		return zhuanye;
	}
}

