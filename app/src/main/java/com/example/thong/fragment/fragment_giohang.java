package com.example.thong.fragment;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thong.APIs;
import com.example.thong.banhangonline.Database;
import com.example.thong.banhangonline.R;
import com.example.thong.model.GioHang;

import java.util.ArrayList;

public class fragment_giohang extends Fragment {
    View view;
    ArrayList<GioHang>dsgh =new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_giohang,container,false);
        return  view;
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
