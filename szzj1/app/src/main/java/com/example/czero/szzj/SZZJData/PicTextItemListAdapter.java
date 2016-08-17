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
import com.example.czero.szzj.SZZJModel.PicText;
import com.example.czero.szzj.SZZJModel.SecondTrade;
import com.example.czero.szzj.SZZJView.PicTextActivity;

import java.util.ArrayList;

/**
 * 适配器--适配二手交易物品列表数据
 * @date 2014-9-15
 * @author Stone
 */
public class PicTextItemListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater = null;
	private ArrayList<PicText> mPictext = null; //物品列表
	private AQuery aq;




	public PicTextItemListAdapter(Context context, ArrayList<PicText> picTexts) {
		mContext = context;
		mPictext = picTexts;
		mInflater = LayoutInflater.from(context);
		aq = new AQuery(mContext);
	}




	@Override
	public int getCount() {
		return mPictext.size();
	}

	@Override
	public Object getItem(int position) {
		return mPictext.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(ArrayList<PicText> list) {
		mPictext = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		PicTextHolder picTextHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_pictext, null);
			picTextHolder = new PicTextHolder();
			picTextHolder.pictextimg = (ImageView) convertView
					.findViewById(R.id.pictextimg);
			picTextHolder.pictextname = (TextView) convertView
					.findViewById(R.id.pictextname);
			picTextHolder.pictextcontent = (TextView) convertView
					.findViewById(R.id.pictextcontent);
			picTextHolder.pictexttime= (TextView) convertView
					.findViewById(R.id.pictext_time);
			convertView.setTag(picTextHolder);
		} else {
			picTextHolder = (PicTextHolder) convertView.getTag();
		}

		AQuery aqImg = new AQuery(picTextHolder.pictextimg);
		PicText picText = mPictext.get(position);
		if (null != picText.getPictextimg() &&  !picText.getPictextimg().getFileUrl(mContext).isEmpty()) {
			String url = picText.getPictextimg().getFileUrl(mContext);
			if(url != null && !url.isEmpty()) {
				//shouldDelay(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent, String url)
				if(!aqImg.shouldDelay(position, false, convertView, parent, url))
					aqImg.image(url, true, true, 240, 0, null, android.R.anim.fade_in, 0.8f);
			}
		else {
			aqImg.id(R.id.pictextimg).image(R.drawable.ic_app);
		}
		}
		picTextHolder.pictextname.setText(mPictext.get(position).getName());
		picTextHolder.pictextcontent.setText(mPictext.get(position).getContent());
		picTextHolder.pictexttime.setText(mPictext.get(position).getTime());

		return convertView;
	}
	
}
