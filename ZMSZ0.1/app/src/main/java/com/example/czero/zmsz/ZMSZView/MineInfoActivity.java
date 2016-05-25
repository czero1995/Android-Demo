package com.example.czero.zmsz.ZMSZView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.czero.zmsz.R;
import com.example.czero.zmsz.ZMSZModel.User;

import cn.bmob.v3.BmobUser;

/**
 * 个人资料卡
 * @date 2014-5-21 
 * @author Stone
 */
public class MineInfoActivity extends Activity {

	private TextView tvUsername;
	private TextView tvSchool;
	private TextView tvCademy;
	private TextView tvDorPart;
	private TextView tvDorNum;
	private TextView tvPhone;
	private TextView tvQQ;
    public static final int MINE_INFO_FINISH_FIND_USER = 100;
	private User curUser ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mine_info);
		getCurUser();
	}

	private void initView() {
		tvUsername = (TextView) findViewById(R.id.tv_mineinfo_username);
		tvSchool = (TextView) findViewById(R.id.tv_mineinfo_school);
		tvCademy = (TextView) findViewById(R.id.tv_mineinfo_cademy);
		tvDorPart = (TextView) findViewById(R.id.tv_mineinfo_dorpart);
		tvDorNum = (TextView) findViewById(R.id.tv_mineinfo_dornum);
		tvPhone = (TextView) findViewById(R.id.tv_mineinfo_phone);
		tvQQ = (TextView) findViewById(R.id.tv_mineinfo_qq);

		tvUsername.setText(curUser.getUsername());
		tvSchool.setText(curUser.getSchool());
		tvCademy.setText(curUser.getCademy());
		tvDorPart.setText(curUser.getDorPart());
		tvDorNum.setText(curUser.getDorNum());
		tvPhone.setText(curUser.getPhone());
		tvQQ.setText(curUser.getQQ());
	}

	private void getCurUser() {
		curUser = BmobUser.getCurrentUser(this, User.class);
		if(curUser!=null)
		{
			Message msg = new Message();
            msg.what =MINE_INFO_FINISH_FIND_USER;
            mHandler.sendMessage(msg);
        }
	}

	private void refresh()
	{
		getCurUser();
		initView();
	}
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MINE_INFO_FINISH_FIND_USER:
                    initView();
                    break;
                default:
                    break;
            }
        }
    };

	public void clickEdit(View v) {
		Intent toEditMineInfo = new Intent(MineInfoActivity.this, MineInfoEditActivity.class);
//		Bundle bundle = new Bundle();
//		bundle.putString("username", curUser.getUsername());
//		bundle.putString("school", curUser.getSchool());
//		bundle.putString("cademy", curUser.getCademy());
//		bundle.putString("dorpart", curUser.getDorPart());
//		bundle.putString("dornum", curUser.getDorNum());
//		bundle.putString("phone", curUser.getPhone());
//		bundle.putString("qq", curUser.getQQ());
//		toEditMineInfo.putExtras(bundle);
		startActivityForResult(toEditMineInfo, 200);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 200) {
             refresh();
        }
	}
	
}
