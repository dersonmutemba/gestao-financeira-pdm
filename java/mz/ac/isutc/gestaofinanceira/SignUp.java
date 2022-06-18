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
                        Toast.makeText(
                                getApplicationContext(),
                                R.string.user_insertion_success,
                                Toast.LENGTH_SHORT
                        ).show();
                        cursor.close();
                        db.close();
                        finish();
                    }
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.email_already_used_error,
                            Toast.LENGTH_LONG
                    ).show();
                }
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        R.string.different_passwords_error,
                        Toast.LENGTH_LONG
                ).show();
            }
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_field_error,
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    public void voltar(View view) {
        finish();
    }
}