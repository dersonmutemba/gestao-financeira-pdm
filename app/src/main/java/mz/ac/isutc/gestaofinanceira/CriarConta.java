package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

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