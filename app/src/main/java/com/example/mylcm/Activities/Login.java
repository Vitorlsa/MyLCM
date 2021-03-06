package com.example.mylcm.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Login.LoginDTO;
import com.example.mylcm.Retrofit.RetrofitService;
import com.example.mylcm.Retrofit.Login.ServerResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    //Connect connect;
    public static String userName, userEmail, userProfPic;
    TextView cadastrar, esqueci;
    public static int pid;
    public double ratingUser;
    EditText userID, userPWD;
    Button btnLogin;
    //ServerResponse resposta = new ServerResponse();
    ProgressDialog progress;
    ProgressBar prog;
    RelativeLayout rellay1;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        public void run() {
            rellay1.setVisibility(View.VISIBLE);

        }
    };
    Runnable runn = new Runnable(){
        public void run(){
            rellay1.setVisibility(View.GONE);
            prog.setVisibility(View.VISIBLE);
        }
    };
    Runnable derunn = new Runnable(){
        public void run(){
            rellay1.setVisibility(View.VISIBLE);
            prog.setVisibility(View.GONE);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences prefs = getSharedPreferences("log", 0);
        boolean alreadyLogged = prefs.getBoolean("isLogged", false);

        if(alreadyLogged){
            canLogin();
        }
        else{}
        cadastrar = (TextView) findViewById(R.id.txtCadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://35.222.85.4/cadastro";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        esqueci = (TextView) findViewById(R.id.txtEsqueciSenha);
        esqueci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://35.222.85.4/login";

                Intent i =  new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        handler.postDelayed(runnable, 3500);

        prog = (ProgressBar) findViewById(R.id.progressBar);
        prog.setVisibility(View.GONE);

        //connect = new Connect();
        userID = (EditText) findViewById(R.id.Username);
        userPWD = (EditText) findViewById(R.id.Password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogin.setEnabled(false);

                handler.postDelayed(runn, 350);

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
                            userProfPic = serverResponseData.getImagem();
                            ratingUser = serverResponseData.getRatingUsuario();
                            pid = serverResponseData.getID();

                            //Salva o ID do usuário em um shared preferences
                            SharedPreferences presID = getSharedPreferences("PID", 0);
                            SharedPreferences.Editor pid_editor = presID.edit();
                            pid_editor.putInt("PID", pid);
                            pid_editor.commit();

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

                            //Salva a imagem do usuário em um shared preferences
                            SharedPreferences profPic = getSharedPreferences("profPic", 0);
                            SharedPreferences.Editor profPic_editor = profPic.edit();
                            profPic_editor.putString("profPic", userProfPic);
                            profPic_editor.commit();

                            //Salva o estado de logado do usuário
                            SharedPreferences prefs = getSharedPreferences("log", 0);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("isLogged", true);
                            editor.commit();

                            //Salva o rating do usuário em um shared preferences
                            SharedPreferences ratingUsr = getSharedPreferences("rating", 0);
                            SharedPreferences.Editor rating_editor = ratingUsr.edit();
                            rating_editor.putString("rating", String.valueOf(ratingUser));
                            rating_editor.commit();

                            //progress.dismiss();
                            canLogin();

                        } else{

                            Toast.makeText(getApplicationContext(),"Insira Usuário e Senha válidos", Toast.LENGTH_SHORT).show();
                            handler.postDelayed(derunn, 350);
                            btnLogin.setEnabled(true);
                        }

                    } else {

                        Toast.makeText(getApplicationContext(),"Ops, você não é um Prestador de Serviço", Toast.LENGTH_SHORT).show();
                        handler.postDelayed(derunn, 350);
                        btnLogin.setEnabled(true);
                    }

                } else {

                    Toast.makeText(getApplicationContext(),"Resposta não foi um sucesso", Toast.LENGTH_SHORT).show();
                    // segura os erros de requisição
                    ResponseBody errorBody = response.errorBody();
                    handler.postDelayed(derunn, 350);
                    btnLogin.setEnabled(true);
                }

                //progress.dismiss();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
                handler.postDelayed(derunn, 350);
                btnLogin.setEnabled(true);
            }
        });

    }

    public void canLogin(){
        startActivity(new Intent(Login.this, NavDrawerMenu.class));
        finish();
    }
}
