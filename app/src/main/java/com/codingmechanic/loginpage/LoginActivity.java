package com.codingmechanic.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText passWord;
    private Button login;
    private CheckBox remember;
    private TextView register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText) findViewById(R.id.txtUserNameLogin);
        passWord = (EditText) findViewById(R.id.txtPasswordLogin);
        login = (Button) findViewById(R.id.btnLogin);
        remember = (CheckBox) findViewById(R.id.chkRemember);
        register = (TextView) findViewById(R.id.txtRegisterNow);

        Utility.customView(login, ContextCompat.getColor(this, R.color.register_page));
//        Utility.customView(login, Color.parseColor(getString(R.string.login_button_color_string)));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != userName.getText() && null != passWord.getText()) {
                    if (Utility.validatePassword(passWord.getText().toString())) {

                        //TODO: write method to check with database and validate username and password

                        //It is left as true now but, later will be replaced with check from database
                        if (true) {
                            Intent intent = new Intent(view.getContext(), MainActivity.class);
                            startActivity(intent);
                        } else {

                        }
                    } else {
                        Toast.makeText(view.getContext(),
                                "Password must contain 8 char (uppercase, lowercase, special character and a number",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(view.getContext(),
                            "Must Enter Username or password",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
