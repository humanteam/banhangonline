package com.example.thong.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thong.banhangonline.R;

public class Phan_Hoi_Ve_Admin extends Dialog {

    public Phan_Hoi_Ve_Admin(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_nhanxetkhachhang);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        final Button startBtn = (Button) this.findViewById(R.id.btnsendemail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Send email", "");
                String[] TO = {"tnthong.oplai@gmail.com"};
                String[] CC = {"ntcong95.oplai@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                EditText edt1 = findViewById(R.id.edttieude);
                EditText edt2 = findViewById(R.id.edtnoidung);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, edt1.getText());
                emailIntent.putExtra(Intent.EXTRA_TEXT, edt2.getText());
                try {
                    getContext().startActivity(Intent.createChooser(emailIntent, "Gửi Email..."));

                    Log.i("Finished...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "Đã gửi thư !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        cancel();
    }
}