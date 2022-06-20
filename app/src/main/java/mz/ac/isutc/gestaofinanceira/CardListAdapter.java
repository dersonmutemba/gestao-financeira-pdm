package mz.ac.isutc.gestaofinanceira;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class CardListAdapter extends FragmentPagerAdapter {

    private Context context;
    private ArrayList<ContaView> contas;

    private long baseId = 0;

    public CardListAdapter(Context context, @NonNull FragmentManager fm, ArrayList<ContaView> contas) {
        super(fm);
        this.context = context;
        this.contas = contas;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        for(int i = 0; i < contas.size(); i++) {
            if(contas.size() - position - 1 == i)
                return contas.get(i);
        }
        return new AdicionarConta();
    }

    @Override
    public int getCount() {
        return contas.size() + 1;
    }
}
