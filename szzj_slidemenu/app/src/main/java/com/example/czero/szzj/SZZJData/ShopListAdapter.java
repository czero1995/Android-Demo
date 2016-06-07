package com.example.czero.szzj.SZZJData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.Shop;

import java.util.ArrayList;

/**
 * 适配器--适配某一分类下的店铺列表数据
 * 
 * @date 2014-4-29
 * @author Stone
 */
public class ShopListAdapter extends BaseAdapter {

	@SuppressWarnings("unused")
	private Context mContext;
	
	private LayoutInflater mInflater = null;
	private ArrayList<Shop> mShopList = null; // 所选分类下的所有店铺列表
	private String mType; // 商店的分类

	public ShopListAdapter(Context context, ArrayList<Shop> shopList,
						   String type) {
		mContext = context;
		mShopList = shopList;
		mType = type;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mShopList.size();
	}

	@Override
	public Object getItem(int position) {
		return mShopList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(ArrayList<Shop> list) {
		mShopList = list;
		//将数字的类型编号转换为文字
		exchangeType(mType);
		notifyDataSetChanged();
	}

	/**
	 * 根据当前的type类型, 转换成相应的文字
	 * @date 2014-4-29
	 * @param typeString
	 */
	private void exchangeType(String typeString) {


	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ShopHolder shopHodler;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.waimai_all_list_item, null);
			shopHodler = new ShopHolder();
			shopHodler.tvShopName = (TextView) convertView
					.findViewById(R.id.tv_shop_name);
			shopHodler.tvShopLoc = (TextView) convertView
					.findViewById(R.id.tv_shop_location);
			shopHodler.tvwaimaiphone1= (TextView) convertView.findViewById(R.id.tv_waimai_phone1);
			shopHodler.tvwaimaiphone2= (TextView) convertView.findViewById(R.id.tv_waimai_phone2);
			convertView.setTag(shopHodler);
		} else {
			shopHodler = (ShopHolder) convertView.getTag();
		}

		shopHodler.tvShopName.setText(mShopList.get(position).getName());
		// 商店的类型需要单独处理
		shopHodler.tvShopLoc.setText(mShopList.get(position).getLocation());
		shopHodler.tvwaimaiphone1.setText(mShopList.get(position).getPhone1());
		shopHodler.tvwaimaiphone2.setText(mShopList.get(position).getPhone2());
		return convertView;
	}

}
