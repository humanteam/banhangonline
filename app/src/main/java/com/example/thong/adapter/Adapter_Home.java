package com.example.thong.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thong.Dialog.MuaSanPham;
import com.example.thong.banhangonline.R;
import com.example.thong.model.SanPham;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Home extends RecyclerView.Adapter<Adapter_Home.ViewHolder> {
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
        holder.btnmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MuaSanPham muaSanPham =new MuaSanPham(holder.itemView.getContext(),R.style.myDialog,dssp.get(position));
                muaSanPham.getWindow().setGravity(Gravity.BOTTOM);
                muaSanPham.show();
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
        Button btnmua;
        public ViewHolder(View itemView) {
            super(itemView);
            btnmua=itemView.findViewById(R.id.btnmua);
            txtstt=itemView.findViewById(R.id.tvStt);
            img=itemView.findViewById(R.id.imganhsp);
            txtten=itemView.findViewById(R.id.tvTitle);
            txtchitiet=itemView.findViewById(R.id.tvContent);
            txtgia=itemView.findViewById(R.id.btngia);

        }
    }
}
