package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SingleEntidade extends AppCompatActivity {

    Entidade entidade;

    EditText editTextNome;
    Spinner spinner;
    CheckBox checkBox;
    RadioGroup radioGroup;
    RadioButton radioButtonDebit, radioButtonCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_entidade);

        Intent intent = getIntent();
        entidade = (Entidade) intent.getSerializableExtra(DatabaseVariables.ENTIDADE_TABLE);

        editTextNome = findViewById(R.id.entityEditText1);
        spinner = findViewById(R.id.spinnerCategoryEditEntity);
        radioGroup = findViewById(R.id.radioGroupEditEntity);
        radioButtonCredit = findViewById(R.id.radioButtonCreditEditEntity);
        radioButtonDebit = findViewById(R.id.radioButtonDebitEditEntity);
        checkBox = findViewById(R.id.checkBoxCategoryPromptEditEntity);
        editTextNome.setText(entidade.getNome());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                SingleEntidade.this, R.array.credit_categories,
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
                            SingleEntidade.this, R.array.credit_categories,
                            android.R.layout.simple_spinner_item
                    );
                } else {
                    adapter = ArrayAdapter.createFromResource(
                            SingleEntidade.this, R.array.debit_categories,
                            android.R.layout.simple_spinner_item
                    );
                }
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
            }
        });
    }

    public void guardar(View view) {
        if(!editTextNome.getText().toString().equals("")) {
            boolean hasCategory = true;
            if(!checkBox.isChecked() || spinner.getSelectedItem().toString().equals("Outros"))
                hasCategory = false;
            Database database = new Database(getApplicationContext());
            long result = -1;
            if(hasCategory) {
                result = database.updateEntidade(
                        entidade.getId(),
                        editTextNome.getText().toString(),
                        spinner.getSelectedItem().toString()
                );
            } else {
                result = database.updateEntidade(
                        entidade.getId(),
                        editTextNome.getText().toString(),
                        "Outros"
                );
            }
            if(result != -1) {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.successful_update,
                        Toast.LENGTH_SHORT
                ).show();
                database.close();
                finish();
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.error,
                        Toast.LENGTH_SHORT
                ).show();
            }
            database.close();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_field_error,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    public void remover(View view) {
        Database database = new Database(getApplicationContext());
        long result = database.removeEntidade(entidade.getId());
        if(result != -1) {
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.successful_deletion),
                    Toast.LENGTH_SHORT
            ).show();
            database.close();
            finish();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.error),
                    Toast.LENGTH_SHORT
            ).show();
        }
        database.close();
    }

    public void voltar(View view) {
        finish();
    }
}