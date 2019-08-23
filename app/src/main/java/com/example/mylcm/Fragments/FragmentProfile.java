package com.example.mylcm.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylcm.Activities.NavDrawerMenu;
import com.example.mylcm.R;
import com.example.mylcm.Retrofit.Connect;
import com.example.mylcm.Retrofit.EditDTO;
import com.example.mylcm.Retrofit.RetrofitService;

import java.io.File;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment {


    public FragmentProfile() {
        // Required empty public constructor
    }

    Button btnSave;
    TextView nameProfile, txtEditar;
    EditText edtProfileEmail, edtProfileSex, edtProfileState, edtProfileDoB, edtProfileCpf, edtProfileTel, edtProfileCity, edtProfileNhood, edtProfileCep, edtProfileStreet, edtProfileNumber, edtProfileComplement;
    CircleImageView imgProfile;
    RelativeLayout rellayProfile;
    public static String nameUser, login, password, emailUser, profPict, date, sex, cpf, tel, state, nhood, cep, street, number, complement, comment, curriculum;
    public static int pid, cidade, sexo;
    ArrayList<Integer> competencia;
    Boolean termos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragment_profile, container, false);

        getActivity().setTitle("Perfil");


        rellayProfile = (RelativeLayout) v.findViewById(R.id.rellayProfile);
        nameProfile = (TextView)  v.findViewById(R.id.nameProfile);
        edtProfileEmail = (EditText) v.findViewById(R.id.edtProfileEmail);
        imgProfile = (CircleImageView) v.findViewById(R.id.imgProfile);
        edtProfileSex = (EditText) v.findViewById(R.id.edtProfileSex);
        edtProfileState = (EditText) v.findViewById(R.id.edtProfileState);
        edtProfileDoB = (EditText) v.findViewById(R.id.edtProfileDoB);
        edtProfileCpf = (EditText) v.findViewById(R.id.edtProfileCPF);
        edtProfileTel = (EditText) v.findViewById(R.id.edtProfileTel);
        edtProfileCity = (EditText) v.findViewById(R.id.edtProfileCity);
        edtProfileNhood = (EditText) v.findViewById(R.id.edtProfileNHood);
        edtProfileCep = (EditText) v.findViewById(R.id.edtProfileZIP);
        edtProfileStreet = (EditText) v.findViewById(R.id.edtProfileStreetAddress);
        edtProfileNumber = (EditText) v.findViewById(R.id.edtProfileNumber);
        edtProfileComplement = (EditText) v.findViewById(R.id.edtProfileComplement);
        txtEditar = (TextView) v.findViewById(R.id.txtEditar);
        btnSave = (Button) v.findViewById(R.id.btnSave);

        //--Pegando o nome, email, e foto de perfil com sharedPrefs.
        SharedPreferences  presID = this.getActivity().getSharedPreferences("PID", 0);
        pid = presID.getInt("PID", -1);

        SharedPreferences names = this.getActivity().getSharedPreferences("name", 0);
        nameUser = names.getString("name", "");

        SharedPreferences emails = this.getActivity().getSharedPreferences("email", 0);
        emailUser = emails.getString("email", "");

        SharedPreferences profPics = this.getActivity().getSharedPreferences("profPic", 0);
        profPict = profPics.getString("profPic", "");

        SharedPreferences getDates = this.getActivity().getSharedPreferences("date", 0);
        date = getDates.getString("date", "");

        SharedPreferences getSex = this.getActivity().getSharedPreferences("sex", 0);
        sexo = getSex.getInt("sex", -1);

        SharedPreferences getCpfs = this.getActivity().getSharedPreferences("cpf", 0);
        cpf = getCpfs.getString("cpf", "");

        SharedPreferences getTel = this.getActivity().getSharedPreferences("tel", 0);
        tel = getTel.getString("tel", "");

        SharedPreferences getStates = this.getActivity().getSharedPreferences("state", 0);
        state = getStates.getString("state", "");

        SharedPreferences getCities = this.getActivity().getSharedPreferences("city", 0);
        cidade = getCities.getInt("city", -1);
        String city = Integer.toString(cidade);

        SharedPreferences getNhood = this.getActivity().getSharedPreferences("nhood", 0);
        nhood = getNhood.getString("nhood", "");

        SharedPreferences getCep = this.getActivity().getSharedPreferences("cep", 0);
        cep = getCep.getString("cep", "");

        SharedPreferences getStreet = this.getActivity().getSharedPreferences("street", 0);
        street = getStreet.getString("street", "");

        SharedPreferences getNumber = this.getActivity().getSharedPreferences("number", 0);
        number = getNumber.getString("number", "");

        SharedPreferences getComplement = this.getActivity().getSharedPreferences("complement", 0);
        complement = getComplement.getString("complement", "");
        //--Finaliza os sharedPrefs.

        //Muda o sexo de ID pra Masculino, Feminino e Outros
        if (sexo == 1){
            sex = "Masculino";
        }
        else if (sexo == 2){
            sex = "Feminino";
        }
        else {
            sex = "Outros";
        }

        //Pego o cpf e adiciono "." e "-"
        cpf = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);

        //Pego o cep e adiciono "-"
        cep = cep.substring(0, 5) + "-" + cep.substring(5, 8);

        //Pego a data em substring e divido ela nos traços
        date = date.substring(0, 10);
        String data[] = date.split("-");

        //Transformo a string de base64 em bitmap.
        String base64profPict = profPict.split(",")[1];
        byte[] decodedString = Base64.decode(base64profPict, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        nameProfile.setText(nameUser);
        edtProfileEmail.setText(emailUser);
        imgProfile.setImageBitmap(decodedByte);
        edtProfileDoB.setText(data[2]+ "/" + data[1] + "/" + data[0]);
        edtProfileSex.setText(sex);
        edtProfileCpf.setText(cpf);
        edtProfileTel.setText(tel);
        edtProfileState.setText(state);
        edtProfileCity.setText(city);
        edtProfileNhood.setText(nhood);
        edtProfileCep.setText(cep);
        edtProfileStreet.setText(street);
        edtProfileNumber.setText(number);
        edtProfileComplement.setText(complement);

        txtEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rellayProfile.setVisibility(View.GONE);
                edtProfileEmail.setEnabled(true);
                edtProfileTel.setEnabled(true);
                edtProfileNhood.setEnabled(true);
                edtProfileCep.setEnabled(true);
                edtProfileStreet.setEnabled(true);
                edtProfileNumber.setEnabled(true);
                edtProfileComplement.setEnabled(true);
                btnSave.setVisibility(View.VISIBLE);

            }
        });

        edtProfileEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    edtProfileEmail.setBackgroundColor(getResources().getColor(R.color.grey));
                } else {
                    edtProfileEmail.setBackgroundColor(getResources().getColor(R.color.transparent));
                }
            }
        });

        edtProfileTel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    edtProfileTel.setBackgroundColor(getResources().getColor(R.color.grey));
                } else {
                    edtProfileTel.setBackgroundColor(getResources().getColor(R.color.transparent));
                }
            }
        });

        edtProfileNhood.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    edtProfileNhood.setBackgroundColor(getResources().getColor(R.color.grey));
                } else {
                    edtProfileNhood.setBackgroundColor(getResources().getColor(R.color.transparent));
                }
            }
        });

        edtProfileCep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    edtProfileCep.setBackgroundColor(getResources().getColor(R.color.grey));
                } else {
                    edtProfileCep.setBackgroundColor(getResources().getColor(R.color.transparent));
                }
            }
        });

        edtProfileStreet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    edtProfileStreet.setBackgroundColor(getResources().getColor(R.color.grey));
                } else {
                    edtProfileStreet.setBackgroundColor(getResources().getColor(R.color.transparent));
                }
            }
        });

        edtProfileNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    edtProfileNumber.setBackgroundColor(getResources().getColor(R.color.grey));
                } else {
                    edtProfileNumber.setBackgroundColor(getResources().getColor(R.color.transparent));
                }
            }
        });

        edtProfileComplement.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    edtProfileComplement.setBackgroundColor(getResources().getColor(R.color.grey));
                } else {
                    edtProfileComplement.setBackgroundColor(getResources().getColor(R.color.transparent));
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                emailUser = edtProfileEmail.getText().toString();
                tel = edtProfileTel.getText().toString();
                nhood = edtProfileNhood.getText().toString();
                cep = edtProfileCep.getText().toString();
                street = edtProfileStreet.getText().toString();
                number = edtProfileNumber.getText().toString();
                complement = edtProfileComplement.getText().toString();

                retrofitEdit(pid, emailUser, sexo, state, date, cpf, tel, cidade, nhood, cep, street, number, complement);

                edtProfileEmail.setBackgroundColor(getResources().getColor(R.color.transparent));
                edtProfileTel.setBackgroundColor(getResources().getColor(R.color.transparent));
                edtProfileNhood.setBackgroundColor(getResources().getColor(R.color.transparent));
                edtProfileCep.setBackgroundColor(getResources().getColor(R.color.transparent));
                edtProfileStreet.setBackgroundColor(getResources().getColor(R.color.transparent));
                edtProfileNumber.setBackgroundColor(getResources().getColor(R.color.transparent));
                edtProfileComplement.setBackgroundColor(getResources().getColor(R.color.transparent));

                rellayProfile.setVisibility(View.VISIBLE);
                edtProfileEmail.setEnabled(false);
                edtProfileTel.setEnabled(false);
                edtProfileNhood.setEnabled(false);
                edtProfileCep.setEnabled(false);
                edtProfileStreet.setEnabled(false);
                edtProfileNumber.setEnabled(false);
                edtProfileComplement.setEnabled(false);
                btnSave.setVisibility(View.GONE);
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                }
                else {
                    startGallery();
                }
            }
        });

        return v;
    }

    private void startGallery(){
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            //super method removed
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == 1000) {
                    Uri returnUri = data.getData();
                    final String path = getPathFromURI(returnUri);
                    if (path != null) {
                        File f = new File(path);
                        returnUri = Uri.fromFile(f);
                    }

                    imgProfile.setImageURI(returnUri);
                }
            }
        } catch (Exception e) {
            Log.e("FIleSelectorActivity", "File select error", e);
        }
    }

    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public void retrofitEdit(final int pid, final String emailUser, final int sexo, final String state, final String date, final String cpf, final String tel, final int cidade, final String nhood, final String cep, final String street, final String number, final String complement){

        RetrofitService service = Connect.createService(RetrofitService.class);

        final EditDTO edit = new EditDTO(pid, emailUser, sexo, state, date, cpf, tel, cidade, nhood, cep, street, number, complement);

        Call<EditDTO> call = service.setEdit(edit);

        call.enqueue(new Callback<EditDTO>() {
            @Override
            public void onResponse(Call<EditDTO> call, Response<EditDTO> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getActivity().getApplicationContext(),"Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(),"Não foi possível salvar suas informações", Toast.LENGTH_SHORT).show();
            }
        });
    }
}