package com.example.czero.zhihu.adapter;

import android.content.Context;

import android.media.Image;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.czero.zhihu.R;
import com.example.czero.zhihu.bean.News;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by czero on 7/25/16.
 */

public class NewsAdapter extends BaseAdapter{
    private Context c;
    private ArrayList<News> list;

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.ic_launcher)
            .showImageOnFail(R.mipmap.ic_launcher)
            .showImageForEmptyUri(R.mipmap.ic_launcher)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();
    public NewsAdapter(Context context,ArrayList<News> news) {
        this.c = context;
        this.list = news;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (viewHolder == null) {
            convertView = View.inflate(c, R.layout.listview_item, null);
            viewHolder = new ViewHolder();
            viewHolder.newstitle = (TextView) convertView.findViewById(R.id.news_title);
            viewHolder.newsimg = (ImageView) convertView.findViewById(R.id.news_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        News n = list.get(position);
        List<String> images = n.getImages();
        viewHolder.newstitle.setText(n.getTitle());
//        imageLoader.displayImage(String.valueOf(images),viewHolder.newsimg,options);
        if (images != null && images.size() > 0) {
            Glide.with(c).load(images.get(0)).placeholder(R.mipmap.ic_launcher).into(viewHolder.newsimg);
        }
        return convertView;
    }
class ViewHolder{
    TextView newstitle;
    ImageView newsimg;
}

}
