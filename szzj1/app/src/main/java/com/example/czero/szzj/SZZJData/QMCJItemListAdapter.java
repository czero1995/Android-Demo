package com.example.czero.szzj.SZZJData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.Course;
import com.example.czero.szzj.SZZJModel.QMCJ;

import java.util.ArrayList;

/**
 * 适配器--适配二手交易物品列表数据
 * @date 2014-9-15
 * @author Stone
 */
public class QMCJItemListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater = null;
	private ArrayList<QMCJ> mqmcj = null; //物品列表

	public QMCJItemListAdapter(Context context, ArrayList<QMCJ> qmcjs) {
		mContext = context;
		mqmcj = qmcjs;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mqmcj.size();
	}

	@Override
	public Object getItem(int position) {
		return mqmcj.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(ArrayList<QMCJ> list) {
		mqmcj = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		QMCJItemHolder qmcjItemHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_qmcj, null);
			qmcjItemHolder = new QMCJItemHolder();

			qmcjItemHolder.qmcj_name=(TextView) convertView.findViewById(R.id.qmcj_name);
			qmcjItemHolder.qmcj_xi=(TextView) convertView.findViewById(R.id.qmcj_xi);
			qmcjItemHolder.qmcj_zhuanye=(TextView) convertView.findViewById(R.id.qmcj_zhuanye);
			qmcjItemHolder.qmcj_cj=(TextView) convertView.findViewById(R.id.qmcj_cj);

			convertView.setTag(qmcjItemHolder);

		} else {
			qmcjItemHolder = (QMCJItemHolder) convertView.getTag();
		}

		qmcjItemHolder.qmcj_name.setText(mqmcj.get(position).getName());
		qmcjItemHolder.qmcj_xi.setText(mqmcj.get(position).getXi());
		qmcjItemHolder.qmcj_zhuanye.setText(mqmcj.get(position).getZhuanye());
		qmcjItemHolder.qmcj_cj.setText(mqmcj.get(position).getCj());
		return convertView;
	}
	
}
