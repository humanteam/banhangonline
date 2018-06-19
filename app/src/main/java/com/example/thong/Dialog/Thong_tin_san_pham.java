package com.example.thong.Dialog;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thong.APIs;
import com.example.thong.banhangonline.R;
import com.example.thong.model.SanPham;
import com.squareup.picasso.Picasso;

public class Thong_tin_san_pham extends Dialog{

        SQLiteDatabase database;
        TextView tensp;
        ImageView imghinhanh;
        Button btnmua,btngia,btnthemgiohang;
        WebView webViewchitiet;
        SanPham sp;
        Context context;
        public Thong_tin_san_pham(@NonNull Context context, int themeResId, SanPham sp) {
            super(context, themeResId);
            this.context=context;
         this.sp=sp;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.thong_tin_san_pham);
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            addControlls();
            addEvents();
        }

    private void addEvents() {
      btnmua.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             MuaSanPham mua =new MuaSanPham(context,R.style.myDialog,sp);
             mua.getWindow().setGravity(Gravity.BOTTOM);
             mua.show();
          }
      });
      btnthemgiohang.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              database=getContext().openOrCreateDatabase(APIs.database_name, Context.MODE_PRIVATE,null);
              Cursor cursor =database.rawQuery("SELECT Id,SoLuong FROM GioHang WHERE MaSP="+sp.getMasp(),null);
              if(cursor.moveToFirst()){
                  //Toast.makeText(holder.itemView.getContext(), "Đã tồn tại sản phẩm trong giỏ hàng", Toast.LENGTH_SHORT)
                  // .show();
                  int soluong=Integer.parseInt(cursor.getString(1));
                  soluong+=1;
                  ContentValues contentValues =new ContentValues();
                  contentValues.put("SoLuong",soluong+"");
                  contentValues.put("ThanhTien",thanhtien(soluong+"",sp.getGia()+""));
                  database.update("GioHang",contentValues,"MaSP="+sp.getMasp(),null);
                  Toast.makeText(getContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
              }
              else {
                  ContentValues contentValues =new ContentValues();
                  contentValues.put("MaSP",sp.getMasp());
                  contentValues.put("TenSP",sp.getTensp());
                  contentValues.put("Anh",sp.getAnh());
                  contentValues.put("ChiTiet",sp.getChitiet());
                  contentValues.put("MaTheLoai",sp.getMatheloai());
                  contentValues.put("Gia",sp.getGia());
                  contentValues.put("SoLuong",1);
                  contentValues.put("ThanhTien",sp.getGia());
                  contentValues.put("TrangThai",0);
                  database.insert("GioHang",null,contentValues);
                  Toast.makeText(getContext(), "Đã thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
              }
              cursor.close();
          }
      });
    }

    private void addControlls() {
         tensp=findViewById(R.id.txt_tttensp);
         imghinhanh=findViewById(R.id.img_ttsp);
         btnmua=findViewById(R.id.btn_ttmua);
         btngia=findViewById(R.id.btn_tt_gia);
         btnthemgiohang=findViewById(R.id.btn_tt_giohang);
         tensp.setText(sp.getTensp());
         Picasso.with(getContext()).load(sp.getAnh()).error(R.drawable.mun).into(imghinhanh);
         btngia.setText(sp.getGia());
         webViewchitiet=findViewById(R.id.webview_chitiet);
         webViewchitiet.loadData(sp.getChitiet(),"text/html","UTF-8");
         webViewchitiet.setHorizontalScrollbarOverlay(true);
         Paint paint =new Paint();
         paint.setColor(Color.GRAY);
         paint.setStyle(Paint.Style.FILL);
         paint.setTextAlign(Paint.Align.CENTER);
         paint.setFakeBoldText(true);
         paint.setAntiAlias(true);
         paint.setLinearText(true);
         webViewchitiet.setLayerType(View.OVER_SCROLL_ALWAYS,paint);
        }
    private String thanhtien(String soluong,String dongia){
        double giamoi =Double.parseDouble(dongia.substring(0,dongia.length()-5));
        double tinhtien=giamoi*Double.parseDouble(soluong);
        String duatienlenmanhinh=tinhtien+"000";
        String sotienmoi=duatienlenmanhinh.substring(0,(duatienlenmanhinh.indexOf("."))-1)+duatienlenmanhinh.substring((duatienlenmanhinh.indexOf("."))+1,duatienlenmanhinh.length());
        Log.e("sotiendatabase",sotienmoi);
        return sotienmoi;
    }
}
