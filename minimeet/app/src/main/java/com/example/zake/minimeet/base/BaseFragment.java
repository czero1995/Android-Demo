package com.example.zake.minimeet.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    protected Context mContext = null;

//    private VaryLoadingController mVaryLoadingController = null;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isBindEventBusHere()) {
//            EventBus.getDefault().register(this); //注册事件
        }
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
        if (getLoadingTargetView() != null)
//            mVaryLoadingController = new VaryLoadingController(getLoadingTargetView());
        initDatas(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBindEventBusHere()) {
//            EventBus.getDefault().unregister(this);  //取消事件注册
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(getClass().getSimpleName() + "");
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(getClass().getSimpleName() + "");
    }

    protected void showToast(String content) {

//        if (TextUtil.isValidate(content)) {
//            Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
//            Snackbar.make(((Activity) mContext).getWindow().getDecorView(), content, Snackbar.LENGTH_LONG).show();
        }

//    }

    /**
     * 特别注意: <br/>
     * 一定要在动态布局加载完成才调用 toggleShowLoading此方法！！！！<br/>
     * 显示是否正在加载中
     */
//    protected void toggleShowLoading(boolean toggle) {
//        if (null == mVaryLoadingController) {
//            throw new IllegalArgumentException("You must return a right target view for loading");
//        }
//
//        if (toggle) {
//            mVaryLoadingController.showLoading("");
//        } else {
//            mVaryLoadingController.restore();
//        }
//    }


    /**
     * 网络错误
     */
//    protected void showNetworkError(boolean toggle, View.OnClickListener onClickListener) {
//        if (null == mVaryLoadingController) {
//            throw new IllegalArgumentException("You must return a right target view for loading");
//        }
//
//        if (toggle) {
//            mVaryLoadingController.showNetworkError(onClickListener);
//        } else {
//            mVaryLoadingController.restore();
//        }
//    }

    /**
     * 加载失败
//     */
//    protected void toggleShowEmpty(boolean toggle, String content, View.OnClickListener onClickListener) {
//        if (null == mVaryLoadingController) {
//            throw new IllegalArgumentException("You must return a right target view for loading");
//        }
//
//        if (toggle) {
//            mVaryLoadingController.showEmpty("", onClickListener );
//        } else {
//            mVaryLoadingController.restore();
//        }
//    }

    /**
     * 是否绑定EventBus
     */
    protected abstract boolean isBindEventBusHere();

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
