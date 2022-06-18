package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.widget.Toast;

public class Def extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    Button btnPerfil;
    private String [] moedas = {"Metical MZN", "Rand RND", "Euro EUR", "Dollar DLR"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spinner = findViewById(R.id.spinnerMoedas);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter adpt = new ArrayAdapter(this, android.R.layout.simple_spinner_item, moedas);
        adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adpt);

        btnPerfil = findViewById(R.id.btPerfil);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
    }
    public void openNewActivity(){
        Intent intent = new Intent(this, Perfil.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, moedas[position],Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}