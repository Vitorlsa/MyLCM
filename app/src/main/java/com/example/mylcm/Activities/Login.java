package com.example.mylcm.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.R;
import com.example.mylcm.Retrofit.LoginDTO;
import com.example.mylcm.Retrofit.RetrofitService;
import com.example.mylcm.Retrofit.ServerResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    //Connect connect;
    public static String userName, userEmail;
    EditText userID, userPWD;
    Button btnLogin;
    //ServerResponse resposta = new ServerResponse();
    ProgressDialog progress;
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

        SharedPreferences prefs = getSharedPreferences("log", 0);
        boolean alreadyLogged = prefs.getBoolean("isLogged", false);

        if(alreadyLogged){
            startActivity(new Intent(Login.this, NavDrawerMenu.class));
        }
        else{}

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        handler.postDelayed(runnable, 3500);

        //connect = new Connect();
        userID = (EditText) findViewById(R.id.Username);
        userPWD = (EditText) findViewById(R.id.Password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress = new ProgressDialog(Login.this);
                progress.setTitle("Logging In");

                String username = userID.getText().toString();
                String password = userPWD.getText().toString();

                //Chama o retrofit
                retrofitLogon(username, password);
            }
        });
    }

    /**
     * Método que chama Retrofit e faz a requisição para
     * Consumir Json no Android
     */
    public void retrofitLogon(String login, String senha){

        RetrofitService service = Connect.createService(RetrofitService.class);
        
        final LoginDTO userLog = new LoginDTO(login, senha);
        
        Call<ServerResponse> call = service.getCredentials(userLog);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                if (response.isSuccessful()) {

                    ServerResponse serverResponse = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (serverResponse != null) {

                        if(serverResponse.getID() != 0) {

                            ServerResponse serverResponseData = response.body();
                            userName = serverResponseData.getNome();
                            userEmail = serverResponseData.getEmail();

                            //Salva o nome do usuário em um shared preferences
                            SharedPreferences nome = getSharedPreferences("name", 0);
                            SharedPreferences.Editor nome_editor = nome.edit();
                            nome_editor.putString("name", userName);
                            nome_editor.commit();


                            //Salva o email do usuário em um shared preferences
                            SharedPreferences email = getSharedPreferences("email", 0);
                            SharedPreferences.Editor email_editor = email.edit();
                            email_editor.putString("email", userEmail);
                            email_editor.commit();

                            //Salva o estado de logado do usuário
                            SharedPreferences prefs = getSharedPreferences("log", 0);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("isLogged", true);
                            editor.commit();

                            progress.dismiss();
                            canLogin();

                        } else{

                            Toast.makeText(getApplicationContext(),"Insira Usuário e Senha válidos", Toast.LENGTH_SHORT).show();
                        }

                    } else {

                        Toast.makeText(getApplicationContext(),"Ops, você não é um Prestador de Serviço", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(getApplicationContext(),"Resposta não foi um sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                }

                progress.dismiss();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void canLogin(){
        startActivity(new Intent(Login.this, NavDrawerMenu.class));
        finish();
    }
}
