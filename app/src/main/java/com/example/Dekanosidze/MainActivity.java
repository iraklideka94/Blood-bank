package com.example.Dekanosidze;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Dekanosidze.RoomDB.AppDatabase;
import com.example.Dekanosidze.RoomDB.BloodBank;

public class MainActivity extends AppCompatActivity {
    EditText etName, etBloodGroup, etPhone, etLocation, etDate, etWeight;
    Button btn;
    private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(this);

        btn = findViewById(R.id.btnAdd);
        etName = findViewById(R.id.etName);
        etBloodGroup = findViewById(R.id.etBloodGroup);
        etPhone = findViewById(R.id.etPhone);
        etLocation = findViewById(R.id.etLocation);
        etDate = findViewById(R.id.etDate);
        etWeight = findViewById(R.id.etWeight);



        btn.setOnClickListener(v->{
            db.userDao().insert(new BloodBank(etName.getText().toString(), etBloodGroup.getText().toString(), etPhone.getText().toString(),
                    etLocation.getText().toString(), Integer.parseInt(etDate.getText().toString()), Integer.parseInt(etWeight.getText().toString())));

//            edtr.putString("name",etName.getText().toString());
//            edtr.putString("flavor",etFlavor.getText().toString());
//            edtr.putString("expDate",etExpDate.getText().toString());
//            edtr.putString("brand",etBrand.getText().toString());
//            edtr.putString("weight",Integer.parseInt(etWeight.getText().toString()));
//            edtr.commit();

            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, PagingActivity.class);
        startActivity(intent);
        });
    }

    }
