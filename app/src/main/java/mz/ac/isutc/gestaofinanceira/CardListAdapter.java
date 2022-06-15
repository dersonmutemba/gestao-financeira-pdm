package mz.ac.isutc.gestaofinanceira;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CardListAdapter extends FragmentPagerAdapter {

    private Context context;
    private int totalTabs;

    public CardListAdapter(Context context, @NonNull FragmentManager fm, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AdicionarConta();
            case 1:
                return ContaView.newInstance("Conta Geral", 0, "");
            case 2:
                return ContaView.newInstance("Salário", 1000, "Millenium BIM");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
