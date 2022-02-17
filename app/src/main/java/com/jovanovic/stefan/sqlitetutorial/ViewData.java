package com.jovanovic.stefan.sqlitetutorial;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;

public class ViewData extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView no_data2;
    MyDatabaseHelper myDB;  //initialise class object
    ArrayList<String> sample_id, pulp_id, carton_id, box_id; //contain string
    ImageView empty_imageview2;
    CustomAdapter customAdapter2;

    EditText searchPulp, searchCarton, searchBox;
    Button searchButton;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    String NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        recyclerView = findViewById(R.id.recyclerView2);
        no_data2 = findViewById(R.id.no_data2);
        empty_imageview2 = findViewById(R.id.empty_imageview2);
        searchPulp = (EditText) findViewById(R.id.search_pulp);
        searchCarton = (EditText) findViewById(R.id.search_carton);
        searchBox = (EditText) findViewById(R.id.search_box);
        searchButton = (Button) findViewById(R.id.searchButton);

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

        searchPulp = (EditText) findViewById(R.id.search_pulp);
        searchCarton = (EditText) findViewById(R.id.search_carton);
        searchBox = (EditText) findViewById(R.id.search_box);
        searchButton = (Button) findViewById(R.id.searchButton);
        //search
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NAME = searchBox.getText().toString();
                sqLiteDatabase = myDB.getReadableDatabase();
                cursor = myDB.searchContacts(NAME,sqLiteDatabase);
                if(NAME.length()>0) {
                    if (cursor.moveToFirst()) {
                        do {
                            String pulp, carton;
                            pulp = cursor.getString(0);
                            carton = cursor.getString(1);

                            searchPulp.setText(pulp);
                            searchCarton.setText(carton);

                        } while (cursor.moveToNext());
                    }
                }
            }
        });
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }
/*
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

 */
}