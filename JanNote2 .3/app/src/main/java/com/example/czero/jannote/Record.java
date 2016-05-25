package com.example.czero.jannote;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import android.os.Environment;

import android.os.Handler;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/**
 * Created by zake on 4/21/16.
 */
public class Record extends Activity {

private Button mAudioStartBtn;
    private Button mAudioStopBtn;
    private File mRecAudioFile;
    private File mRecAudioPath;
    private File playFile;
    private MediaRecorder mMediaRecorder;
    private String strTempFile = "record:";
    private ListView mList;
    private NoteDB noteDB;
    private SQLiteDatabase dbwriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        initRecAudioPath();
        initList();
        noteDB=new NoteDB(this);
        dbwriter =noteDB.getWritableDatabase();
        initButton();
    }

    private void initList() {

        mList = (ListView) findViewById(R.id.lv);

        View emptyView = findViewById(R.id.empty);
        mList.setEmptyView(emptyView);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                List<Map<String, Object>> listdata = (List<Map<String, Object>>) mList
                        .getTag();
                Map<String, Object> map = listdata.get(position);
                String name = (String) map.get("text");

                playFile = new File(mRecAudioPath.getAbsolutePath()
                        + File.separator + name);
                playMusic(playFile);
            }
        });
        mList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            String mName = null;
            int mPosition = 0;

            @Override
            public boolean onItemLongClick(AdapterView<?> l, View v,
                                           int position, long id) {
                List<Map<String, Object>> listdata = (List<Map<String, Object>>) l
                        .getTag();
                Map<String, Object> map = listdata.get(position);

                mName = (String) map.get("text");
                mPosition = position;

                new AlertDialog.Builder(Record.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("操作选项").setMessage("删除\n\"" + mName + "\"取消")
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                new File(mRecAudioPath.getAbsolutePath()
                                        + File.separator + mName).delete();
                                deleteItem(mPosition);
                            }
                        }).setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int whichButton) {
                    }
                }).show();
                return true;
            }
        });
        musicList();
    }


    private void initButton() {
        mAudioStartBtn = (Button) findViewById(R.id.AudioStartBtn);
        mAudioStopBtn = (Button) findViewById(R.id.AudioStopBtn);
        mAudioStartBtn.setEnabled(true);
        mAudioStopBtn.setEnabled(false);
        mAudioStartBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                mAudioStartBtn.setEnabled(false);
                mAudioStopBtn.setEnabled(true);
                mHandler.sendEmptyMessage(MSG_RECORD);
            }
        });

        mAudioStopBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                mAudioStartBtn.setEnabled(true);
                mAudioStopBtn.setEnabled(false);
                mHandler.sendEmptyMessage(MSG_STOP);
            }
        });

    }

    private void startRecord() {
        try {
            if (!initRecAudioPath()) {
                return;
            }
            mAudioStartBtn.setEnabled(false);
            mAudioStopBtn.setEnabled(true);
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
//
            try {
                mRecAudioFile = File.createTempFile(strTempFile, ".amr",
                        mRecAudioPath);

            } catch (Exception e) {
                e.printStackTrace();
            }
            mMediaRecorder.setOutputFile(mRecAudioFile.getAbsolutePath());
            mMediaRecorder.prepare();
            mMediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopRecord() {
        if (mRecAudioFile != null) {
            mAudioStartBtn.setEnabled(true);
            mAudioStopBtn.setEnabled(false);
            mMediaRecorder.stop();
            addItem(mRecAudioFile);
            ContentValues cv = new ContentValues();
            cv.put(NoteDB.RECORD,mRecAudioFile+"");
            dbwriter.insert(NoteDB.TABLE_NAME, null, cv);
            mMediaRecorder.release();
            mMediaRecorder = null;
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(NoteDB.RECORD,playFile+" ");
//            dbwriter.insert(NoteDB.TABLE_NAME, null, cv);
        }
    }

    private static final int MSG_RECORD = 0;
    private static final int MSG_STOP = 1;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RECORD:
                    startRecord();
                    break;
                case MSG_STOP:
                    stopRecord();
                    break;
                default:
                    break;
            }
        };
    };
//
    private void playMusic(File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "audio");
        startActivity(intent);
    }

    public void musicList() {
        List<Map<String, Object>> listdata = getData();
        setListAdapter(listdata);
        mList.setTag(listdata);
    }

    private void addItem(File item) {
        List<Map<String, Object>> tag = (List<Map<String, Object>>) mList
                .getTag();
        List<Map<String, Object>> listdata = tag;
        listdata.add(getOneItem(item));
        setListAdapter(listdata);
    }
//
    private Map<String, Object> getOneItem(File file) {
        Map<String, Object> map = new HashMap<String, Object>();
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("###,###,###,###,###");
        map.put("text", file.getName());
        map.put("text_length", df.format(file.length()));
        map.put("text_time", GetFilePlayTime(file));
        return map;
    }


    public void deleteItem(int position) {
        List<Map<String, Object>> listdata = (List<Map<String, Object>>) mList
                .getTag();
        listdata.remove(position);
        setListAdapter(listdata);
    }

    private void setListAdapter(List<Map<String, Object>> listdata) {
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), listdata,
                R.layout.recordlistview, new String[] { "text","text_length","text_time" },
                new int[] { R.id.text,R.id.text_length,R.id.text_time });
        mList.setAdapter(adapter);
    }

    private MusicFilter mFilter = new MusicFilter();
    class MusicFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".amr"));
        }
    }

    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        File home = mRecAudioPath;
        if (home != null) {
            File[] files = home.listFiles(mFilter);
            if (files != null && files.length > 0) {
                for (File file : files) {
                    list.add(getOneItem(file));
                }
            }
        }
        return list;
    }

    private String GetFilePlayTime(File file){
        java.util.Date date;
        SimpleDateFormat sy1;
        String dateFormat = "error";

        try {
            sy1 = new SimpleDateFormat("HH:mm:ss");

            MediaPlayer mediaPlayer;
            mediaPlayer = MediaPlayer.create(getBaseContext(), Uri.parse(file.toString()));
            date = sy1.parse("00:00:00");
            date.setTime(mediaPlayer.getDuration() + date.getTime());
            dateFormat = sy1.format(date);

            mediaPlayer.release();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat;
    }




    private boolean sdcardIsValid() {
        if (Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            Toast.makeText(getBaseContext(), "请插入内存", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private boolean initRecAudioPath() {
        if (sdcardIsValid()) {
            String path = Environment.getExternalStorageDirectory().toString()
                    + File.separator + "record";
            mRecAudioPath = new File(path);
            if (!mRecAudioPath.exists()) {
                mRecAudioPath.mkdirs();
            }
        } else {
            mRecAudioPath = null;
        }
        return mRecAudioPath != null;
    }
}















































































































