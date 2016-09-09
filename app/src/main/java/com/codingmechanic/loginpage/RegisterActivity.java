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

        Utility.customView(register, ContextCompat.getColor(this, R.color.login_page));


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
                if (userName.getText().toString() == "" ||
                        password.getText().toString() == "" ||
                        email.getText().toString() == "" ||
                        spinner.getSelectedItemId() == 0) {
                    Toast.makeText(view.getContext(), "All fields must be filled", Toast.LENGTH_LONG).show();
                } else {
                    Log.d(LOG_TAG, "Username - " + userName.getText().toString());
                    Log.d(LOG_TAG, "Email - " + email.getText().toString());
                    Log.d(LOG_TAG, "Password - " + password.getText().toString());
                    Log.d(LOG_TAG, "Type - " + spinner.getSelectedItem().toString());

                }
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}
