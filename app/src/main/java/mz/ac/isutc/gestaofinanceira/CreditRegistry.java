package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class CreditRegistry extends AppCompatActivity {

    Spinner spinnerAccount, spinnerEntity;
    EditText editTextAmount;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_registry);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra(DatabaseVariables.USUARIO_TABLE);

        spinnerAccount = findViewById(R.id.creditSpinnerAccount);
        spinnerEntity = findViewById(R.id.creditSpinnerEntity);
        Spinner spinnerCategory = findViewById(R.id.creditSpinnerCategory);

        Database db = new Database(getApplicationContext());
        ArrayList<Conta> contas = db.getContasArrayList(new String[]{usuario.getEmail()});
        ArrayList<Entidade> entidades = db.getEntidadesByUsuarioArrayList(new String[]{usuario.getEmail()});
        String[] contasNames = new String[contas.size() + 1];
        String[] entidadesNames = new String[entidades.size() + 1];
        String[] resourcesCategorias = getResources().getStringArray(R.array.credit_categories);
        String[] categoriasNames = new String[resourcesCategorias.length + 1];
        categoriasNames[0] = getString(R.string.category_spinner_tooltip);
        for(int i = 1; i < categoriasNames.length; i++) {
            categoriasNames[i] = resourcesCategorias[i - 1];
        }
        contasNames[0] = getString(R.string.account_spinner_tooltip);
        entidadesNames[0] = getString(R.string.entity_spinner_tooltip);

        for(int i = 1; i < contasNames.length; i++)
            contasNames[i] = contas.get(i - 1).getAccountName();

        for(int i = 1; i < entidadesNames.length; i++)
            entidadesNames[i] = entidades.get(i - 1).getNome();

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
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<Entidade> entidades1 = new ArrayList<>();
                for(Entidade entidade : entidades){
                    if(entidade.getCategoria().equals(spinnerCategory.getSelectedItem().toString()))
                        entidades1.add(entidade);
                }
                String[] entidadesNames1 = new String[entidades1.size() + 1];
                entidadesNames1[0] = getString(R.string.entity_spinner_tooltip);
                for(int i = 1; i < entidadesNames1.length; i++) {
                    entidadesNames1[i] = entidades1.get(i - 1).getNome();
                }
                Context wrapper = new ContextThemeWrapper(CreditRegistry.this, R.style.Theme_GestaoFinanceira);
                ArrayAdapter<String> adapterEntidades = new ArrayAdapter<>(wrapper,
                        android.R.layout.simple_spinner_item, entidadesNames1);
                adapterEntidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerEntity.setAdapter(adapterEntidades);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        db.close();
    }

    public void voltar(View view) {
        finish();
    }

    public void registar(View view) {
    }
}