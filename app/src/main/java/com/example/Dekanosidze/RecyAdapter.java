package com.example.Dekanosidze;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Dekanosidze.room.BloodBank;
import com.example.Dekanosidze.R;

import java.util.ArrayList;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {

    private ArrayList<BloodBank> list;

    public RecyAdapter(ArrayList<BloodBank> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyAdapter.ViewHolder holder, int position) {
//        Collections.sort(list.get(position).getWeight());
        BloodBank item = list.get(position);
        holder.bindTo(item);
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView bloodgroup;
        private final TextView phone;
        private final TextView date;
        private final TextView location;
        private final TextView weight;
        private BloodBank bloodBank;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvRVName);
            bloodgroup = itemView.findViewById(R.id.tvRVBloodGroup);
            phone = itemView.findViewById(R.id.tvRVPhone);
            date = itemView.findViewById(R.id.tvRVDate);
            location = itemView.findViewById(R.id.tvRVLocation);
            weight = itemView.findViewById(R.id.tvRVWeight);

        }

        public BloodBank getBloodBank() {
            return bloodBank;
        }

        @SuppressLint("SetTextI18n")
        void bindTo(BloodBank bloodBank) {
            this.bloodBank = bloodBank;
            name.setText(bloodBank.getName());
            bloodgroup.setText(bloodBank.getBloodGroup());
            phone.setText(bloodBank.getPhone());
            location.setText(bloodBank.getLocation());
            date.setText(String.valueOf(bloodBank.getDate()) + " year");
            weight.setText(String.valueOf(bloodBank.getWeight()) + "kg");
        }
    }
}
