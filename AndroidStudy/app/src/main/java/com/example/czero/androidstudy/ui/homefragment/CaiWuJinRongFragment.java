package com.example.czero.androidstudy.ui.homefragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.czero.androidstudy.R;


/**
 * Created by filip on 8/21/2015.
 */
public class CaiWuJinRongFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.home_caiwujinrong,container,false);
    }
}