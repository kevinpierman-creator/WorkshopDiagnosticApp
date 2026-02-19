package com.workshop.diagnostic;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(16, 16, 16, 16);

        TextView title = new TextView(this);
        title.setText("Workshop Diagnostic Tool");
        title.setTextSize(24f);

        TextView info = new TextView(this);
        info.setText("App is running. You can extend it with car diagnostic features.");
        info.setTextSize(16f);

        layout.addView(title);
        layout.addView(info);
        setContentView(layout);
    }
}