package com.codingmechanic.loginpage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class getClubs extends AppCompatActivity {

    final String LOG_TAG = getClass().getSimpleName();
    ArrayList<Club> mArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_clubs);

        Button submit = (Button) findViewById(R.id.btnSumbitUser);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundWorker bg = new BackgroundWorker(getApplicationContext());
                try {
                    String jsonStr = bg.execute("get_club", "2").get();
                    Log.d(LOG_TAG, jsonStr);
                    JSONObject jObj = new JSONObject(jsonStr);
                    boolean error = jObj.getBoolean("error");

                    // Check for error node in json
                    if (!error) {
                        // user successfully logged in
                        // Create login session

                        // Now store the user in SQLite
                        JSONArray array = new JSONArray(jsonStr);

                        for (int i = 0; i < array.length(); i++) {

                            JSONObject object = array.getJSONObject(i);

                            Club c = new Club(object.getString("club_name"),
                                    object.getString("club_type"),
                                    object.getString("club_email"),
                                    object.getString("club_email"));

                            mArrayList.add(c);
                        }


                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error-reason");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (InterruptedException | ExecutionException | JSONException e) {
                    e.printStackTrace();
                }


                ClubAdapter arrayAdapter = new ClubAdapter(getClubs.this, mArrayList);

                ListView listView = (ListView) findViewById(R.id.list_item);
                listView.setAdapter(arrayAdapter);
            }
        });

    }






    /*
            BackgroundWorker bg = new BackgroundWorker(getApplicationContext());
        try {
            String jsonStr = bg.execute("get_clubs").get();
            Log.d(LOG_TAG, jsonStr);
            JSONObject jObj = new JSONObject(jsonStr);
            boolean error = jObj.getBoolean("error");

            // Check for error node in json
            if (!error) {
                // user successfully logged in
                // Create login session

                // Now store the user in SQLite
                JSONArray array = new JSONArray(jsonStr);

                for (int i = 0; i < array.length(); i++) {

                    JSONObject object = array.getJSONObject(i);

                    Club c = new Club(object.getString("club_name"),
                            object.getString("club_type"),
                            object.getString("club_email"),
                            object.getString("club_email"));

                    mArrayList.add(c);
                }


            } else {
                // Error in login. Get the error message
                String errorMsg = jObj.getString("error-reason");
                Toast.makeText(getApplicationContext(),
                        errorMsg, Toast.LENGTH_LONG).show();
            }
        } catch (InterruptedException | ExecutionException | JSONException e) {
            e.printStackTrace();
        }


        ClubAdapter arrayAdapter = new ClubAdapter(this, mArrayList);

        ListView listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(arrayAdapter);
     */


}
