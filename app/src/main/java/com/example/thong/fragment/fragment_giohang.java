package com.example.thong.fragment;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.thong.APIs;
import com.example.thong.adapter.Adapter_GioHang;
import com.example.thong.banhangonline.Database;
import com.example.thong.banhangonline.R;
import com.example.thong.model.GioHang;

import java.util.ArrayList;

public class fragment_giohang extends Fragment {
    SQLiteDatabase database;
    ArrayList<GioHang>dsgh =new ArrayList<>();
    RecyclerView recyclerView;
    Adapter_GioHang adapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_giohang,container,false);
        getData();
        recyclerView=view.findViewById(R.id.list_giohang);
        GridLayoutManager gridLayoutManager =new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new Adapter_GioHang(dsgh);
        recyclerView.setAdapter(adapter);
        if(dsgh.size()<=0){
            Toast.makeText(getActivity(),"Giỏ hàng của bạn rỗng !",Toast.LENGTH_LONG).show();
        }
        return  view;
    }


    private void getData(){
        dsgh.clear();
        database=getActivity().openOrCreateDatabase(APIs.database_name,Context.MODE_PRIVATE,null);
        Cursor cursor =database.rawQuery("select * from GioHang",null);
        while (cursor.moveToNext()){
            GioHang gh =new GioHang();
            gh.setMasp(cursor.getString(1));
            gh.setTensp(cursor.getString(2));
            gh.setAnh(cursor.getString(3));
            gh.setChitiet(cursor.getString(4));
            gh.setMatheloai(cursor.getString(5));
            gh.setGia(cursor.getString(6));
            gh.setSoluong(cursor.getString(7));
            gh.setThanhtien(cursor.getString(8));
            gh.setTrangthai(cursor.getString(9));
            dsgh.add(gh);
        }
        cursor.close();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){

        }
        else {
            view.clearFocus();
        }
    }

    @Override
    public void onDestroyView() {
        view.clearFocus();
        view.clearAnimation();
        super.onDestroyView();
    }
}
