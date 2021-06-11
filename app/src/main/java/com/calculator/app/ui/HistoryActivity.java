package com.calculator.app.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.calculator.app.adapters.HistoryAdapter;
import com.calculator.app.databinding.HistoryActivityBinding;
import com.calculator.app.model.HistoryData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    HistoryActivityBinding binding;

    ArrayList<HistoryData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HistoryActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {

        Type type = new TypeToken<ArrayList<HistoryData>>() {}.getType();
        data = new Gson().fromJson(getIntent().getStringExtra("history_data"), type);

        if (data != null && data.size() != 0) {
            HistoryAdapter historyAdapter = new HistoryAdapter(this,data);
            binding.recyclerView.setHasFixedSize(true);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
            binding.recyclerView.setAdapter(historyAdapter);
        }
    }

}
