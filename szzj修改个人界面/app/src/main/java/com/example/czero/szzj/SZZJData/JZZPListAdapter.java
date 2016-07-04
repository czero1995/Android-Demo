package com.example.czero.szzj.SZZJData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.JZZP;

import java.util.ArrayList;

/**
 * 适配器--适配二手交易物品列表数据
 * @date 2014-9-15
 * @author Stone
 */
public class JZZPListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater = null;
	private ArrayList<JZZP> mjzzp = null; //物品列表

	public JZZPListAdapter(Context context, ArrayList<JZZP> jzzp) {
		mContext = context;
		mjzzp=jzzp;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mjzzp.size();
	}

	@Override
	public Object getItem(int position) {
		return mjzzp.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(ArrayList<JZZP> list) {
		mjzzp = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		JZZPItemHolder jzzpItemHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_jzzp, null);
			jzzpItemHolder = new JZZPItemHolder();
			jzzpItemHolder.tvJZZPName = (TextView) convertView
					.findViewById(R.id.tv_jzzp_name);
			jzzpItemHolder.tvJZZPContent = (TextView) convertView
					.findViewById(R.id.tv_jzzp_content);

			jzzpItemHolder.tvJZZPTime = (TextView) convertView
					.findViewById(R.id.tv_jzzp_time);
			jzzpItemHolder.tvJZZPPhone = (TextView) convertView
					.findViewById(R.id.tv_phone);
			convertView.setTag(jzzpItemHolder);
		} else {
			jzzpItemHolder = (JZZPItemHolder) convertView.getTag();
		}


		jzzpItemHolder.tvJZZPName.setText(mjzzp.get(position).getName());
		jzzpItemHolder.tvJZZPContent.setText(mjzzp.get(position).getContent());
		jzzpItemHolder.tvJZZPPhone.setText(mjzzp.get(position).getPhone());
		jzzpItemHolder.tvJZZPTime.setText(mjzzp.get(position).getCreatedAt());

//		mTradeItemList.get(position).getPicTradeItem().loadImageThumbnail(mContext, tradeItemHodler.imgTradeItem, 300, 300, 100);
		return convertView;
	}
	
}
