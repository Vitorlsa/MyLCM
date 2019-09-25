package com.example.mylcm.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylcm.Fragments.FragmentMenu;
import com.example.mylcm.Fragments.FragmentProfile;
import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.Profile.CityResponse;
import com.example.mylcm.Retrofit.Profile.ProfileDTO;
import com.example.mylcm.Retrofit.Profile.ProfileResponse;
import com.example.mylcm.Retrofit.RetrofitService;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavDrawerMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentMenu.OnFragmentInteractionListener {

    Fragment fragment = null;
    public static String login, password, date, cpf, tel, state, nhood, cep, street, number, complement, comment, curriculum, imagem, cidade;
    public static int pid, cityId, id, sex;
    ArrayList<Integer> competencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //FloatingActionButton fab = findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "" + nomeUser, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //Seleciona no drawer o item "Menu"
        navigationView.setCheckedItem(R.id.nav_home);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new FragmentMenu();
        fragmentTransaction.replace(R.id.flMenu, fragment).commit();


        //--Pegando o nome, email, foto de perfil e ID do usuário com sharedPrefs.
        SharedPreferences  presID = getSharedPreferences("PID", 0);
        pid = presID.getInt("PID", -1);

        SharedPreferences  names = getSharedPreferences("name", 0);
        String nameUser = names.getString("name", "");

        SharedPreferences emails = getSharedPreferences("email", 0);
        String emailUser = emails.getString("email", "");

        SharedPreferences profPics = getSharedPreferences("profPic", 0);
        String profPict = profPics.getString("profPic", "");
        //--Finaliza os sharedPrefs.

        //Transformo a string de base64 em bitmap.
        String base64profPict = profPict.split(",")[1];
        byte[] decodedString = Base64.decode(base64profPict, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        //Me faz "enxergar" a header
        View header = navigationView.getHeaderView(0);

        CircleImageView profPic = (CircleImageView) header.findViewById(R.id.profPic);
        TextView nome = (TextView) header.findViewById(R.id.tvName);
        TextView email = (TextView) header.findViewById(R.id.tvEmail);
        //Insere os dados na header
        profPic.setImageBitmap(decodedByte);
        nome.setText(nameUser);
        email.setText(emailUser);
    }

    public Boolean exit = false;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() != 0){
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            setTitle("Menu");
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setCheckedItem(R.id.nav_home);
        } else {
            if(getSupportFragmentManager().getBackStackEntryCount() == 0) {
                moveTaskToBack(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer_menu, menu);
        return true;
    }

    //@Override
    /*public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.nav_settings) {
          //  return true;
        //}

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_home){

            setTitle("Menu");
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        } else if (id == R.id.nav_profile) {

            retrofitProfile(pid);

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            SharedPreferences prefs = getSharedPreferences("log", 0);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isLogged", false);
            editor.commit();
            finish();
            startActivity(new Intent(NavDrawerMenu.this, Login.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void retrofitProfile(int pid){

        RetrofitService service = Connect.createService(RetrofitService.class);

        final ProfileDTO presProf = new ProfileDTO(pid);

        Call<ProfileResponse> call = service.getData(presProf);

        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

                if (response.isSuccessful()) {

                    ProfileResponse profileResponse = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (profileResponse != null) {

                        if(profileResponse.getId() != 0) {

                            ProfileResponse profileResponseData = response.body();
                            id = profileResponseData.getId();
                            login = profileResponseData.getLogin();
                            password = profileResponseData.getPassword();
                            sex = profileResponseData.getSex();
                            state = profileResponseData.getState();
                            date = profileResponseData.getDate();
                            cpf = profileResponseData.getCpf();
                            tel = profileResponseData.getTel();
                            cityId = profileResponseData.getCity();
                            nhood = profileResponseData.getNhood();
                            cep = profileResponseData.getCep();
                            street = profileResponseData.getStreet();
                            number = profileResponseData.getNumber();
                            complement = profileResponseData.getComplement();
                            comment = profileResponseData.getComment();
                            curriculum = profileResponseData.getCurriculum();
                            competencia = profileResponseData.getCompetencias();
                            imagem = profileResponseData.getImage();



                            //Salva todos os dados do usuário em shared preferences
                            SharedPreferences sendDates = getSharedPreferences("date", 0);
                            SharedPreferences.Editor date_editor = sendDates.edit();
                            date_editor.putString("date", date);
                            date_editor.commit();

                            SharedPreferences sendLogin = getSharedPreferences("login", 0);
                            SharedPreferences.Editor login_editor = sendLogin.edit();
                            login_editor.putString("login", login);
                            login_editor.commit();

                            SharedPreferences sendPass = getSharedPreferences("password", 0);
                            SharedPreferences.Editor pass_editor = sendPass.edit();
                            pass_editor.putString("password", password);
                            pass_editor.commit();

                            SharedPreferences sendSex = getSharedPreferences("sex", 0);
                            SharedPreferences.Editor sex_editor = sendSex.edit();
                            sex_editor.putInt("sex", sex);
                            sex_editor.commit();

                            SharedPreferences sendCpf = getSharedPreferences("cpf", 0);
                            SharedPreferences.Editor cpf_editor = sendCpf.edit();
                            cpf_editor.putString("cpf", cpf);
                            cpf_editor.commit();

                            SharedPreferences sendTel = getSharedPreferences("tel", 0);
                            SharedPreferences.Editor tel_editor = sendTel.edit();
                            tel_editor.putString("tel", tel);
                            tel_editor.commit();

                            SharedPreferences sendStates = getSharedPreferences("state", 0);
                            SharedPreferences.Editor state_editor = sendStates.edit();
                            state_editor.putString("state", state);
                            state_editor.commit();

                            SharedPreferences sendCities = getSharedPreferences("cityId", 0);
                            SharedPreferences.Editor city_editor = sendCities.edit();
                            city_editor.putInt("cityId", cityId);
                            city_editor.commit();

                            SharedPreferences sendNhood = getSharedPreferences("nhood", 0);
                            SharedPreferences.Editor nhood_editor = sendNhood.edit();
                            nhood_editor.putString("nhood", nhood);
                            nhood_editor.commit();

                            SharedPreferences sendCep = getSharedPreferences("cep", 0);
                            SharedPreferences.Editor cep_editor = sendCep.edit();
                            cep_editor.putString("cep", cep);
                            cep_editor.commit();

                            SharedPreferences sendStreet = getSharedPreferences("street", 0);
                            SharedPreferences.Editor street_editor = sendStreet.edit();
                            street_editor.putString("street", street);
                            street_editor.commit();

                            SharedPreferences sendNumber = getSharedPreferences("number", 0);
                            SharedPreferences.Editor number_editor = sendNumber.edit();
                            number_editor.putString("number", number);
                            number_editor.commit();

                            SharedPreferences sendComplement = getSharedPreferences("complement", 0);
                            SharedPreferences.Editor complement_editor = sendComplement.edit();
                            complement_editor.putString("complement", complement);
                            complement_editor.commit();

                            SharedPreferences sendComm = getSharedPreferences("comm", 0);
                            SharedPreferences.Editor comm_editor = sendComm.edit();
                            comm_editor.putString("comm", comment);
                            comm_editor.commit();

                            SharedPreferences profPict = getSharedPreferences("profPic", 0);
                            SharedPreferences.Editor profPict_editor = profPict.edit();
                            profPict_editor.putString("profPic", imagem);
                            profPict_editor.commit();

                            SharedPreferences sendCurr = getSharedPreferences("curr", 0);
                            SharedPreferences.Editor curr_editor = sendCurr.edit();
                            curr_editor.putString("curr", curriculum);
                            curr_editor.commit();

                            SharedPreferences sendComp = getSharedPreferences("comp", 0);
                            SharedPreferences.Editor comp_editor = sendComp.edit();
                            comp_editor.putString("comp", competencia.toString());
                            comp_editor.commit();

                            retrofitCidade(cityId);

                            //Chama o método pra abrir o fragmento
                            gotoProfile();

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

                //progress.dismiss();
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void retrofitCidade(int cidadeId){
        RetrofitService service = Connect.createService(RetrofitService.class);

        final ProfileDTO city = new ProfileDTO(cidadeId);

        Call<String> call = service.getCityName(city);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {

                    String cityResponse = response.body();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (cityResponse != null) {

                        if(cityResponse != null) {

                            String cityResponseData = response.body();
                            cidade = cityResponseData;


                            SharedPreferences sendCities = getSharedPreferences("cityName", 0);
                            SharedPreferences.Editor city_editor = sendCities.edit();
                            city_editor.putString("cityName", cidade);
                            city_editor.commit();


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
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Salvo com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void gotoProfile(){

        fragment = new FragmentProfile();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flMenu, fragment).addToBackStack("SecondFragment").commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
