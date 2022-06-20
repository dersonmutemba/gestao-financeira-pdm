package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class SingleSubscricao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_subscricao);
    }

    public void voltar(View view) {
        finish();
    }

    public void remover(View view) {
        finish();
    }

    public void guardar(View view) {
        finish();
    }
}