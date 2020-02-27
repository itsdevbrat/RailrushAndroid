package com.example.railrush.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railrush.Models.Train;
import com.example.railrush.R;

import java.util.ArrayList;

public class TrainsRVAdapter extends RecyclerView.Adapter<TrainViewHolder> {
    ArrayList<Train> trainsList;
    public TrainsRVAdapter(ArrayList<Train> trainsList) {
        this.trainsList = trainsList;
    }

    @NonNull
    @Override
    public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_train,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
        holder.start.setText(trainsList.get(position).getStart());
        holder.dest.setText(trainsList.get(position).getDest());
        holder.time.setText(trainsList.get(position).getTime());
        holder.count.setText(trainsList.get(position).getCount());
    }

    @Override
    public int getItemCount() {
        return trainsList.size();
    }
}
class TrainViewHolder extends RecyclerView.ViewHolder{
    TextView start,dest,time,count;
    public TrainViewHolder(@NonNull View itemView) {
        super(itemView);
        start = itemView.findViewById(R.id.start);
        dest = itemView.findViewById(R.id.dest);
        time = itemView.findViewById(R.id.time);
        count = itemView.findViewById(R.id.count);
    }
}
