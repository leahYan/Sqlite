package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView no_data2;
    MyDatabaseHelper myDB;  //initialise class object
    ArrayList<String> book_id, book_title, book_author, book_pages; //contain string
    ImageView empty_imageview2;
    CustomAdapter customAdapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        recyclerView = findViewById(R.id.recyclerView2);
        empty_imageview2 = findViewById(R.id.empty_imageview2);
        no_data2 = findViewById(R.id.no_data2);

        // Create an object of class Main (This will call the constructor)
        myDB = new MyDatabaseHelper(ViewData.this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        storeDataInArrays2();

        customAdapter2 = new CustomAdapter(ViewData.this,this, book_id, book_title, book_author,
                book_pages);
        recyclerView.setAdapter(customAdapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewData.this));
    }

    void storeDataInArrays2(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview2.setVisibility(View.VISIBLE);
            no_data2.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
            empty_imageview2.setVisibility(View.GONE);
            no_data2.setVisibility(View.GONE);
        }
    }
}