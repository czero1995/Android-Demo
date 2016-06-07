package com.example.czero.zmsz.ZMSZData;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.czero.zmsz.R;
import com.example.czero.zmsz.ZMSZModel.SecondTrade;

import java.util.ArrayList;
/**
 * 适配器--适配二手交易物品列表数据
 * @date 2014-9-15
 * @author Stone
 */
public class TradeItemListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater = null;
	private ArrayList<SecondTrade> mTradeItemList = null; //物品列表

	public TradeItemListAdapter(Context context, ArrayList<SecondTrade> tradeItemList) {
		mContext = context;
		mTradeItemList = tradeItemList;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mTradeItemList.size();
	}

	@Override
	public Object getItem(int position) {
		return mTradeItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(ArrayList<SecondTrade> list) {
		mTradeItemList = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TradeItemHolder tradeItemHodler;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.second_trade_all_list_item, null);
			tradeItemHodler = new TradeItemHolder();
			tradeItemHodler.imgTradeItem = (ImageView) convertView
					.findViewById(R.id.img_trade_item);
			tradeItemHodler.tvTradeItemName = (TextView) convertView
					.findViewById(R.id.tv_trade_item_name);
			tradeItemHodler.tvTradeItemType = (TextView) convertView
					.findViewById(R.id.tv_trade_item_type);
			tradeItemHodler.tvTradeItemPrice = (TextView) convertView
					.findViewById(R.id.tv_trade_item_price);
			tradeItemHodler.tvTradeItemOwner = (TextView) convertView
					.findViewById(R.id.tv_trade_item_owner);
			tradeItemHodler.tvTradeItemTime = (TextView) convertView
					.findViewById(R.id.tv_trade_item_time);
			convertView.setTag(tradeItemHodler);
		} else {
			tradeItemHodler = (TradeItemHolder) convertView.getTag();
		}
		if (mTradeItemList.get(position).getPicTradeItem() != null
				&& !mTradeItemList.get(position).getPicTradeItem().getFileUrl(mContext)
				.equals("")) {

//
//			 mTradeItemList.get(position).getPicTradeItem().loadImageThumbnail(mContext,
//			 tradeItemHodler.imgTradeItem, 300, 300, 100);
			mTradeItemList.get(position).getPicTradeItem().getFileUrl(mContext);

            /*ShopApplication.getInstance().getImageLoader().displayImage(
                    mTradeItemList.get(position).getPicTradeItem().getFileUrl(mContext),
                    tradeItemHodler.imgTradeItem,
                    options);*/
//			ShopApplication.getAQuery().id(tradeItemHodler.imgTradeItem).image(
//					mTradeItemList.get(position).getPicTradeItem().getFileUrl(mContext), true, true);
		}
		tradeItemHodler.tvTradeItemName.setText(mTradeItemList.get(position).getItem());
		tradeItemHodler.tvTradeItemType.setText(mTradeItemList.get(position).getType());
		tradeItemHodler.tvTradeItemPrice.setText("￥ " + mTradeItemList.get(position).getPrice());
		tradeItemHodler.tvTradeItemOwner.setText(mTradeItemList.get(position).getOwner());
		tradeItemHodler.tvTradeItemTime.setText(mTradeItemList.get(position).getCreatedAt());
		return convertView;
	}
	
}
