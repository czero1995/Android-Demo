package com.example.czero.zmsz.ZMSZData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.czero.zmsz.R;
import com.example.czero.zmsz.ZMSZView.DelightActivity;
import com.example.czero.zmsz.ZMSZView.HappyActivity;

/**
 * Created by zake on 5/21/16.
 */
public class MineListAdapter extends BaseAdapter {
    private Context mContext;
    private String[] mItemNames;
    private String[] mItemContents;
    private int[] mItemImgIds;
    private LayoutInflater mInflater = null;

    public MineListAdapter(DelightActivity context, String[] names, String[] contents, int[] imgIds) {
        mContext = context;
        mItemNames = names;
        mItemContents = contents;
        mItemImgIds = imgIds;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mItemNames.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MineListHolder holder;
        if(convertView == null){
            convertView=mInflater.inflate(R.layout.minelist_item,null);
            holder = new MineListHolder();
            holder.imgItem = (ImageView) convertView.findViewById(R.id.img_item);
            holder.tvItemName= (TextView) convertView.findViewById(R.id.tv_item_name);
            holder.tvItemContent= (TextView) convertView.findViewById(R.id.tv_item_content);
            convertView.setTag(holder);
        }else{
            holder= (MineListHolder) convertView.getTag();
        }
        holder.imgItem.setBackgroundResource(mItemImgIds[position]);
        holder.tvItemName.setText(mItemNames[position]);
        holder.tvItemContent.setText(mItemContents[position]);
        return convertView;
    }
}
