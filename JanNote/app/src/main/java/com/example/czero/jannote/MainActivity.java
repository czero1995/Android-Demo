package com.example.czero.jannote;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button textbtn,imgbtn,videobtn,recordbtn,drawbtn,clear;
    private ListView lv;
    private Intent i;
    private MyAdapter adapter;
    private NoteDB noteDB;
    private Cursor cursor;
    private SQLiteDatabase dbReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.list);
        textbtn = (Button) findViewById(R.id.text);
        imgbtn = (Button) findViewById(R.id.img);
        videobtn = (Button) findViewById(R.id.video);
        drawbtn = (Button) findViewById(R.id.draw);

        textbtn.setOnClickListener(this);
        imgbtn.setOnClickListener(this);
        videobtn.setOnClickListener(this);
        drawbtn.setOnClickListener(this);

        noteDB = new NoteDB(this);
        dbReader = noteDB.getReadableDatabase();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                cursor.moveToPosition(position);
                Intent i = new Intent(MainActivity.this, SelectAct.class);
                i.putExtra(NoteDB.ID,
                        cursor.getInt(cursor.getColumnIndex(NoteDB.ID)));
                i.putExtra(NoteDB.CONTENT, cursor.getString(cursor
                        .getColumnIndex(NoteDB.CONTENT)));
                i.putExtra(NoteDB.TIME,
                        cursor.getString(cursor.getColumnIndex(NoteDB.TIME)));
                i.putExtra(NoteDB.PATH,
                        cursor.getString(cursor.getColumnIndex(NoteDB.PATH)));
                i.putExtra(NoteDB.VIDEO,
                        cursor.getString(cursor.getColumnIndex(NoteDB.VIDEO)));
//                i.putExtra(NoteDB.RECORD,
//                        cursor.getString(cursor.getColumnIndex(NoteDB.RECORD)));
                i.putExtra(NoteDB.DRAW,
                        cursor.getString(cursor.getColumnIndex(NoteDB.DRAW)));

                startActivity(i);
            }
        });
    }



    @Override
    public void onClick(View v) {
        i = new Intent(this, AddContent.class);
        switch (v.getId()) {
            case R.id.text:
                i.putExtra("flag", "1");
                startActivity(i);
                break;

            case R.id.img:
                i.putExtra("flag", "2");
                startActivity(i);
                break;

            case R.id.video:
                i.putExtra("flag", "3");
                startActivity(i);
                break;
            case R.id.record:
                i.putExtra("flag","4");
                startActivity(i);
                break;
            case R.id.draw:
                i.putExtra("flag","5");
                startActivity(i);
                break;

        }
    }
    public void selectDB() {
        cursor = dbReader.query(NoteDB.TABLE_NAME, null, null, null, null,
                null, null);
        adapter = new MyAdapter(this, cursor);
        lv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }

}
