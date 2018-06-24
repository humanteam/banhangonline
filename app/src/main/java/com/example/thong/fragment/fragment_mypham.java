package com.example.thong.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.thong.APIs;
import com.example.thong.adapter.Adapter_Home;
import com.example.thong.adapter.AlbumsAdapter;
import com.example.thong.banhangonline.R;
import com.example.thong.model.SanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fragment_mypham extends Fragment {

    AlbumsAdapter adapter;
    ArrayList<SanPham> dssp=new ArrayList<>();
    View view;
    RecyclerView recyclerView;

    int lastpositon=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_mypham,container,false);
        addControlls(view);
        addEvents();
        return view;
    }

    private void addEvents() {

    }

    private void addControlls(View view) {
        recyclerView=view.findViewById(R.id.list_mypham);
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(manager);
        adapter =new AlbumsAdapter(getActivity(),dssp);
        recyclerView.setAdapter(adapter);
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
        Volley.newRequestQueue(getActivity()).cancelAll(request);
        view=null;
        super.onDestroyView();
    }
    public JsonArrayRequest request =new JsonArrayRequest(APIs.api_mypham, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            dssp.clear();
            for(int i=lastpositon;i<response.length();i++){
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
            adapter.notifyDataSetChanged();
            Log.e("data_lenght",dssp.size()+"");
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
        }
    }){
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String,String>params =new HashMap<>();
            params.put("content-type","application/json; charset=utf-8");
            return params;
        }
    };
}
