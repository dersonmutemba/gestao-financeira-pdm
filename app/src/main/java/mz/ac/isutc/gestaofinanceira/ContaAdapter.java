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

public class ContaAdapter extends ArrayAdapter<Conta> {

    public ContaAdapter(Context context, ArrayList<Conta> contas) {
        super(context, 0, contas);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentItemView = convertView;

        if(currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.conta,
                    parent, false);
        }

        TextView textViewNome = currentItemView.findViewById(R.id.contaViewName);
        TextView textViewBancoAssociado = currentItemView.findViewById(R.id.contaViewAssociatedBank);

        textViewNome.setText(getItem(position).getAccountName());
        textViewBancoAssociado.setText(getItem(position).getAssociatedBank());

        currentItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SingleConta.class);
                intent.putExtra(DatabaseVariables.CONTA_TABLE, getItem(position));
                getContext().startActivity(intent);
            }
        });

        return currentItemView;
    }
}
