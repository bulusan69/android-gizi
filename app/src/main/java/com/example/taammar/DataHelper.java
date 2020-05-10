package com.example.taammar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gizi.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table mappinggizi (Number integer primary key,Gender text not null,MinUsia text not null," +
                "MaxUsia not null,VitA text not null, VITD  text not null,VITE text not null, " +
                "VitK text not null, VitB1 text not null, VitB2 text not null, VitB3 text not null," +
                " VitB5 text not null, VitB6 text not null, VitH text not null," +
                "VitB9 text not null, VitB12 text not null,VitC text not null ) ";

        String insertdata = "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                "VALUES ('1','Laki-Laki','10','12','0.6','0.015','0.011','0.035','1.1','1.3','12','5','1.3','0.4','0.0035','0.02','50') ";
        try{
            db.execSQL(sql);
            db.execSQL(insertdata);
        } catch (Exception e){
            Log.e("Error kak",e.toString());
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
