package com.example.mylcm;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {

    RelativeLayout rellay1;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
//teste3
//testado e aprovado
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);

        handler.postDelayed(runnable, 3500);
    }

    protected void entrar (View v) {
        startActivity(new Intent(Login.this, Menu.class));
    }
}
