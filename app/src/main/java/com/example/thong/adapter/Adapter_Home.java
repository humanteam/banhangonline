package com.example.thong.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thong.APIs;
import com.example.thong.Dialog.MuaSanPham;
import com.example.thong.Dialog.Thong_tin_san_pham;
import com.example.thong.banhangonline.Database;
import com.example.thong.banhangonline.R;
import com.example.thong.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Home extends RecyclerView.Adapter<Adapter_Home.ViewHolder> {
    SQLiteDatabase database;
    ArrayList<SanPham>dssp;
    public Adapter_Home(ArrayList<SanPham>dssp){
        this.dssp=dssp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.support_hienthi_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        //Set text
        Picasso.with(holder.itemView.getContext()).load(dssp.get(position).getAnh()).into(holder.img);
        holder.txtten.setText(dssp.get(position).getTensp());
        holder.txtgia.setText(dssp.get(position).getGia());
        holder.txtchitiet.setText(dssp.get(position).getChitiet());
        holder.txtstt.setText((position+1)+"");
        //Events
        final SanPham sp =dssp.get(position);
        holder.btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MuaSanPham muaSanPham =new MuaSanPham(holder.itemView.getContext(),R.style.myDialog,sp);
                muaSanPham.getWindow().setGravity(Gravity.BOTTOM);
                muaSanPham.show();
            }
        });
        holder.btnthemgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database=holder.itemView.getContext().openOrCreateDatabase(APIs.database_name, Context.MODE_PRIVATE,null);
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
                    Toast.makeText(holder.itemView.getContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(holder.itemView.getContext(), "Đã thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sp =dssp.get(position);
                Thong_tin_san_pham tt =new Thong_tin_san_pham(holder.itemView.getContext(),R.style.myDialog,sp);
                tt.getWindow().setGravity(Gravity.BOTTOM);
                tt.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dssp.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView txtten,txtgia,txtchitiet,txtstt;
        Button btnmua,btnthemgiohang;
        public ViewHolder(View itemView) {
            super(itemView);
            btnthemgiohang=itemView.findViewById(R.id.themgiohang);
            btnmua=itemView.findViewById(R.id.btnmua);
            txtstt=itemView.findViewById(R.id.tvStt);
            img=itemView.findViewById(R.id.imganhsp);
            txtten=itemView.findViewById(R.id.tvTitle);
            txtchitiet=itemView.findViewById(R.id.tvContent);
            txtgia=itemView.findViewById(R.id.btngia);

        }
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
