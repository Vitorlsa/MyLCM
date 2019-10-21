package com.example.mylcm.Utils.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mylcm.R;
import com.example.mylcm.Utils.Classes_Adapters.Tasks;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends ArrayAdapter<Tasks> {

    private Context sContext;
    private List<Tasks> taskData = new ArrayList<>();

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

        return listItem;
    }
}
