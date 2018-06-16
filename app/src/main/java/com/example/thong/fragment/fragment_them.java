package com.example.thong.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thong.Dialog.Cua_hang_truc_tiep;
import com.example.thong.Dialog.DieuKhoanVaBanQuyen;
import com.example.thong.Dialog.HuongDanSuDung;
import com.example.thong.banhangonline.R;

public class fragment_them extends Fragment {
    View view;
    TextView txtwebsite, txtcuahang, txtnhanxet, txtshare, txtdieukiensudung,txthuongdansudung ;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_them, container, false);
        txtdieukiensudung =(TextView)view.findViewById(R.id.bqvdk);
        txtdieukiensudung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DieuKhoanVaBanQuyen dk = new DieuKhoanVaBanQuyen(getActivity());
                dk.show();
            }
        });

        txtwebsite = (TextView) view.findViewById(R.id.website);
        txtwebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.viewpager, new fragment_lienketwebsite());
               // transaction.addToBackStack("lienketwebsite");
                transaction.commit();
            }
        });

        txtcuahang = (TextView) view.findViewById(R.id.thongtincuahang);
        txtcuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bắt sự kiện vào đây
                Cua_hang_truc_tiep ch = new Cua_hang_truc_tiep(getActivity());
                ch.show();
            }
        });

        txtnhanxet = (TextView) view.findViewById(R.id.nhanxet);
        txtnhanxet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.viewpager, new fragment_nhanxet());
                //transaction.addToBackStack("nhanxet");
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
        txthuongdansudung = (TextView) view.findViewById(R.id.huongdansudung);
        txthuongdansudung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HuongDanSuDung dk = new HuongDanSuDung(getActivity());
                dk.show();
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
        view=null;
        super.onDestroyView();
    }
}