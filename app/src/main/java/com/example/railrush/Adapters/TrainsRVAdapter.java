package com.example.railrush.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.railrush.Models.Train;
import com.example.railrush.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class TrainsRVAdapter extends RecyclerView.Adapter<TrainViewHolder> {
    ArrayList<Train> trainsList;
    Context context;
    public TrainsRVAdapter(ArrayList<Train> trainsList) {
        this.trainsList = trainsList;
    }

    @NonNull
    @Override
    public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new TrainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_train,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
        holder.start.setText(trainsList.get(position).getStart());
        holder.dest.setText(trainsList.get(position).getDest());
        holder.time.setText(trainsList.get(position).getTime());
        holder.count.setText(trainsList.get(position).getCount());
        holder.lastStation.setText(trainsList.get(position).getLastStation());
        if(Integer.parseInt(trainsList.get(position).getCount()) < 80)
            holder.trainsRVItem.setBackground(context.getDrawable(R.drawable.round_corners_green));
        else
            holder.trainsRVItem.setBackground(context.getDrawable(R.drawable.round_corners_red));
    }

    @Override
    public int getItemCount() {
        return trainsList.size();
    }
}
class TrainViewHolder extends RecyclerView.ViewHolder{
    TextView start,dest,time,count,lastStation;
    ConstraintLayout trainsRVItem;
    public TrainViewHolder(@NonNull View itemView) {
        super(itemView);
        trainsRVItem = itemView.findViewById(R.id.trainsRVItem);
        start = itemView.findViewById(R.id.start);
        dest = itemView.findViewById(R.id.dest);
        time = itemView.findViewById(R.id.time);
        count = itemView.findViewById(R.id.count);
        lastStation = itemView.findViewById(R.id.lastStation);
    }
}
