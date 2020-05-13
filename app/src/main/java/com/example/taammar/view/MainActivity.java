package com.example.taammar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.taammar.R;
import com.example.taammar.db.DataHelper;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        Button buttonStart = findViewById(R.id.btn_start);
        Button buttonInfo = findViewById(R.id.btn_info);
        Button buttonAbout = findViewById(R.id.btn_about);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UsiaGenderActivity.class);
        startActivity(intent);
        //initDB();
    }
});
        }
}
