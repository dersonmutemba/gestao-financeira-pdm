package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class CriarConta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
    }

    public void criarConta(View view) {
        EditText editTextNome = findViewById(R.id.accountNameEditText);
        EditText editTextAmount = findViewById(R.id.accountAmountEditText);
        CheckBox checkBoxBankAssociated = findViewById(R.id.checkboxBankAssociated);
        EditText editTextAssociatedBank = findViewById(R.id.associatedBankEditText);
        if(!editTextNome.getText().toString().equals("") &&
                !editTextAmount.getText().toString().equals("") &&
                ((checkBoxBankAssociated.isChecked() && !editTextAssociatedBank.getText().toString().equals("")) ||
                        !checkBoxBankAssociated.isChecked())) {
            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            long id = preferences.getLong(getString(R.string.last_account_id_key), 1000);
            Database db = new Database(getApplicationContext());
            Intent intent = getIntent();
            Usuario usuario = (Usuario) intent.getSerializableExtra(DatabaseVariables.USUARIO_TABLE);
            long result = -1;
            if(checkBoxBankAssociated.isChecked()) {
                result = db.insertConta(
                        ++id,
                        editTextNome.getText().toString(),
                        editTextAssociatedBank.getText().toString(),
                        Double.parseDouble(editTextAmount.getText().toString()),
                        usuario.getEmail()
                );
            } else {
                result = db.insertConta(
                        ++id,
                        editTextNome.getText().toString(),
                        Double.parseDouble(editTextAmount.getText().toString()),
                        usuario.getEmail()
                );
            }
            if(result != -1) {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.account_creation_success,
                        Toast.LENGTH_SHORT
                ).show();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong(getString(R.string.last_account_id_key), id);
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
                    R.string.empty_field_error,
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    public void voltar(View view) {
        finish();
    }

    public void contaAssociada(View view) {
        CheckBox checkBoxBankAssociated = findViewById(R.id.checkboxBankAssociated);
        EditText editTextAssociatedBank = findViewById(R.id.associatedBankEditText);
        editTextAssociatedBank.setEnabled(checkBoxBankAssociated.isChecked());
    }
}