package com.example.czero.szzj.SZZJData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.AmuseTalk;
import com.example.czero.szzj.SZZJModel.Love;

import java.util.ArrayList;

/**
 * 适配器--适配二手交易物品列表数据
 * @date 2014-9-15
 * @author Stone
 */
public class AmuseTalkListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater = null;
	private ArrayList<AmuseTalk> mAmuseTalk = null; //物品列表

	public AmuseTalkListAdapter(Context context, ArrayList<AmuseTalk> amuseTalk) {
		mContext = context;
		mAmuseTalk = amuseTalk;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mAmuseTalk.size();
	}

	@Override
	public Object getItem(int position) {
		return mAmuseTalk.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(ArrayList<AmuseTalk> list) {
		mAmuseTalk = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AmuseTalkItemHolder amuseTalkItemHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.amusetalk_all_litem, null);
			amuseTalkItemHolder = new AmuseTalkItemHolder();

			amuseTalkItemHolder.tv_amusetalk_content = (TextView) convertView
					.findViewById(R.id.tv_amusetalk_content);
			amuseTalkItemHolder.tvTradeItemTime = (TextView) convertView
					.findViewById(R.id.tv_trade_item_time);
			convertView.setTag(amuseTalkItemHolder);
		} else {
			amuseTalkItemHolder = (AmuseTalkItemHolder) convertView.getTag();
		}
		amuseTalkItemHolder.tv_amusetalk_content.setText(mAmuseTalk.get(position).getAmusecontent());

		amuseTalkItemHolder.tvTradeItemTime.setText(mAmuseTalk.get(position).getCreatedAt());
		return convertView;
	}
	
}
