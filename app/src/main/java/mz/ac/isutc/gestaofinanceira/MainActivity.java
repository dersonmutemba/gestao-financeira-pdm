package mz.ac.isutc.gestaofinanceira;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    Inicio inicio;
    Historico historico;
    Menu menu;
    Estatisticas estatisticas;
    Definicoes definicoes;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        usuario = (Usuario) intent.getSerializableExtra(DatabaseVariables.USUARIO_TABLE);

        inicio = Inicio.newInstance(usuario);
        historico = new Historico();
        menu = new Menu();
        estatisticas = new Estatisticas();
        definicoes = new Definicoes();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_page1) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, inicio).commit();
                return true;
            }
            if (item.getItemId() == R.id.nav_page2) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, historico).commit();
                return true;
            }
            if (item.getItemId() == R.id.nav_page3) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, menu).commit();
                return true;
            }
            if (item.getItemId() == R.id.nav_page4) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, estatisticas).commit();
                return true;
            }
            if (item.getItemId() == R.id.nav_page5) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, definicoes).commit();
                return true;
            }
            return false;
        });
        bottomNavigationView.setSelectedItemId(R.id.nav_page1);
    }

    public void goToCriarConta(View view) {
        Intent intent = new Intent(getApplicationContext(), CriarConta.class);
        startActivity(intent);
    }
}