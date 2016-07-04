package com.example.czero.szzj.SZZJView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.czero.szzj.R;
import com.example.czero.szzj.SZZJData.HappyListAdapter;

import com.example.czero.szzj.SZZJData.QQUtil;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;

/**
 * Created by zake on 5/20/16.
 */
public class MeActivity extends Activity implements AdapterView.OnItemClickListener{

    private Button menu_sendwuchat;
    private CheckBox checkBox;
    private IWXAPI api;
    private static final String APP_ID = "wx9d0c2adb4dad845a";
    private TextView mUserInfo;
    private ImageView mUserLogo;
    public static Tencent mTencent;
    private static Intent mPrizeIntent = null;
    private static boolean isServerSideLogin = false;

    private String[] userItemNames = {"szzj"};
    private String[] userItemContents = {""};
    private String[] goItemNames = {"QQ登陆", "个人信息"};
    private String[] goItemContents = {"", ""};
    private String[] amuseItemNames = {"联系我们", "用户反馈", "推荐给朋友", "退出账号"};
    private String[] amuseItemContents = {"", "", "", ""};
    private int[] userImgIds = {R.drawable.ic_person};
    private int[] goImgIds = {R.drawable.ic_qq, R.drawable.ic_person};
    private int[] amuseImgIds = {R.drawable.ic_contact, R.drawable.ic_feedback, R.drawable.ic_share, R.drawable.ic_star_yes};
    private ListView lvMinUser;
    private ListView lvGo;
    private ListView lvAmuse;
    private HappyListAdapter userListAdapter;
    private HappyListAdapter goListAdapter;
    private HappyListAdapter amuseListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabme);
        lvMinUser = (ListView) findViewById(R.id.lv_mine_user);
        lvGo = (ListView) findViewById(R.id.lv_go);
        lvAmuse = (ListView) findViewById(R.id.lv_amuse);
        userListAdapter = new HappyListAdapter(this, userItemNames, userItemContents, userImgIds);
        goListAdapter = new HappyListAdapter(this, goItemNames, goItemContents, goImgIds);
        amuseListAdapter = new HappyListAdapter(this, amuseItemNames, amuseItemContents, amuseImgIds);
        lvMinUser.setAdapter(userListAdapter);
        lvGo.setAdapter(goListAdapter);
        lvAmuse.setAdapter(amuseListAdapter);
        lvMinUser.setOnItemClickListener(this);
        lvGo.setOnItemClickListener(this);
        lvAmuse.setOnItemClickListener(this);
        mUserInfo = (TextView) findViewById(R.id.qquser_nickname);
        mUserLogo = (ImageView) findViewById(R.id.qquser_logo);
        mTencent = Tencent.createInstance("1105370326", this.getApplicationContext());
        api = WXAPIFactory.createWXAPI(getBaseContext(), APP_ID);
        api.registerApp(APP_ID);
        menu_sendwuchat= (Button) findViewById(R.id.menu_sendwechat);

        checkBox= (CheckBox) findViewById(R.id.checkbox);

        menu_sendwuchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editor = new EditText(MeActivity.this);
                editor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
                final AlertDialog.Builder builder = new AlertDialog.Builder(MeActivity.this);
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setTitle("汕职之家");
                builder.setIcon(R.drawable.ic_app);
                builder.setView(editor);
                builder.setMessage("请输入要分享的文本");
                builder.setPositiveButton("分享", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        获取要分享的文本
                        String text = editor.getText().toString();
                        if (text == null || text.length() == 0) {
                            return;
                        }
                        //第一步:创建一个用于封装文本的WXTextObject对象
                        WXTextObject textObj = new WXTextObject();
                        textObj.text = text;
                        //第三部创建WXMediaMessage对象,用于向微信传送数据
                        WXMediaMessage msg = new WXMediaMessage();
                        msg.mediaObject = textObj;
                        msg.description = text;
                        //第三步:创建一个用于请求微信客户端的SendMessageTowx对象
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.message = msg;
                        //设置请求的唯一标识
                        req.transaction = buildTransaction("text");
                        //表示发送给朋友还是朋友圈
                        req.scene = checkBox.isChecked() ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
                        Toast.makeText(MeActivity.this, String.valueOf(api.sendReq(req)), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", null);
                final AlertDialog alert = builder.create();
                alert.show();


            }
        });
initView();
    }
    private void initView() {

        lvMinUser = (ListView) findViewById(R.id.lv_mine_user);
        lvGo = (ListView) findViewById(R.id.lv_go);
        lvAmuse = (ListView) findViewById(R.id.lv_amuse);
        userListAdapter = new HappyListAdapter(this, userItemNames, userItemContents, userImgIds);
        goListAdapter = new HappyListAdapter(this, goItemNames, goItemContents, goImgIds);
        amuseListAdapter = new HappyListAdapter(this, amuseItemNames, amuseItemContents, amuseImgIds);
        lvMinUser.setAdapter(userListAdapter);
        lvGo.setAdapter(goListAdapter);
        lvAmuse.setAdapter(amuseListAdapter);
        lvMinUser.setOnItemClickListener(this);
        lvGo.setOnItemClickListener(this);
        lvAmuse.setOnItemClickListener(this);

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.lv_mine_user) {
            switch (position) {
                case 0:
                    Intent toMineInfo = new Intent(MeActivity.this, MineInfoActivity.class);
                    startActivity(toMineInfo);
                    break;
            }
        }
        if (parent.getId() == R.id.lv_go) {
            switch (position) {
                case 0:
                    if (!mTencent.isSessionValid()) {
                        mTencent.login(MeActivity.this, "all", loginListener);
                        isServerSideLogin = false;
                        Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                    } else {
                        if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                            mTencent.logout(MeActivity.this);
                            mTencent.login(MeActivity.this, "all", loginListener);
                            isServerSideLogin = false;
                            Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                            return;
                        }
                        mTencent.logout(MeActivity.this);
                        updateUserInfo();
                        updateLoginButton();
                    }

                    break;
                case 1:
                    Intent toMineInfo = new Intent(MeActivity.this, MineInfoActivity.class);
                    startActivity(toMineInfo);
                    break;
            }
        }
        if (parent.getId() == R.id.lv_amuse) {
            switch (position) {
                case 0:
                    Intent contact = new Intent(MeActivity.this, ContactActivity.class);
                    startActivity(contact);
                    break;
                case 1:
                    Intent feedback = new Intent(MeActivity.this, FeedbackActivity.class);
                    startActivity(feedback);
                    break;
                case 2:
                    toast("推荐给朋友");
                    break;
                case 3:
                    BmobUser.logOut(this);
                    Intent toLogin = new Intent(MeActivity.this, LoginActivity.class);
                    startActivity(toLogin);
                    finish();
                    break;
            }
        }
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private void toast(String toast) {
        Toast.makeText(MeActivity.this, toast, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.menu_mineinfo:
//                Intent intent1 = new Intent(MeActivity.this,MineInfoActivity.class);
//                startActivity(intent1);
//                break;
//
//            case R.id.menu_contact:
//                Intent intent4 = new Intent(MeActivity.this,ContactActivity.class);
//                startActivity(intent4);
//                break;
//            case R.id.menu_feedback:
//                Intent intent5 = new Intent(MeActivity.this,FeedbackActivity.class);
//                startActivity(intent5);
//                break;
//            case R.id.menu_logout:
//                BmobUser.logOut(this);
//                Intent toLogin = new Intent(MeActivity.this, LoginActivity.class);
//                startActivity(toLogin);
//                finish();
//                break;
//            case R.id.qqlogin:
//                loginout.setVisibility(View.GONE);
//                if (!mTencent.isSessionValid()) {
//                    mTencent.login(MeActivity.this, "all", loginListener);
//                    isServerSideLogin = false;
//                    Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
//                } else {
//                    if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
//                        mTencent.logout(MeActivity.this);
//                        mTencent.login(MeActivity.this, "all", loginListener);
//                        isServerSideLogin = false;
//                        Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
//                        return;
//                    }
//                    mTencent.logout(MeActivity.this);
//                    updateUserInfo();
//                    updateLoginButton();
//                }
//
//        }
//    }
    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
            initOpenidAndToken(values);
            updateUserInfo();
            updateLoginButton();
        }
    };
    private void updateLoginButton() {
        if (mTencent != null && mTencent.isSessionValid()) {
//            if (isServerSideLogin) {
//                qqlogin.setTextColor(Color.BLUE);
//                qqlogin.setText("登录");
//                ;
//            } else {
//                qqlogin.setTextColor(Color.RED);
//                qqlogin.setText("退出帐号");
//
//            }
//        } else {
//            qqlogin.setTextColor(Color.BLUE);
//            qqlogin.setText("登录");
        }
//        }
    }
    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                QQUtil.showResultDialog(MeActivity.this, "返回为空", "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                QQUtil.showResultDialog(MeActivity.this, "返回为空", "登录失败");
                return;
            }
            QQUtil.showResultDialog(MeActivity.this, response.toString(), "登录成功");
            // 有奖分享处理
            handlePrizeShare();
            doComplete((JSONObject) response);
        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }

        protected void doComplete(JSONObject values) {

        }

        private void handlePrizeShare() {
            // -----------------------------------
            // 下面的注释请勿删除，编译lite版的时候需要删除, 注意//[不要有空格。
            //[liteexludestartmeta]
            if (null == mPrizeIntent || null == mTencent) {
                return;
            }
            // 有奖分享处理
            boolean hasPrize = mTencent.checkPrizeByIntent(MeActivity.this, mPrizeIntent);
            if (hasPrize) {
                QQUtil.showConfirmCancelDialog(MeActivity.this, "有奖品领取", "请使用QQ登录后，领取奖品！", prizeShareConfirmListener);
            }
            //[liteexludeendmeta]
        }
    }
    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                JSONObject response = (JSONObject) msg.obj;
                if (response.has("nickname")) {
                    try {
                        mUserInfo.setVisibility(View.VISIBLE);
                        mUserInfo.setText(response.getString("nickname"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }else if(msg.what == 1){
                Bitmap bitmap = (Bitmap)msg.obj;
                mUserLogo.setImageBitmap(bitmap);
                mUserLogo.setVisibility(View.VISIBLE);
            }
        }

    };

    private void updateUserInfo() {
        if (mTencent != null && mTencent.isSessionValid()) {
            IUiListener listener = new IUiListener() {

                @Override
                public void onError(UiError e) {

                }

                @Override
                public void onComplete(final Object response) {
                    Message msg = new Message();
                    msg.obj = response;
                    msg.what = 0;
                    mHandler.sendMessage(msg);
                    new Thread(){

                        @Override
                        public void run() {
                            JSONObject json = (JSONObject)response;
                            if(json.has("figureurl")){
                                Bitmap bitmap = null;
                                try {
                                    bitmap = QQUtil.getbitmap(json.getString("figureurl_qq_2"));
                                } catch (JSONException e) {

                                }
                                Message msg = new Message();
                                msg.obj = bitmap;
                                msg.what = 1;
                                mHandler.sendMessage(msg);
                            }
                        }

                    }.start();
                }

                @Override
                public void onCancel() {

                }
            };
            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(listener);

        } else {
            mUserInfo.setText("");
            mUserInfo.setVisibility(View.GONE);
            mUserLogo.setVisibility(View.GONE);
        }
    }
    private DialogInterface.OnClickListener prizeShareConfirmListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    boolean isLogin = mTencent.isSessionValid();
                    if (isLogin) {
                        // 本地已经有保存openid和accesstoken的情况下，先调用mTencent.setAccesstoken().
                        // 也可在奖品列表页，主动调用此接口获取未领取的奖品
                        if (null != mPrizeIntent) {
                            mTencent.queryUnexchangePrize(MeActivity.this, mPrizeIntent.getExtras(),
                                    prizeQueryUnexchangeListener);
                        }
                    } else {
                        // 未登陆提示用户使用QQ号登陆
                        onClickLogin();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private IUiListener prizeQueryUnexchangeListener = new IUiListener() {

        @Override
        public void onError(UiError e) {
            QQUtil.toastMessage(MeActivity.this, "onError: " + e.errorDetail);
            QQUtil.dismissDialog();
        }

        @Override
        public void onCancel() {
            QQUtil.toastMessage(MeActivity.this, "onCancel: ");
            QQUtil.dismissDialog();
        }

        @Override
        public void onComplete(Object response) {
            QQUtil.showConfirmCancelDialog(MeActivity.this, "兑换奖品", response.toString(),
                    new PrizeClickExchangeListener(response.toString()));
            // 兑换奖品后，mPrizeIntent 置为空
            mPrizeIntent = null;
        }
    };
    private IUiListener prizeExchangeListener = new IUiListener() {

        @Override
        public void onError(UiError e) {
            QQUtil.toastMessage(MeActivity.this, "onError: " + e.errorDetail);
            QQUtil.dismissDialog();
        }

        @Override
        public void onCancel() {
            QQUtil.toastMessage(MeActivity.this, "onCancel: ");
            QQUtil.dismissDialog();
        }

        @Override
        public void onComplete(Object response) {
            QQUtil.showResultDialog(MeActivity.this, response.toString(), "兑换信息");
        }
    };

    private class PrizeClickExchangeListener implements DialogInterface.OnClickListener {
        String response = "";

        PrizeClickExchangeListener(String strResponse) {
            response = strResponse;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    if (null != mTencent) {
                        Bundle params = new Bundle();
                        ArrayList<String> shareIdList = handlePrizeResponse(response);
                        if (null != shareIdList) {
                            ArrayList<String> list = new ArrayList<String>();
                            // 后台测试环境目前只支持一个shareid的兑换，正式环境会支持多个shareid兑换。
                            list.add(shareIdList.get(0));
                            params.putStringArrayList("shareid_list", list);
                            mTencent.exchangePrize(MeActivity.this, params, prizeExchangeListener);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };
    private ArrayList<String> handlePrizeResponse(String response) {
        ArrayList<String> shareIdList = new ArrayList<String>();
        if (TextUtils.isEmpty(response)) {
            return null;
        }
        try {
            JSONObject obj = new JSONObject(response);
            int code = obj.getInt("ret");
            int subCode = obj.getInt("subCode");
            if (code == 0 && subCode == 0) {
                JSONObject data = obj.getJSONObject("data");
                JSONArray prizeList = data.getJSONArray("prizeList");
                int size = prizeList.length();
                JSONObject prize = null;
                for (int i = 0; i < size; i++) {
                    prize = prizeList.getJSONObject(i);
                    if (null != prize) {
                        shareIdList.add(prize.getString("shareId"));
                    }
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return shareIdList;
    }
    private void onClickLogin() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
            isServerSideLogin = false;
            Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                mTencent.logout(this);
                mTencent.login(this, "all", loginListener);
                isServerSideLogin = false;
                Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                return;
            }
            mTencent.logout(this);
            updateUserInfo();
            updateLoginButton();
        }
    }
    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch(Exception e) {
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }
    }
    private UserInfo mInfo;
}

