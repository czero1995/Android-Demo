package com.example.czero.szzj.SZZJData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.SecondTrade;
import com.example.czero.szzj.SZZJModel.Union;

import java.util.ArrayList;

/**
 * 适配器--适配二手交易物品列表数据
 * @date 2014-9-15
 * @author Stone
 */
public class UnionItemListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater = null;
	private ArrayList<Union> munion = null; //物品列表

	public UnionItemListAdapter(Context context, ArrayList<Union> unions) {
		mContext = context;
		munion = unions;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return munion.size();
	}

	@Override
	public Object getItem(int position) {
		return munion.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(ArrayList<Union> list) {
		munion = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		UnionItemHolder unionItemHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.union_all_list_item, null);
			unionItemHolder = new UnionItemHolder();
			unionItemHolder.imgUnion = (ImageView) convertView
					.findViewById(R.id.img_uninon);
			unionItemHolder.tvUnionName = (TextView) convertView
					.findViewById(R.id.tv_union_name);
			unionItemHolder.tvUnionType = (TextView) convertView.findViewById(R.id.tv_union_type);
			convertView.setTag(unionItemHolder);
		} else {
			unionItemHolder = (UnionItemHolder) convertView.getTag();
		}

		unionItemHolder.tvUnionName.setText(munion.get(position).getName());
		unionItemHolder.tvUnionType.setText(munion.get(position).getType());
		return convertView;
	}
	
}
