package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText pulp_input, carton_input, box_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        pulp_input = findViewById(R.id.pulp_input);
        carton_input = findViewById(R.id.carton_input);
        box_input = findViewById(R.id.box_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(pulp_input.getText().toString().trim(),
                        carton_input.getText().toString().trim(),
                        Integer.valueOf(box_input.getText().toString().trim()));
            }
        });
    }
}
