package com.example.thong.banhangonline;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class FlashScreen extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.flash_screen);
        if(checkInternet()==true){
            Toast.makeText(getApplicationContext(), "Có kết nối internet", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Không có kết nối internet", Toast.LENGTH_SHORT).show();
        }
    }

    boolean checkInternet(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info =manager.getNetworkInfo();
        if(info!=null && info.isConnected()){
            return true;
        }
        return false;
    }
}
