package com.example.taammar.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.taammar.model.MappingGizi;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gizi.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        SQLiteDatabase db=this.getWritableDatabase();
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

    public MappingGizi getValue(String Usia, String Gender){
        SQLiteDatabase db = this.getReadableDatabase();
        String table = "mappinggizi";
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit ="1";
        Cursor cursor = db.query(table,columns,"Gender=? AND MaxUsia >=? AND MinUsia <=? " ,new String[] { Gender,Usia,Usia},groupBy,having,orderBy,limit);
        if(cursor != null && cursor.getCount()>0)
            cursor.moveToFirst();
            try {
                MappingGizi mappingGizi = new MappingGizi();
                mappingGizi.setNumber(Integer.parseInt(cursor.getString(0)));
                mappingGizi.setGender(cursor.getString(1));
                mappingGizi.setMinUsia(cursor.getString(2));
                mappingGizi.setMaxUsia(cursor.getString(3));
                mappingGizi.setVitA(cursor.getString(4));
                mappingGizi.setVITD(cursor.getString(5));
                mappingGizi.setVITE(cursor.getString(6));
                mappingGizi.setVitK(cursor.getString(7));
                mappingGizi.setVitB1(cursor.getString(8));
                mappingGizi.setVitB2(cursor.getString(9));
                mappingGizi.setVitB3(cursor.getString(10));
                mappingGizi.setVitB5(cursor.getString(11));
                mappingGizi.setVitB6(cursor.getString(12));
                mappingGizi.setVitH(cursor.getString(13));
                mappingGizi.setVitB9(cursor.getString(14));
                mappingGizi.setVitB12(cursor.getString(15));
                mappingGizi.setVitC(cursor.getString(16));
                return mappingGizi;
            }
            catch (Exception e){
                Log.e("Error",e.toString());
            }
            return null;
        }

}
