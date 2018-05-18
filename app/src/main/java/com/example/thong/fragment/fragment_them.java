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
    Button btnshare;
    Button btndanhgia;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them, container, false);


        btnshare = (Button) view.findViewById(R.id.chiaseapp);
        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkApp = "https://play.google.com/store/apps/details?id=" + getActivity().getPackageName();
                Intent intenShare = new Intent(Intent.ACTION_SEND);
                intenShare.setType("text/plain");
                intenShare.putExtra(Intent.EXTRA_TEXT, linkApp);
                startActivity(Intent.createChooser(intenShare, "Chia sẻ app với bạn bè của bạn"));
            }
        });

        btndanhgia = (Button) view.findViewById(R.id.danhgiaapp);
        btndanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Thông Tin App");
                FragmentManager manager =getActivity().getFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.viewpager,new fragment_danhgia());
                transaction.addToBackStack("m2");
                transaction.commit();
            }
        });
        return view;
    }
}