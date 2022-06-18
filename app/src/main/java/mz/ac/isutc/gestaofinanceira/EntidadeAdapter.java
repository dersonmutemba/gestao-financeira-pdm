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

public class EntidadeAdapter extends ArrayAdapter<Entidade> {

    public EntidadeAdapter(Context context, ArrayList<Entidade> entidades) {
        super(context, 0, entidades);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if(currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.entidade,
                    parent, false);
        }

        TextView textViewNome = currentItemView.findViewById(R.id.entidadeViewName);
        TextView textViewCategoria = currentItemView.findViewById(R.id.entidadeViewCategoria);

        textViewNome.setText(getItem(position).getNome());
        textViewCategoria.setText(getItem(position).getCategoria());

        currentItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SingleEntidade.class);
                intent.putExtra(DatabaseVariables.ENTIDADE_TABLE, getItem(position));
                getContext().startActivity(intent);
            }
        });

        return currentItemView;
    }

}
