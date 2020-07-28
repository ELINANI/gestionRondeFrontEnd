package com.example.gestionrondefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class agentInfo extends AppCompatActivity {
  TextView codeAgent,nomAgent,prenomAgent,pwdAgent,codePlanning;
  int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_info);
        codeAgent = findViewById(R.id.codeAgentInfo);
        nomAgent = findViewById(R.id.nomAgentInfo);
        prenomAgent = findViewById(R.id.prenomAgentInfo);
        pwdAgent=findViewById(R.id.pwdAgentInfo);
        codePlanning =findViewById(R.id.codePlanningAgentInfo);
        Intent intent = getIntent();
        position= intent.getExtras().getInt("position");
        codeAgent.setText("CodeAgent:  "+listAgent.agentModelArrayList.get(position).getCodeAgent());
        nomAgent.setText("nom Agent:  "+listAgent.agentModelArrayList.get(position).getNomAgent());
        prenomAgent.setText("Prenom Agent :  "+listAgent.agentModelArrayList.get(position).getPrenomAgent());
        pwdAgent.setText("Password:  "+listAgent.agentModelArrayList.get(position).getPawdAgent());
        codePlanning.setText("code Planning:  "+listAgent.agentModelArrayList.get(position).getCodePlanning());
    }
}
