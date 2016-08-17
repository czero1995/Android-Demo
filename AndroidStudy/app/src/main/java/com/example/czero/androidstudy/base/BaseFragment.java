package com.example.czero.androidstudy.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by czero on 7/24/16.
 */

public abstract class BaseFragment extends Fragment {
   protected Context mContext = null;
   @Override
   public void onAttach(Context context) {
      super.onAttach(context);
      mContext = context;
   }
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

   }
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View layout = inflater.inflate(obtainLayoutID(), container, false);
      return layout;
   }
   @Override
   public void onViewCreated(View view, Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      onViewCreated(view);
      initDatas(savedInstanceState);
   }
   /**
    * 返回onCreateView中需要的layoutID
    */
   protected abstract int obtainLayoutID();

   /**
    * 初始化控件
    */
   protected abstract void onViewCreated(View view);

   /**
    * 数据未显示时候加载布局所在的view
    */
   protected abstract View getLoadingTargetView();

   /**
    * 加载数据
    */
   protected abstract void initDatas(Bundle savedInstanceState);


}
