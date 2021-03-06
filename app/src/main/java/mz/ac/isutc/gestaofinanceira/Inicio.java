package mz.ac.isutc.gestaofinanceira;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Inicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Inicio extends Fragment {

    private static final String ARG_USUARIO = "usuario";

    private Usuario usuario;

    private ViewPager viewPagerCards;

    public Inicio() {
        // Required empty public constructor
    }

    public static Inicio newInstance(Usuario usuario) {
        Inicio fragment = new Inicio();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USUARIO, usuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            usuario = (Usuario) getArguments().getSerializable(ARG_USUARIO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPagerCards = getView().findViewById(R.id.viewPagerCards);
        ArrayList<ContaView> fragments = new ArrayList<ContaView>();
        Database db = new Database(getContext());
        ArrayList<Conta> contas = db.getContasArrayList(new String[] {usuario.getEmail()});
        for(Conta conta : contas) {
            fragments.add(ContaView.newInstance(conta));
        }

        final CardListAdapter adapter = new CardListAdapter(getContext(), getChildFragmentManager(), fragments);
        viewPagerCards.setAdapter(adapter);

        ExtendedFloatingActionButton fab = getView().findViewById(R.id.inicioFAB);
        fab.setExtended(false);
        TextView textViewName = getView().findViewById(R.id.textNameInicio);
        TextView textViewDate = getView().findViewById(R.id.textDateInicio);
        textViewName.setText("Ol??, " + usuario.getNome() + "!");
        Calendar date = Calendar.getInstance();
        String day = date.get(Calendar.DATE) + "";
        String month = (date.get(Calendar.MONTH) + 1) + "";
        String year = date.get(Calendar.YEAR) + "";
        textViewDate.setText((day.length() == 1 ?
                "0" + day : day) + "/" + ((month.length() == 1) ?
                "0" + month : month) + "/" + year);
    }

    @Override
    public void onStart() {
        super.onStart();

        ArrayList<ContaView> fragments = new ArrayList<>();
        final CardListAdapter adapter = new CardListAdapter(getContext(), getChildFragmentManager(), fragments);
        viewPagerCards.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<ContaView> fragments = new ArrayList<>();
        Database db = new Database(getContext());
        ArrayList<Conta> contas = db.getContasArrayList(new String[] {usuario.getEmail()});
        for(Conta conta : contas) {
            fragments.add(ContaView.newInstance(conta));
        }
        final CardListAdapter adapter = new CardListAdapter(getContext(), getChildFragmentManager(), fragments);
        adapter.notifyDataSetChanged();
        viewPagerCards.setAdapter(adapter);
    }
}