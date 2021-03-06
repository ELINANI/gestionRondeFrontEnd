package com.example.gestionrondefrontend;

import androidx.appcompat.app.AppCompatActivity;

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
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BLEActivity extends AppCompatActivity {
    private static final String URL = "http://192.168.1.12:9094/api/BLE";
    public static final String DEBUGTAG = "BLEActivity";
    EditText idBLE,heure;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_l_e);
        idBLE = findViewById(R.id.idBLE);
        heure= findViewById(R.id.heure);
        btn = findViewById(R.id.btnAddBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }
    public void add(){
        String idBLET = idBLE.getText().toString().trim();
        String heureT = heure.getText().toString().trim();

        JSONObject jo = new JSONObject();
        try {
            jo.put("idBLE" , idBLET);
            jo.put("heurAjout" ,heureT);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, URL, jo,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(),"success " ,Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),listBLE.class);
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
