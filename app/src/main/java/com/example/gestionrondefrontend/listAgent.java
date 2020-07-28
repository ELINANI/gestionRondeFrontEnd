package com.example.gestionrondefrontend;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class listAgent extends AppCompatActivity {
    public static  final  String URL = "http://192.168.1.12:9094/api/Agent";
    public static final String DEBUGTAG = "listAgentActivity";
    ListView listView  ;
    AgentAdapter agentAdapter ;
    AgentModel   agentModel;
    public static ArrayList<AgentModel> agentModelArrayList = new ArrayList<AgentModel>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_agent);
        listView = findViewById(R.id.myListViewAgent);
        agentAdapter = new AgentAdapter(this,agentModelArrayList);

        listView.setAdapter(agentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog =  new ProgressDialog(view.getContext());
                CharSequence[] dialogItem = {"View Data","Delete Data"};
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0 :startActivity(new Intent(getApplicationContext(),agentInfo.class)
                                    .putExtra("position" ,position));
                                break;
                            case 1 : deleteData(agentModelArrayList.get(position).getCodeAgent());
                                break;
                        }

                    }
                });
                builder.create().show();

            }
        });
        data();
    }
    private void deleteData(int code ){
        StringRequest request = new StringRequest(Request.Method.DELETE, URL+"/"+code, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject js = new JSONObject(response);
                    if(js.has("codeAgent")){
                        Toast.makeText(getApplicationContext(),"Agent deleted",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),listAgent.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Agent not deleted",Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }
 public void data(){
     StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {
             agentModelArrayList.clear();
             try{
                 JSONArray jsonArray = new JSONArray(response);
                 for( int i = 0; i<= jsonArray.length();i++){
                     JSONObject js = jsonArray.getJSONObject(i);
                     int codeAgent = js.getInt("codeAgent");
                     String nomAgent = js.getString("nomAgent");
                     String prenomAgent = js.getString("prenomAgent");
                     String pwdAgent = js.getString("pawdAgent");
                    // int codePlanning = js.getInt("codePlanning");
                     agentModel = new AgentModel(codeAgent,nomAgent,prenomAgent,pwdAgent,1);
                     agentModelArrayList.add(agentModel);
                     agentAdapter.notifyDataSetChanged();
                 }
             }catch(JSONException e){
                 e.printStackTrace();
             }

         }
     }, new Response.ErrorListener() {
         @Override
         public void onErrorResponse(VolleyError error) {
             Log.d(DEBUGTAG,error.getMessage());
         }
     });
     RequestQueue requestQueue = Volley.newRequestQueue(this);
     requestQueue.add(request);


 }
    public void btn_add_activity(View view) {
        Intent I = new Intent(getApplicationContext(),AgentActivity.class);
        startActivity(I);
    }

    public void Back(View view) {
        Intent i = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(i);
    }
}
