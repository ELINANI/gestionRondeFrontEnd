package com.example.gestionrondefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class tacheDetails extends AppCompatActivity {
    TextView codePlanning,desc ,codeAdmin ;
    int postion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tache_details);
        codePlanning = findViewById(R.id.TachecodePlanningDetails);
        desc= findViewById(R.id.TachedescDetails);
        Intent intent = getIntent();
        postion= intent.getExtras().getInt("position");
        codePlanning.setText("CodePlanning:  "+TacheAgentActivity.planningModelArrayList.get(postion).getCodePlanning());
        desc.setText("Description:  "+TacheAgentActivity.planningModelArrayList.get(postion).getDesc());
    }

    public void commencer(View view) {
     Intent i = new Intent(getApplicationContext(),tacheAgentPlanning.class);
     startActivity(i);
    }
}
