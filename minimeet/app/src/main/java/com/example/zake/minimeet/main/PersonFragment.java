package com.example.zake.minimeet.main;

import android.os.Bundle;
import android.view.View;

import com.example.zake.minimeet.R;
import com.example.zake.minimeet.base.BaseFragment;

/**
 * Created by zake on 7/15/2016.
 */
public class PersonFragment extends BaseFragment {
    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected int obtainLayoutID() {
        return R.layout.fragment_personal_center;
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
    public static PersonFragment newInstance() {
        PersonFragment fragment = new PersonFragment();
        return fragment;
    }

}
