package com.example.mylcm.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.mylcm.Activities.Contracts;
import com.example.mylcm.Activities.Medicamentos;
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

    Button btnCalendario, btnContratos, btnBeneficiario, btnMedicamentos;
    String names[] = {"Selecionar Beneficiário","Roberto", "Carlos", "José"};
    ArrayAdapter<String> arrayAdapter;
    public static int pid, qtd;
    public static String NameBenef, NameContract, ReqDate;

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

        SharedPreferences presID = this.getActivity().getSharedPreferences("PID", 0);
        pid = presID.getInt("PID", -1);

        btnCalendario = (Button) v.findViewById(R.id.btn_Calendario);
        btnCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCalendario.setEnabled(false);
                //make your toast here
                startActivity(new Intent(getContext(), Calendario.class));
            }
        });

        btnBeneficiario = (Button) v.findViewById(R.id.btn_Beneficiario);
        btnBeneficiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnBeneficiario.setEnabled(false);
                startActivity(new Intent(getContext(), Beneficiarios.class));
                //make your toast here
            }
        });

        btnContratos = (Button) v.findViewById(R.id.btn_Contratos);
        btnContratos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnContratos.setEnabled(false);
                //make your toast here
                startActivity(new Intent(getContext(), Contracts.class));
            }
        });

        btnMedicamentos = (Button) v.findViewById(R.id.btn_Medicamentos);
        btnMedicamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnMedicamentos.setEnabled(false);
                //make your toast here
                startActivity(new Intent(getContext(), Medicamentos.class));
            }
        });

        return v;
    }

    public void onResume(){
        super.onResume();

        btnCalendario.setEnabled(true);
        btnBeneficiario.setEnabled(true);
        btnContratos.setEnabled(true);
        btnMedicamentos.setEnabled(true);
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
