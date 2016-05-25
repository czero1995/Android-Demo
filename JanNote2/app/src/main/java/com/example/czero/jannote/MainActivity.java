package com.example.czero.jannote;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.czero.jannote.ArcMenu;
import com.example.czero.jannote.ArcMenu.OnMenuItemClickListener;

public class MainActivity extends Activity  {
    private Slidemenu slidemenu;
    private ArcMenu arcMenu;
    private ImageView text;
    private NoteDB noteDB;
    private MyAdapter myAdapter;
    private Cursor cursor;
    private ListView listview;
    private SQLiteDatabase dbReader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        arcMenu= (ArcMenu) findViewById(R.id.id_menuright
        );
        listview = (ListView) findViewById(R.id.listview);
        slidemenu = (Slidemenu) findViewById(R.id.id_menu);
        noteDB = new NoteDB(this);
        dbReader = noteDB.getReadableDatabase();
        Button switch_menu = (Button) findViewById(R.id.switch_menu);
        switch_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slidemenu.toggle();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                Intent intent = new Intent(MainActivity.this,SelectContent.class);
                intent.putExtra(NoteDB.ID,cursor.getInt(cursor.getColumnIndex(NoteDB.ID)));
                intent.putExtra(NoteDB.TEXT,cursor.getString(cursor.getColumnIndex(NoteDB.TEXT)));
                intent.putExtra(NoteDB.IMAGE,cursor.getString(cursor.getColumnIndex(NoteDB.IMAGE)));
                intent.putExtra(NoteDB.VIDEO,cursor.getString(cursor.getColumnIndex(NoteDB.VIDEO)));
                startActivity(intent);
            }
        });
        init();

    }


    private void init() {
        arcMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Intent i = new Intent(MainActivity.this,Addcontent.class);
                switch (view.getId()){
                    case R.id.text:
                        i.putExtra("flag","1");
                        startActivity(i);
                        break;
                    case R.id.camera:
                        i.putExtra("flag","2");
                        startActivity(i);
                        break;
                    case R.id.video:
                        Toast.makeText(MainActivity.this,"video",Toast.LENGTH_SHORT).show();
                        i.putExtra("flag","3");
                        startActivity(i);
                        break;
                    case R.id.draw:
                        i.putExtra("flag","4");
                        startActivity(i);
                        break;
                    case R.id.record:
                        i.putExtra("flag","5");
                        startActivity(i);
                        break;
                }
            }
        });
    }

    public void selectDB(){
        cursor = dbReader.query(NoteDB.TABLE_NAME,null,null,null,null,null,null,null);
        myAdapter=new MyAdapter(this,cursor);
        listview.setAdapter(myAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        selectDB();
    }
}
