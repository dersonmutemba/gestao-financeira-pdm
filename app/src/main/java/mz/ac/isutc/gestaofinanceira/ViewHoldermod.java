package mz.ac.isutc.gestaofinanceira;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ViewHoldermod extends RecyclerView.ViewHolder {
    TextView textViewTitle;
    TextView textViewDate;
    TextView textViewEntity;
    TextView textViewTransaction;
    TextView textViewAmount;
    View view;

    ViewHoldermod(View itemView) {
        super(itemView);
        textViewTitle = (TextView) itemView.findViewById(R.id.titleId);
        textViewDate = (TextView) itemView.findViewById(R.id.dateText);
        textViewEntity = (TextView) itemView.findViewById(R.id.entityText);
        textViewTransaction = (TextView) itemView.findViewById(R.id.transactionText);
        textViewAmount = (TextView) itemView.findViewById(R.id.amountText);
        view = itemView;
    }
}
