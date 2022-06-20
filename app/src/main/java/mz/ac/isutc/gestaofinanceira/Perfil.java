package mz.ac.isutc.gestaofinanceira;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Perfil extends AppCompatActivity {
    Usuario usuario;
    EditText oldPass, newPass;
    EditText firstName;
    Button btnGuardarDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra(DatabaseVariables.USUARIO_TABLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Database db = new Database(getApplicationContext());
        Cursor cursor = db.getUsuario(new String[]{usuario.getEmail()});

        setContentView(R.layout.activity_perfil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstName = findViewById(R.id.editMudarNome);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            firstName.setText(usuario.getNome());
        }

        btnGuardarDados = findViewById(R.id.btLimparDados);
        btnGuardarDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vericaSenha();
                atualizaNome(firstName.getText().toString());
            }
        });
    }

    private void vericaSenha(){
        Database db = new Database(getApplicationContext());
        Cursor cursor = db.getUsuario(new String[]{oldPass.getText().toString()});

        oldPass = findViewById(R.id.editSenhaAntiga);
        newPass = findViewById(R.id.editSenhaNova);

        if(oldPass.getText().toString().equals("")  & !newPass.getText().toString().equals("") ){
            Toast.makeText(
                    getApplicationContext(),
                    "Insira a senha actual",
                    Toast.LENGTH_LONG
            ).show();
        }
        if(cursor.getCount() != 0) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                String password = cursor.getString(2);
                if(oldPass.getText().toString().equals(password)) {
                    Intent intent = new Intent(Perfil.this, MainActivity.class); //Neste caso minha main activity seriam as definicoes
                    intent.putExtra(DatabaseVariables.USUARIO_TABLE,
                            new Usuario(cursor.getString(0),
                                    cursor.getString(1),
                                    cursor.getString(2)));
                    startActivity(intent);
                    db.close();
                    cursor.close();
                    return;
                }
                Toast.makeText(
                        getApplicationContext(),
                        "Senha incorreta",
                        Toast.LENGTH_LONG
                ).show();
            }
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Senha errada",
                    Toast.LENGTH_LONG
            ).show();
        }
        db.close();
        cursor.close();
    }

    private String atualizaNome(String newNome){

        newNome = firstName.getText().toString();

        return newNome;
    }
}