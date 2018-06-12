package com.example.thong.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.thong.APIs;
import com.example.thong.adapter.Adapter_Hone;
import com.example.thong.banhangonline.R;
import com.example.thong.model.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.support.v7.widget.LinearLayoutManager.*;

public class fragment_home extends Fragment {

    RecyclerView list;
    Adapter_Hone adapter;
    ArrayList<SanPham>dssp =new ArrayList<>();
    int min=0,max=5,lastpositon=0;
    boolean is_loading =false;
    View view;
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         view =inflater.inflate(R.layout.fragment_home,container,false);
         addControlls();
         addEvents();
        return view;
    }

    private void addEvents(){
     list.addOnScrollListener(new RecyclerView.OnScrollListener() {
         @Override
         public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
             super.onScrollStateChanged(recyclerView, newState);
         }

         @Override
         public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
             super.onScrolled(recyclerView, dx, dy);
             
         }
     });
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addControlls() {
        list=view.findViewById(R.id.list_home);
        LinearLayoutManager manager =new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        list.setLayoutManager(manager);
        adapter=new Adapter_Hone(dssp);
        list.setAdapter(adapter);
        Volley.newRequestQueue(getActivity()).add(request);
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
        JsonArrayRequest request =new JsonArrayRequest(APIs.api_home, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
              for(int i=lastpositon;i<lastpositon+8 && i<response.length();i++){
                  try {
                      JSONObject obj =response.getJSONObject(i);
                      SanPham sp =new SanPham();
                      sp.setMasp(obj.getString("MaSP"));
                      sp.setTensp(obj.getString("TenSP"));
                      sp.setAnh(obj.getString("AnhSP"));
                      sp.setChitiet(obj.getString("ChiTietSP"));
                      sp.setGia(obj.getString("GiaSP"));
                      sp.setMatheloai(obj.getString("MaTheLoai"));
                      dssp.add(sp);
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
              }
              lastpositon+=8;
              adapter.notifyDataSetChanged();
                Log.e("data_lenght",dssp.size()+"");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });
}
