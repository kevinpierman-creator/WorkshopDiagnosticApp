package com.workshop.diagnostic;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
            Log.d(TAG, "MainActivity successfully created");
        } catch (Exception e) {
            Log.e(TAG, "Error loading activity layout", e);
            Toast.makeText(this, "Error loading app: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }
}