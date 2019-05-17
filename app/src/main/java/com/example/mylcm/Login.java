package com.example.mylcm;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {

    Connect connect;
    EditText edtuserid, edtuserpwd;
    Button btnLogin;

    RelativeLayout rellay1;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        public void run() {
            rellay1.setVisibility(View.VISIBLE);

        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        handler.postDelayed(runnable, 3500);

        connect = new Connect();
        edtuserid = (EditText) findViewById(R.id.Username);
        edtuserpwd = (EditText) findViewById(R.id.Password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(edtuserid.getText().toString(), edtuserpwd.getText().toString());
            }
        });

    }

    protected void login (String userID, String userPWD) {
        if((userID.equals("Admin")) && (userPWD.equals("Admin"))) {
            startActivity(new Intent(Login.this, Menu.class));
        }else {
            Toast.makeText(getApplicationContext(), "Usuário ou Senha inválidos", Toast.LENGTH_SHORT).show();
        }
    }
}
