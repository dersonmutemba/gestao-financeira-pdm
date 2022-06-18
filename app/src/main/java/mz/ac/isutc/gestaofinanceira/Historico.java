package mz.ac.isutc.gestaofinanceira;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class Historico extends Fragment {

    RecyclerAdapter adapter;
    RecyclerView recyclerView;
    ClickListiner listiner;
    EditText search;

    List<Transaction> list = new ArrayList<Transaction>();
    List<Transaction> filteredlist = new ArrayList<Transaction>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        for (Transaction transaction : list) {
            if (transaction.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(transaction);
            }
        }
        adapter.filteredList(filteredlist);

    }

    private List<Transaction> getData() {
        List<Transaction> list = new ArrayList<>();
        list.add(new Transaction("Pagamento Mensalidade",getTodaysDate(), 82001, "Debito",10950));
        list.add(new Transaction("Pagamento Wifi",getTodaysDate(), 80001, "Debito",5000));
        list.add(new Transaction("Pagamento Água",getTodaysDate(), 5000, "Debito",2000));
        list.add(new Transaction("Pagamento Wallets",getTodaysDate(), 80001, "Debito",000));
        list.add(new Transaction("Pagamento Waves",getTodaysDate(), 8001, "Credito",5000));
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