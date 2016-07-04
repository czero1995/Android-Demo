package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.WaimaiListAdapter;
import com.example.czero.szzj.SZZJModel.Shop;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

public class WaimaiActivity extends Activity implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener{
    private TextView tvTitle;
    private TextView tvEmptyBg;  //当数据为空时现实的视图

    private ListView lvShopAllList;
    private WaimaiListAdapter waimaiListAdapter;
    private SwipeRefreshLayout swipeLayout;
    //记录从ShopActivity中传过来的当前点击项的类型
    private String type;
    private List<Shop> shopList = new ArrayList<Shop>();


    //下拉刷新
    private static final int STATE_REFRESH = 0;// 下拉刷新
    private int limit = 100;		// 每页的数据是10条
    private int curPage = 0;		// 当前页的编号，从0开始
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waimai);
        //得到从上级Activity中传入的Type数据
        type = getIntent().getStringExtra("type");

//        获取商店数据
        queryData(0, STATE_REFRESH);
        Shop shop = new Shop();
        initView();


    }
    public void initView() {

        //设置标题
        tvTitle = (TextView) findViewById(R.id.tv_title);
//        tvTitle.setText(getIntent().getStringExtra("title"));
        tvEmptyBg = (TextView) findViewById(R.id.ll_msg_empty);
        tvEmptyBg.setVisibility(View.GONE);

        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.lv_shop_all_swipe_container);

        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        lvShopAllList = (ListView) findViewById(R.id.lv_shop_all);
        waimaiListAdapter = new WaimaiListAdapter(this, (ArrayList<Shop>) shopList, type);
        lvShopAllList.setAdapter(waimaiListAdapter);
        lvShopAllList.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        //toast("点击了： " + position);
        //将当前点击的Shop对象传递给下一个Activity
        Intent toShopItem = new Intent(WaimaiActivity.this, WaimaiItemActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("shop", shopList.get(position) );
        bundle.putString("shopID", shopList.get(position).getObjectId()); //商铺的ID需要单独传递,否则获取到的是null
        toShopItem.putExtras(bundle);
        startActivity(toShopItem);
    }

    /**
     * 加载当前分类的所有店铺到ListView中
     */
    @SuppressWarnings("unused")
    private void getShopsDate() {

        BmobQuery<Shop> query = new BmobQuery<Shop>();
        query.order("--updatedAt");
        Shop shop = new Shop();
        shop.setType(type);
        query.addWhereEqualTo("type", shop.getType());    // 查询当前类型的所有店铺
        query.findObjects(this, new FindListener<Shop>() {

            @Override
            public void onSuccess(List<Shop> object) {
                //toast("查询成功. 共计" + object.size());
                if(object.size()==0)
                    toast("亲, 你来得太早了点哦");
                shopList = object;
                // 通知Adapter数据更新
                waimaiListAdapter.refresh((ArrayList<Shop>) shopList);
                waimaiListAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(int arg0, String msg) {
                toast("查询失败,请检查网络");
            }

        });
    }

    /**
     * 分页获取数据
     * @param page	页码
     * @param actionType	ListView的操作类型（下拉刷新、上拉加载更多）
     */
    private void queryData(final int page, final int actionType){

        BmobQuery<Shop> query = new BmobQuery<Shop>();
        Shop shop = new Shop();
        shop.setType(type);
        query.addWhereEqualTo("type", shop.getType());    // 查询当前类型的所有店铺
        query.order("-createdAt");
        query.setLimit(limit);			// 设置每页多少条数据
        query.setSkip(page*limit);		// 从第几条数据开始，
        query.findObjects(this, new FindListener<Shop>() {

            @Override
            public void onSuccess(List<Shop> arg0) {

                if(arg0.size()>0){

                    // 将本次查询的数据添加到bankCards中
                    for (Shop shop : arg0) {
                        shopList.add(shop);
                    }
                    // 通知Adapter数据更新
                    waimaiListAdapter.refresh((ArrayList<Shop>) shopList);
                    waimaiListAdapter.notifyDataSetChanged();
                    // 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
                    curPage++;
                }else {
                    if(page == 0)
                    {
                        tvEmptyBg.setVisibility(View.VISIBLE);
                    }
                    toast("没有更多数据了");
                }
            }

            @Override
            public void onError(int arg0, String arg1) {
                toast("查询失败:"+arg1);
            }
        });
    }

    private void toast(String toast) {
        Toast.makeText(this, toast,  Toast.LENGTH_SHORT).show();
    }



    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                swipeLayout.setRefreshing(false);
                queryData(curPage, STATE_REFRESH);
            }
        }, 1000);
    };
}
