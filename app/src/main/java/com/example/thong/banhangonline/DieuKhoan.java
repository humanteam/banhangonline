package com.example.thong.banhangonline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thong.banhangonline.R;

public class DieuKhoan extends AppCompatActivity {
    private final int CALL_REQUEST = 100;
    private static boolean activity;
    Button btn;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dieu_khoan);
        addControl();
        addEvent();
    }

    private void addControl() {

        btn = (Button) findViewById(R.id.dongy);
        txt= (TextView)findViewById(R.id.txtdieukhoan);
    }

    private void addEvent() {
        SharedPreferences dataSave = getSharedPreferences("firstLog", 0);
        if(dataSave.getString("firstTime", "").toString().equals("no")){
            // đã đăng nhập rồi
            if (DieuKhoan.activity == false) {
                Intent intent = new Intent();
                intent.setClass(DieuKhoan.this,MainActivity.class);
                startActivity(intent);
                DieuKhoan.this.finish();
            }
        }
        else{
            //  đây là lần đăng nhập đầu tiên
            if (DieuKhoan.activity == true) {
                Intent intent = new Intent();
                intent.setClass(DieuKhoan.this, MainActivity.class);
                startActivity(intent);
            }
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DieuKhoan.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            SharedPreferences.Editor editor = dataSave.edit();
            editor.putString("firstTime", "no");
            editor.commit();
        }
    }
}

