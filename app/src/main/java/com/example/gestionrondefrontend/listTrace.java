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

public class listTrace extends AppCompatActivity {
    public static  final  String URL = "http://192.168.1.12:9094/api/trace";
    public static final String DEBUGTAG = "listTRaceActivity";
    ListView listView  ;
    TraceAdapter traceAdapter ;
    TraceModel traceModel;
    public static ArrayList<TraceModel> traceModelArrayList = new ArrayList<TraceModel>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_trace);
        listView = findViewById(R.id.myListViewTace);
        traceAdapter = new TraceAdapter(this,traceModelArrayList);
        listView.setAdapter(traceAdapter);
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
                            case 0 :startActivity(new Intent(getApplicationContext(),TraceDetails.class)
                                    .putExtra("position" ,position));
                                break;
                            case 1: deleteData(traceModelArrayList.get(position).getCodeTrace());
                                  break;

                        }

                    }
                });
                builder.create().show();

            }
        });
        data();
    }
    private void deleteData(String code ){
        StringRequest request = new StringRequest(Request.Method.DELETE, URL+"/"+code, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject js = new JSONObject(response);
                    if(js.has("codeTrace")){
                        Toast.makeText(getApplicationContext(),"Trace deleted",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),listTrace.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Trace not deleted",Toast.LENGTH_SHORT).show();
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
                traceModelArrayList.clear();
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    for( int i = 0; i<= jsonArray.length();i++){
                        JSONObject js = jsonArray.getJSONObject(i);
                        String codeTrace = js.getString("codeTrace");
                        String wifiName = js.getString("wifiName");
                        String codeAgent = js.getString("codeAgent");
                        traceModel = new TraceModel(codeTrace,wifiName,codeAgent);
                        traceModelArrayList.add(traceModel);
                        traceAdapter.notifyDataSetChanged();
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

    public void Back(View view) {
        Intent i = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(i);
    }
}
