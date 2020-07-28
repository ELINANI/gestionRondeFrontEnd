package com.example.gestionrondefrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

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

public class listBLE extends AppCompatActivity {
    public static  final  String URL = "http://192.168.1.12:9094/api/BLE";
    public static final String DEBUGTAG = "listBLEActivity";
    ListView listView  ;
    BLEAdapter BLEAdapter ;
    BLEModel   BLEModel;
    public static ArrayList<BLEModel> BLEModelArrayList = new ArrayList<BLEModel>() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_b_l_e);
        listView = findViewById(R.id.myListViewBLE);
        BLEAdapter = new BLEAdapter(this,BLEModelArrayList);
        listView.setAdapter(BLEAdapter);
        data();
    }
   public void data(){
       StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               BLEModelArrayList.clear();
               try{
                   JSONArray jsonArray = new JSONArray(response);
                   for( int i = 0; i<= jsonArray.length();i++){
                       JSONObject js = jsonArray.getJSONObject(i);
                       int idBLe = js.getInt("idBLE");
                       String heurAjout = js.getString("heurAjout");

                       BLEModel = new BLEModel(idBLe,heurAjout);
                       BLEModelArrayList.add(BLEModel);
                       BLEAdapter.notifyDataSetChanged();
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
        Intent i = new Intent(getApplicationContext(),BLEActivity.class);
        startActivity(i);
    }

    public void Back(View view) {
        Intent i = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(i);
    }
}
