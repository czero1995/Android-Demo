package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.JZZPListAdapter;
import com.example.czero.szzj.SZZJData.TradeItemListAdapter;
import com.example.czero.szzj.SZZJModel.JZZP;
import com.example.czero.szzj.SZZJModel.SecondTrade;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;


/**
 * 某一分类下的所有店铺页面
 * @author Stone
 * @date  2014-4-26 
 */
public class JZZPActivity extends Activity {


	private ListView lvjzzp;
	private JZZPListAdapter jzzpListAdapter;

	private List<JZZP> jzzpList = new ArrayList<JZZP>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activit_jzzp_all);

		lvjzzp = (ListView) findViewById(R.id.lv_jzzp);
		jzzpListAdapter = new JZZPListAdapter(JZZPActivity.this, (ArrayList<JZZP>) jzzpList);
		lvjzzp.setAdapter(jzzpListAdapter);
		getJZZPData();
	}

	private void getJZZPData(){
		BmobQuery<JZZP> query = new BmobQuery<JZZP>();
		query.order("-updatedAt");
		query.findObjects(this, new FindListener<JZZP>() {
			
		    @Override
		    public void onSuccess(List<JZZP> object) {

		        //toast("查询成功. 共计" + object.size());
		    	if(object.size()==0)
		    		toast("亲, 你来得太早了点哦");
		    	else {
		    		jzzpList = object;
			        // 通知Adapter数据更新
			    	jzzpListAdapter.refresh((ArrayList<JZZP>) jzzpList);
			    	//tradeItemListAdapter.notifyDataSetChanged();
				}
		    }
		    
			@Override
			public void onError(int arg0, String msg) {
				toast("查询失败:"+msg);
			}
			
		});
	}
	
	private void toast(String toast) {
		Toast.makeText(this, toast,  Toast.LENGTH_SHORT).show();
	}

}
