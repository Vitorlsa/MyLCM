package com.example.mylcm.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mylcm.Activities.Beneficiarios;
import com.example.mylcm.Activities.Calendario;
import com.example.mylcm.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMenu.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMenu extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMenu newInstance(String param1, String param2) {
        FragmentMenu fragment = new FragmentMenu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    Button btnCalendario, btnCustos, btnBeneficiario, btnMedicamentos;
    Spinner spnBeneficiarios;
    String names[] = {"Selecionar Beneficiário","Roberto", "Carlos", "José"};
    ArrayAdapter<String>arrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_menu, container, false);

        btnCalendario = (Button) v.findViewById(R.id.btn_Calendario);
        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make your toast here
                if(spnBeneficiarios.getSelectedItemPosition() == 0){
                    Toast.makeText(getActivity().getApplicationContext(),"Por favor selecione um Beneficiário", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Abrir Calendário " + spnBeneficiarios.getSelectedItem(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), Calendario.class));
                }
            }
        });

        btnBeneficiario = (Button) v.findViewById(R.id.btn_Beneficiario);
        btnBeneficiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make your toast here
                if(spnBeneficiarios.getSelectedItemPosition() == 0){
                    Toast.makeText(getActivity().getApplicationContext(),"Por favor selecione um Beneficiário", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Abrir Beneficiário " + spnBeneficiarios.getSelectedItem(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getContext(), Beneficiarios.class));
                }
            }
        });

        btnCustos = (Button) v.findViewById(R.id.btn_Custos);
        btnCustos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make your toast here
                if(spnBeneficiarios.getSelectedItemPosition() == 0){
                    Toast.makeText(getActivity().getApplicationContext(),"Por favor selecione um Beneficiário", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Abrir Custos " + spnBeneficiarios.getSelectedItem(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnMedicamentos = (Button) v.findViewById(R.id.btn_Medicamentos);
        btnMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //make your toast here
                if(spnBeneficiarios.getSelectedItemPosition() == 0){
                    Toast.makeText(getActivity().getApplicationContext(),"Por favor selecione um Beneficiário", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Abrir Medicamentos " + spnBeneficiarios.getSelectedItem(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        spnBeneficiarios = (Spinner) v.findViewById(R.id.spn_Beneficiarios);
        arrayAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, names);
        spnBeneficiarios.setAdapter(arrayAdapter);
        spnBeneficiarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).toString().equals("Selecionar Beneficiário")){
                    Toast.makeText(getActivity().getApplicationContext(),"Por favor selecione um Beneficiário", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Você selecionou " + names[position], Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
