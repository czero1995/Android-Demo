package com.example.zake.minimeet.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.zake.minimeet.R;
import com.example.zake.minimeet.base.BaseFragment;
import com.example.zake.minimeet.bean.UnreadMsgBean;
import com.example.zake.minimeet.view.bottomtabstrip.viewpagerindicator.CustomPagerSlidingTabStrip;
import com.example.zake.minimeet.view.bottomtabstrip.viewpagerindicator.ViewHolder;

/**
 * Created by zake on 7/15/2016.
 */
public class OrderFragment extends BaseFragment {
    private CustomPagerSlidingTabStrip mTabStrip;
    private ViewPager mViewPager;
    private OrderAdapter mOrderAdapter;
    private static final int VIEW_FIRST = 0;
    private static final int VIEW_SECOND = 1;
    private static final int VIEW_THIRD = 2;
    private static final int VIEW_SIZE = 3;
    private UnreadMsgBean mUnreadMsg;
    private int mUnreadNumInProgress = 0;
    private int mUnreadNumComment = 0;
    private int mUnreadNumEnded = 0;

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected int obtainLayoutID() {
        return R.layout.fragment_order;
    }

    @Override
    protected void onViewCreated(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.taborder_content);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }

    public static OrderFragment newInstance() {
        OrderFragment fragment = new OrderFragment();
        return fragment;
    }

    private void initViewPager() {
        mOrderAdapter = new OrderAdapter(getChildFragmentManager());
        mViewPager.setOffscreenPageLimit(VIEW_SIZE);
        mViewPager.setAdapter(mOrderAdapter);
        mOrderAdapter.notifyDataSetChanged();
        mTabStrip.setViewPager(mViewPager);
    }

    class OrderAdapter extends FragmentStatePagerAdapter implements CustomPagerSlidingTabStrip.CustomTabProvider {
        protected LayoutInflater mInflater;

        public OrderAdapter(FragmentManager fm) {
            super(fm);
            mInflater = LayoutInflater.from(mContext);
        }

        @Override
        public Fragment getItem(int position) {
            return null;
        }

        @Override
        public View getSelectTabView(int position, View convertView) {
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.common_select_tab, null);
            }
            TextView tv = ViewHolder.get(convertView, R.id.common_tv_selecttab);
            tv.setText(getPageTitle(position));
            TextView labelTV = ViewHolder.get(convertView, R.id.tv_unread_label);
            switch (position) {
                case 0:
                    if (mUnreadNumInProgress > 0) {
                        labelTV.setVisibility(View.VISIBLE);
                    } else {
                        labelTV.setVisibility(View.GONE);
                    }
                    break;
                case 1:
                    if (mUnreadNumComment > 0) {
                        labelTV.setVisibility(View.VISIBLE);
                    } else {
                        labelTV.setVisibility(View.GONE);
                    }
                    break;
                case 2:
                    if (mUnreadNumEnded > 0) {
                        labelTV.setVisibility(View.VISIBLE);
                    } else {
                        labelTV.setVisibility(View.GONE);
                    }
                    break;
            }
            return convertView;
        }

        @Override
        public View getDisSelectTabView(int position, View convertView) {
            return null;
        }

        @Override
        public int getCount() {
            return VIEW_SIZE;
        }
    }
    public CharSequence getPageTitle(int position) {
        if (position >= 0 && position < VIEW_SIZE) {
            switch (position) {
                case VIEW_FIRST:
                    return "进行中";
                case VIEW_SECOND:
                    return "待评价";
                case VIEW_THIRD:
                    return "已结束";
                default:
                    break;
            }
        }
        return null;
    }
}
