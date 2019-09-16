package com.example.mylcm.Activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mylcm.R;
import com.example.mylcm.Utils.Solicitacao;
import com.example.mylcm.Utils.SolicitacaoAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;

public class Calendario extends AppCompatActivity {

    ImageButton backBtn;
    MaterialCalendarView tasks;
    ListView taskList;
    ArrayList<Solicitacao> contractData = new ArrayList<>();
    private SolicitacaoAdapter solicitacao;

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

        tasks.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                contractData.add(new Solicitacao(1, 2, 3, "Vitor", "", ""));

                solicitacao = new SolicitacaoAdapter(getApplicationContext(), contractData);

                taskList.setAdapter(solicitacao);

            }
        });

    }
}
