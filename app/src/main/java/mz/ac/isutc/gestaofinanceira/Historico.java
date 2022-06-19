package mz.ac.isutc.gestaofinanceira;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Historico extends Fragment {

    private static final String ARG_USUARIO = "usuario";

    private Usuario usuario;

    RecyclerAdapter adapter;
    RecyclerView recyclerView;
    ClickListiner listiner;
    EditText search;

    List<Movimento> list = new ArrayList<Movimento>();
    List<Movimento> filteredlist = new ArrayList<Movimento>();

    public static Historico newInstance(Usuario usuario) {
        Historico fragment = new Historico();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_historico, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = getData();

        recyclerView = getView().findViewById(R.id.recyclerview);
        recyclerView.setNestedScrollingEnabled(false);

        search = getView().findViewById(R.id.search_field);

        listiner = new ClickListiner() {
            @Override
            public void click(int index){
                Toast.makeText(getContext(), "clicked item index is "+index, Toast.LENGTH_LONG).show();
            }
        };
        adapter = new RecyclerAdapter(list, getContext(),listiner);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filteredlist.clear();
                if(editable.toString().isEmpty()){
                    adapter = new RecyclerAdapter(list, getContext(),listiner);
                    recyclerView.setAdapter(adapter);
                }else{
                    Filter(editable.toString());
                }
            }
        });
    }

    private void Filter(String text) {

        for (Movimento movimento : list) {
            if (movimento.getTitulo().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(movimento);
            }
        }
        adapter.filteredList(filteredlist);

    }

    private List<Movimento> getData() {
        List<Movimento> list = new ArrayList<>();
        Database database = new Database(getContext());
        List<Entidade> entidades = database.getEntidadesByUsuarioArrayList(new String[]{usuario.getEmail()});
        for(Entidade entidade : entidades) {
            ArrayList<Movimento> movimentos = database.getMovimentosByEntidadeArrayList(new String[]{entidade.getId() + ""});
            for(Movimento movimento : movimentos) {
                list.add(movimento);
            }
        }
        return list;
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;// because the months in class Calender begin to zero
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

}