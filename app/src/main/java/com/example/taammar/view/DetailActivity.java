package com.example.taammar.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.example.taammar.R;

public class DetailActivity extends AppCompatActivity {

    WebView webviewDetail;
    TextView toolbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        webviewDetail = findViewById(R.id.webview_detail);
        toolbarTitle = findViewById(R.id.tv_toolbar_title);
        String Source = getIntent().getStringExtra("source");
        if (Source.equals("info")) {
            toolbarTitle.setText("Info");
            webviewDetail.loadUrl("file:///android_asset/info.html");

        }
        else{
            toolbarTitle.setText("Tentang Aplikasi");
            webviewDetail.loadUrl("file:///android_asset/about.html");

        }
    }
}
