package com.example.czero.jannote;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import android.os.Bundle;

/**
 * Created by zake on 3/24/16.
 */
public class SelectAct extends Activity implements View.OnClickListener {

    private Button s_delete, s_back;
    private ImageView s_img,s_draw;
    private VideoView s_video,s_record;
    private TextView s_tv;
    private NoteDB notesDB;
    private SQLiteDatabase dbWriter;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        // System.out.println(getIntent().getIntExtra(NotesDB.ID, 0));
        s_delete = (Button) findViewById(R.id.s_delete);
        s_back = (Button) findViewById(R.id.s_back);
        s_img = (ImageView) findViewById(R.id.s_img);
       s_draw= (ImageView) findViewById(R.id.s_draw);
        s_video = (VideoView) findViewById(R.id.s_video);

        s_tv = (TextView) findViewById(R.id.s_tv);
        notesDB = new NoteDB(this);
        dbWriter = notesDB.getWritableDatabase();
        s_back.setOnClickListener(this);
        s_delete.setOnClickListener(this);

        if (getIntent().getStringExtra(NoteDB.PATH).equals("null")) {
            s_img.setVisibility(View.GONE);
        } else {
            s_img.setVisibility(View.VISIBLE);
        }
//        if (getIntent().getStringExtra(NoteDB.DRAW).equals("null")) {
//            s_draw.setVisibility(View.GONE);
//        } else {
//            s_draw.setVisibility(View.VISIBLE);
//        }
        if (getIntent().getStringExtra(NoteDB.VIDEO).equals("null")) {
            s_video.setVisibility(View.GONE);
        } else {
            s_video.setVisibility(View.VISIBLE);
        }
        s_tv.setText(getIntent().getStringExtra(NoteDB.CONTENT));
        Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra(
                NoteDB.PATH));
        s_img.setImageBitmap(bitmap);
        s_draw.setImageBitmap(bitmap);
        s_video.setVideoURI(Uri
                .parse(getIntent().getStringExtra(NoteDB.VIDEO)));
        s_video.start();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.s_delete:
                deleteDate();
                finish();
                break;

            case R.id.s_back:
                finish();
                break;
        }
    }

    public void deleteDate() {
        dbWriter.delete(NoteDB.TABLE_NAME,
                "_id=" + getIntent().getIntExtra(NoteDB.ID, 0), null);
    }
}