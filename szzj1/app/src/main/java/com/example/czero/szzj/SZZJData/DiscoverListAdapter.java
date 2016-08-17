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
import com.example.czero.szzj.SZZJModel.Discover;
import com.example.czero.szzj.SZZJModel.Shop;

import java.util.ArrayList;

/**
 * 适配器--适配某一分类下的店铺列表数据
 * 
 * @date 2014-4-29
 * @author Stone
 */
public class DiscoverListAdapter extends BaseAdapter {

	private Context mContext;

	private LayoutInflater mInflater = null;
	private ArrayList<Discover> mdiscoverList = null; // 所选分类下的所有店铺列表
	private String mType;
	private AQuery aq;// 商店的分类

	public DiscoverListAdapter(Context context, ArrayList<Discover> discovers) {
		mContext = context;
		mdiscoverList = discovers;
		mInflater = LayoutInflater.from(context);
		aq = new AQuery(mContext);
	}

	@Override
	public int getCount() {
		return mdiscoverList.size();
	}

	@Override
	public Object getItem(int position) {
		return mdiscoverList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(ArrayList<Discover> list) {
		mdiscoverList = list;
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
		DiscoverHolder discoverHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_discover, null);
			discoverHolder = new DiscoverHolder();
			discoverHolder.imgDiscover= (ImageView) convertView.findViewById(R.id.img_discover);
			discoverHolder.tvDiscovername = (TextView) convertView
					.findViewById(R.id.tv_discover_name);
			discoverHolder.tvDiscovertype = (TextView) convertView
					.findViewById(R.id.tv_discover_type);
			discoverHolder.tvDiscovercontent= (TextView) convertView.findViewById(R.id.tv_discover_content);
			convertView.setTag(discoverHolder);
		} else {
			discoverHolder = (DiscoverHolder) convertView.getTag();
		}
		AQuery aqImg = new AQuery(discoverHolder.imgDiscover);
		Discover discover = mdiscoverList.get(position);
		if (null != discover.getPicDiscover() &&  !discover.getPicDiscover().getFileUrl(mContext).isEmpty()) {
			String url = discover.getPicDiscover().getFileUrl(mContext);
			if(url != null && !url.isEmpty()) {
				//shouldDelay(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent, String url)
				if(!aqImg.shouldDelay(position, false, convertView, parent, url))
					aqImg.image(url, true, true, 240, 0, null, android.R.anim.fade_in, 0.8f);
			}
			else {
				aqImg.id(R.id.img_discover).image(R.drawable.ic_app);
			}
		}









		discoverHolder.tvDiscovername.setText(mdiscoverList.get(position).getDiscovername());
		discoverHolder.tvDiscovertype.setText(mdiscoverList.get(position).getDiscovertype());
		discoverHolder.tvDiscovercontent.setText(mdiscoverList.get(position).getDiscovercontent());
		return convertView;
	}

}
