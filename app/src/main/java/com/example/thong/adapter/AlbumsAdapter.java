package com.example.thong.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.thong.Dialog.MuaSanPham;
import com.example.thong.banhangonline.R;
import com.example.thong.model.SanPham;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> implements PopupMenu.OnMenuItemClickListener {

    private Context mContext;
    private List<SanPham> albumList;
    int positon=-1;
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.themvaotui:{

            };return  true;
            case R.id.muangay:{

            };return true;
        }
        return false;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }


    public AlbumsAdapter(Context mContext, List<SanPham> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_phu_album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SanPham album = albumList.get(position);
        holder.title.setText(album.getTensp());
        holder.count.setText(album.getGia());

        // loading album cover using Glide library
        Glide.with(mContext).load(album.getAnh()).error(R.drawable.virgo).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return albumList.size();
    }

}
