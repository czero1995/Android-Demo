package com.example.czero.jannote;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by zake on 4/17/16.
 */
public class SelectContent extends Activity implements View.OnClickListener {
    private Button select_delete,select_back;
    private ImageView select_img;
    private ImageView select_draw;
    private VideoView select_video;
    private TextView select_text;
    private NoteDB noteDB;
    private SQLiteDatabase dbwriter;
    private Addcontent addcontent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectcontent);
        init();
        addcontent=new Addcontent();
    }

    private void init() {
        select_delete= (Button) findViewById(R.id.select_delete);
        select_back= (Button) findViewById(R.id.select_back);
        select_img= (ImageView) findViewById(R.id.select_img);
        select_draw= (ImageView) findViewById(R.id.select_draw);
        select_video= (VideoView) findViewById(R.id.select_video);
        select_text= (TextView) findViewById(R.id.select_text);
        select_delete.setOnClickListener(this);
        select_back.setOnClickListener(this);
        noteDB=new NoteDB(this);
        dbwriter=noteDB.getWritableDatabase();
        if(getIntent().getStringExtra(NoteDB.IMAGE).equals("null")){
            select_img.setVisibility(View.GONE);
        }else{
            select_img.setVisibility(View.VISIBLE);
        }
        if(getIntent().getStringExtra(NoteDB.VIDEO).equals("null")){
            select_video.setVisibility(View.GONE);
        }else{
            select_video.setVisibility(View.VISIBLE);
        }
        select_text.setText(getIntent().getStringExtra(NoteDB.TEXT));
        Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra(NoteDB.IMAGE));
        select_img.setImageBitmap(bitmap);
//        Bitmap bitmap1 = BitmapFactory.decodeFile(addcontent.draw);
//        select_draw.setImageBitmap(bitmap1);
        select_video.setVideoURI(Uri.parse(getIntent().getStringExtra(NoteDB.VIDEO)));
        select_video.start();
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.select_delete:
                    deleteData();
                    finish();
                    break;
                case R.id.select_back:
                    finish();
                    break;
            }
    }

    private void deleteData() {
        dbwriter.delete(NoteDB.TABLE_NAME,"_id="+getIntent().getIntExtra(NoteDB.ID,0),null);
    }
}
