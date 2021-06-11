package com.calculator.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.calculator.app.databinding.HistoryDataCardBinding;
import com.calculator.app.model.HistoryData;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<HistoryData> historyDataList;
    private Context context;

    public HistoryAdapter(Context context, ArrayList<HistoryData> historyDataArrayList) {
        this.context = context;
        this.historyDataList = historyDataArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(HistoryDataCardBinding.
                inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryData historyData = historyDataList.get(position);
        holder.binding.tvOperation.setText(historyData.getOperation() );
        holder.binding.tvResult.setText("  =  " + historyData.getResults());

    }


    @Override
    public int getItemCount() {
        return historyDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        HistoryDataCardBinding binding;
        public ViewHolder( HistoryDataCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
