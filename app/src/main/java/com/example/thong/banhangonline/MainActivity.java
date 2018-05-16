package com.example.thong.banhangonline;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.thong.fragment.fragment_dientu;
import com.example.thong.fragment.fragment_giohang;
import com.example.thong.fragment.fragment_home;
import com.example.thong.fragment.fragment_mypham;
import com.example.thong.fragment.fragment_toc;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    NavigationTabBar navigationTabBar;
    Fragment fragment =null;
    ArrayList<NavigationTabBar.Model> listmodel =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addData();
        addControlls();
        addEvent();
    }

    private void addData() {
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
                .title("Điện Tử")
                .badgeTitle("dientu").build());
        listmodel.add(new NavigationTabBar.Model
                .Builder(getResources().getDrawable(R.drawable.buy),Color.WHITE)
                .title("Giỏ Hàng")
                .badgeTitle("giohang").build());
    }

    private void addControlls() {

      navigationTabBar =findViewById(R.id.ntb);

        navigationTabBar.setModels(listmodel);
        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
        navigationTabBar.setBadgeGravity(NavigationTabBar.BadgeGravity.BOTTOM);
        navigationTabBar.setBadgePosition(NavigationTabBar.BadgePosition.CENTER);
        navigationTabBar.setIsBadged(true);
        navigationTabBar.setIsTitled(true);;
        navigationTabBar.setIsSwiped(true);
        navigationTabBar.setBadgeSize(15);
        navigationTabBar.setTitleSize(15);
        navigationTabBar.setIconSizeFraction(0.5f);
    }

    void changeFramgent(FragmentManager manager,String name,Fragment fragment){
        FragmentTransaction transaction =manager.beginTransaction();
        transaction.replace(R.id.viewpager,fragment,name);
        transaction.commit();
    }

    private void addEvent() {
        final FragmentManager manager=getFragmentManager();
        navigationTabBar.setOnTabBarSelectedIndexListener(new NavigationTabBar.OnTabBarSelectedIndexListener() {
            @Override
            public void onStartTabSelected(NavigationTabBar.Model model, int index) {
            }

            @Override
            public void onEndTabSelected(NavigationTabBar.Model model, int index) {
               switch (index){
                   case 0: {
                       fragment =new fragment_home();
                       changeFramgent(manager,"home",fragment);
                   }break;
                   case 1:{
                       fragment =new fragment_mypham();
                       changeFramgent(manager,"mypham",fragment);
                   };break;
                   case 2:{
                       fragment=new fragment_toc();
                       changeFramgent(manager,"toc",fragment);
                   };break;
                   case 3:{
                       fragment =new fragment_dientu();
                       changeFramgent(manager,"dientu",fragment);
                   };break;
                   case 4:{
                       fragment=new fragment_giohang();
                       changeFramgent(manager,"giohang",fragment);
                   };break;
               }
            }
        });
    }
}
