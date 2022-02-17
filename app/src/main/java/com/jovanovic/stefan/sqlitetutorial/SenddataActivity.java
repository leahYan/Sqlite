package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SenddataActivity extends AppCompatActivity {

    private EditText eMsg;
    private Button btn;
    CustomAdapter customAdapter;
    MyDatabaseHelper myDB;  //initialise class object
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senddata);

        myDB = new MyDatabaseHelper(SenddataActivity.this);
        eMsg = findViewById(R.id.txtMsg);
        btn = findViewById(R.id.btnSend);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);
                sqLiteDatabase = myDB.getReadableDatabase();
                it.setData(Uri.parse("mailto:")); // only email apps should handle this
                it.putExtra(Intent.EXTRA_EMAIL, new String[]{"RTAData@Riotinto.com"});
                it.putExtra(Intent.EXTRA_SUBJECT,"Test email");
                it.putExtra(Intent.EXTRA_TEXT,eMsg.getText());
                it.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(sqLiteDatabase));
                it.setType("message/rfc822");
                startActivity(Intent.createChooser(it,"Choose Mail App"));
            }
        });

    }
}

/*
// Gets the current date
    System.currentTimeMillis();
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Date date = new Date(System.currentTimeMillis());

*/