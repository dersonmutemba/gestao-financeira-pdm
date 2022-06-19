package mz.ac.isutc.gestaofinanceira;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class Estatisticas extends Fragment {

    private static final String ARG_USUARIO = "usuario";

    private Usuario usuario;

    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;

    public static Estatisticas newInstance(Usuario usuario) {
        Estatisticas fragment = new Estatisticas();
        Bundle args = new Bundle();
        args.putSerializable(ARG_USUARIO, usuario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            usuario = (Usuario) getArguments().getSerializable(ARG_USUARIO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_estatisticas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initializing variable for bar chart.
        barChart = getView().findViewById(R.id.idBarChart);

        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Data Analysis");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
    }

    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        ArrayList data = getData();
        ArrayList types = new ArrayList();
        types.add("Servicos");
        types.add("Lazer");
        types.add("Alimentacao");
        types.add("Salario");
        types.add("outros");
        Movimento move ;
        double soma;
        String type, type2;

        for(int i = 0;i< types.size();i++){
            soma = 0;
            type = (String) types.get(i);
            for(int j =0; j< data.size();j++){
                move = (Movimento) data.get(j);
                type2 = (String) types.get(i);
                if(type.equals(type2)){
                    soma += move.getValor();
                }
            }
            barEntriesArrayList.add(new BarEntry(i, (float) soma));



        }


        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
//        barEntriesArrayList.add(new BarEntry(1f, 4000));
//        barEntriesArrayList.add(new BarEntry(2f, 600));
//        barEntriesArrayList.add(new BarEntry(3f, 8999));
//        barEntriesArrayList.add(new BarEntry(4f, 290));
//        barEntriesArrayList.add(new BarEntry(5f, 420));
//        barEntriesArrayList.add(new BarEntry(6f, 199));
    }


    private ArrayList getData(){
        ArrayList<Movimento> list = new ArrayList<>();
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

}