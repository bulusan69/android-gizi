package com.example.taammar.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.taammar.model.MappingGizi;
import com.example.taammar.model.Produk;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "gizi.db";
    private static final int DATABASE_VERSION = 1;
    private static DataHelper instance;

    public static DataHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DataHelper(context);
        }
        return instance;
    }

    public DataHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table mappinggizi (Number integer primary key,Gender text not null,MinUsia text not null," +
                "MaxUsia not null,VitA text not null, VITD  text not null,VITE text not null, " +
                "VitK text not null, VitB1 text not null, VitB2 text not null, VitB3 text not null," +
                " VitB5 text not null, VitB6 text not null, VitH text not null," +
                "VitB9 text not null, VitB12 text not null,VitC text not null ) ";

        //Todo : Sesuaikan Script CreateTabel
        String sql1 = "create table produk (Number integer primary key, NamaProduk text not null," +
                "VitA text not null, VitD  text not null,VitE text not null, " +
                "VitK text not null, VitB1 text not null, VitB2 text not null, VitB3 text not null," +
                " VitB5 text not null, VitB6 text not null, VitH text not null," +
                "VitB9 text not null, VitB12 text not null,VitC text not null ) ";
//        String insertdata = "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
//                "VALUES ('1','Laki-Laki','10','12','0.6','0.015','0.011','0.035','1.1','1.3','12','5','1.3','0.4','0.0035','0.02','50') ";
        try{
            db.execSQL(sql);

            //Todo : uncommand script dibawah setelah script create table disesuaikan
            db.execSQL(sql1);

            insertMasterDataMappingGizi(db);

            insertProduk(db);
            //Todo : panggil insertProduct

        } catch (Exception e){
            Log.e("Error onCreate db",e.toString());
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //fungsi untuk select data
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
                Log.e("Error getValue",e.toString());
            }
            return null;
        }

    public Produk getAllProduk(){
        SQLiteDatabase db = this.getReadableDatabase();
        String table = "produk";
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        Cursor cursor = db.query(table,columns,selection , selectionArgs ,groupBy,having,orderBy);
        if(cursor != null && cursor.getCount()>0)
            cursor.moveToFirst();
        try {
            Produk produk = new Produk();
            produk.setNumber(Integer.parseInt(cursor.getString(0)));
            produk.setNamaProduk(cursor.getString(1));
            produk.setVitA(cursor.getString(2));
            produk.setVitD(cursor.getString(3));
            produk.setVitE(cursor.getString(4));
            produk.setVitK(cursor.getString(5));
            produk.setVitB1(cursor.getString(6));
            produk.setVitB2(cursor.getString(7));
            produk.setVitB3(cursor.getString(8));
            produk.setVitB5(cursor.getString(9));
            produk.setVitB6(cursor.getString(10));
            produk.setVitH(cursor.getString(11));
            produk.setVitB9(cursor.getString(12));
            produk.setVitB12(cursor.getString(13));
            produk.setVitC(cursor.getString(14));

            return produk;
        }
        catch (Exception e){
            Log.e("Error getValue",e.toString());
        }
        return null;
    }


    //fungsi insert ke table mapping gizi
    private void insertMasterDataMappingGizi(SQLiteDatabase db ){
        //Todo : lengkapi query select
        String[] INSERT_QUERY = {"INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('1','Laki-Laki','10','12','0.6','0.015','0.011','0.035','1.1','1.3','12','5','1.3','0.4','0.0035','0.02','50')", //0
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('2','Laki-Laki','13','15','0.6','0.015','0.015','0.055','1.2','1.3','16','5','1.3','0.4','0.004','0.025','75')", //1
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('3','Laki-Laki','16','18','0.7','0.015','0.015','0.055','1.2','1.3','16','5','1.3','0.4','0.004','0.03','90')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('4','Laki-Laki','19','29','0.65','0.015','0.015','0.065','1.2','1.3','16','5','1.3','0.4','0.004','0.03','90')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('5','Laki-Laki','30','49','0.65','0.015','0.015','0.065','1.2','1.3','16','5','1.3','0.4','0.004','0.03','90')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('6','Laki-Laki','50','64','0.65','0.015','0.015','0.065','1.2','1.3','16','5','1.7','0.4','0.004','0.03','90')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('7','Laki-Laki','65','80','0.65','0.02','0.015','0.065','1.2','1.3','16','5','1.3','0.4','0.004','0.03','90')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('8','Laki-Laki','81','999','0.65','0.02','0.015','0.065','1.2','1.3','16','5','1.3','0.4','0.004','0.03','90')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('9','Perempuan','10','12','0.6','0.015','0.015','0.035','1','1','12','5','1.2','0.4','0.0035','0.020','50')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('10','Perempuan','13','15','0.6','0.015','0.015','0.055','1.1','1','14','5','1.2','0.4','0.004','0.025','65')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('11','Perempuan','16','18','0.6','0.015','0.015','0.055','1.1','1','14','5','1.2','0.4','0.004','0.030','75')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('12','Perempuan','19','29','0.6','0.015','0.015','0.055','1.1','1.1','14','5','1.3','0.4','0.004','0.030','75')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('13','Perempuan','30','49','0.6','0.015','0.015','0.055','1.1','1.1','14','5','1.3','0.4','0.004','0.030','75')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('14','Perempuan','50','64','0.6','0.015','0.015','0.055','1.1','1.1','14','5','1.5','0.4','0.004','0.030','75')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('15','Perempuan','65','80','0.6','0.020','0.020','0.055','1.1','1.1','14','5','1.5','0.4','0.004','0.030','75')",
                                    "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                    "VALUES ('16','Perempuan','81','999','0.6','0.020','0.020','0.055','1.1','1.1','14','5','1.5','0.4','0.004','0.030','75')",

                        };

        for(String s : INSERT_QUERY) {
            try {
                db.execSQL(s);
            }
            catch (Exception e){
                Log.e("Error insertMasterData" , e.toString());
            }
        }
//
//        for(int x = 0; x < INSERT_QUERY.length; x = x+1){
//            db.execSQL(INSERT_QUERY[x]);
//        }
    }


    //fungsi insert ke table product
    private void insertProduk(SQLiteDatabase db) {

        String[] INSERT_QUERY = {"INSERT INTO produk( Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                                "VALUES ('1','Buavita','0.6','0.015','0.011','0.035','1.1','1.3','12','5','1.3','0.4','0.0035','0.02','50')",
    };
        //Todo : copy query dari function insertMasterDataMappingGizi
        for(String s : INSERT_QUERY) {
            try {
                db.execSQL(s);
            }
            catch (Exception e){
                Log.e("Error insertMasterData" , e.toString());
            }
        }
    }

}
