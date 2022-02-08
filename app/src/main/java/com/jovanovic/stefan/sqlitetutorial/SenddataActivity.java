package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SenddataActivity extends AppCompatActivity {

    private EditText eMsg;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senddata);

        eMsg = findViewById(R.id.txtMsg);
        btn = findViewById(R.id.btnSend);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Intent.ACTION_SEND);
                it.putExtra(Intent.EXTRA_EMAIL, new String[]{"RTAData@Riotinto.com"});
                it.putExtra(Intent.EXTRA_SUBJECT,"Test email");
                it.putExtra(Intent.EXTRA_TEXT,eMsg.getText());
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