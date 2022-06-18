package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Perfil extends AppCompatActivity {

    EditText oldPass, newPass;
    EditText firstName, lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Database db = new Database(getApplicationContext());
        Cursor cursor = db.getUsuario(new String[]{});

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firstName = findViewById(R.id.editMudarNome);
        lastName = findViewById(R.id.editMudarApelido);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            firstName.setText(Usuario);
        }

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
}