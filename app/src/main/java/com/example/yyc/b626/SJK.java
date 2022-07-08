package com.example.yyc.b626;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Yyc on 2022/6/27.
 */

public class SJK extends SQLiteOpenHelper{

    static  String name = "11.db";

    //带有全部参数的构造函数，此构造函数是必须需要的。以便于生成数据库对象。
    public SJK(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, 1);
    }

    public SJK(Context context) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库sql语句
        String s = "create table zc(User test,PassWd test)";
        String b = "insert into zc values(?,?)";

        //执行sql语句
        db.execSQL(s);
        db.execSQL(b,new Object[]{"zcyh","123456"});

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
