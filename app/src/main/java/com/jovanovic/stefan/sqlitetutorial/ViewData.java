package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView no_data2;
    MyDatabaseHelper myDB;  //initialise class object
    ArrayList<String> sample_id, pulp_id, carton_id, box_id; //contain string
    ImageView empty_imageview2;
    CustomAdapter customAdapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        recyclerView = findViewById(R.id.recyclerView2);
        no_data2 = findViewById(R.id.no_data2);
        empty_imageview2 = findViewById(R.id.empty_imageview2);


        // Create an object of class Main (This will call the constructor)
        myDB = new MyDatabaseHelper(ViewData.this);
        sample_id = new ArrayList<>();
        pulp_id = new ArrayList<>();
        carton_id = new ArrayList<>();
        box_id = new ArrayList<>();

        storeDataInArrays2();

        customAdapter2 = new CustomAdapter(ViewData.this, this, sample_id, pulp_id, carton_id,
                box_id);
        recyclerView.setAdapter(customAdapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewData.this));
    }

    void storeDataInArrays2() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            empty_imageview2.setVisibility(View.VISIBLE);
            no_data2.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                sample_id.add(cursor.getString(0));
                pulp_id.add(cursor.getString(1));
                carton_id.add(cursor.getString(2));
                box_id.add(cursor.getString(3));
            }
            empty_imageview2.setVisibility(View.GONE);
            no_data2.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete_all) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(ViewData.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(ViewData.this, ViewData.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }
}