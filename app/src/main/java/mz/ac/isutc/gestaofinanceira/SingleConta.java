package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import mz.ac.isutc.gestaofinanceira.Conta;
import mz.ac.isutc.gestaofinanceira.Database;
import mz.ac.isutc.gestaofinanceira.DatabaseVariables;
import mz.ac.isutc.gestaofinanceira.R;

public class SingleConta extends AppCompatActivity {

    Conta conta;

    EditText editTextNome, editTextSaldo, editTextBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_conta);

        Intent intent = getIntent();
        conta = (Conta) intent.getSerializableExtra(DatabaseVariables.CONTA_TABLE);

        editTextNome = findViewById(R.id.accountNameEditText1);
        editTextSaldo = findViewById(R.id.accountAmountEditText1);
        editTextBanco = findViewById(R.id.associatedBankEditText1);

        editTextBanco.setEnabled(conta.getAssociatedBank() != null);
        editTextNome.setText(conta.getAccountName());
        editTextSaldo.setText(conta.getAccountAmount() + "");
        editTextBanco.setText(conta.getAssociatedBank());
    }

    public void voltar(View view) {
        finish();
    }

    public void remover(View view) {
        Database database = new Database(getApplicationContext());
        long result = database.removeConta(conta.getId());
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

    public void guardar(View view) {
        if(!editTextNome.getText().toString().equals("") && !editTextSaldo.getText().toString().equals("") && (
                !editTextBanco.isEnabled() || (editTextBanco.isEnabled() && !editTextBanco.getText().toString().equals(""))
                )) {
            Database database = new Database(getApplicationContext());
            long result = -1;
            if(editTextBanco.isEnabled())
                result = database.updateConta(
                        conta.getId(),
                        editTextNome.getText().toString(),
                        editTextBanco.getText().toString(),
                        Double.parseDouble(editTextSaldo.getText().toString())
                );
            else
                result = database.updateConta(
                        conta.getId(),
                        editTextNome.getText().toString(),
                        Double.parseDouble(editTextSaldo.getText().toString())
                );
            if(result != -1) {
                Toast.makeText(
                        getApplicationContext(),
                        getString(R.string.successful_update),
                        Toast.LENGTH_SHORT
                ).show();
                database.close();
                finish();
            }
            database.close();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    getString(R.string.empty_field_error),
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}