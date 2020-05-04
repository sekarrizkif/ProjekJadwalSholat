package com.example.projekkkk.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.projekkkk.R;
import java.util.ArrayList;


public class LihatDataActivity extends AppCompatActivity {

    private DataDiriAdapter dataDiriAdapter;
    private RecyclerView rvdatadiri;
    private AppDatabase appDatabase;
    private ArrayList<DataDiri> listdatadiri = new ArrayList<DataDiri>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        rvdatadiri = findViewById(R.id.rv_lihatdata);
        dataDiriAdapter = new DataDiriAdapter(getApplicationContext());
        dataDiriAdapter.notifyDataSetChanged();

        if (appDatabase == null){
            appDatabase = AppDatabase.iniDB(getApplicationContext());
        }

        listdatadiri.addAll(appDatabase.dao().getData());
        dataDiriAdapter.setDatadiriItem(listdatadiri);

        rvdatadiri.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvdatadiri.setAdapter(dataDiriAdapter);


    }

}
