package com.example.projekkkk.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekkkk.R;

import java.util.ArrayList;


public class DataDiriAdapter extends RecyclerView.Adapter<DataDiriAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DataDiri> datadiriItem = new ArrayList<>();
    private AppDatabase appDatabase;

    public DataDiriAdapter(Context context) {
        this.context = context;
        appDatabase = AppDatabase.iniDB(this.context);
    }

    public void setDatadiriItem(ArrayList<DataDiri> items) {
        datadiriItem.clear();
        datadiriItem.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lihat_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvNama.setText(datadiriItem.get(position).getName());
        holder.tvEmail.setText(datadiriItem.get(position).getEmail());
        holder.tvAlamat.setText(datadiriItem.get(position).getAlamat());


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DataDiri dataDiri = new DataDiri();

                dataDiri.setId(datadiriItem.get(position).getId());
                dataDiri.setName(datadiriItem.get(position).getName());
                dataDiri.setEmail(datadiriItem.get(position).getEmail());
                dataDiri.setAlamat(datadiriItem.get(position).getAlamat());


                appDatabase.dao().deletedata(dataDiri);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datadiriItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button btnDelete;
        TextView tvNama, tvEmail, tvAlamat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnDelete = itemView.findViewById(R.id.btn_hapus);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvEmail = itemView.findViewById(R.id.tv_email);
            tvAlamat = itemView.findViewById(R.id.tv_alamat);
        }
    }
}