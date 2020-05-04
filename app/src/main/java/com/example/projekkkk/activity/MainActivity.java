package com.example.projekkkk.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.projekkkk.R;
import com.example.projekkkk.adapter.ListKotaAdapter;
import com.example.projekkkk.service.OnClickCityListener;
import com.example.projekkkk.model.ResponseCityModel;
import com.example.projekkkk.service.JadwalSholatService;
import com.example.projekkkk.service.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements OnClickCityListener {

    private ListKotaAdapter listKotaAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list_kota);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        JadwalSholatService ApiJadwal = RetrofitUtils.getClient().create(JadwalSholatService.class);

        Call<ResponseCityModel> cityListCall = ApiJadwal.getListCity();
        cityListCall.enqueue(new Callback<ResponseCityModel>() {
            @Override
            public void onResponse(Call<ResponseCityModel> call, Response<ResponseCityModel> response) {

                listKotaAdapter = new ListKotaAdapter(response.body().getKota());
                recyclerView.setAdapter(listKotaAdapter);
                listKotaAdapter.setCityClickListener(MainActivity.this);
            }

            @Override
            public void onFailure(Call<ResponseCityModel> call, Throwable t) {
            }
        });
    }

    @Override
    public void onCityClicked(String id, String name) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("city_id",id);
        intent.putExtra("city_name", name);
        startActivity(intent);
    }
}

