package com.example.taammar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.taammar.R;

public class InfoActivity extends AppCompatActivity {

    RelativeLayout rv_penggunaan_aplikasi;
    RelativeLayout rv_info_aplikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        rv_penggunaan_aplikasi = findViewById(R.id.rv_penggunaan_aplikasi);
        rv_info_aplikasi = findViewById(R.id.rv_info_aplikasi);
        rv_penggunaan_aplikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, DetailActivity.class);
                intent.putExtra("source","howtouse");
                startActivity(intent);
            }
        });

        rv_info_aplikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, DetailActivity.class);
                intent.putExtra("source","Informasi");
                startActivity(intent);
            }
        });
    }
}
