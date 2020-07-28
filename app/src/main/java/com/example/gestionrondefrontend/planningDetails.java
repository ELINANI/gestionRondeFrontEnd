package com.example.gestionrondefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class planningDetails extends AppCompatActivity {
 TextView codePlanning,desc ,codeAdmin ;
 int postion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning_details);
        codePlanning = findViewById(R.id.codePlanningDetails);
        desc = findViewById(R.id.descDetails);
        codeAdmin=findViewById(R.id.codeAdminDetails);
        Intent intent = getIntent();
        postion= intent.getExtras().getInt("position");
        codePlanning.setText("CodePlanning:  "+listPlanning.planningModelArrayList.get(postion).getCodePlanning());
        desc.setText("Description:  "+listPlanning.planningModelArrayList.get(postion).getDesc());
        codeAdmin.setText("CodePlanning:  "+listPlanning.planningModelArrayList.get(postion).getCodeAdmin());

    }
}
