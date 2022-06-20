package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class Entidades extends AppCompatActivity {

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entidades);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra(DatabaseVariables.USUARIO_TABLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Database database = new Database(getApplicationContext());
        ArrayList<Entidade> entidades = database.getEntidadesByUsuarioArrayList(new String[] {usuario.getEmail()});

        EntidadeAdapter adapter = new EntidadeAdapter(Entidades.this, entidades);
        ListView listView = findViewById(R.id.listViewEntidade);
        listView.setAdapter(adapter);
        database.close();
    }

    public void voltar(View view) {
        finish();
    }

    public void goToCriarEntidade(View view) {
        Intent intent = new Intent(Entidades.this, CriarEntidade.class);
        intent.putExtra(DatabaseVariables.USUARIO_TABLE, usuario);
        startActivity(intent);
    }
}