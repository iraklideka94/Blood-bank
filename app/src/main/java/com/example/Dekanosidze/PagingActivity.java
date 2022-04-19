package com.example.Dekanosidze;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.Dekanosidze.PagingAdapter.PagingAdapter;
import com.example.Dekanosidze.PagingAdapter.PagingViewModel;
import com.example.Dekanosidze.PagingAdapter.ViewModelFactory;
import com.example.Dekanosidze.RoomDB.BloodBank;

public class PagingActivity extends AppCompatActivity {
    private PagingViewModel mViewModel;
    private RecyclerView mRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);
        ViewModelFactory smileyViewModelFactory = ViewModelFactory.createFactory(this);
        mViewModel = ViewModelProviders.of(this, smileyViewModelFactory).get(PagingViewModel.class);

        mRecycler = findViewById(R.id.rvSmileyList);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        PagingAdapter adapter = new PagingAdapter(getApplicationContext());

        initAction();

        mViewModel.getAllSmileys().observe(this, adapter::submitList);

        mRecycler.setAdapter(adapter);

    }

    public void initAction() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                        @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                BloodBank smiley = ((PagingAdapter.BloodViewHolder) viewHolder).getBloodBank();
                mViewModel.delete(smiley);

                String text = "deleted: %1$s" + smiley.getName();
            }
        });

        itemTouchHelper.attachToRecyclerView(mRecycler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.home) {
            Intent intent = new Intent(PagingActivity.this, MainActivity.class);
            startActivity(intent);
        }else if(id == R.id.sortWeight){
            Intent intent = new Intent(PagingActivity.this, Sort.class);
            intent.putExtra("weight", "weight");
            startActivity(intent);
        }else if(id == R.id.sortBlood){
            Intent intent = new Intent(PagingActivity.this, Sort.class);
            intent.putExtra("weight", "date");
            startActivity(intent);
        } return true;
    }
}