package com.example.czero.szzj.SZZJModel;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;


public class Discover extends BmobObject {


	private String discovername = ""; //
	private String discovertype = "";
	private String discovercontent = "";
	private String discoveritemcontent = "";

	private BmobFile picDiscover = null;

	public String getDiscovername() {
		return discovername;
	}

	public String getDiscovertype() {
		return discovertype;
	}

	public BmobFile getPicDiscover() {
		return picDiscover;
	}

	public String getDiscovercontent() {
		return discovercontent;
	}
	public String getDiscoveritemcontent() {
		return discoveritemcontent;
	}

	public void setDiscovername(String discovername) {
		this.discovername = discovername;
	}

	public void setPicDiscover(BmobFile picDiscover) {
		this.picDiscover = picDiscover;
	}

	public void setDiscovertype(String discovertype) {
		this.discovertype = discovertype;
	}

	public void setDiscovercontent(String discovercontent) {
		this.discovercontent = discovercontent;
	}
	public void setDiscoveritemcontent(String discoveritemcontent) {
		this.discoveritemcontent = discoveritemcontent;
	}
}
