package com.codingmechanic.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {

    private final String LOG_TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final String[] spinnerText = new String[1];


        final EditText userName = (EditText) findViewById(R.id.txtUserNameRegister);
        final EditText password = (EditText) findViewById(R.id.txtPasswordRegister);
        final EditText email = (EditText) findViewById(R.id.txtEmailRegister);
        final TextView choose = (TextView) findViewById(R.id.txtPlaceHolder);
        final Button register = (Button) findViewById(R.id.btnRegister);
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerType);
        final TextView signup = (TextView) findViewById(R.id.txtSigninNow);

        Utility.customView(register, ContextCompat.getColor(this, R.color.color_button));


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    choose.setAlpha(1);
                } else {
                    choose.setAlpha(0);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameuser = userName.getText().toString().toLowerCase().trim();
                String emailAddr = email.getText().toString().toLowerCase().trim();
                String pass = password.getText().toString().trim();
                String userType = "";
                if (spinner.getSelectedItemId() != 0) {
                    userType = spinner.getSelectedItem().toString().trim();
                }

                String dataType = "register";
                if (!nameuser.isEmpty() && !emailAddr.isEmpty() && !pass.isEmpty() && !userType.isEmpty()) {
                    BackgroundWorker bg = new BackgroundWorker(getApplicationContext());
                    try {
                        String jsonStr = bg.execute(dataType, nameuser, emailAddr, pass, userType).get();
                        Log.d(LOG_TAG, jsonStr);
                        JSONObject jObj = new JSONObject(jsonStr);
                        boolean error = jObj.getBoolean("error");

                        // Check for error node in json
                        if (!error) {
                            // user successfully logged in
                            // Create login session

                            // Now store the user in SQLite


                            JSONObject user = jObj.getJSONObject("user");
                            String uid = user.getString("id");
                            String name = user.getString("username");
                            String email = user.getString("email");
                            String type = user.getString("type");

                            // Inserting row in users table
                            Log.d(LOG_TAG, "user : " + name + "\tEmail : " + email + "\tType : " + type);
                            // Launch main activity
                            Intent intent = new Intent(RegisterActivity.this,
                                    LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Error in login. Get the error message
                            String errorMsg = jObj.getString("error-reason");
                            Toast.makeText(getApplicationContext(),
                                    errorMsg, Toast.LENGTH_LONG).show();
                        }
                    } catch (InterruptedException | ExecutionException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
