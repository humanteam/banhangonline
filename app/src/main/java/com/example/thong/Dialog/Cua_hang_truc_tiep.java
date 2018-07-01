package com.example.thong.Dialog;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thong.banhangonline.R;

public class Cua_hang_truc_tiep extends Dialog implements View.OnClickListener {
    private final int CALL_REQUEST = 100;
    TextView a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;
    EditText mobileNoEt;
    Button callBt;

    public Cua_hang_truc_tiep(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.cua_hang_truc_tiep);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        a3 = findViewById(R.id.a3);
        a4 = findViewById(R.id.a4);
        a5 = findViewById(R.id.a5);
        a6 = findViewById(R.id.a6);
        a7 = findViewById(R.id.a7);
        a8 = findViewById(R.id.a8);
        a9 = findViewById(R.id.a9);
        a10 = findViewById(R.id.a10);
        initializeViews();
        Button smsButton = (Button) findViewById(R.id.btn_launch_sms);
        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMSMessage();
            }
        });
        Button dialerButton = (Button) findViewById(R.id.btn_launch_call);
        dialerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchDialer();
            }
        });
    }

    private void launchDialer() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + mobileNoEt.getText().toString().trim()));
        getContext().startActivity(callIntent);
    }

    private void sendSMSMessage() {
        try {
            Uri uri = Uri.parse("smsto:" + mobileNoEt.getText().toString().trim());
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, uri);
            smsIntent.putExtra("sms_body", "Tôi muốn được tư vấn thêm và đặt sản phẩm");
            getContext().startActivity(smsIntent);
        } catch (Exception e) {
            Toast.makeText(getContext(),
                    "SMS Thất bại, Vui lòng thử lại!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private boolean validationCheck() {
        try {
            String phoneNo = mobileNoEt.getText().toString().trim();
            if (phoneNo.equalsIgnoreCase("")) {
                mobileNoEt.setError(getContext().getString(R.string.phone_empty_message));
                mobileNoEt.requestFocus();
                return false;
            }
            if (phoneNo.length() < 6 || phoneNo.length() > 13) {
                mobileNoEt.setError(getContext().getString(R.string.invalid_no_message));
                mobileNoEt.requestFocus();
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return true;
    }

    private void initializeViews() {

        try {
            mobileNoEt = (EditText) findViewById(R.id.activityMain_mobileNoEt);
            callBt = (Button) findViewById(R.id.activityMain_callBt);
            callBt.setOnClickListener(this);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        cancel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activityMain_callBt: {
                if (validationCheck()) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + mobileNoEt.getText().toString().trim()));
                        getContext().startActivity(callIntent);
                    } else {
                        Toast.makeText(getContext(), "Please turn on Call Phone Permission !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            ;
            break;
            case R.id.a1: {
                mobileNoEt.setText(a1.getText());
            }
            ;
            break;
            case R.id.a2: {
                mobileNoEt.setText(a2.getText());
            }
            ;
            break;
            case R.id.a3: {
                mobileNoEt.setText(a3.getText());
            }
            ;
            break;
            case R.id.a4: {
                mobileNoEt.setText(a4.getText());
            }
            ;
            break;
            case R.id.a5: {
                mobileNoEt.setText(a5.getText());
            }
            ;
            break;
            case R.id.a6: {
                mobileNoEt.setText(a6.getText());
            }
            ;
            break;
            case R.id.a7: {
                mobileNoEt.setText(a7.getText());
            }
            ;
            break;
            case R.id.a8: {
                mobileNoEt.setText(a8.getText());
            }
            ;
            break;
            case R.id.a9: {
                mobileNoEt.setText(a9.getText());
            }
            ;
            break;
            case R.id.a10: {
                mobileNoEt.setText(a10.getText());
            }
            ;
            break;
        }
    }
}