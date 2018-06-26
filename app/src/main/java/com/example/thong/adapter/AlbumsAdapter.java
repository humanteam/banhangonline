package com.example.thong.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.thong.APIs;
import com.example.thong.Dialog.MuaSanPham;
import com.example.thong.Dialog.Thong_tin_san_pham;
import com.example.thong.banhangonline.R;
import com.example.thong.model.SanPham;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyViewHolder> {

    SQLiteDatabase database;
    private Context mContext;
    private List<SanPham> dssp;

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


    public AlbumsAdapter(Context mContext, List<SanPham> dssp) {
        this.mContext = mContext;
        this.dssp = dssp;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_phu_album_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final SanPham sp = dssp.get(position);
        holder.title.setText(sp.getTensp());
        holder.count.setText(sp.getGia());

        // loading album cover using Glide library
        Glide.with(mContext).load(sp.getAnh()).error(R.drawable.virgo).into(holder.thumbnail);
        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showPopupMenu(holder.itemView,position,sp);
            }
        });
        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sp =dssp.get(position);
                Thong_tin_san_pham tt =new Thong_tin_san_pham(holder.itemView.getContext(),R.style.myDialog,sp);
                tt.getWindow().setGravity(Gravity.BOTTOM);
                tt.show();
            }
        });
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view, int postion, final SanPham sp) {
        // inflate menu
        final PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.muangay:{
                        MuaSanPham muaSanPham =new MuaSanPham(mContext,R.style.myDialog,sp);
                        muaSanPham.getWindow().setGravity(Gravity.BOTTOM);
                        muaSanPham.show();
                    };return true;
                    case R.id.themvaotui:{
                        database=mContext.openOrCreateDatabase(APIs.database_name, Context.MODE_PRIVATE,null);
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
                            Toast.makeText(mContext, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(mContext, "Đã thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                        }
                        cursor.close();
                    };return true;
                }
                return false;
            }
        });
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    @Override
    public int getItemCount() {
        return dssp.size();
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