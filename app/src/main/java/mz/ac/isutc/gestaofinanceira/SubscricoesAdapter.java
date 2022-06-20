package mz.ac.isutc.gestaofinanceira;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SubscricoesAdapter extends ArrayAdapter<Subscricao> {

    public SubscricoesAdapter(Context context, ArrayList<Subscricao> subscricoes) {
        super(context, 0, subscricoes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if(currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.subscricao,
                    parent, false);
        }

        TextView textViewNome = currentItemView.findViewById(R.id.subscricaoViewName);
        TextView textViewPeriodicidade = currentItemView.findViewById(R.id.subscricaoViewPeriodicidade);

        textViewNome.setText(getItem(position).getNome());
        textViewPeriodicidade.setText(getItem(position).getPeriodicidade());

        currentItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SingleSubscricao.class);
                intent.putExtra(DatabaseVariables.SUBSCRICAO_TABLE, getItem(position));
                getContext().startActivity(intent);
            }
        });

        return currentItemView;
    }

}
