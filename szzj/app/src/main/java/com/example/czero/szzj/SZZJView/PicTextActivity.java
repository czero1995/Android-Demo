package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.PicTextItemListAdapter;
import com.example.czero.szzj.SZZJModel.PicText;
import com.example.czero.szzj.SZZJModel.SecondTrade;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by zake on 6/21/16.
 */
public class PicTextActivity extends Activity {
    private ListView lvpictext;
    private PicTextItemListAdapter picTextItemListAdapter;
    private List<PicText> picTextActivityList = new ArrayList<>();
    private Button pictextbaoming,pictextlook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pictext);
        initView();
        getPicTextData();
        pictextbaoming= (Button) findViewById(R.id.pictextbaoming);
        pictextlook= (Button) findViewById(R.id.pictextlook);
        pictextbaoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PicTextActivity.this,PicTextBaoming.class);
                startActivity(intent);
            }
        });
        pictextlook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("服务器正在创建,详细内容尽请期待!");
            }
        });
    }

    public void initView() {

        lvpictext = (ListView) findViewById(R.id.lv_pictext);
        picTextItemListAdapter = new PicTextItemListAdapter(this, (ArrayList<PicText>) picTextActivityList);
        lvpictext.setAdapter(picTextItemListAdapter);

    }

    private void getPicTextData() {
        BmobQuery<PicText> query = new BmobQuery<PicText>();
        query.order("-updatedAt");
        query.findObjects(this, new FindListener<PicText>() {

            @Override
            public void onSuccess(List<PicText> object) {

                //toast("查询成功. 共计" + object.size());
                if (object.size() == 0)
                    toast("亲, 你来得太早了点哦");
                else {
                    picTextActivityList = object;
                    // 通知Adapter数据更新
                    picTextItemListAdapter.refresh((ArrayList<PicText>) picTextActivityList);
                    //tradeItemListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(int arg0, String msg) {
                toast("查询失败:" + msg);
            }

        });
    }

    private void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    ;
}
