package com.example.taammar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UsiaGenderActivity extends AppCompatActivity {

    String age ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usia_gender);

        Button buttonOK = findViewById(R.id.btn_ok);
        final EditText editTextAge = findViewById(R.id.et_age);
        Spinner spinnerGender = findViewById(R.id.sp_gender);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age = editTextAge.getText().toString();
                validateUI(age);
            }
        });
    }

    public void validateUI(String age){
        int a = 0;
        try {
             a = Integer.parseInt(age);
        }
        catch (Exception e ){
            Log.e("Error", e.toString());
        }
        if( a <=10){
            Toast.makeText(this,"Tezt",Toast.LENGTH_SHORT).show();
        }
    }
}
