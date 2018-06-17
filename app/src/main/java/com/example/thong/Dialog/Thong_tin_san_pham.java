package com.example.thong.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thong.banhangonline.R;
import com.example.thong.model.SanPham;
import com.squareup.picasso.Picasso;

public class Thong_tin_san_pham extends Dialog{

        TextView tensp;
        ImageView imghinhanh;
        Button btnmua,btngia,btnthemgiohang;
        WebView webViewchitiet;
        SanPham sp;
        public Thong_tin_san_pham(@NonNull Context context, int themeResId, SanPham sp) {
            super(context, themeResId);
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

    }

    private void addControlls() {
         tensp=findViewById(R.id.txt_tttensp);
         imghinhanh=findViewById(R.id.img_ttsp);
         btnmua=findViewById(R.id.btn_ttmua);
         btngia=findViewById(R.id.btn_tt_gia);
         btnthemgiohang=findViewById(R.id.btn_tt_giohang);
        // tensp.setText(sp.getTensp());
        // Picasso.with(getContext()).load(sp.getGia()).into(imghinhanh).;
        // btngia.setText(sp.getGia());
        // webViewchitiet.loadData(sp.getChitiet(),null,"UTF-8");
        // webViewchitiet.setBackgroundColor(Color.WHITE);
        }
}
