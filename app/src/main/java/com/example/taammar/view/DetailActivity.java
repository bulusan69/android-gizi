package com.example.taammar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.taammar.R;

public class DetailActivity extends AppCompatActivity {

    WebView webviewDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        webviewDetail = findViewById(R.id.webview_detail);
        String Source = getIntent().getStringExtra("source");
        if (Source.equals("info")) {
            webviewDetail.loadUrl("file:///android_asset/info.html");
        }
        else{
            webviewDetail.loadUrl("file:///android_asset/about.html");
        }
    }
}
