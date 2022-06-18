package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

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
        intent.putExtra(DatabaseVariables.USUARIO_TABLE, usuario);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void showMenu(View view) {
        ExtendedFloatingActionButton fab = (ExtendedFloatingActionButton) view;
        if(fab.isExtended())
            fab.shrink();
        else
            fab.extend();
        Context wrapper = new ContextThemeWrapper(this, R.style.Theme_GestaoFinanceira);
        PopupMenu popupMenu = new PopupMenu(wrapper, view);
        popupMenu.inflate(R.menu.transaction_menu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true);
        }
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                fab.shrink();
            }
        });
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.transaction_credit) {
                    Intent intent = new Intent(getApplicationContext(), CreditRegistry.class);
                    intent.putExtra(DatabaseVariables.USUARIO_TABLE, usuario);
                    startActivity(intent);
                    return true;
                } else if(item.getItemId() == R.id.transaction_debit) {
                    Intent intent = new Intent(getApplicationContext(), DebitRegistry.class);
                    intent.putExtra(DatabaseVariables.USUARIO_TABLE, usuario);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    public void entidades(View view) {
        Intent intent = new Intent(MainActivity.this, Entidades.class);
        intent.putExtra(DatabaseVariables.USUARIO_TABLE, usuario);
        startActivity(intent);
    }

    public void subscricoes(View view) {
        Intent intent = new Intent(MainActivity.this, Subscricoes.class);
        intent.putExtra(DatabaseVariables.USUARIO_TABLE, usuario);
        startActivity(intent);
    }

    public void contas(View view) {
        Intent intent = new Intent(MainActivity.this, Contas.class);
        intent.putExtra(DatabaseVariables.USUARIO_TABLE, usuario);
        startActivity(intent);
    }
}