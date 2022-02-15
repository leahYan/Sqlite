package com.jovanovic.stefan.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

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
                int errorcount = 0;
                int d = 0;
                //view.setBackgroundColor(Color.rgb(0, 0, 0)); //Color.RED
                if (pulp_input.length()==0) {
                    pulp_input.setError("Pulp ID can not be null");
                    d =1;
                    errorcount = errorcount+1;

                }
                if (carton_input.length()==0){
                    carton_input.setError("Carton ID can not be null");
                   d =2;
                    errorcount =errorcount+1;
                }
                if (box_input.length()==0){
                    box_input.setError("Box ID is null");
                    d =3;
                    errorcount = errorcount+1;
                }
                if (errorcount == 1 ){
                  switch(d){
                      case 1:Toast.makeText(AddActivity.this, "Pulp ID can not be null", Toast.LENGTH_SHORT).show();
                      break;
                      case 2:Toast.makeText(AddActivity.this, "Carton ID can not be null", Toast.LENGTH_SHORT).show();
                      break;
                      case 3:Toast.makeText(AddActivity.this, "Box ID can not be null", Toast.LENGTH_SHORT).show();
                          break;
                  }
                }
                else if (errorcount >= 2){
                    Toast.makeText(AddActivity.this, "Multiple invalid input", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                    myDB.addBook(pulp_input.getText().toString().trim(),
                            carton_input.getText().toString().trim(),
                            Integer.valueOf(box_input.getText().toString().trim()));
                }
                }
            });

        carton_input.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    carton_input.setError(null);

                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                int after) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void afterTextChanged(Editable s) {
                    carton_input.setError(null);

                }
            });

    }
}
