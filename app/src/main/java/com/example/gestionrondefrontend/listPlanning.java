package com.example.gestionrondefrontend;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

public class listPlanning  extends AppCompatActivity {
    public static  final  String URL = "http://192.168.1.12:9094/api/planning";
    public static final String DEBUGTAG = "listPlanningActivity";
    ListView  listView  ;
    planningAdapter planningAdapter ;
    PlanningModel planningModel;
    public static ArrayList<PlanningModel> planningModelArrayList = new ArrayList<PlanningModel>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_planning);
        listView = findViewById(R.id.myListView);
        planningAdapter = new planningAdapter(this,planningModelArrayList);
        listView.setAdapter(planningAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog =  new ProgressDialog(view.getContext());
                CharSequence[] dialogItem = {"View Data"};
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                          switch (i){
                              case 0 :startActivity(new Intent(getApplicationContext(),planningDetails.class)
                                       .putExtra("position" ,position));
                                      break;

                          }

                    }
                });
                builder.create().show();

            }
        });
     data();
    }

 public  void data(){
     StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
         @Override
         public void onResponse(String response) {
             planningModelArrayList.clear();
             try{
                 JSONArray jsonArray = new JSONArray(response);
                 for( int i = 0; i<= jsonArray.length();i++){
                        JSONObject js = jsonArray.getJSONObject(i);
                        int codePlanning = js.getInt("codePlanning");
                        String desc = js.getString("desc");
                        int codeAdmin = js.getInt("codeAdmin");
                        planningModel = new PlanningModel(codePlanning,desc,codeAdmin);
                        planningModelArrayList.add(planningModel);
                        planningAdapter.notifyDataSetChanged();
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
        Intent i = new Intent(getApplicationContext(),Planning.class);
        startActivity(i);
    }

    public void Back(View view) {
        Intent i = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(i);
    }
}
