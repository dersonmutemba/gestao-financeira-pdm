package mz.ac.isutc.gestaofinanceira;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


public class PassivosFragment extends Fragment {

    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;

    private static final String ARG_USUARIO = "usuario";

    private Usuario usuario;

    public static PassivosFragment newInstance(Usuario usuario) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_USUARIO, usuario);
        PassivosFragment fragment = new PassivosFragment();
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

        return inflater.inflate(R.layout.fragment_activos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // initializing variable for bar chart.
        barChart = getView().findViewById(R.id.idBarChart);

        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Data analysis");

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
        types.add("Venda");
        types.add("Empréstimo");
        types.add("Salário");
        Movimento move ;
        double soma;
        String type, type2;

        for(int i = 0;i< types.size();i++){
            soma = 0;
            type = (String) types.get(i);
            for(int j =0; j< data.size();j++){
                move = (Movimento) data.get(j);
                Database database = new Database(getContext());
                Entidade entidade = database.getEntidade(move.getEntidade());
                if(entidade != null) {
                    type2 = (String) entidade.getCategoria();
                    if(type.equals(type2)){
                        soma += move.getValor();
                    }
                }
            }
            barEntriesArrayList.add(new BarEntry(i,(float) soma));

        }

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(6);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(types));


        Legend l = barChart.getLegend();
        l.setXOffset(20f);
        l.setStackSpace(5f);
        l.setXEntrySpace(50f);
//        barEntriesArrayList.add(new BarEntry(1f, 4));
//        barEntriesArrayList.add(new BarEntry(2f, 6));
//        barEntriesArrayList.add(new BarEntry(3f, 8));
//        barEntriesArrayList.add(new BarEntry(4f, 2));
//        barEntriesArrayList.add(new BarEntry(5f, 4));
//        barEntriesArrayList.add(new BarEntry(6f, 1));

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