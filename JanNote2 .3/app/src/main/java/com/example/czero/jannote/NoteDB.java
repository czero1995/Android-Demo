package com.example.czero.jannote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zake on 4/16/16.
 */
public class NoteDB extends SQLiteOpenHelper {
    public static  final String TABLE_NAME = "note";
    public static  final String TEXT = "text";
    public static  final String TIME = "time";
    public static  final String DRAW = "draws";
    public static  final String IMAGE = "image";
    public static  final String VIDEO = "video";
    public static  final String ID = "_id";
    public static  final String RECORD = "record";


    public NoteDB(Context context) {
        super(context, "note", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE" + TABLE_NAME +"(" + ID
//        +"INTERGER PRIMARY KEY AUTOINCREMENT,"
//        +TEXT + "TEXT NOT NULL,"
//        +IMG +"TEXT NOT NULL,"
//        +VIDEO +"TEXT NOT NULL,"
//        +TIME + "TEXT NOT NULL)");
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TEXT + " TEXT NOT NULL ,"
                + IMAGE + " TEXT NOT NULL ,"
                + DRAW + " TEXT NOT NULL ,"
                + VIDEO + " TEXT NOT NULL ,"
//                + RECORD + " TEXT NOT NULL ,"
                + TIME + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
