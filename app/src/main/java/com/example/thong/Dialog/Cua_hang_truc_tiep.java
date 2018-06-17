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
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.thong.banhangonline.MainActivity;
import com.example.thong.banhangonline.R;

public class Cua_hang_truc_tiep extends Dialog {
    private final int CALL_REQUEST = 100;
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

            callBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (validationCheck()) {
                        callPhoneNumber();
                    }
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void callPhoneNumber() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling

                    ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST);

                    return;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        cancel();
    }
}
