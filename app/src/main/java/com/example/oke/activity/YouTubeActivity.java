package com.example.oke.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oke.R;

public class YouTubeActivity extends AppCompatActivity {
    WebView webViewYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);

        webViewYoutube = findViewById(R.id.webViewYoutube);
        WebSettings webSettings = webViewYoutube.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webViewYoutube.setWebViewClient(new WebViewClient());

        // Nhận linkvideo từ intent
        String videoId = getIntent().getStringExtra("linkvideo");
        if (videoId != null && !videoId.isEmpty()) {
            String url = "https://www.youtube.com/embed/" + videoId;
            webViewYoutube.loadUrl(url);
        }
    }
}
