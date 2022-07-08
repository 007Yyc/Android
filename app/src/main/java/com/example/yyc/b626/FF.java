package com.example.yyc.b626;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Yyc on 2022/6/27.
 */

public class FF {

    android.content.Context Context;
    SJK SJK;
    SQLiteDatabase db;

    public FF(android.content.Context Context){
        this.Context = Context;
        SJK = new SJK(Context);
    }

    //插入
    public boolean insert(String User,String PassWd){
        db = SJK.getReadableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("User",User);
        vs.put("PassWd",PassWd);
        long l = db.insert("zc",null,vs);
        if (l > 0){
            return true;
        }else {
            return false;
        }
    }

    //删除
    public boolean delete(String User){
        db = SJK.getReadableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("User",User);
        int l = db.delete("zc","User=?",new String[]{User});
        if (l >0 ){
            return true;
        }else {
            return false;
        }
    }

    //查询
    public boolean quer(String User){
        db = SJK.getReadableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("User",User);
        String sql = "select*From zc where User=?";
        Cursor Cursor = db.rawQuery(sql,new String[]{User});
        if (Cursor.moveToNext()){
            return true;
        }else {
            return false;
        }
    }

    //确认
    public  boolean query(String User,String PassWd){
        db = SJK.getReadableDatabase();
        String sql = "select*from zc where User=? and PassWd=?";
        Cursor Cursor = db.rawQuery(sql, new String[]{User,PassWd});
        if (Cursor.moveToNext()){
            String mima = Cursor.getString(1);
            if (mima.equals(PassWd)){
                return true;
            }else {
                return false;
            }
        }

        return false;
    }
}
