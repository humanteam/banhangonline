package com.example.thong.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.thong.APIs;
import com.example.thong.banhangonline.R;
import com.example.thong.model.SanPham;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MuaSanPham extends Dialog {


    Dialog dialog;
    EditText edt_khachhang,edt_sdt,edt_diachi,edt_soluong;
    TextView txt_tenmathang,txt_dongia,txt_thanhtien,txt_xacnhan;
    SanPham sp;
    SQLiteDatabase database;
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
        final double giamoi =Double.parseDouble(sp.getGia().substring(0,sp.getGia().length()-5));
     txt_xacnhan.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             final String tenkhachhang =edt_khachhang.getText().toString();
             final String sdt=edt_sdt.getText().toString();
             final String diachi=edt_diachi.getText().toString();
             int soluong = 0;
             try{
                   soluong =Integer.parseInt(edt_soluong.getText().toString());
             }
             catch (NumberFormatException ex){
                 Toast.makeText(context,"Số lượng không được để trống",Toast.LENGTH_LONG).show();
                 return;
             }

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
                 dialog.show();
                 try {
                     String donhang="Tên khách hàng: "+tenkhachhang+"\n"+
                             "Địa chỉ giao hàng: "+diachi+"\n"+
                             "Số điện thoại: "+sdt+"\n"+
                             "Mã sản phẩm: "+sp.getMasp()+"\n"+
                             "Tên sản phẩm: "+sp.getTensp()+"\n"+
                             "Đơn giá: "+sp.getGia()+"\n"+
                             "Số lượng: "+edt_soluong.getText()+"\n"+
                             "Thành tiền: "+txt_thanhtien.getText().toString();
                     JSONObject  jsonbody =new JSONObject();
                     jsonbody.put("tenkhachhang",tenkhachhang);
                     jsonbody.put("donhang",donhang);
                     final String requestbody =jsonbody.toString();
                     StringRequest request =new StringRequest(Request.Method.POST, APIs.api_send_mail, new Response.Listener<String>() {
                         @Override
                         public void onResponse(String response) {
                             Log.i("resvo", response);
                         }
                     }, new Response.ErrorListener() {
                         @Override
                         public void onErrorResponse(VolleyError error) {
                             dialog.cancel();
                             Toast.makeText(context, "Gửi thất bại vui lòng thử lại", Toast.LENGTH_SHORT).show();
                             Log.e("errorvo", error.toString());
                         }
                     }){
                         @Override
                         public String getBodyContentType() {
                             return "application/json; charset=utf-8";
                         }

                         @Override
                         public byte[] getBody() throws AuthFailureError {
                             try {
                                 return requestbody == null ? null : requestbody.getBytes("utf-8");
                             } catch (UnsupportedEncodingException uee) {
                                 VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestbody, "utf-8");
                                 return null;
                             }
                         }

                         @Override
                         protected Response<String> parseNetworkResponse(NetworkResponse response) {
                             dialog.cancel();
                             String responseString = "";
                             if (response != null) {
                                 responseString = String.valueOf(response.statusCode);
                                 if(responseString.equalsIgnoreCase("200")){
                                     database=context.openOrCreateDatabase(APIs.database_name,Context.MODE_PRIVATE,null);
                                     Cursor cursor =database.rawQuery("SELECT Id,SoLuong FROM GioHang WHERE MaSP="+sp.getMasp(),null);
                                     if(cursor.moveToFirst()){
                                         //Toast.makeText(holder.itemView.getContext(), "Đã tồn tại sản phẩm trong giỏ hàng", Toast.LENGTH_SHORT)
                                         // .show();
                                         int soluong=Integer.parseInt(cursor.getString(1));
                                         soluong+=Integer.parseInt(edt_soluong.getText().toString());
                                         ContentValues contentValues =new ContentValues();
                                         contentValues.put("SoLuong",soluong+"");
                                         contentValues.put("ThanhTien",thanhtien(soluong+"",sp.getGia()+""));
                                         contentValues.put("TrangThai",1);
                                         database.update("GioHang",contentValues,"MaSP="+sp.getMasp(),null);
                                     }
                                     else {
                                         ContentValues contentValues =new ContentValues();
                                         contentValues.put("MaSP",sp.getMasp());
                                         contentValues.put("TenSP",sp.getTensp());
                                         contentValues.put("Anh",sp.getAnh());
                                         contentValues.put("ChiTiet",sp.getChitiet());
                                         contentValues.put("MaTheLoai",sp.getMatheloai());
                                         contentValues.put("Gia",sp.getGia());
                                         contentValues.put("SoLuong",edt_soluong.getText().toString());
                                         contentValues.put("ThanhTien",sp.getGia());
                                         contentValues.put("TrangThai",1);
                                         database.insert("GioHang",null,contentValues);
                                     }


                                 }
                                 Log.e("repont",responseString);
                             }
                             else {
                                 Toast.makeText(context,"Gửi thất bại vui lòng thử lại!",Toast.LENGTH_SHORT).show();
                             }
                             return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                         }

                         @Override
                         protected VolleyError parseNetworkError(VolleyError volleyError) {
                             dialog.cancel();
                             Toast.makeText(context,"Gửi thất bại vui lòng thử lại!",Toast.LENGTH_SHORT).show();
                             return volleyError;
                         }
                     };
                   Volley.newRequestQueue(context).add(request);
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
         }

     });

     edt_soluong.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {
                 if(s.length()!=0 && Double.parseDouble(s.toString())<=100){
                    double tien =tinhtien(giamoi,Double.parseDouble(edt_soluong.getText().toString()));
                    String duatienlenmanhinh=tien+"000";
                    String sotienmoi=duatienlenmanhinh.substring(0,(duatienlenmanhinh.indexOf("."))-1)+duatienlenmanhinh.substring((duatienlenmanhinh.indexOf("."))+1,duatienlenmanhinh.length());
                     Log.e("tien",sotienmoi);
                    txt_thanhtien.setText(sotienmoi);
                 }
         }

         @Override
         public void afterTextChanged(Editable s) {

         }
     });
    }


    private String thanhtien(String soluong,String dongia){
        double giamoi =Double.parseDouble(dongia.substring(0,dongia.length()-5));
        double tinhtien=giamoi*Double.parseDouble(soluong);
        String duatienlenmanhinh=tinhtien+"000";
        String sotienmoi=duatienlenmanhinh.substring(0,(duatienlenmanhinh.indexOf("."))-1)+duatienlenmanhinh.substring((duatienlenmanhinh.indexOf("."))+1,duatienlenmanhinh.length());
        Log.e("sotiendatabase",sotienmoi);
        return sotienmoi;
    }

    private double tinhtien(double gia,double soluong){
        return gia*soluong;
    }

    private void addControlls() {
        dialog=new Dialog(context);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.progress);
        edt_khachhang=findViewById(R.id.edt_khachhang);
        edt_sdt=findViewById(R.id.edt_sdt);
        edt_diachi=findViewById(R.id.edt_diachi);
        edt_soluong=findViewById(R.id.edt_soluong);
        txt_tenmathang=findViewById(R.id.txt_tenmathang);
        txt_dongia=findViewById(R.id.txt_dongia);
        txt_thanhtien=findViewById(R.id.txt_thanhtien);
        txt_xacnhan=findViewById(R.id.txt_xacnhan);
        txt_tenmathang.setText(sp.getTensp());
        txt_dongia.setText(sp.getGia());
        txt_thanhtien.setText(sp.getGia());
    }
}
