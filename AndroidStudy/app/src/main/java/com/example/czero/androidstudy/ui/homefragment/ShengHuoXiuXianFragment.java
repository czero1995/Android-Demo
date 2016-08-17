package com.example.czero.androidstudy.ui.homefragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.example.czero.androidstudy.R;
import com.example.czero.androidstudy.adapter.ClumsyIndicator;
import com.example.czero.androidstudy.adapter.StaggerItemAdapter;
import com.example.czero.androidstudy.adapter.TopItemAdapter;
import com.example.czero.androidstudy.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by filip on 8/21/2015.
 */
public class ShengHuoXiuXianFragment extends BaseFragment {

    @Override
    protected int obtainLayoutID() {
        return R.layout.home_shenghuoxiuxian;
    }

    @Override
    protected void onViewCreated(View view) {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }
}
