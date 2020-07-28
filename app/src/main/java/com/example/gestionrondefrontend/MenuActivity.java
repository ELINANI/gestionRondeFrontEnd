package com.example.gestionrondefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void flyToPlanning(View view) {
        Intent i = new Intent(getApplicationContext(),listPlanning.class);
        startActivity(i);
    }

    public void flyToAgent(View view) {
        Intent i = new Intent(getApplicationContext(),listAgent.class);
        startActivity(i);
    }

    public void flyToBLE(View view) {
        Intent i = new Intent(getApplicationContext(),listBLE.class);
        startActivity(i);
    }

    public void flyToTrace(View view) {
        Intent i = new Intent(getApplicationContext(),listTrace.class);
        startActivity(i);
    }

    public void Back(View view) {
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);
    }
}
