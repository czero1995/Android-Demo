package com.example.czero.szzj.Tab;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.ShanZhiQuanListAdapter;
import com.example.czero.szzj.SZZJModel.LiuYanBan;
import com.example.czero.szzj.SZZJModel.ShanZhiQuan;
import com.example.czero.szzj.SZZJModel.User;
import com.example.czero.szzj.SZZJView.DongTaiActivity;
import com.example.czero.szzj.SZZJView.LoginActivity;
import com.example.czero.szzj.View.SupperTitle;
import com.sdsmdg.tastytoast.TastyToast;
import com.yalantis.phoenix.PullToRefreshView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;


public class ShanZhiQuanActivity extends Fragment{

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private ListView lvamusetalk;

    private SwipeRefreshLayout refreshamusetalk;
    private ShanZhiQuanListAdapter shanZhiQuanListAdapter;
    private List<ShanZhiQuan> shanZhiQuan = new ArrayList<ShanZhiQuan>();
    private User curUser;

    PullToRefreshView mPtrNewsList;

    public static ShanZhiQuanActivity newInstance(String param1) {
        ShanZhiQuanActivity fragment = new ShanZhiQuanActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public ShanZhiQuanActivity() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.activity_liuyanban, container, false);
        FloatingActionButton fab = (FloatingActionButton)myview.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "够真橙，活青春！", Snackbar.LENGTH_LONG)
                        .setAction("发动态", new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                curUser = BmobUser.getCurrentUser(getContext(), User.class);
                                if(curUser ==null){
                                    Toast.makeText(getContext(),"请先登陆",Toast.LENGTH_SHORT).show();
                                    Intent login = new Intent(getContext(), LoginActivity.class);
                                    startActivity(login);
                                }else {
                                    Intent i = new Intent(getContext(), DongTaiActivity.class);
                                    startActivity(i);
                                }
                            }
                        }).show();
            }
        });
        SupperTitle supperTitle;
        supperTitle = (SupperTitle) myview.findViewById(R.id.suppertitle);
        supperTitle.setTitle("汕职圈");
        supperTitle.setTitleBackground(getResources().getColor(R.color.white));
        lvamusetalk = (ListView) myview.findViewById(R.id.lv_amusetalk);
        mPtrNewsList= (PullToRefreshView)myview.findViewById(R.id.pull_to_refresh);
        mPtrNewsList.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAmuseTalkData();
                mPtrNewsList.setRefreshing(false);
            }
        });


        shanZhiQuanListAdapter = new ShanZhiQuanListAdapter(getContext(), (ArrayList<ShanZhiQuan>) shanZhiQuan);
        lvamusetalk.setAdapter(shanZhiQuanListAdapter);

        getAmuseTalkData();

        return myview;
    }
    private void getAmuseTalkData() {
        BmobQuery<ShanZhiQuan> query = new BmobQuery<ShanZhiQuan>();
        query.order("-createdAt");
        query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        query.findObjects(getContext(), new FindListener<ShanZhiQuan>() {

            @Override
            public void onSuccess(List<ShanZhiQuan> object) {


                    shanZhiQuan = object;
                    // 通知Adapter数据更新
                    shanZhiQuanListAdapter.refresh((ArrayList<ShanZhiQuan>) shanZhiQuan);


            }

            @Override
            public void onError(int arg0, String msg) {
                TastyToast.makeText(getContext(), "亲！请检查网络连接", TastyToast.LENGTH_LONG, TastyToast.WARNING);

            }

        });
    }





}
