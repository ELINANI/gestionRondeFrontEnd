package com.example.gestionrondefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Planning extends AppCompatActivity {
    private static final String URL = "http://192.168.1.12:9094/api/planning";
    public static final String DEBUGTAG = "PlanningActivity";
    EditText codePlanning ;
    EditText desc ;
    EditText codeAdmin;
     Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);
        codePlanning = findViewById(R.id.codePlanning);
        desc = findViewById(R.id.desc);
        codeAdmin = findViewById(R.id.codeAdmin);
        codeAdmin.setText(LoginActivity.login);
        btn = findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlanning();
            }
        });
    }

    public void addPlanning() {
        final ProgressDialog  progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading... ");
        String codePlanningT = codePlanning.getText().toString().trim() ;
        String descT = desc.getText().toString().trim();
        String codeAdminT = codeAdmin.getText().toString().trim();
        JSONObject jo = new JSONObject();
        try {
            jo.put("codePlanning" , codePlanningT);
            jo.put("codeAdmin" , codeAdminT);
            jo.put("desc" ,descT);

        } catch (JSONException e) {
            e.printStackTrace();
        }
       // progressDialog.show();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, URL, jo,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(),"success " ,Toast.LENGTH_SHORT).show();
                        //progressDialog.dismiss();
                       Intent i = new Intent(getApplicationContext(),listPlanning.class);
                        startActivity(i);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(DEBUGTAG, "Error\t " + error.getMessage());
            }
        }) {


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };


        MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(jsonObjReq);

    }
}
