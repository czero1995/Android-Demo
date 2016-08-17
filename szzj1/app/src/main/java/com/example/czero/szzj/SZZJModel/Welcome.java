package com.example.czero.szzj.SZZJModel;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;


public class Welcome extends BmobObject {



	private BmobFile picwelcome = null;

	public void setPicwelcome(BmobFile picwelcome) {
		this.picwelcome = picwelcome;
	}

	public BmobFile getPicwelcome() {

		return picwelcome;
	}



}
