package mz.ac.isutc.gestaofinanceira;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Definicoes#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Definicoes extends Fragment implements AdapterView.OnItemSelectedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USUARIO = "usuario";

    // TODO: Rename and change types of parameters
    private Usuario usuario;

    public Definicoes() {
        // Required empty public constructor
    }

    Spinner spinner;
    Button btnPerfil;
    ArrayAdapter adpt;

    private String [] moedas = {"Metical MZN", "Rand RND", "Euro EUR", "Dollar DLR"};

    public static Definicoes newInstance(Usuario usuario) {
        Definicoes fragment = new Definicoes();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USUARIO, usuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            usuario = (Usuario) getArguments().getSerializable(ARG_USUARIO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_definicoes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = getView().findViewById(R.id.spinnerMoedas);
        spinner.setOnItemSelectedListener(this);

        adpt = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item, moedas);
        adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adpt);

        btnPerfil = getView().findViewById(R.id.btPerfil);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
    }

    private void myClick(){
        Database db = new Database(getContext());
        Cursor cursor = db.getUsuario(new String[]{usuario.getEmail()});
        //db.onUpgradeDelete();
    }

    private void openNewActivity(){
        Intent intent = new Intent(getContext(), Perfil.class);

        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        File file = getActivity().getFileStreamPath(getString(R.string.currency_key));
        switch (position) {
            case 0:
                Helper.writeCurrency(file, "MT");
                break;
            case 1:
                Helper.writeCurrency(file, "RND");
                break;
            case 2:
                Helper.writeCurrency(file, "€");
                break;
            case 3:
                Helper.writeCurrency(file, "$");
                break;
            default:
                Helper.writeCurrency(file, "MT");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}