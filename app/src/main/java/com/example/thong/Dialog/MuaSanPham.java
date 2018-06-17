package com.example.thong.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thong.banhangonline.R;
import com.example.thong.model.DonHang;
import com.example.thong.model.SanPham;

public class MuaSanPham extends Dialog {



    EditText edt_khachhang,edt_sdt,edt_diachi,edt_soluong;
    TextView txt_tenmathang,txt_dongia,txt_thanhtien,txt_xacnhan;
    SanPham sp;
    Activity context;
    public MuaSanPham(@NonNull Context context, int themeResId, SanPham sp) {
        super(context, themeResId);
        this.sp=sp;
        this.context= (Activity) context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.don_hang);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        addControlls();
        addEvents();
    }

    private void addEvents() {
     txt_xacnhan.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String tenkhachhang =edt_khachhang.getText().toString();
             String sdt=edt_sdt.getText().toString();
             String diachi=edt_diachi.getText().toString();
             int soluong =Integer.parseInt(edt_soluong.getText().toString());
             if(tenkhachhang.length()<=0){


                 Toast.makeText(getContext().getApplicationContext(), "Tên khách hàng không được bỏ trống", Toast.LENGTH_LONG).show();

                 Log.e("toast","Da toast len thong bao");
             }
             else if(sdt.length()<=9 || sdt.length()>=15){
                 Toast.makeText(context, "Bạn vui lòng kiểm tra thừa thiếu số điện thoại", Toast.LENGTH_SHORT).show();
             }
             else if(diachi.length()<=0){
                 Toast.makeText(context, "Địa chỉ không hợp lệ", Toast.LENGTH_SHORT).show();
             }
             else if(soluong<=0||soluong>100){
                 Toast.makeText(context, "Đơn hàng vượt quá số lượng cho phép.Nhập số lượng <=100 sản phẩm", Toast.LENGTH_SHORT).show();
             }
             else{

             }
         }
     });

     edt_soluong.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {
          if(s.length()!=0){
              float tien=(float)Integer.parseInt(s.toString())*(float)Integer.parseInt(txt_dongia.getText().toString());
              txt_thanhtien.setText(tien+"");
          }
         }

         @Override
         public void afterTextChanged(Editable s) {

         }
     });
    }

    private void addControlls() {
        edt_khachhang=findViewById(R.id.edt_khachhang);
        edt_sdt=findViewById(R.id.edt_sdt);
        edt_diachi=findViewById(R.id.edt_diachi);
        edt_soluong=findViewById(R.id.edt_soluong);
        txt_tenmathang=findViewById(R.id.txt_tenmathang);
        txt_dongia=findViewById(R.id.txt_dongia);
        txt_thanhtien=findViewById(R.id.txt_thanhtien);
        txt_xacnhan=findViewById(R.id.txt_xacnhan);
    }
}
