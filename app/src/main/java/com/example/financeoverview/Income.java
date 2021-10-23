package com.example.financeoverview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


public class Income extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue);

        String[] s1 = {"Canadian Tire", "REI.UN DIV"};
        String[] s2 = {"Hardware Associate", "test"};

        RecyclerView recyclerView = findViewById(R.id.recyclyerView);

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, s1, s2);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}