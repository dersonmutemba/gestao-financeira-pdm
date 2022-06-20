package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void entrar(View view) {
        EditText editTextEmail = findViewById(R.id.emailEditTextLogin);
        EditText editTextPassword = findViewById(R.id.passwordEditTextLogin);
        if(!editTextEmail.getText().toString().equals("") &&
                !editTextPassword.getText().toString().equals("")) {
            Database db = new Database(getApplicationContext());
            Cursor cursor = db.getUsuario(new String[]{editTextEmail.getText().toString()});
            if(cursor.getCount() != 0) {
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    String password = cursor.getString(2);
                    if(editTextPassword.getText().toString().equals(password)) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
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
                            R.string.wrong_email_or_password_error,
                            Toast.LENGTH_LONG
                    ).show();
                }
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.wrong_email_or_password_error,
                        Toast.LENGTH_LONG
                ).show();
            }
            db.close();
            cursor.close();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_field_error,
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void goToRegistar(View view) {
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
    }
}