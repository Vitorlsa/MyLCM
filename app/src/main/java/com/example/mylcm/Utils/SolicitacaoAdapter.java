package com.example.mylcm.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylcm.Activities.Contracts;
import com.example.mylcm.R;

import java.util.ArrayList;
import java.util.List;

public class SolicitacaoAdapter extends ArrayAdapter<Solicitacao> {

    private Context sContext;
    private List<Solicitacao> contractData = new ArrayList<>();
    private boolean yorn;
    private int idSol;

    public SolicitacaoAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Solicitacao> list){
        super(context, 0, list);
        sContext = context;
        contractData = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){

        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(sContext).inflate(R.layout.item_contract, parent,false);


        final Solicitacao presenteSolicitacao = contractData.get(position);

        TextView nameContract = (TextView) listItem.findViewById(R.id.contract_name);
        nameContract.setText(presenteSolicitacao.getNomeContratante());

        TextView nameBenef = (TextView) listItem.findViewById(R.id.contract_benef);
        nameBenef.setText(presenteSolicitacao.getNomeBeneficiario());

        Button accept = (Button) listItem.findViewById(R.id.contract_accept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idSol = presenteSolicitacao.getId();
                remove(presenteSolicitacao);
                notifyDataSetChanged();
                yorn = true;
                Contracts contracts = new Contracts();
                contracts.retrofitAceitar(idSol, yorn);
            }
        });

        Button deny = (Button) listItem.findViewById(R.id.contract_deny);
        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idSol = presenteSolicitacao.getId();
                remove(presenteSolicitacao);
                notifyDataSetChanged();
                yorn = false;
                Contracts contracts = new Contracts();
                contracts.retrofitAceitar(idSol, yorn);
            }
        });

        return listItem;
    }

}
