package com.example.thong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thong.banhangonline.R;
import com.example.thong.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Hone extends RecyclerView.Adapter<Adapter_Hone.ViewHolder> {
    ArrayList<SanPham>dssp;
    public Adapter_Hone(ArrayList<SanPham>dssp){
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(holder.itemView.getContext()).load(dssp.get(position).getAnh()).into(holder.img);
        holder.txtten.setText(dssp.get(position).getTensp());
        holder.txtgia.setText(dssp.get(position).getGia());
        holder.txtchitiet.setText(dssp.get(position).getChitiet());
    }

    @Override
    public int getItemCount() {
        return dssp.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView txtten,txtgia,txtchitiet;
        public ViewHolder(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.imganhsp);
            txtten=itemView.findViewById(R.id.tvTitle);
            txtchitiet=itemView.findViewById(R.id.tvContent);
            txtgia=itemView.findViewById(R.id.tvReadmore);

        }
    }
}
