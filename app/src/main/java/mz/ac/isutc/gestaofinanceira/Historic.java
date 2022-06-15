package mz.ac.isutc.gestaofinanceira;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Historic extends AppCompatActivity {
    RecyclerAdapter adapter;
    RecyclerView recyclerView;
    ClickListiner listiner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        //Adicionar toolbar se tiver time

        List<Transaction> list = new ArrayList<Transaction>();
        list = getData();

        recyclerView = findViewById(R.id.recyclerview);

        listiner = new ClickListiner() {
            @Override
            public void click(int index){
                Toast.makeText(Historic.this, "clicked item index is "+index, Toast.LENGTH_LONG).show();
            }
        };
        adapter = new RecyclerAdapter(list, getApplication(),listiner);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Historic.this));
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    private List<Transaction> getData() {
        List<Transaction> list = new ArrayList<>();
        list.add(new Transaction("Pagamento Mensalidade",getTodaysDate(), 82001, "Debito",10950));
        list.add(new Transaction("Pagamento Wifi",getTodaysDate(), 80001, "Debito",5000));
        list.add(new Transaction("Pagamento Água",getTodaysDate(), 5000, "Debito",2000));
        list.add(new Transaction("Salario ",getTodaysDate(), 82001, "Credito",200000));
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