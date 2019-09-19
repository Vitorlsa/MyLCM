package com.example.mylcm.Utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mylcm.Activities.Beneficiarios;
import com.example.mylcm.R;

import java.util.ArrayList;
import java.util.List;

public class BenefAdapter extends ArrayAdapter<Beneficiario> {

    private Context sContext;
    private List<Beneficiario> benefData = new ArrayList<>();
    private Dialog benefModal;

    public BenefAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Beneficiario> list){
        super(context, 0, list);
        sContext = context;
        benefData = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(sContext).inflate(R.layout.item_benef, parent,false);


        final Beneficiario presenteBeneficiario = benefData.get(position);

        benefModal = new Dialog(sContext);

        TextView nameContract = (TextView) listItem.findViewById(R.id.contract_name);
        nameContract.setText(presenteBeneficiario.getNomeContratante());

        TextView nameBenef = (TextView) listItem.findViewById(R.id.contract_benef);
        nameBenef.setText(presenteBeneficiario.getNomeBeneficiario());


        Button more = (Button) listItem.findViewById(R.id.btnMore);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                benefModal.setContentView(R.layout.modal_benef);


                Button close = (Button) benefModal.findViewById(R.id.BenefClose);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        benefModal.dismiss();
                    }
                });

                benefModal.show();
            }
        });





        return listItem;
    }
}
