package com.ugurcangursen.guideofistanbul;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 291 on 15.12.2017.
 */

public class GuideDB extends SQLiteOpenHelper {

    private  static final int DATABASE_VERSION= 1;
    private static final String DATABASE_NAME= "guide_db";
    private static final String TABLE_NAME = "guide_list";
    private static String GUIDE_ID = "guide_id";
    private static String GUIDE_BASLIK = "guide_baslik";
    private static String GUIDE_ACIKLAMA = "guide_aciklama";
    private static String GUIDE_ADRES= "guide_adres";
    private static String GUIDE_KATEGORI = "guide_kategori";
    private static String GUIDE_IMG = "guides_image";

    public GuideDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + "("
                + GUIDE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + GUIDE_BASLIK + " TEXT, "
                + GUIDE_ACIKLAMA + " TEXT, "
                + GUIDE_ADRES + " TEXT, "
                + GUIDE_KATEGORI + " TEXT, "
                + GUIDE_IMG + " BLOB);" ;
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public long addGuide(String baslik,String aciklama,String adres,String kategori,byte [] image)
    {

        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(GUIDE_BASLIK,baslik);
            cv.put(GUIDE_ACIKLAMA,aciklama);
            cv.put(GUIDE_ADRES,adres);
            cv.put(GUIDE_KATEGORI,kategori);
            cv.put(GUIDE_IMG,image);
            return db.insert(TABLE_NAME,GUIDE_ID,cv);
        }catch (Exception e){e.printStackTrace();}
        return 0;

    }


    public Cursor getGuides(){
        SQLiteDatabase gdb = getReadableDatabase();
        String [] columns = {GUIDE_BASLIK,GUIDE_ACIKLAMA,GUIDE_ADRES,GUIDE_IMG};
        return  gdb.query(TABLE_NAME,columns,null,null,null,null,null);

    }

    public Cursor getFoodGuides(){
        SQLiteDatabase gdb = getReadableDatabase();
        String [] columns = {GUIDE_BASLIK,GUIDE_ACIKLAMA,GUIDE_ADRES,GUIDE_IMG};
        return gdb.query(TABLE_NAME,columns,GUIDE_KATEGORI+" = 'Food'",null,null,null,null);
    }

    public Cursor getHotelsGuides(){
        SQLiteDatabase gdb = getReadableDatabase();
        String [] columns = {GUIDE_BASLIK,GUIDE_ACIKLAMA,GUIDE_ADRES,GUIDE_IMG};
        return gdb.query(TABLE_NAME,columns,GUIDE_KATEGORI+" = 'Hotel'",null,null,null,null);
    }

    public Cursor getArtGuides(){
        SQLiteDatabase gdb = getReadableDatabase();
        String [] columns = {GUIDE_BASLIK,GUIDE_ACIKLAMA,GUIDE_ADRES,GUIDE_IMG};
        return gdb.query(TABLE_NAME,columns,GUIDE_KATEGORI+" = 'Art'",null,null,null,null);
    }

    public Cursor getShoppingGuides(){
        SQLiteDatabase gdb = getReadableDatabase();
        String [] columns = {GUIDE_BASLIK,GUIDE_ACIKLAMA,GUIDE_ADRES,GUIDE_IMG};
        return gdb.query(TABLE_NAME,columns,GUIDE_KATEGORI+" = 'Shopping'",null,null,null,null);
    }

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
       db.delete(TABLE_NAME,null,null);
    }


}
