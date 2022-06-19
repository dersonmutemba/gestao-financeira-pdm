package mz.ac.isutc.gestaofinanceira;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class DebitRegistry extends AppCompatActivity {

    Spinner spinnerAccount, spinnerEntity;
    EditText editTextAmount;

    Usuario usuario;
    long[] contasID, entidadesID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit_registry);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra(DatabaseVariables.USUARIO_TABLE);

        spinnerAccount = findViewById(R.id.debitSpinnerAccount);
        spinnerEntity = findViewById(R.id.debitSpinnerEntity);
        Spinner spinnerCategory = findViewById(R.id.debitSpinnerCategory);
        editTextAmount = findViewById(R.id.debitEditTextAmount);

        Database db = new Database(getApplicationContext());
        ArrayList<Conta> contas = db.getContasArrayList(new String[]{usuario.getEmail()});
        ArrayList<Entidade> entidades = db.getEntidadesByUsuarioArrayList(new String[]{usuario.getEmail()});
        String[] contasNames = new String[contas.size() + 1];
        contasID = new long[contas.size()];
        String[] entidadesNames = new String[entidades.size() + 1];
        entidadesID = new long[entidades.size()];
        String[] resourcesCategorias = getResources().getStringArray(R.array.debit_categories);
        String[] categoriasNames = new String[resourcesCategorias.length + 1];
        categoriasNames[0] = getString(R.string.category_spinner_tooltip);
        for(int i = 1; i < categoriasNames.length; i++) {
            categoriasNames[i] = resourcesCategorias[i - 1];
        }
        contasNames[0] = getString(R.string.account_spinner_tooltip);
        entidadesNames[0] = getString(R.string.entity_spinner_tooltip);

        for(int i = 1; i < contasNames.length; i++) {
            contasNames[i] = contas.get(i - 1).getAccountName();
            contasID[i - 1] = contas.get(i - 1).getId();
        }

        for(int i = 1; i < entidadesNames.length; i++) {
            entidadesNames[i] = entidades.get(i - 1).getNome();
            entidadesID[i - 1] = entidades.get(i - 1).getId();
        }

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
                    entidadesID[i - 1] = entidades1.get(i - 1).getId();
                }
                Context wrapper = new ContextThemeWrapper(DebitRegistry.this, R.style.Theme_GestaoFinanceira);
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
        if(spinnerEntity.getSelectedItemPosition() != 0 &&
                spinnerAccount.getSelectedItemPosition() != 0 &&
                !editTextAmount.getText().toString().equals("")) {
            Database database = new Database(getApplicationContext());
            long id = getLastMovimentoID();
            Calendar calendar = Calendar.getInstance();
            long result = database.insertMovimento(
                    ++id,
                    getString(R.string.transaction_debit),
                    Double.parseDouble(editTextAmount.getText().toString()),
                    calendar.toString(),
                    getString(R.string.transaction_debit) + " - " + spinnerEntity.getSelectedItem().toString(),
                    calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE),
                    entidadesID[spinnerEntity.getSelectedItemPosition() - 1]
            );
            if(result != -1) {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.movement_creation_success,
                        Toast.LENGTH_SHORT
                ).show();
                writeLastMovimentoID(id);
                Conta conta = database.getObjectConta(contasID[spinnerAccount.getSelectedItemPosition() - 1]);
                conta.setAccountAmount(conta.getAccountAmount() -
                        Double.parseDouble(editTextAmount.getText().toString()));
                database.updateConta(conta.getId(), conta.getAccountName(), conta.getAssociatedBank(), conta.getAccountAmount());
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

    public long getLastMovimentoID () {
        File file = getFileStreamPath(getString(R.string.last_movement_id_key));
        if(file.exists()) {
            try{
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                long id = objectInputStream.readLong();
                objectInputStream.close();
                fileInputStream.close();
                return id;
            }
            catch (Exception e) {
                Log.e("IO Error", Log.getStackTraceString(e.fillInStackTrace()));
            }
        }
        return 1000;
    }

    public void writeLastMovimentoID (long id) {
        File file = getFileStreamPath(getString(R.string.last_movement_id_key));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeLong(id);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (Exception e) {
            Log.e("IO Error", Log.getStackTraceString(e.fillInStackTrace()));
        }
    }
}