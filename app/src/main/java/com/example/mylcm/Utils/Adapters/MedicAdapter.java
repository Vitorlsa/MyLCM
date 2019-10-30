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
import com.example.mylcm.Utils.Classes_Adapters.MedicosBenef;

import java.util.ArrayList;
import java.util.List;

public class MedicAdapter extends ArrayAdapter<MedicosBenef> {

    private Context sContext;
    private List<MedicosBenef> medicData = new ArrayList<>();

    public MedicAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<MedicosBenef> list){
        super(context, 0, list);
        sContext = context;
        medicData = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(sContext).inflate(R.layout.item_medicos, parent,false);


        final MedicosBenef presenteMedico = medicData.get(position);

        TextView nameMed = (TextView) listItem.findViewById(R.id.nome_medic);
        nameMed.setText(presenteMedico.getNomeMedico());

        TextView qtdMed = (TextView) listItem.findViewById(R.id.tel_Medic);
        qtdMed.setText(String.valueOf(presenteMedico.getTelMedico()));

        return listItem;
    }
}
