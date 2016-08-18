package com.example.zake.minimeet.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.zake.minimeet.R;
import com.example.zake.minimeet.base.BaseFragment;
import com.example.zake.minimeet.main.homefragment.CaiWuJinRongFragment;
import com.example.zake.minimeet.main.homefragment.ChuangYeFengTouFragment;
import com.example.zake.minimeet.main.homefragment.HuLianWangFragment;
import com.example.zake.minimeet.main.homefragment.JiaoYuPeiXunFragment;
import com.example.zake.minimeet.main.homefragment.ReMenTuiJianFragment;
import com.example.zake.minimeet.main.homefragment.ShengHuoXiuXianFragment;
import com.example.zake.minimeet.main.homefragment.YiLiaoJianKangFragment;
import com.example.zake.minimeet.main.homefragment.ZhiChangFaZhanFragment;

/**sda
 * Created by zake on 7/15/2016.
 */
public class HomeFragment extends BaseFragment{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected int obtainLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onViewCreated(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.home_pager_tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.home_viewPager);
        mViewPager.setAdapter(new CustomAdapter(getFragmentManager(),getContext()));
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected View getLoadingTargetView() {
        return mViewPager;
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }




    private class CustomAdapter extends FragmentPagerAdapter {

        private String fragments[] = {"热门推荐", "互联网+", "生活休闲", "职场发展","教育培训","创业风投","财务金融","医疗健康"};

        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new ReMenTuiJianFragment();
                case 1:
                    return new HuLianWangFragment();
                case 2:
                    return new ShengHuoXiuXianFragment();
                case 3:
                    return new ZhiChangFaZhanFragment();
                case 4:
                    return new JiaoYuPeiXunFragment();
                case 5:
                    return new ChuangYeFengTouFragment();
                case 6:
                    return new CaiWuJinRongFragment();
                case 7:
                    return new YiLiaoJianKangFragment();
                default:
                    return null;
            }

        }

        @Override
        public int getCount() {
            return fragments.length;    }
        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }
}
}
