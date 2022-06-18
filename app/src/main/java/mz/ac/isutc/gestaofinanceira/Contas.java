package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class Contas extends AppCompatActivity {

    Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contas);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra(DatabaseVariables.USUARIO_TABLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Database database = new Database(getApplicationContext());
        ArrayList<Conta> contas = database.getContasArrayList(new String[] {usuario.getEmail()});
        ContaAdapter adapter = new ContaAdapter(Contas.this, contas);
        ListView listView = findViewById(R.id.listViewConta);
        listView.setAdapter(adapter);
        database.close();
    }

    public void voltar(View view) {
        finish();
    }

    public void goToCriarConta(View view) {
        Intent intent = new Intent(getApplicationContext(), CriarConta.class);
        intent.putExtra(DatabaseVariables.USUARIO_TABLE, usuario);
        startActivity(intent);
    }
}