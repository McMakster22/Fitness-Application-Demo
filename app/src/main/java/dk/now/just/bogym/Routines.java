package dk.now.just.bogym;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;

public class Routines extends MainActivity {

    ListView routinesList;
    TextView noRotinesText;
    ArrayList<String> al = new ArrayList<>();
    int totalRoutines = 0;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        routinesList = findViewById(R.id.routinesList);
        noRotinesText = findViewById(R.id.noRoutines);
        pd = new ProgressDialog(Routines.this);
        pd.setMessage("Loading...");
        pd.show();
        String url = "https://fitnessapp-77bda.firebaseio.com/routines/" + RoutinesDetails.username + ".json";
        StringRequest request = new StringRequest(Request.Method.GET, url,new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("I am the error in users" + volleyError);
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(Routines.this);
        rQueue.add(request);
        routinesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RoutinesDetails.userRoutines = al.get(position);
                startActivity(new Intent(Routines.this, SelectedRoutine.class));
            }
        });
}
   public void doOnSuccess(String s){
        try {
            JSONObject obj = new JSONObject(s);
            Iterator i = obj.keys();
            String key = "";
            while (i.hasNext()){
                key = i.next().toString();
                al.add(key);
                totalRoutines++;
            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }

       if(totalRoutines <1){
           noRotinesText.setVisibility(View.VISIBLE);
           routinesList.setVisibility(View.GONE);
       }
       else{
           noRotinesText.setVisibility(View.GONE);
           routinesList.setVisibility(View.VISIBLE);
           routinesList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
       }
       pd.dismiss();
   }
    }