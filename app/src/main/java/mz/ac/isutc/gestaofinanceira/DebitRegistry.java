package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class DebitRegistry extends AppCompatActivity {

    Spinner spinnerAccount, spinnerEntity;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit_registry);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra(DatabaseVariables.USUARIO_TABLE);

        spinnerAccount = findViewById(R.id.debitSpinnerAccount);
        spinnerEntity = findViewById(R.id.debitSpinnerEntity);
        Spinner spinnerCategory = findViewById(R.id.debitSpinnerCategory);

        Database db = new Database(getApplicationContext());
        ArrayList<Conta> contas = db.getContasArrayList(new String[]{usuario.getEmail()});
        String[] contasNames = new String[contas.size() + 1];
        String[] entidadesNames = new String[1];
        String[] resourcesCategorias = getResources().getStringArray(R.array.debit_categories);
        String[] categoriasNames = new String[resourcesCategorias.length + 1];
        categoriasNames[0] = getString(R.string.category_spinner_tooltip);
        for(int i = 1; i < categoriasNames.length; i++) {
            categoriasNames[i] = resourcesCategorias[i - 1];
        }
        contasNames[0] = getString(R.string.account_spinner_tooltip);
        entidadesNames[0] = getString(R.string.entity_spinner_tooltip);

        for(int i = 1; i < contasNames.length; i++)
            contasNames[i] = contas.get(i - 1).getAccountName();

        ArrayAdapter<String> adapterContas = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, contasNames);
        adapterContas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAccount.setAdapter(adapterContas);

        ArrayAdapter<String> adapterEntidades = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, entidadesNames);
        adapterEntidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEntity.setAdapter(adapterEntidades);

        ArrayAdapter<String> adapterCategorias = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categoriasNames);
        adapterCategorias.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategorias);
    }

    public void voltar(View view) {
        finish();
    }

    public void registar(View view) {

    }
}