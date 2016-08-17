package com.example.czero.androidstudy.view.s.loadmore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.czero.androidstudy.R;

import java.util.List;

/**
 * @author SunnyCoffee
 * @date 2014-2-2
 * @version 1.0
 * @desc 适配器
 * 
 */
public class ListViewAdapter extends BaseAdapter {

	private ViewHolder holder;
	private List<String> list;
	private Context context;

	public ListViewAdapter(Context context, List<String> list) {
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_listview, null);
			holder.hometvmame = (TextView) convertView.findViewById(R.id.home_tv_name);
			holder.hometvjob = (TextView) convertView.findViewById(R.id.home_tv_userorjob);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.hometvmame.setText(list.get(position));
		holder.hometvjob.setText(list.get(position));
		return convertView;
	}

	private static class ViewHolder {
		TextView hometvmame;
		TextView hometvjob;
	}

}
