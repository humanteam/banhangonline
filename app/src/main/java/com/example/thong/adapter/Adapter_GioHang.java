package com.example.thong.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thong.APIs;
import com.example.thong.Dialog.MuaSanPham;
import com.example.thong.banhangonline.R;
import com.example.thong.model.GioHang;
import com.example.thong.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_GioHang extends RecyclerView.Adapter<Adapter_GioHang.ViewHolder> {
    SQLiteDatabase database;
    ArrayList<GioHang>dsgh;
    public Adapter_GioHang(ArrayList<GioHang>dsgh){
        this.dsgh=dsgh;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.item_gio_hang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Picasso.with(holder.itemView.getContext()).load(dsgh.get(position).getAnh()).error(R.drawable.mun).into(holder.img_giohang);
        holder.txttensp.setText(dsgh.get(position).getTensp().toString());
        holder.btnGia.setText(dsgh.get(position).getGia());
        holder.txt_soluongiohang.setText(dsgh.get(position).getSoluong());
        int tt =Integer.parseInt(dsgh.get(position).getTrangthai());
        if(tt==0){
            holder.txttrangthai.setText("Chưa đặt hàng");
        }
        else if(tt==1){
            holder.txttrangthai.setText("Đã đặt hàng");
        }
        holder.btnXoagiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database =holder.itemView.getContext().openOrCreateDatabase(APIs.database_name,Context.MODE_PRIVATE,null);
                database.delete("GioHang","MaSP="+dsgh.get(position).getMasp(),null);
                dsgh.remove(position);
                notifyDataSetChanged();
            }
        });
        holder.btnMuagiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sp =new SanPham();
                sp.setMasp(dsgh.get(position).getMasp());
                sp.setTensp(dsgh.get(position).getTensp());
                sp.setGia(dsgh.get(position).getGia());
                sp.setChitiet(dsgh.get(position).getChitiet());
                sp.setAnh(dsgh.get(position).getAnh());
                sp.setMatheloai(dsgh.get(position).getMatheloai());
                MuaSanPham mua =new MuaSanPham(holder.itemView.getContext(),R.style.myDialog,sp,Integer.parseInt(dsgh.get(position).getSoluong()),dsgh.get(position).getThanhtien());
                mua.getWindow().setGravity(Gravity.BOTTOM);
                mua.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsgh.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img_giohang,imgCong,imgTru;
        Button btnMuagiohang,btnXoagiohang,btnGia;
        TextView txttensp,txttrangthai,txt_soluongiohang;
        public ViewHolder(View itemView) {
            super(itemView);
            imgCong=itemView.findViewById(R.id.imgcong);
            imgTru=itemView.findViewById(R.id.imgtru);
            txt_soluongiohang=itemView.findViewById(R.id.txt_soluonggiohang);
            img_giohang=itemView.findViewById(R.id.img_giohang);
            btnMuagiohang=itemView.findViewById(R.id.btn_muagiohang);
            btnGia=itemView.findViewById(R.id.txt_giagiohang);
            btnXoagiohang=itemView.findViewById(R.id.btnxoa_giohang);
            txttensp=itemView.findViewById(R.id.txt_tenspgiohang);
            txttrangthai=itemView.findViewById(R.id.txt_trangthaigiohang);
        }
    }
}
