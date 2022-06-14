package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void registar(View view) {
        EditText editTextUsername = findViewById(R.id.usernameEditTextSignUp);
        EditText editTextEmail = findViewById(R.id.emailEditTextSignUp);
        EditText editTextPassword = findViewById(R.id.passwordEditTextSignUp);
        EditText editTextPasswordConfirmation = findViewById(R.id.passwordConfirmationEditTextSignUp);

        if(!editTextUsername.getText().toString().equals("") &&
                !editTextEmail.getText().toString().equals("") &&
                !editTextPassword.getText().toString().equals("") &&
                !editTextPasswordConfirmation.getText().toString().equals("")) {
            if(editTextPassword.getText().toString().equals(
                    editTextPasswordConfirmation.getText().toString())) {
                Database db = new Database(getApplicationContext());
                Cursor cursor = db.getUsuario(new String[]{editTextEmail.getText().toString()});
                if(cursor.getCount() == 0) {
                    long result = db.insertUsuario(
                            editTextEmail.getText().toString(),
                            editTextUsername.getText().toString(),
                            editTextPassword.getText().toString()
                    );
                    if(result != -1) {
                        editTextEmail.setText("");
                        editTextPassword.setText("");
                        editTextPasswordConfirmation.setText("");
                        editTextUsername.setText("");
                        Toast.makeText(
                                getApplicationContext(),
                                "Usuário inserido com sucesso!",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Email já foi usado, insira outro!",
                            Toast.LENGTH_LONG
                    ).show();
                }
                db.close();
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        "Senhas diferentes",
                        Toast.LENGTH_LONG
                ).show();
            }
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Preencha todos os campos",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void voltar(View view) {
        finish();
    }
}