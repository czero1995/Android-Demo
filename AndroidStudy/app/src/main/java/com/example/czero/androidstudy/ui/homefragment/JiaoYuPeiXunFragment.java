package com.example.czero.androidstudy.ui.homefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.czero.androidstudy.R;
import com.example.czero.androidstudy.base.BaseFragment;


/**
 * Created by filip on 8/21/2015.
 */
public class JiaoYuPeiXunFragment extends BaseFragment {
    private ListView zhihulv;

    @Nullable
    @Override
    protected int obtainLayoutID() {
        return R.layout.home_jiaoyupeixun;
    }

    @Override
    protected void onViewCreated(View view) {
        zhihulv = (ListView) view.findViewById(R.id.zhihulv);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }
}
