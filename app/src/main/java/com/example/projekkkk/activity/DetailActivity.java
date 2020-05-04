package com.example.projekkkk.activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projekkkk.R;
import com.example.projekkkk.fragment.TanggalFragment;
import com.example.projekkkk.model.ResponseDetailModel;
import com.example.projekkkk.service.JadwalSholatService;
import com.example.projekkkk.service.RetrofitUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView tvSubuh;
    private TextView tvDzuhur;
    private TextView tvAshar;
    private TextView tvMagrib;
    private TextView tvIsya;
    private TextView tvTanggal;
    private LinearLayout jadwalContainer;
    private ProgressBar progressBar;
    private String kotaId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        kotaId = getIntent().getStringExtra("city_id");

        tvSubuh = findViewById(R.id.tv_subuh);
        tvDzuhur = findViewById(R.id.tv_dzuhur);
        tvAshar = findViewById(R.id.tv_ashar);
        tvMagrib = findViewById(R.id.tv_magrib);
        tvIsya = findViewById(R.id.tv_isya);
        tvTanggal = findViewById(R.id.tv_tanggal);
        jadwalContainer = findViewById(R.id.jadwal_container);
        progressBar = findViewById(R.id.progress_bar);

        Button button = findViewById(R.id.btn_tanggal);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new TanggalFragment();
                datePicker.show(getSupportFragmentManager(), "tanggal");
            }
        });


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateString = dateFormat.format(date);
        tvTanggal.setText(dateString);
        requestData(kotaId, dateString);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);

        Date date = new Date();
        date.setTime(calendar.getTimeInMillis());
        String dateString = dateFormat.format(date);

        tvTanggal.setText(dateString);
        requestData(kotaId, dateString);
    }


    private void requestData(String cityId, final String dateString) {
        progressBar.setVisibility(View.VISIBLE);
        jadwalContainer.setVisibility(View.GONE);

        JadwalSholatService ApiJadwal = RetrofitUtils.getClient().create(JadwalSholatService.class);
        Call<ResponseDetailModel> cityListCall = ApiJadwal.getJadwalSholat(cityId, dateString);
        cityListCall.enqueue(new Callback<ResponseDetailModel>() {
            @Override
            public void onResponse(Call<ResponseDetailModel> call, Response<ResponseDetailModel> response) {
                ResponseDetailModel responseDetailModel = response.body();
                tvSubuh.setText(responseDetailModel.getJadwal().getData().getSubuh());
                tvDzuhur.setText(responseDetailModel.getJadwal().getData().getDzuhur());
                tvAshar.setText(responseDetailModel.getJadwal().getData().getAshar());
                tvMagrib.setText(responseDetailModel.getJadwal().getData().getMaghrib());
                tvIsya.setText(responseDetailModel.getJadwal().getData().getIsya());
                progressBar.setVisibility(View.GONE);
                jadwalContainer.setVisibility(View.VISIBLE);


            }

            @Override
            public void onFailure(Call<ResponseDetailModel> call, Throwable t) {
            }
        });
    }


}
