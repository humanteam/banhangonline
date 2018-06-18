package com.example.thong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thong.banhangonline.R;
import com.example.thong.model.GioHang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_GioHang extends RecyclerView.Adapter<Adapter_GioHang.ViewHolder> {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(holder.itemView.getContext()).load(dsgh.get(position).getAnh()).error(R.drawable.mun).into(holder.img_giohang);
        holder.txttensp.setText(dsgh.get(position).getTensp().toString());
        holder.btnGia.setText(dsgh.get(position).getGia());
        int tt =Integer.parseInt(dsgh.get(position).getTrangthai());
        if(tt==0){
            holder.txttrangthai.setText("Chưa đặt hàng");
        }
        else if(tt==1){
            holder.txttrangthai.setText("Đã đặt hàng");
        }
    }

    @Override
    public int getItemCount() {
        return dsgh.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img_giohang;
        Button btnMuagiohang,btnXoagiohang,btnGia;
        TextView txttensp,txttrangthai;
        public ViewHolder(View itemView) {
            super(itemView);
            img_giohang=itemView.findViewById(R.id.img_giohang);
            btnMuagiohang=itemView.findViewById(R.id.btn_muagiohang);
            btnGia=itemView.findViewById(R.id.txt_giagiohang);
            btnXoagiohang=itemView.findViewById(R.id.btnxoa_giohang);
            txttensp=itemView.findViewById(R.id.txt_tenspgiohang);
            txttrangthai=itemView.findViewById(R.id.txt_trangthaigiohang);
        }
    }
}
