package dk.now.just.bogym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dk.now.just.bogym.Adapter.RoutineAdapter;
import dk.now.just.bogym.Model.RoutineObjectItem;

public class SelectedRoutine extends AppCompatActivity {

    TextView muscleGroup;
    String set;
    String rep;
    String exercise;

    private ListView listView;
    private RoutineAdapter mAdapter;
    List<RoutineObjectItem> itemsLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_routine);


        String url = "https://fitnessapp-77bda.firebaseio.com/routines/" + RoutinesDetails.username + "/" + RoutinesDetails.userRoutines + "/Muscle" + ".json";


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                doOnSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("I am the error in users" + error);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(SelectedRoutine.this);
        requestQueue.add(request);


    }

    public void doOnSuccess(String s) {
        listView = (ListView) findViewById(R.id.routine_list);
        ArrayList<RoutineObjectItem> routineList = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(s);
            Iterator i = obj.keys();
            String key = "";

            while (i.hasNext()) {
                key = i.next().toString();

                switch (key) {
                    case "Back":
                        String muscleBack = key;
                        JSONObject back = obj.getJSONObject("Back");
                        Iterator b = back.keys();

                        while (b.hasNext()) {
                            String ex = b.next().toString();

                            switch (ex) {

                                case "WeekDay":
                                    String chestDay = back.getString(ex);
                                    routineList.add(new RoutineObjectItem(chestDay, muscleBack, exercise, set, rep));
                                    break;


                                default:
                                    exercise = ex;
                                    JSONObject chestEx = back.getJSONObject(ex);
                                    Iterator z = chestEx.keys();
                                    while (z.hasNext()) {
                                        String key1 = z.next().toString();
                                        switch (key1) {
                                            case "Set":
                                                set = chestEx.getString(key1);

                                                break;
                                            case "rep":
                                                rep = chestEx.getString(key1);
                                                break;

                                        }
                                    }

                            }


                        }
                    case "Chest":

                        JSONObject chest = obj.getJSONObject("Chest");
                        Iterator y = chest.keys();
                        String muscleChest = "Chest";

                        while (y.hasNext()) {
                            String ex = y.next().toString();

                            switch (ex) {

                                case "WeekDay":
                                    String chestDay = chest.getString(ex);

                                    routineList.add(new RoutineObjectItem(chestDay, muscleChest, exercise, set, rep));


                                default:
                                    exercise = ex;
                                    JSONObject chestEx = chest.getJSONObject(ex);
                                    Iterator z = chestEx.keys();
                                    while (z.hasNext()) {
                                        String key1 = z.next().toString();
                                        switch (key1) {
                                            case "Set":
                                                set = chestEx.getString(key1);

                                                break;
                                            case "rep":
                                                rep = chestEx.getString(key1);

                                        }
                                    }

                            }


                        }




                }

//                if(key.equals("Chest"))
//                {
//
//
//                }
//                if(key.equals("WeekOfDay"))
//                {
//
//                }
//                if(key.equals("Muscle")){
//                  JSONObject values = obj.getJSONObject("Exercise");
//                  Iterator y = values.keys();
//
//                  while (y.hasNext()){
//                      System.out.print(values.toString());
//                  }
//
//
//                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mAdapter = new RoutineAdapter(this, routineList);
        listView.setAdapter(mAdapter);


    }


}
