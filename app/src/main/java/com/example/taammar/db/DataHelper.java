package com.example.taammar.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.taammar.model.MappingGizi;
import com.example.taammar.model.Produk;

import java.util.ArrayList;
import java.util.List;

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
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE mappinggizi (Number INTEGER PRIMARY KEY,Gender TEXT NOT NULL,MinUsia INTEGER NOT NULL," +
                "MaxUsia INTEGER NOT NULL,VitA TEXT NOT NULL, VitD  TEXT NOT NULL,VitE TEXT NOT NULL, " +
                "VitK TEXT NOT NULL, VitB1 TEXT NOT NULL, VitB2 TEXT NOT NULL, VitB3 TEXT NOT NULL," +
                " VitB5 TEXT NOT NULL, VitB6 TEXT NOT NULL, VitH TEXT NOT NULL," +
                "VitB9 TEXT NOT NULL, VitB12 TEXT NOT NULL,VitC TEXT NOT NULL) ";

        //Todo : Sesuaikan Script CreateTabel
        String sql1 = "CREATE TABLE produk (Number INTEGER PRIMARY KEY, NamaProduk TEXT NOT NULL," +
                "VitA TEXT NOT NULL, VitD  TEXT NOT NULL,VitE TEXT NOT NULL, " +
                "VitK TEXT NOT NULL, VitB1 TEXT NOT NULL, VitB2 TEXT NOT NULL, VitB3 TEXT NOT NULL," +
                " VitB5 TEXT NOT NULL, VitB6 TEXT NOT NULL, VitH TEXT NOT NULL," +
                "VitB9 TEXT NOT NULL, VitB12 TEXT NOT NULL,VitC TEXT NOT NULL) ";
//        String insertdata = "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
//                "VALUES ('1','Laki-Laki','10','12','0.6','0.015','0.011','0.035','1.1','1.3','12','5','1.3','0.4','0.0035','0.02','50') ";
        try {
            db.execSQL(sql);

            //Todo : uncommand script dibawah setelah script create table disesuaikan
            db.execSQL(sql1);

            insertMasterDataMappingGizi(db);

            insertProduk(db);
            //Todo : panggil insertProduct

        } catch (Exception e) {
            Log.e("Error onCreate db", e.toString());
        }

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //fungsi untuk select data
    public MappingGizi getValue(String Usia, String Gender) {
        SQLiteDatabase db = this.getReadableDatabase();
        String table = "mappinggizi";
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = "1";
        Cursor cursor = db.query(table, columns, "Gender=? AND MaxUsia >=? AND MinUsia <=? ", new String[]{Gender, Usia, Usia}, groupBy, having, orderBy, limit);
        if (cursor != null && cursor.getCount() > 0)
            try {
                cursor.moveToFirst();
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
            } catch (Exception e) {
                Log.e("Error getValue", e.toString());
            } finally {
                cursor.close();
            }
        return null;
    }

    public List<Produk> getAllProduk() {
        SQLiteDatabase db = this.getReadableDatabase();
        String table = "produk";
        String[] columns = null;
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        List<Produk> listProduk = new ArrayList<>();
        Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
        try {
            if (cursor.moveToFirst()) {
                do {
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

                    listProduk.add(produk);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }
        return listProduk;
    }


    //fungsi insert ke table mapping gizi
    //Todo : lengkapi query select
    private void insertMasterDataMappingGizi(SQLiteDatabase db) {
        String[] INSERT_QUERY = {"INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                "VALUES ('1','Laki-Laki','10','12','0.6','0.015','11','0.035','1.1','1.3','12','5','1.3','0.4','0.0035','0.02','50')", //0
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('2','Laki-Laki','13','15','0.6','0.015','15','0.055','1.2','1.3','16','5','1.3','0.4','0.004','0.025','75')", //1
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('3','Laki-Laki','16','18','0.7','0.015','15','0.055','1.2','1.3','16','5','1.3','0.4','0.004','0.03','90')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('4','Laki-Laki','19','29','0.65','0.015','15','0.065','1.2','1.3','16','5','1.3','0.4','0.004','0.03','90')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('5','Laki-Laki','30','49','0.65','0.015','15','0.065','1.2','1.3','16','5','1.3','0.4','0.004','0.03','90')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('6','Laki-Laki','50','64','0.65','0.015','15','0.065','1.2','1.3','16','5','1.7','0.4','0.004','0.03','90')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('7','Laki-Laki','65','80','0.65','0.02','15','0.065','1.2','1.3','16','5','1.3','0.4','0.004','0.03','90')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('8','Laki-Laki','81','120','0.65','0.02','15','0.065','1.2','1.3','16','5','1.3','0.4','0.004','0.03','90')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('9','Perempuan','10','12','0.6','0.015','15','0.035','1','1','12','5','1.2','0.4','0.0035','0.020','50')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('10','Perempuan','13','15','0.6','0.015','15','0.055','1.1','1','14','5','1.2','0.4','0.004','0.025','65')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('11','Perempuan','16','18','0.6','0.015','15','0.055','1.1','1','14','5','1.2','0.4','0.004','0.030','75')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('12','Perempuan','19','29','0.6','0.015','15','0.055','1.1','1.1','14','5','1.3','0.4','0.004','0.030','75')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('13','Perempuan','30','49','0.6','0.015','15','0.055','1.1','1.1','14','5','1.3','0.4','0.004','0.030','75')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('14','Perempuan','50','64','0.6','0.015','15','0.055','1.1','1.1','14','5','1.5','0.4','0.004','0.030','75')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('15','Perempuan','65','80','0.6','0.020','20','0.055','1.1','1.1','14','5','1.5','0.4','0.004','0.030','75')",
                "INSERT INTO mappinggizi( Number, Gender, MinUsia, MaxUsia, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('16','Perempuan','81','120','0.6','0.020','20','0.055','1.1','1.1','14','5','1.5','0.4','0.004','0.030','75')"
        };

        for (String s : INSERT_QUERY) {
            try {
                db.execSQL(s);
            } catch (Exception e) {
                Log.e("Error insertMasterData", e.toString());
            }
        }
//
//        for(int x = 0; x < INSERT_QUERY.length; x = x+1){
//            db.execSQL(INSERT_QUERY[x]);
//        }
    }


    //fungsi insert ke table product
    //Todo : copy query dari function insertMasterDataMappingGizi
    private void insertProduk(SQLiteDatabase db) {
        String[] INSERT_QUERY = {"INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                "VALUES ('1','Buavita Apple 250ml','0.625','0','0','0','0.069','0.12','4.5','0','0.15','0','0','0','53.625')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('2','Buavita Lychee 250ml','0.5625','0','0','0','0.115','0','0','0','0.3','0','0','0','33')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('3','Buavita Mango 250ml','0.59375','0','0','0','0.345','0','5.25','0','0.3','0','0.001','0','28.875')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('4','Buavita Orange 250ml','0.34375','0','0','0','0.115','0','0','0','0','0','0','0','24.75')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('5','Bear Brand Gold Malt Putih 140ml','0.15625','0.0009','2.25','0','0.23','0.24','0','0','0.45','0','0','0.0045','8.25')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('6','Diva Mixberries 85ml','0.58','0','15','0','0','0','0','0','0','0','0','0','0')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('7','Energen Vanila 290g(isi 10, 29g/piece)','0.1875','0.0012','1.5','0','0.115','0.18','0','0','0.225','0','0.0008','0.0075','0')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('8','Frisian Flag Coconut Delight 225ml','0.15625','0.003','0','0','0.2875','0.24','4.5','1.25','0.3','0','0','0.0075','0')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('9','Frisian Flag Full Cream 225ml','0.15625','0.0045','0.9','0','0.23','0.24','3','0.75','0.375','0','0','0.009','0')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('10','Frisian Flag Kacang Hijau 225ml','0.125','0.003','0','0','0.2875','0.36','3.75','1.5','0.3','0','0','0.0094','0')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('11','Frisian Flag Sweet Delight 225ml','0.2187','0.00375','0','0','0.2875','0.36','4.5','2','0.525','0','0','0.0075','0')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('12','Indomilk FullCream Susu Segar 250ml', '0.1562','0.006','0.09','0','0.1725','0.3','0','1','0','0.0012','0','0','0')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('13','Nestum Sereal Cokelat (isi 4, 32g/piece)','0.09375','0.00225','2.25','0','0.1725','0.12','1.5','0.75','0.3','0.1','0.00024','0.0024','28.875')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('14','Real Good Rasa Sereal Coklat 125ml','0.1875','0','0','0.039','0.115','0.3','1.5','0','0','0.04','0.001','0','0')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('15','Real Good Rasa Sereal Setroberi 125ml','0.125','0','0','0.033','0.115','0.36','3','0','0','0.04','0.0012','0','0')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('16','Ultra Milk Chocolate 250ml','0.125','0.00225','0','0.0036','0.1725','0.18','1.5','0.5','0.225','0.06','0','0.006','6.6')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('17','Ultra Milk Full Cream 250ml','0.125','0.00225','0','0.0048','0.1725','0.18','1.5','0.5','0.15','0.06','0','0.0075','6.6')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('18','Ultra Milk Rasa Moka 250ml','0.125','0.00225','0','0','0.1725','0.18','1.5','0.5','0.225','0.08','0','0.0075','6.6')",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('19','Ultra Sari Kacang Ijo 250ml','0','0','0','0','0.5175','0.3','3.75','0','0.6','0','0.1','0.0285','4.95') ",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('20','Ultra Milk Rasa Stroberi 250ml','0.125','0.00225','0','0.0036','0.1725','0.18','2.25',\t'0.5','0.225','0.08','0','0.006','6.6'",
                "INSERT INTO produk (Number, NamaProduk, VitA, VitD, VitE, VitK, VitB1,VitB2, VitB3, VitB5, VitB6, VitH, VitB9, VitB12, VitC) " +
                        "VALUES ('21','YOU C1000 Orange 140ml','0','0','2.25','0','0','0','0','0','0','0','0','0','915.75')",


        };
        for (String s : INSERT_QUERY) {
            try {
                db.execSQL(s);
            } catch (Exception e) {
                Log.e("Error insertMasterData", e.toString());
            }
        }
    }

}
