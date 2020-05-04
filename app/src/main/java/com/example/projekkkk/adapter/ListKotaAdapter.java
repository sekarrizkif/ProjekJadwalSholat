package com.example.projekkkk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.projekkkk.R;
import com.example.projekkkk.service.OnClickCityListener;
import com.example.projekkkk.model.CityModel;

import java.util.ArrayList;

public class ListKotaAdapter extends RecyclerView.Adapter<ListKotaAdapter.CityViewHolder> {

    private OnClickCityListener onClickCityListener;
    private ArrayList<CityModel> cityList;


    public ListKotaAdapter(ArrayList<CityModel> cityModelArrayList) {
        cityList = cityModelArrayList;
    }

    public void setCityClickListener(OnClickCityListener onClickCityListener){
        this.onClickCityListener = onClickCityListener;
    }


    @Override
    public ListKotaAdapter.CityViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_city, parent, false);
        CityViewHolder viewHolder = new CityViewHolder(v);
        return viewHolder;
    }


    public class CityViewHolder extends RecyclerView.ViewHolder {
        public TextView tvKota;

        public CityViewHolder(View v) {
            super(v);
            tvKota = v.findViewById(R.id.tv_kota);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CityModel cityModel = cityList.get(getAdapterPosition());
                    onClickCityListener.onCityClicked(cityModel.getId(), cityModel.getNama());
                }
            });
        }
    }


    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {

        holder.tvKota.setText(cityList.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }


}

