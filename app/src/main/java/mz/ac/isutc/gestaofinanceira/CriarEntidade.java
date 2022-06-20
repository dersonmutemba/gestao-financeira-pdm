package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class CriarEntidade extends AppCompatActivity {

    EditText editTextNome;
    Spinner spinner;
    CheckBox checkBox;
    RadioButton radioButtonCredit, radioButtonDebit;
    RadioGroup radioGroup;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_entidade);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra(DatabaseVariables.USUARIO_TABLE);

        editTextNome = findViewById(R.id.entityEditText);
        spinner = findViewById(R.id.spinnerCategory);
        checkBox = findViewById(R.id.checkBoxCategoryPrompt);
        radioButtonCredit = findViewById(R.id.radioButtonCredit);
        radioButtonDebit = findViewById(R.id.radioButtonDebit);
        radioGroup = findViewById(R.id.radioGroup);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                CriarEntidade.this, R.array.credit_categories,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spinner.setEnabled(isChecked);
                radioButtonCredit.setEnabled(isChecked);
                radioButtonDebit.setEnabled(isChecked);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ArrayAdapter<CharSequence> adapter;
                if(radioButtonCredit.isChecked()) {
                    adapter = ArrayAdapter.createFromResource(
                            CriarEntidade.this, R.array.credit_categories,
                            android.R.layout.simple_spinner_item
                    );
                } else {
                    adapter = ArrayAdapter.createFromResource(
                            CriarEntidade.this, R.array.debit_categories,
                            android.R.layout.simple_spinner_item
                    );
                }
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        });
    }

    public void criarEntidade(View view) {
        if(!editTextNome.getText().toString().equals("")) {
            boolean hasCategory = true;
            if(!checkBox.isChecked() || spinner.getSelectedItem().toString().equals("Outros"))
                hasCategory = false;
            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            long id = preferences.getLong(getString(R.string.last_entity_id_key), 1000);
            Database db = new Database(getApplicationContext());
            long result = -1;
            if(hasCategory) {
                result = db.insertEntidade(
                        ++id,
                        editTextNome.getText().toString(),
                        spinner.getSelectedItem().toString(),
                        usuario.getEmail()
                );
            } else {
                result = db.insertEntidade(
                        ++id,
                        editTextNome.getText().toString(),
                        "Outros",
                        usuario.getEmail()
                );
            }
            if(result != -1) {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.entity_creation_success,
                        Toast.LENGTH_SHORT
                ).show();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong(getString(R.string.last_entity_id_key), id);
                editor.apply();
                db.close();
                finish();
            } else {
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

    public void voltar(View view) {
        finish();
    }
}