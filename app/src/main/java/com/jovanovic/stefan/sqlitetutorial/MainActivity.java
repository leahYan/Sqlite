package com.jovanovic.stefan.sqlitetutorial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.provider.Settings;
import android.provider.Settings.System;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;
    private Button viewdata;

    MyDatabaseHelper myDB;  //initialise class object

    //CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        viewdata = findViewById(R.id.Viewdata);
        viewdata.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ViewData.class);  //moving to a new screen
            startActivity(intent);
        });

        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        // Create an object of class Main (This will call the constructor)
        myDB = new MyDatabaseHelper(MainActivity.this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.senddata) {
            Intent i = new Intent(MainActivity.this, SenddataActivity.class);
            this.startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

