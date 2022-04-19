package com.example.Dekanosidze.paging;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Dekanosidze.BloodForJason;
import com.example.Dekanosidze.R;
import com.example.Dekanosidze.room.BloodBank;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class BloodPagingAdapter extends PagedListAdapter<BloodBank, BloodPagingAdapter.BloodViewHolder> {

    Context context;

    public BloodPagingAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public BloodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new BloodViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BloodViewHolder holder, int position) {
        BloodBank item = getItem(position);
        holder.bindTo(item);

if(position<2) {
    for(int i=0; i<2; i++) {
        String jsonFileString = Utils.getJsonFromAssets(context, "BloodGroup.json");
        Log.i("data", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<BloodBank>>() {
        }.getType();
        BloodForJason users = gson.fromJson(jsonFileString, BloodForJason.class);
        holder.name.setText(users.getBloodBanks().get(position).getName());
        holder.bloodgroup.setText(users.getBloodBanks().get(position).getBloodGroup());
        holder.location.setText(users.getBloodBanks().get(position).getLocation());
        holder.phone.setText(String.valueOf(users.getBloodBanks().get(position).getPhone()));
        holder.date.setText(String.valueOf(users.getBloodBanks().get(position).getDate()) + " year");
        holder.weight.setText(String.valueOf(users.getBloodBanks().get(position).getWeight()) + "kg");
    }
    }



    }

    public static class BloodViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView bloodgroup;
        private final TextView phone;
        private final TextView date;
        private final TextView location;
        private final TextView weight;
        private BloodBank bloodBank;

        BloodViewHolder(View itemView) {
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
            date.setText(String.valueOf(bloodBank.getDate())+ " year");
            weight.setText(String.valueOf(bloodBank.getWeight())+ "kg");
        }
    }

    private static final DiffUtil.ItemCallback<BloodBank> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<BloodBank>() {
                @Override
                public boolean areItemsTheSame(@NonNull BloodBank oldItem, @NonNull BloodBank newItem) {
                    return oldItem.getName().equals(newItem.getName());
                }

                @Override
                public boolean areContentsTheSame(@NonNull BloodBank oldItem, @NonNull BloodBank newItem) {
                    return oldItem == newItem;
                }
            };
}
