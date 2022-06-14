package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Stack;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void entrar(View view) {
        EditText editTextUsername = findViewById(R.id.usernameEditTextLogin);
        EditText editTextPassword = findViewById(R.id.passwordEditTextLogin);

        SharedPreferences preferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        String username = preferences.getString("Username", "/");
        Log.i("Info", username);


    }
}