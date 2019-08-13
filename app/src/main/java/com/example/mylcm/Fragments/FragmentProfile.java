package com.example.mylcm.Fragments;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mylcm.Activities.NavDrawerMenu;
import com.example.mylcm.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment {


    public FragmentProfile() {
        // Required empty public constructor
    }

    TextView nameProfile;
    EditText edtProfileEmail, edtProfileSex, edtProfileState;
    CircleImageView imgProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_profile, container, false);

        nameProfile = (TextView)  v.findViewById(R.id.nameProfile);
        edtProfileEmail = (EditText) v.findViewById(R.id.edtProfileEmail);
        imgProfile = (CircleImageView) v.findViewById(R.id.imgProfile);
        edtProfileSex = (EditText) v.findViewById(R.id.edtProfileSex);
        edtProfileState = (EditText) v.findViewById(R.id.edtProfileState);


        //--Pegando o nome, email, e foto de perfil com sharedPrefs.
        SharedPreferences names = this.getActivity().getSharedPreferences("name", 0);
        String nameUser = names.getString("name", "");

        SharedPreferences emails = this.getActivity().getSharedPreferences("email", 0);
        String emailUser = emails.getString("email", "");

        SharedPreferences profPics = this.getActivity().getSharedPreferences("profPic", 0);
        String profPict = profPics.getString("profPic", "");

        SharedPreferences getSex = this.getActivity().getSharedPreferences("sex", 0);
        String sexo = getSex.getString("sex", "");

        SharedPreferences getStates = this.getActivity().getSharedPreferences("state", 0);
        String state = getStates.getString("state", "");
        //--Finaliza os sharedPrefs.

        //Transformo a string de base64 em bitmap.
        String base64profPict = profPict.split(",")[1];
        byte[] decodedString = Base64.decode(base64profPict, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        nameProfile.setText(nameUser);
        edtProfileEmail.setText(emailUser);
        imgProfile.setImageBitmap(decodedByte);
        edtProfileSex.setText(sexo);
        edtProfileState.setText(state);

        return v;
    }
}
