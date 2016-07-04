package com.example.czero.szzj.SZZJData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.Union;
import com.example.czero.szzj.SZZJModel.Welcome;

import java.util.ArrayList;

/**
 * 适配器--适配二手交易物品列表数据
 * @date 2014-9-15
 * @author Stone
 */
public class WelcomeItemListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater = null;
	private ArrayList<Welcome> mwelcome = null; //物品列表
	private AQuery aq;
	public WelcomeItemListAdapter(Context context, ArrayList<Welcome> welcomes) {
		mContext = context;
		mwelcome = welcomes;
		mInflater = LayoutInflater.from(context);
		aq = new AQuery(mContext);
	}

	@Override
	public int getCount() {
		return mwelcome.size();
	}

	@Override
	public Object getItem(int position) {
		return mwelcome.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(ArrayList<Welcome> list) {
		mwelcome = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WelcomeHolder welcomeHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_welcome, null);
			welcomeHolder = new WelcomeHolder();
			welcomeHolder.welcomeimg = (ImageView) convertView
					.findViewById(R.id.img_welcome);

			convertView.setTag(welcomeHolder);
		} else {
			welcomeHolder = (WelcomeHolder) convertView.getTag();
		}
		AQuery aqImg = new AQuery(welcomeHolder.welcomeimg);
		Welcome welcome = mwelcome.get(position);
		if (null != welcome.getPicwelcome() &&  !welcome.getPicwelcome().getFileUrl(mContext).isEmpty()) {
			String url = welcome.getPicwelcome().getFileUrl(mContext);
			if(url != null && !url.isEmpty()) {
				//shouldDelay(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent, String url)
				if(!aqImg.shouldDelay(position, false, convertView, parent, url))
					aqImg.image(url, true, true, 480, 800, null, android.R.anim.fade_in, 0.8f);
			}
			else {
				aqImg.id(R.id.img_welcome);
//						.image(R.drawable.ic_app);
			}
		}

		return convertView;
	}
	
}
