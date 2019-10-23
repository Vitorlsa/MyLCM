package com.example.mylcm.Activities;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.Contract.BenefDTO;
import com.example.mylcm.Retrofit.Contract.ContractDTO;
import com.example.mylcm.Retrofit.RetrofitService;
import com.example.mylcm.Retrofit.Tasks.TasksDTO;
import com.example.mylcm.Retrofit.Tasks.TasksDoneDTO;
import com.example.mylcm.Retrofit.Tasks.TasksResponse;
import com.example.mylcm.Utils.Adapters.TasksAdapter;
import com.example.mylcm.Utils.Classes_Adapters.Solicitacao;
import com.example.mylcm.Utils.Adapters.SolicitacaoAdapter;
import com.example.mylcm.Utils.Classes_Adapters.Tasks;
import com.example.mylcm.Utils.StringWithTag;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Calendario extends AppCompatActivity {

    ImageButton backBtn;
    MaterialCalendarView tasks;
    Date hoje;
    CalendarDay dia;
    public Object tag;
    public String diadehoje, NameBenef, Title, TaskComment, MedName, HexaColor, dataTask;
    ListView taskList;
    ArrayList<Tasks> taskData = new ArrayList<>();
    ArrayList<StringWithTag> benefNames = new ArrayList<>();
    private TasksAdapter tasksAdapter;
    public int pid, qtd, contratId, idContrat, MedQtd, taskId;
    public Time tStart, tEnd;
    public Spinner spnTasks;

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

        SharedPreferences presID = getSharedPreferences("PID", 0);
        pid = presID.getInt("PID", -1);

        spnTasks = (Spinner) findViewById(R.id.spnTasks);
        benefNames.add(new StringWithTag("Selecione um Beneficiário", 0));
        ArrayAdapter<StringWithTag> taskBenefAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_item, benefNames);
        spnTasks.setAdapter(taskBenefAdapter);

        taskList = (ListView) findViewById(R.id.taskList);
        tasks = (MaterialCalendarView) findViewById(R.id.materialCalendar);

        tasks.setDateSelected(CalendarDay.today(), true);

        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        hoje = Calendar.getInstance().getTime();

        retrofitBenefNames(pid);

        spnTasks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                StringWithTag s = (StringWithTag) adapterView.getItemAtPosition(pos);
                tag = s.tag;
                idContrat = Integer.parseInt(tag.toString());
                dia = tasks.getSelectedDate();

                diadehoje = dia.toString();

                diadehoje = diadehoje.substring(12, 22);
                if(idContrat == 0){
                    Toast.makeText(getApplicationContext(),"Por favor selecione um Beneficiário", Toast.LENGTH_SHORT).show();
                }

                if(idContrat != 0){
                    if(tasksAdapter == null){
                        retrofitTasks(idContrat, diadehoje);
                    }
                    else {
                        taskData.clear();
                        tasksAdapter.notifyDataSetChanged();
                        taskList.setAdapter(tasksAdapter);
                        retrofitTasks(idContrat, diadehoje);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tasks.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                dia = tasks.getSelectedDate();

                diadehoje = String.valueOf(dia.getYear()) + "-" + String.valueOf(dia.getMonth()) + "-" + String.valueOf(dia.getDay());

                if(idContrat != 0){
                    if(tasksAdapter != null){
                        taskData.clear();
                        tasksAdapter.notifyDataSetChanged();
                        taskList.setAdapter(tasksAdapter);
                        retrofitTasks(idContrat, diadehoje);
                    }
                    else{
                        retrofitTasks(idContrat, diadehoje);
                    }
                }
            }
        });
    }

    public void retrofitTasks(int cid, String dia){
        RetrofitService service = Connect.createService(RetrofitService.class);

        final TasksDTO tasks = new TasksDTO(cid, dia);

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
                                taskId = tasksResponseData.get(i).Id;
                                Title = tasksResponseData.get(i).Titulo;
                                String TimeStart = tasksResponseData.get(i).HoraInicio;
                                String TimeEnd = tasksResponseData.get(i).HoraFim;
                                TaskComment = tasksResponseData.get(i).Comentario;
                                MedQtd = tasksResponseData.get(i).QuantidadeMedicamento;
                                MedName = tasksResponseData.get(i).NomeMedicamento;
                                HexaColor = tasksResponseData.get(i).CorHexa;
                                dataTask = tasksResponseData.get(i).Data;

                                dataTask = dataTask.substring(0, 9);

                                tStart = Time.valueOf(TimeStart);
                                tEnd = Time.valueOf(TimeEnd);

                                ajustarListTasks();

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

    public void retrofitBenefNames(int pid){
        RetrofitService service = Connect.createService(RetrofitService.class);

        final ContractDTO benef = new ContractDTO(pid);

        Call<ArrayList<BenefDTO>> call = service.getBenef(benef);

        call.enqueue(new Callback<ArrayList<BenefDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<BenefDTO>> call, Response<ArrayList<BenefDTO>> response) {

                if (response.isSuccessful()) {

                    ArrayList<BenefDTO> benefResponse = response.body();
                    qtd = response.body().size();

                    //verifica aqui se o corpo da resposta não é nulo
                    if (benefResponse != null) {

                        for(int i = 0; i < qtd; i++){

                            if(benefResponse.get(i).Id != 0) {

                                ArrayList<BenefDTO> benefResponseData = response.body();
                                NameBenef = benefResponseData.get(i).NomeBeneficiario;
                                contratId = benefResponseData.get(i).Id;

                                popularSpinnerBenef();

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
            public void onFailure(Call<ArrayList<BenefDTO>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Erro na chamada ao servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void retrofitDoneTasks(int tarefaId, String Comentario, String Data, String Hora, boolean Realizada, int tarefaRealizadaId){
        RetrofitService service = Connect.createService(RetrofitService.class);

        final TasksDoneDTO done = new TasksDoneDTO(tarefaId, Comentario, Data, Hora, Realizada, tarefaRealizadaId);

        Call<TasksDoneDTO> call = service.setTasksDone(done);

        call.enqueue(new Callback<TasksDoneDTO>() {
            @Override
            public void onResponse(Call<TasksDoneDTO> call, Response<TasksDoneDTO> response) {
                if (response.isSuccessful()){

                }
            }

            @Override
            public void onFailure(Call<TasksDoneDTO> call, Throwable t) {

            }
        });
    }

    public void popularSpinnerBenef(){
        benefNames.add(new StringWithTag(NameBenef, contratId));
        ArrayAdapter<StringWithTag> benefNamesAdapter = new ArrayAdapter<StringWithTag>(this, android.R.layout.simple_spinner_item, benefNames);
        spnTasks.setAdapter(benefNamesAdapter);
    }

    public void ajustarListTasks(){
        taskData.add(new Tasks(taskId, Title, tStart, tEnd, TaskComment, MedQtd, MedName, HexaColor, dataTask));
        if(taskData.size() == 1) {
            popularListTasks();
        }
        else if(taskData.size() > 1) {
            for(int i = 0; i < taskData.size()-1; i++){
                if(taskData.get(i).getTimeStart().after(taskData.get(i+1).getTimeStart())){
                    Collections.swap(taskData, i, i+1);
                }
            }
            popularListTasks();
        }
    }

    public void popularListTasks(){
        tasksAdapter = new TasksAdapter(this, taskData);
        taskList.setAdapter(tasksAdapter);
    }
}
