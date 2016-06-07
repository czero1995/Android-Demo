package com.example.czero.szzj.SZZJData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJModel.News;

import java.util.ArrayList;

/**
 * 适配器--适配二手交易物品列表数据
 * @date 2014-9-15
 * @author Stone
 */
public class NewsItemListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater = null;
	private ArrayList<News> mNews = null; //物品列表

	public NewsItemListAdapter(Context context, ArrayList<News> news) {
		mContext = context;
		mNews = news;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return mNews.size();
	}

	@Override
	public Object getItem(int position) {
		return mNews.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 刷新列表中的数据
	public void refresh(ArrayList<News> list) {
		mNews = list;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		NewsHolder newsHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.news_list_item, null);
			newsHolder = new NewsHolder();

			newsHolder.tvNewsType = (TextView) convertView
					.findViewById(R.id.tv_news_type);
			newsHolder.tvNewsTitle = (TextView) convertView
					.findViewById(R.id.tv_news_title);
			convertView.setTag(newsHolder);
			newsHolder.tvNewsDate= (TextView) convertView.findViewById(R.id.tv_news_date);
		} else {
			newsHolder = (NewsHolder) convertView.getTag();
		}
		newsHolder.tvNewsType.setText(mNews.get(position).getType());
		newsHolder.tvNewsTitle.setText(mNews.get(position).getTitle());
		newsHolder.tvNewsDate.setText(mNews.get(position).getCreatedAt());
		return convertView;
	}
	
}
