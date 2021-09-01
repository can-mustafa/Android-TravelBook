package com.mustafacan.travelmarks.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mustafacan.travelmarks.databinding.RecyclerRowBinding;
import com.mustafacan.travelmarks.model.TravelMark;
import com.mustafacan.travelmarks.view.MapsActivity;

import java.util.List;

public class TravelMarkAdapter extends RecyclerView.Adapter<TravelMarkAdapter.TravelMarkHolder> {

    List<TravelMark> travelMarkList;

    public TravelMarkAdapter(List<TravelMark> travelMarkList){
        this.travelMarkList = travelMarkList;
    }

    @NonNull
    @Override
    public TravelMarkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new TravelMarkHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelMarkHolder holder, int position) {
        holder.recyclerRowBinding.recyclerViewTextView.setText(travelMarkList.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), MapsActivity.class);
                intent.putExtra("info","old");
                intent.putExtra("travelmark",travelMarkList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return travelMarkList.size();
    }

    public class TravelMarkHolder extends RecyclerView.ViewHolder {
        RecyclerRowBinding recyclerRowBinding;
        public TravelMarkHolder(RecyclerRowBinding recyclerRowBinding) {
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }
    }
}
