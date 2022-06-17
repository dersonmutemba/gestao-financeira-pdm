package mz.ac.isutc.i33.projectofinalpdm22;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;

public class
MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button btnPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSpinner();

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


    private void setSpinner() {
        spinner = findViewById(R.id.spinnerMoedas);

        ArrayList<String> sizes = new ArrayList<>();
        sizes.add("Metical MZN");
        sizes.add("Rand RND");
        sizes.add("Euro EUR");
        sizes.add("Dollar DLR");

        ArrayAdapter adpt = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sizes);
        adpt.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adpt);
    }
}