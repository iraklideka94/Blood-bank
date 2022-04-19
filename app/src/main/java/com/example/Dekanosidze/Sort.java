package com.example.Dekanosidze;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.Dekanosidze.RoomDB.AppDatabase;
import com.example.Dekanosidze.RoomDB.BloodBank;
import com.example.Dekanosidze.PagingAdapter.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort extends AppCompatActivity {
    AppDatabase db;
    RecyclerView rv;
    ArrayList<BloodBank> bloodies;
    RecycleView adapter;
    BloodBank bl;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);
        db = AppDatabase.getInstance(this);
        bloodies = new ArrayList<BloodBank>();

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "BloodGroup.json");
        Log.i("data", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<BloodBank>>() {
        }.getType();
        Jason users = gson.fromJson(jsonFileString, Jason.class);

        bloodies = (ArrayList<BloodBank>) db.userDao().getAllBloodies();
        bloodies.addAll(users.getBloodBanks());

        String checker = getIntent().getStringExtra("weight");

        if(checker != null) {
            if (checker.equals("weight")) {
                Collections.sort(bloodies, Comparator.comparing(BloodBank::getWeight));
            } else if (checker.equals("date")){
                Collections.sort(bloodies, Comparator.comparing(BloodBank::getDate));
            }
        }


        adapter = new RecycleView(bloodies);
        rv = findViewById(R.id.rvSort);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.homepage) {
            Intent intent = new Intent(Sort.this, MainActivity.class);
            startActivity(intent);
        }return true;
    }
}