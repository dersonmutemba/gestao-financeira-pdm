package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import mz.ac.isutc.gestaofinanceira.R;

public class Subscricoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscricoes);
    }

    public void voltar(View view) {
        finish();
    }
}