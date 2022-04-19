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

import com.example.Dekanosidze.paging.BloodPagingAdapter;
import com.example.Dekanosidze.paging.BloodPagingViewModel;
import com.example.Dekanosidze.paging.BloodViewModelFactory;
import com.example.Dekanosidze.room.BloodBank;
import com.example.Dekanosidze.R;

public class PagingActivity extends AppCompatActivity {
    private BloodPagingViewModel mViewModel;
    private RecyclerView mRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);
        BloodViewModelFactory smileyViewModelFactory = BloodViewModelFactory.createFactory(this);
        mViewModel = ViewModelProviders.of(this, smileyViewModelFactory).get(BloodPagingViewModel.class);

        mRecycler = findViewById(R.id.rvSmileyList);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        BloodPagingAdapter adapter = new BloodPagingAdapter(getApplicationContext());

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
                BloodBank smiley = ((BloodPagingAdapter.BloodViewHolder) viewHolder).getBloodBank();
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
            Intent intent = new Intent(PagingActivity.this, SortingActivity.class);
            intent.putExtra("weight", "weight");
            startActivity(intent);
        }else if(id == R.id.sortBlood){
            Intent intent = new Intent(PagingActivity.this, SortingActivity.class);
            intent.putExtra("weight", "date");
            startActivity(intent);
        } return true;
    }
}