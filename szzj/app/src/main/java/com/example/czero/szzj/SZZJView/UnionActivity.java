package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.UnionItemListAdapter;
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
public class UnionActivity extends Activity implements AdapterView.OnItemClickListener{


	private ListView lvunion;
	private UnionItemListAdapter unionItemListAdapter;
    private Button unionbaoming;
	private List<Union> unions = new ArrayList<Union>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_union);

		lvunion = (ListView) findViewById(R.id.lv_union);
		unionItemListAdapter = new UnionItemListAdapter(UnionActivity.this, (ArrayList<Union>) unions);
		lvunion.setAdapter(unionItemListAdapter);
		lvunion.setOnItemClickListener(this);
		unionbaoming= (Button) findViewById(R.id.unionbaoming);
		unionbaoming.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(UnionActivity.this,UnionBaoming.class);
				startActivity(intent);
			}
		});
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent toShopItem = new Intent(UnionActivity.this, UnionItemActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable("union", unions.get(position) );
		bundle.putString("unionID", unions.get(position).getObjectId()); //商铺的ID需要单独传递,否则获取到的是null
		toShopItem.putExtras(bundle);
		startActivity(toShopItem);
	}
}
