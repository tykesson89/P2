package com.example.henrik.p2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonShowMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        initListeners();

    }

    private void initListeners() {
        buttonShowMap.setOnClickListener(new ButtonListener());
    }

    private void initComponents() {
        buttonShowMap = (Button)findViewById(R.id.buttonShowMap);
    }

    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
           Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intent);
        }
    }
}
