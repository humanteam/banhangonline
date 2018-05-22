package com.example.thong.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thong.banhangonline.R;

public class fragment_them extends Fragment {
    View view;

    TextView txtwebsite, txtcuahang, txtnhanxet, txtshare, txtdanhgia;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_them, container, false);

        txtwebsite = (TextView) view.findViewById(R.id.website);
        txtwebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //bắt sự kiện vào đây
                FragmentManager manager = getActivity().getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.viewpager, new fragment_lienketwebsite());
                transaction.addToBackStack("m1");
                transaction.commit();
            }
        });

        txtcuahang = (TextView) view.findViewById(R.id.thongtincuahang);
        txtcuahang.setOnClickListener(new View.OnClickListener() {
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
                transaction.addToBackStack("m2");
                transaction.commit();
            }
        });

        txtdanhgia = (TextView) view.findViewById(R.id.danhgia);
        txtdanhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager =getFragmentManager();
                FragmentTransaction transaction =manager.beginTransaction();
                transaction.addToBackStack("m3");
                transaction.replace(R.id.viewpager,new fragment_danhgia());
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