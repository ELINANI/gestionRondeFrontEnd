package com.example.gestionrondefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public static String login;
    public static String pwd ;
    EditText loginEditText;
    EditText pwdEditText ;
    Button btn ;
    public static final String DEBUGTAG = "LoginActivity";

    public static final  String URL_Base ="http://192.168.1.12:9094/api/login/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            loginEditText = findViewById(R.id.userLogin);
             pwdEditText = findViewById(R.id.userPwd);
        btn = findViewById(R.id.Btnlogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 login();
            }
        });
    }
    public void login() {
        String loginT = loginEditText.getText().toString().trim();
        String pwdT = pwdEditText.getText().toString().trim();
        if(loginT.isEmpty()  || pwdT.isEmpty() || loginT == null || pwdT == null){
            Toast.makeText(getApplicationContext(),"Edit Text Is Empty",Toast.LENGTH_LONG).show();
            return ;
        }
        else{
            StringRequest req = new StringRequest(Request.Method.GET,URL_Base+loginEditText.getText()+'/'+pwdEditText.getText(), new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONObject json = null;
                    try {
                        json = new JSONObject(response);
                        if(json.has("error"))
                            Toast.makeText(getApplicationContext(),json.getString("error"),Toast.LENGTH_LONG).show();
                        else
                        {
                            Intent i = new Intent(getApplicationContext(),MenuActivity.class);
                            startActivity(i);
                        }
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d(DEBUGTAG,response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(DEBUGTAG,error.getMessage());
                }
            });
            MySingleton.getInstance(this.getApplicationContext()).addToRequestQueue(req);
        }


    }

    public void back(View view) {
        Intent i = new Intent(getApplicationContext(),welcomeActivity.class);
        startActivity(i);
    }
}
