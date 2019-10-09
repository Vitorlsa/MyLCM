package com.example.mylcm.Activities;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.RetrofitService;
import com.example.mylcm.Retrofit.Tasks.TasksDTO;
import com.example.mylcm.Retrofit.Tasks.TasksResponse;
import com.example.mylcm.Utils.Classes_Adapters.Solicitacao;
import com.example.mylcm.Utils.Adapters.SolicitacaoAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Calendario extends AppCompatActivity {

    ImageButton backBtn;
    MaterialCalendarView tasks;
    Date hoje;
    CalendarDay dia;
    public String diadehoje;
    ListView taskList;
    ArrayList<Solicitacao> contractData = new ArrayList<>();
    private SolicitacaoAdapter solicitacao;
    public int cid = 0, qtd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        backBtn = (ImageButton) findViewById(R.id.imgbtnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        taskList = (ListView) findViewById(R.id.taskList);
        tasks = (MaterialCalendarView) findViewById(R.id.materialCalendar);

        tasks.setDateSelected(CalendarDay.today(), true);

        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        hoje = Calendar.getInstance().getTime();


        tasks.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                dia = tasks.getSelectedDate();

                diadehoje = dia.toString();

                diadehoje = diadehoje.substring(12, 21);

                retrofitTasks(cid, diadehoje);

                /*contractData.add(new Solicitacao(1, 2, 3, diadehoje, "", ""));

                solicitacao = new SolicitacaoAdapter(getApplicationContext(), contractData);

                taskList.setAdapter(solicitacao);*/

            }
        });

    }

    public void retrofitTasks(int pid, String dia){
        RetrofitService service = Connect.createService(RetrofitService.class);

        final TasksDTO tasks = new TasksDTO(pid, dia);

        Call<ArrayList<TasksResponse>> call = service.getTasks(tasks);

        call.enqueue(new Callback<ArrayList<TasksResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TasksResponse>> call, Response<ArrayList<TasksResponse>> response) {

                if (response.isSuccessful()) {

                    ArrayList<TasksResponse> tasksResponse = response.body();
                    qtd = response.body().size();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (tasksResponse != null) {

                        for(int i = 0; i < qtd; i++){

                            if(tasksResponse.get(i).Id != 0) {

                                ArrayList<TasksResponse> tasksResponseData = response.body();

                            } else{

                                Toast.makeText(getApplicationContext(),"Insira Usuário e Senha válidos", Toast.LENGTH_SHORT).show();
                            }
                        }

                    } else {

                        Toast.makeText(getApplicationContext(),"Ops, você não é um Prestador de Serviço", Toast.LENGTH_SHORT).show();

                    }

                } else {

                    Toast.makeText(getApplicationContext(),"Resposta não foi um sucesso", Toast.LENGTH_SHORT).show();

                }

                //progress.dismiss();
            }

            @Override
            public void onFailure(Call<ArrayList<TasksResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
