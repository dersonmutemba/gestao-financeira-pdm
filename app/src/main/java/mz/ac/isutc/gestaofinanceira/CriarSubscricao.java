package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CriarSubscricao extends AppCompatActivity {

    Spinner spinnerEntidades;
    SeekBar seekBar;
    TextView seekBarLabel;
    EditText editTextNome, editTextAmount;
    RadioButton radioButtonCredito, radioButtonDebito;

    Usuario usuario;

    long[] entidadesID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_subscricao);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra(DatabaseVariables.USUARIO_TABLE);

        spinnerEntidades = findViewById(R.id.subscriptionEntityList);
        seekBar = findViewById(R.id.periodicity_seekbar);
        seekBarLabel = findViewById(R.id.periodicity_label);
        editTextNome = findViewById(R.id.subscriptionEditText);
        editTextAmount = findViewById(R.id.subscriptionEditTextAmount);

        Database db = new Database(getApplicationContext());
        ArrayList<Entidade> entidades = db.getEntidadesByUsuarioArrayList(new String[]{usuario.getEmail()});
        String[] entidadesNames = new String[entidades.size() + 1];
        entidadesID = new long[entidades.size()];

        entidadesNames[0] = getString(R.string.entity_spinner_tooltip);
        for(int i = 1; i < entidadesNames.length; i++) {
            entidadesNames[i] = entidades.get(i - 1).getNome();
            entidadesID[i - 1] = entidades.get(i - 1).getId();
        }
        ArrayAdapter<String> adapterEntidades = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, entidadesNames);
        adapterEntidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEntidades.setAdapter(adapterEntidades);
        String[] periodicidadesResource = getResources().getStringArray(R.array.periodicidades);
        seekBarLabel.setText(periodicidadesResource[seekBar.getProgress()]);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarLabel.setText(periodicidadesResource[progress]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        radioButtonCredito = findViewById(R.id.subscriptionRadioButtonCredit);
        radioButtonDebito = findViewById(R.id.subscriptionRadioButtonDebit);
    }

    public void voltar(View view) {
        finish();
    }

    public void criarSubscricao(View view) {
        if(!editTextNome.getText().toString().equals("") &&
                !editTextAmount.getText().toString().equals("") &&
                spinnerEntidades.getSelectedItemPosition() != 0) {
            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            long id = preferences.getLong(getString(R.string.last_subscription_id_key), 1000);
            SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat(Movimento.DATE_FORMAT);
            Calendar calendar = Calendar.getInstance();
            String date = simpleDateFormatDate.format(calendar.getTime());
            Database database = new Database(getApplicationContext());
            Entidade entidade = database.getEntidadesByNameArrayList(new String[]{spinnerEntidades.getSelectedItem().toString()}).get(0);
            long result = database.insertSubscricao(
                    ++id,
                    editTextNome.getText().toString(),
                    date,
                    seekBarLabel.getText().toString(),
                    Double.parseDouble(editTextAmount.getText().toString()),
                    radioButtonCredito.isChecked() ? radioButtonCredito.getText().toString() : radioButtonDebito.getText().toString(),
                    entidade != null ? entidade.getId() : 1000
            );
            database.close();
            if(result != -1) {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.entity_creation_success,
                        Toast.LENGTH_SHORT
                ).show();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong(getString(R.string.last_subscription_id_key), id);
                editor.apply();
                finish();
            }
            else {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.insertion_error,
                        Toast.LENGTH_SHORT
                ).show();
            }
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.empty_field_error),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}