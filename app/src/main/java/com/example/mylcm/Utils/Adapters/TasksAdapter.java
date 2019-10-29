package com.example.mylcm.Utils.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylcm.Activities.Calendario;
import com.example.mylcm.R;
import com.example.mylcm.Utils.Classes_Adapters.Tasks;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends ArrayAdapter<Tasks> {

    private Context sContext;
    private List<Tasks> taskData = new ArrayList<>();
    private Dialog tasksModal;
    private boolean yorn;
    private int tarefaId, tarefaRealizadaId;
    private String comentario, data, hora, HexaColor;

    public TasksAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Tasks> list){
        super(context, 0, list);
        sContext = context;
        taskData = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(sContext).inflate(R.layout.item_tasks, parent,false);

        final Tasks presenteTask = taskData.get(position);

        TextView taskTitle = (TextView) listItem.findViewById(R.id.tasksTitle);
        taskTitle.setText(presenteTask.getTitle());

        EditText taskColor = (EditText) listItem.findViewById(R.id.taskColor);
        if(presenteTask.getHexaColor().isEmpty()){
            HexaColor = "#FFFFFF";
        }
        else{
            HexaColor = presenteTask.getHexaColor();
            taskColor.setBackgroundColor(Color.parseColor(HexaColor));
        }

        TextView taskTime = (TextView) listItem.findViewById(R.id.taskTime);
        taskTime.setText(presenteTask.getTimeStart().toString().substring(0,5));

        tasksModal = new Dialog(sContext);

        Button done = (Button) listItem.findViewById(R.id.tasksDone);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasksModal.setContentView(R.layout.modal_tasksdone);
                tasksModal.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                yorn = true;
                tarefaId = presenteTask.getTaskId();
                data = presenteTask.getDataTask();
                hora = String.valueOf(presenteTask.getTimeStart());
                tarefaRealizadaId = 0;
                final EditText comentarios = (EditText) tasksModal.findViewById(R.id.edtComentario);

                Button enviar = tasksModal.findViewById(R.id.btnSend);
                enviar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        comentario = comentarios.getEditableText().toString();
                        if(comentario.equals("")){
                            Toast.makeText(sContext,"Por favor digite um comentário.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Calendario calendario = new Calendario();
                            calendario.retrofitDoneTasks(tarefaId, comentario, data, hora, yorn, tarefaRealizadaId);
                        }
                    }
                });

                tasksModal.show();
            }
        });

        Button notDone = (Button) listItem.findViewById(R.id.tasksNotDone);
        notDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasksModal.setContentView(R.layout.modal_tasksdone);
                tasksModal.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                yorn = false;
                tarefaId = presenteTask.getTaskId();
                data = presenteTask.getDataTask();
                hora = String.valueOf(presenteTask.getTimeStart());
                tarefaRealizadaId = 0;
                final EditText comentarios = (EditText) tasksModal.findViewById(R.id.edtComentario);

                Button enviar = tasksModal.findViewById(R.id.btnSend);
                enviar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        comentario = comentarios.getEditableText().toString();
                        if(comentario.equals("")){
                            Toast.makeText(sContext,"Por favor digite um comentário.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Calendario calendario = new Calendario();
                            calendario.retrofitDoneTasks(tarefaId, comentario, data, hora, yorn, tarefaRealizadaId);
                            tasksModal.dismiss();
                        }
                    }
                });

                tasksModal.show();
            }
        });

        return listItem;
    }

    public void dismissModal(){
        tasksModal.dismiss();
    }
}
