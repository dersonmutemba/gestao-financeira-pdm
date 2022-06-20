package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import mz.ac.isutc.gestaofinanceira.R;

public class Subscricoes extends AppCompatActivity {

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscricoes);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra(DatabaseVariables.USUARIO_TABLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Database database = new Database(getApplicationContext());
        ArrayList<Subscricao> subscricoesFullList = database.getSubscricoesArrayList();
        ArrayList<Subscricao> subscricoes = new ArrayList<>();
        for(Subscricao subscricao : subscricoesFullList) {
            Entidade entidade = database.getEntidade(subscricao.getEntidade());
            if(entidade != null && entidade.getUsuario().equals(usuario.getEmail()))
                subscricoes.add(subscricao);
        }

        SubscricoesAdapter adapter = new SubscricoesAdapter(Subscricoes.this, subscricoes);
        ListView listView = findViewById(R.id.listViewSubscricao);
        listView.setAdapter(adapter);
        database.close();
    }

    public void voltar(View view) {
        finish();
    }

    public void goToCriarSubscricao(View view) {
        Intent intent = new Intent(Subscricoes.this, CriarSubscricao.class);
        intent.putExtra(DatabaseVariables.USUARIO_TABLE, usuario);
        startActivity(intent);
    }
}