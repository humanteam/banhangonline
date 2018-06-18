package com.example.thong.banhangonline;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class Database extends SQLiteOpenHelper {
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.e("database","da tao thanh cong database");
    }

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void QueryData(String query){
         SQLiteDatabase database =getWritableDatabase();
         database.execSQL(query);
    }
    public Cursor getData(String query){
        SQLiteDatabase database =getReadableDatabase();
        return database.rawQuery(query,null);
    }
    public void create_TB_GioHang(){
        String sql ="CREATE TABLE IF NOT EXISTS GioHang (Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "MaSP INTEGER,TenSP NVARCHAR(200),Anh TEXT ,ChiTiet TEXT ,MaTheLoai INTEGER,Gia TEXT,SoLuong INTEGER,ThanhTien               FLOAT,TrangThai INTEGER)";
        SQLiteDatabase database =getWritableDatabase();
        database.execSQL(sql);
        Log.e("database","da tao table thanh cong");
    }
}
