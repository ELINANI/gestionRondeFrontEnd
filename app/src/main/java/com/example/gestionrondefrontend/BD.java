package com.example.gestionrondefrontend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BD extends SQLiteOpenHelper {
    public static  final  String bdName ="gestionRondeLocaleBd.db";
    String traceTable  ="CREATE TABLE TRACE (codeTrace TEXT PRIMARY KEY,wifiName TEXT,codeAgent TEXT)";

  public BD(@Nullable Context context) {
        super(context, bdName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(traceTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS TRACE ");
       onCreate(db);
    }
    public Boolean insertData(String codeTrace,String wifiName,String codeAgent){
        SQLiteDatabase sql =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("codeTrace",codeTrace);
        contentValues.put("wifiName",wifiName);
        contentValues.put("codeAgent",codeAgent);
        long result = sql.insert("TRACE",null ,contentValues);
        if(result == -1){
            return  false ;
        }
        else
        {
            return  true;
        }

    }
    public ArrayList<TraceModel> getAllTrace(){
      ArrayList<TraceModel> listTrace = new ArrayList<TraceModel>();
      TraceModel tm ;
      SQLiteDatabase sql = this.getReadableDatabase();
        Cursor crs = sql.rawQuery("SELECT * from TRACE",null);
        crs.moveToFirst();
        while (crs.isAfterLast() == false){
         tm = new TraceModel(crs.getString(0),crs.getString(1),crs.getString(2));
         Log.d("bd","f");
         listTrace.add(tm);
         crs.moveToNext();
        }

        return  listTrace;
    }
    public void cleanBd(){
        SQLiteDatabase sql =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long result = sql.delete("TRACE",null ,null);
    }
}
