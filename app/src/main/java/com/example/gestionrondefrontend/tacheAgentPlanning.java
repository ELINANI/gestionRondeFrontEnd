package com.example.gestionrondefrontend;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class tacheAgentPlanning extends AppCompatActivity {
    BD bd = new BD(this);
    EditText codeTrace ,wifiName,codeAgent;
    private static final String URL = "http://192.168.1.12:9094/api/trace";
    public static final String DEBUGTAG = "traceActivity";
    Random r ;
    Button btn ;
    WifiManager wifiManager ;
    WifiReceiver wifiReceiver;
   // ListView wifiList ;
    List<ScanResult> myWifiList ;
    @SuppressLint("WifiManagerLeak")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tache_agent_planning);
        codeTrace = findViewById(R.id.codeTraceTache);
        wifiName =findViewById(R.id.wifiNameTraceTache);
        codeAgent = findViewById(R.id.codeAgentTraceTache);
        btn = findViewById(R.id.btnAddTache);
         r= new Random();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()){
                      addDataOnLine();
                }else
                {
                    addDataOffLine();
                }


            }
        });
        wifiManager =(WifiManager)getSystemService(Context.WIFI_SERVICE);
        wifiReceiver = new WifiReceiver();
        registerReceiver(wifiReceiver,new IntentFilter(wifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},0);

        }
        else {
            scanWifiList();
        }
        if(haveNetwork()){
            synchroServer();
            Toast.makeText(getApplicationContext(),"The Synchronisation is done",Toast.LENGTH_SHORT).show();
        }
    }
    private void scanWifiList(){
        wifiManager.startScan();
        myWifiList = wifiManager.getScanResults();
        codeTrace.setText(System.currentTimeMillis()+"");
        wifiName.setText(myWifiList.get(0).SSID);
        codeAgent.setText(loginAgent.login);
    }
    class  WifiReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
   public void  addDataOnLine(){
       final ProgressDialog progressDialog = new ProgressDialog(this);
       progressDialog.setMessage("Loading... ");
       String jsCodeTrace = codeTrace.getText().toString().trim();
       String jsWifiName = wifiName.getText().toString().trim();
       String jsCodeAgent = codeAgent.getText().toString().trim();
       JSONObject jo = new JSONObject();
       try {
           jo.put("codeTrace" , jsCodeTrace);
           jo.put("codeAgent" ,jsCodeAgent);
           jo.put("wifiName" , jsWifiName);

       } catch (JSONException e) {
           e.printStackTrace();
       }
       // progressDialog.show();
       JsonObjectRequest jsonObjReq = new JsonObjectRequest(
               Request.Method.POST, URL, jo,
               new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {
                       Toast.makeText(getApplicationContext(),"success  onLine " ,Toast.LENGTH_SHORT).show();
                       //progressDialog.dismiss();
                       Intent i = new Intent(getApplicationContext(),TacheAgentActivity.class);
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
    public void  addDataOffLine(){
       Log.d(DEBUGTAG,"Off line work  good bro");
      String stCodeTrace = codeTrace.getText().toString().trim();
      String stWifiName = wifiName.getText().toString().trim();
      String stCodeAgent = codeAgent.getText().toString().trim();
      if(bd.insertData(stCodeTrace,stWifiName,stCodeAgent)){
          Toast.makeText(getApplicationContext(),"success offLine " ,Toast.LENGTH_SHORT).show();
          Intent i = new Intent(getApplicationContext(),TacheAgentActivity.class);
          startActivity(i);
      }else{
          Toast.makeText(getApplicationContext(),"Error " ,Toast.LENGTH_SHORT).show();

      }
    }
    public Boolean haveNetwork(){
        boolean haveWifi = false ;
        boolean haveMobileData = false ;
        ConnectivityManager connectivityManager =(ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
         NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
         for(NetworkInfo info :networkInfos){
             if(info.getTypeName().equalsIgnoreCase("WIFI"))
                 if(info.isConnected())
                 haveWifi =true;
             if(info.getTypeName().equalsIgnoreCase("MOBILE"))
                 if(info.isConnected())
                 haveMobileData =true;
         }
         return haveMobileData||haveWifi;

    }
     public void synchroServer(){
         final ProgressDialog progressDialog = new ProgressDialog(this);
         progressDialog.setMessage("Synch... ");

        JSONObject js = new JSONObject();
        ArrayList<TraceModel> tm =  bd.getAllTrace();
        if(tm.isEmpty()){
            Toast.makeText(getApplicationContext(),"nothing to synch with the server",Toast.LENGTH_SHORT).show();
        }
        for( int i = 0 ; i <tm.size();i++){
            Log.e("tacheagentPlanning", tm.get(i).getCodeTrace() );
            try {
                js.put("codeTrace" , tm.get(i).getCodeTrace());

                js.put("codeAgent" ,tm.get(i).getCodeAgent());
                js.put("wifiName" , tm.get(i).getWifiName());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressDialog.show();
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                    Request.Method.POST, URL, js,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


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
         progressDialog.dismiss();

        Toast.makeText(getApplicationContext(),"success to upload to the server",Toast.LENGTH_SHORT).show();
         bd.cleanBd();
     }

    public  boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

}
