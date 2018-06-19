package com.example.thong.banhangonline;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.Toast;

import com.example.thong.APIs;
import com.example.thong.fragment.fragment_them;
import com.example.thong.fragment.fragment_thietbi;
import com.example.thong.fragment.fragment_giohang;
import com.example.thong.fragment.fragment_home;
import com.example.thong.fragment.fragment_mypham;
import com.example.thong.fragment.fragment_toc;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.HashMap;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    AdView adView;
    Database database;
    FragmentManager manager =getFragmentManager();
    NavigationTabBar navigationTabBar;
    ArrayList<NavigationTabBar.Model> listmodel =new ArrayList<>();
    HashMap<String,Fragment>listFragment =new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, APIs.admod_id);
        database =new Database(this, APIs.database_name,null,1);
        database.create_TB_GioHang();
        addData();
        if(!checkinternet()){
            Toast.makeText(getApplicationContext(),"Please checked internet !",Toast.LENGTH_LONG).show();
        }
        else {
            changeFragment(listFragment.get("home"),manager,"home",0);
        }

        addControlls();
        addEvent();
    }

    private void addData() {
        listFragment.put("home",new fragment_home());
        listFragment.put("mypham",new fragment_mypham());
        listFragment.put("toc",new fragment_toc());
        listFragment.put("thietbi",new fragment_thietbi());
        listFragment.put("giohang",new fragment_giohang());
        listFragment.put("them",new fragment_them());
        listmodel.add(new NavigationTabBar.Model
                .Builder(getResources().getDrawable(R.drawable.home)
                ,Color.WHITE).title("Home")
                .badgeTitle("fragment_home").build());
        listmodel.add(new NavigationTabBar.Model
                .Builder(getResources().getDrawable(R.drawable.mypham),Color.WHITE)
                .title("Mỹ Phẩm").badgeTitle("mypham").build());
        listmodel.add(new NavigationTabBar.Model.
                Builder(getResources().getDrawable(R.drawable.sanphamtoc)
                ,Color.WHITE)
                .title("Làm Tóc")
                .badgeTitle("lamtoc").build());
        listmodel.add(new NavigationTabBar.Model
                .Builder(getResources().getDrawable(R.drawable.dodientu),Color.WHITE)
                .title("Thiết Bị")
                .badgeTitle("thiet bi").build());
        listmodel.add(new NavigationTabBar.Model
                .Builder(getResources().getDrawable(R.drawable.buy),Color.WHITE)
                .title("Giỏ Hàng")
                .badgeTitle("giohang").build());
        listmodel.add(new NavigationTabBar.Model
                .Builder(getResources().getDrawable(R.drawable.caidat),Color.WHITE)
                .title("Thêm")
                .badgeTitle("Thêm").build());
    }

    private void addControlls() {

        adView=findViewById(R.id.admod);
        AdRequest request =new AdRequest.Builder().build();
        adView.loadAd(request);
        navigationTabBar =findViewById(R.id.ntb);
        navigationTabBar.setModels(listmodel);
        navigationTabBar.setModelIndex(0);
        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
        navigationTabBar.setBadgeGravity(NavigationTabBar.BadgeGravity.BOTTOM);
        navigationTabBar.setBadgePosition(NavigationTabBar.BadgePosition.CENTER);
        navigationTabBar.setBadgeSize(15);
        navigationTabBar.setTitleSize(20);
        navigationTabBar.setIconSizeFraction(0.5f);
    }

    private void addEvent() {
        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(NavigationTabBar.Model model, int index) {
            }

            @Override
            public void onEndTabSelected(NavigationTabBar.Model model, int index) {
                switch (index){
                    case 0: {
                        if(!checkinternet()){
                            Toast.makeText(getApplicationContext(),"Please checked internet !",Toast.LENGTH_LONG).show();
                        }else {
                            changeFragment(listFragment.get("home"),manager,"home",0);
                        }
                    }break;
                    case 1:{
                        if(!checkinternet()){
                            Toast.makeText(getApplicationContext(),"Please checked internet !",Toast.LENGTH_LONG).show();
                        }else {
                            changeFragment(listFragment.get("mypham"),manager,"mypham",1);
                        }
                    };break;
                    case 2:{
                        if(!checkinternet()){
                            Toast.makeText(getApplicationContext(),"Please checked internet !",Toast.LENGTH_LONG).show();
                        }else{
                            changeFragment(listFragment.get("toc"),manager,"toc",2);
                        }

                    };break;
                    case 3:{
                        if(!checkinternet()){
                            Toast.makeText(getApplicationContext(),"Please checked internet !",Toast.LENGTH_LONG).show();
                        }
                        else {
                            changeFragment(listFragment.get("thietbi"),manager,"thietbi",3);
                        }

                    };break;
                    case 4:{
                        changeFragment(listFragment.get("giohang"),manager,"giohang",4);
                    };break;
                    case 5:{
                        changeFragment(listFragment.get("them"),manager,"them",5);
                    };break;
                }
            }
        });
    }

    void changeFragment(Fragment fragment,FragmentManager manager,String tag,int id){
        FragmentTransaction transaction =manager.beginTransaction();
        Fragment bridge =getFragmentManager().findFragmentByTag(tag);
        if(bridge!=null){
            getFragmentManager().popBackStack(tag,id);
        }
        else {
            Log.e("fragment","Không tồn tại fragment");
            transaction.replace(R.id.viewpager,fragment);
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStack();
        }
        else{
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Thoát Ứng Dụng")
                    .setMessage("Bạn có muốn thoát ứng dụng không ?")
                    .setPositiveButton("Có", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("Không", null)
                    .show();
        }
    }

    private boolean checkinternet(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
