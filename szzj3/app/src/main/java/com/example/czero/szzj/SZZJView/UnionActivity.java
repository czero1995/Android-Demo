package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.TourListAdapter;
import com.example.czero.szzj.SZZJData.UnionItemListAdapter;
import com.example.czero.szzj.SZZJModel.Tour;
import com.example.czero.szzj.SZZJModel.Union;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;


/**
 * 某一分类下的所有店铺页面
 * @author Stone
 * @date  2014-4-26 
 */
public class UnionActivity extends Activity {


	private ListView lvunion;
	private UnionItemListAdapter unionItemListAdapter;

	private List<Union> unions = new ArrayList<Union>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activit_union_all);

		lvunion = (ListView) findViewById(R.id.lv_union);
		unionItemListAdapter = new UnionItemListAdapter(UnionActivity.this, (ArrayList<Union>) unions);
		lvunion.setAdapter(unionItemListAdapter);
		getUnionData();
	}

	private void getUnionData(){
		BmobQuery<Union> query = new BmobQuery<Union>();
		query.order("-updatedAt");
		query.findObjects(this, new FindListener<Union>() {
			
		    @Override
		    public void onSuccess(List<Union> object) {

		        //toast("查询成功. 共计" + object.size());
		    	if(object.size()==0)
		    		toast("亲, 你来得太早了点哦");
		    	else {
		    		unions = object;
			        // 通知Adapter数据更新
			    	unionItemListAdapter.refresh((ArrayList<Union>) unions);
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
