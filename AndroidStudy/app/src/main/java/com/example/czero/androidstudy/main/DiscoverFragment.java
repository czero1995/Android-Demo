package com.example.czero.androidstudy.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.czero.androidstudy.R;
import com.example.czero.androidstudy.base.BaseFragment;
import com.example.czero.androidstudy.ui.homefragment.TabButtonActivity;
import com.example.czero.androidstudy.view.s.CircularAnimUtil.CircularAnimUtil;
import com.lzp.floatingactionbuttonplus.FabTagLayout;
import com.lzp.floatingactionbuttonplus.FloatingActionButtonPlus;

/**
 * Created by czero on 7/24/16.
 */

public class DiscoverFragment extends BaseFragment {
   private Button tiaozhuan;
    private FloatingActionButtonPlus mActionButtonPlus;
    public static DiscoverFragment newInstance() {
        DiscoverFragment fragment = new DiscoverFragment();
        return fragment;
    }
    @Override
    protected int obtainLayoutID() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void onViewCreated(View view) {

       tiaozhuan= (Button) view.findViewById(R.id.tiaozhuan);
        tiaozhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CircularAnimUtil.startActivity((Activity) getContext(), TabButtonActivity.class, view, R.drawable.ic_background);
            }
        });
    }


    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }
}
