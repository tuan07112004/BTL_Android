package com.example.oke.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oke.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        ProgressBar progressBar = findViewById(R.id.progressBar);

        // Start progress (though it's indeterminate, this makes it visible)
        progressBar.setIndeterminate(true);

        // Delay for 2 seconds then navigate to LoginActivity
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close this activity so user can't go back
        }, 2000);
    }

    @Override
    protected void onDestroy() {
        // Clean up resources if needed
        super.onDestroy();
    }
}