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


public class PassivosFragment extends Fragment {

    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;


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
        types.add("Servicos");
        types.add("Lazer");
        types.add("Alimentacao");
        types.add("Emprestimo");
        types.add("outros");
        move move ;
        double soma;
        String type, type2;

        for(int i = 0;i< types.size();i++){
            soma = 0;
            type = (String) types.get(i);
            for(int j =0; j< data.size();j++){
                move = (move) data.get(j);
                type2 = (String) move.getType();
                if(type.equals(type2)){
                    soma += move.getValor();
                }
            }
            barEntriesArrayList.add(new BarEntry(i,(float) soma));

        }

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(false);
        xAxis.setTextSize(19f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(6);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(types));


        Legend l = barChart.getLegend();
        l.setXOffset(20f);
        l.setStackSpace(5f);
        l.setXEntrySpace(50f);


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

        ArrayList moves = new ArrayList();
        moves.add(new move(124,"Servicos"));
        moves.add(new move(124,"Lazer"));
        moves.add(new move(124,"Servicos"));
        moves.add(new move(124,"Alimentacao"));
        moves.add(new move(124,"Outros"));
        moves.add(new move(124,"Outros"));
        moves.add(new move(124,"Outros"));
        moves.add(new move(124,"Alimentacao"));
        moves.add(new move(124,"Alimentacao"));
        moves.add(new move(124,"Servicos"));
        ;
        return moves;

    }
}