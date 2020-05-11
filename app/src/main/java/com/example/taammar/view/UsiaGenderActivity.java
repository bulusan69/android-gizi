package com.example.taammar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.taammar.R;
import com.example.taammar.db.DataHelper;
import com.example.taammar.helper.Utility;
import com.example.taammar.model.MappingGizi;

public class UsiaGenderActivity extends AppCompatActivity {

    String age ;
    DataHelper dbcenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usia_gender);
        dbcenter = new DataHelper(this);
        Button buttonOK = findViewById(R.id.btn_ok);
        final EditText editTextAge = findViewById(R.id.et_age);
        final Spinner spinnerGender = findViewById(R.id.sp_gender);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age = editTextAge.getText().toString();
                validateUI(age,spinnerGender.getSelectedItem().toString());
            }
        });
    }

    public void validateUI(String age, String Gender){
        int Age = Utility.stringToInt(age);
        if( Age <=10){
            Toast.makeText(this,"Tezt",Toast.LENGTH_SHORT).show();
        }
        else {
            final MappingGizi mappingGizi = dbcenter.getValue(age,Gender);
            if ( mappingGizi != null ) {
                Toast.makeText(this, "VitC = " + mappingGizi.getVitC(), Toast.LENGTH_SHORT).show();
                //Todo: Data db masuk model MappingGizi, Tanmpilkan MappingGizi ke Activity Selanjutnya
            }
            else {
                Toast.makeText(this, "Can not find data ", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
