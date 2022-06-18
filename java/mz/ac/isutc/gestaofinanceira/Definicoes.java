package mz.ac.isutc.gestaofinanceira;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Definicoes#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Definicoes extends Fragment implements AdapterView.OnItemSelectedListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Definicoes() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Definicoes.
     */
    // TODO: Rename and change types and number of parameters

    Spinner spinner;
    Button btnPerfil;
    ArrayAdapter adpt;
    private String [] moedas = {"Metical MZN", "Rand RND", "Euro EUR", "Dollar DLR"};

    public static Definicoes newInstance(String param1, String param2) {
        Definicoes fragment = new Definicoes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

        return inflater.inflate(R.layout.fragment_definicoes, container, false);
    }

    private void openNewActivity(){
        Intent intent = new Intent(getContext(), Perfil.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), moedas[position],Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}