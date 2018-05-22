package com.example.thong.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thong.banhangonline.R;


public class fragment_nhanxet extends Fragment implements View.OnClickListener {

    Button btnclickxong;
    private View mview1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mview1 = inflater.inflate(R.layout.fragment_nhanxetkhachhang, container, false);
        getUiInitiazalization(mview1);
        btnclickxong = (Button)mview1.findViewById(R.id.btnclickxong);
        btnclickxong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.viewpager, new fragment_them());
                transaction.addToBackStack("m2");
                transaction.commit();
            }
        });
        return mview1;
    }

    public void getUiInitiazalization(final View mview1) {
        Button startBtn = (Button) this.mview1.findViewById(R.id.btnsendemail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail(mview1);
            }
        });

    }

    protected void sendEmail(View mview1) {
        Log.i("Send email", "");
        String[] TO = {"tnthong.oplai@gmail.com"};
        String[] CC = {"ntcong95.oplai@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        EditText edt1=mview1.findViewById(R.id.edttieude);
        EditText edt2=mview1.findViewById(R.id.edtnoidung);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, edt1.getText());
        emailIntent.putExtra(Intent.EXTRA_TEXT, edt2.getText());
        try {
            startActivity(Intent.createChooser(emailIntent, "Gửi Email..."));

            Log.i("Finished...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "Đã gửi thư !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Fragment newFragment = new fragment_home();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.viewpager, newFragment);
        transaction.addToBackStack(null);
    }

}
