package com.example.gestionrondefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TraceDetails extends AppCompatActivity {
   TextView codeTrace,idBLE,codeAgent;
   int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace_details);
        codeTrace = findViewById(R.id.codeTraceDetails);
        idBLE= findViewById(R.id.idBLEDetails);
        codeAgent = findViewById(R.id.codeAgentTraceDetails);
        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");
        codeTrace.setText("CodePlanning:  "+listTrace.traceModelArrayList.get(position).getCodeTrace());
        idBLE.setText("CodePlanning:  "+listTrace.traceModelArrayList.get(position).getWifiName());
        codeAgent.setText("CodePlanning:  "+listTrace.traceModelArrayList.get(position).getCodeAgent());
    }
}
