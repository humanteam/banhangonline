package com.example.thong.fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.thong.banhangonline.R;

public class fragment_them extends Fragment {
    TextView txttinnhan, txtdonhang, txtnhanxet, txtshare, txtdanhgia;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them, container, false);

        txttinnhan = (TextView) view.findViewById(R.id.tinnhan);
        txttinnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //bắt sự kiện vào đây
            }
        });

        txtdonhang = (TextView) view.findViewById(R.id.donhangcuatoi);
        txtdonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bắt sự kiện vào đây
            }
        });

        txtnhanxet = (TextView) view.findViewById(R.id.nhanxet);
        txtnhanxet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bắt sự kiện vào đây
                FragmentManager manager = getActivity().getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.viewpager, new fragment_nhanxet());
                transaction.addToBackStack("m1");
                transaction.commit();
            }
        });

        txtdanhgia = (TextView) view.findViewById(R.id.danhgia);
        txtdanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ((AppCompatActivity) getActivity()).getSupportActionBar();
                FragmentManager manager = getActivity().getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.viewpager, new fragment_danhgia());
                transaction.addToBackStack("m1");
                transaction.commit();
            }
        });

        txtshare = (TextView) view.findViewById(R.id.chiase);
        txtshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkApp = "https://play.google.com/store/apps/details?id=" + getActivity().getPackageName();
                Intent intenShare = new Intent(Intent.ACTION_SEND);
                intenShare.setType("text/plain");
                intenShare.putExtra(Intent.EXTRA_TEXT, linkApp);
                startActivity(Intent.createChooser(intenShare, "Chia sẻ app với bạn bè của bạn"));
            }
        });
        return view;
    }
}