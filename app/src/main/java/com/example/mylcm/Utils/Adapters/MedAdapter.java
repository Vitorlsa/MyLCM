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
import com.example.mylcm.Utils.Classes_Adapters.Medicamento;

import java.util.ArrayList;
import java.util.List;

public class MedAdapter extends ArrayAdapter<Medicamento> {

    private Context sContext;
    private List<Medicamento> medData = new ArrayList<>();

    public MedAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Medicamento> list){
        super(context, 0, list);
        sContext = context;
        medData = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(sContext).inflate(R.layout.item_med, parent,false);


        final Medicamento presenteMedicamento = medData.get(position);

        TextView nameMed = (TextView) listItem.findViewById(R.id.nome_med);
        nameMed.setText(presenteMedicamento.getNomeMedicamento());

        TextView qtdMed = (TextView) listItem.findViewById(R.id.qtd_med);
        qtdMed.setText(String.valueOf(presenteMedicamento.getQuantidade()));

        TextView dosagemMed = (TextView) listItem.findViewById(R.id.dosagem_med);
        dosagemMed.setText(presenteMedicamento.getPosologia());


        return listItem;
    }
}
