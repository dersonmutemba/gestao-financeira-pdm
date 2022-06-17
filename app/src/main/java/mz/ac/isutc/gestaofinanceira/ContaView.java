package mz.ac.isutc.gestaofinanceira;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContaView extends Fragment {

    private static final String PARAM_ACCOUNT = "account";

    private Conta conta;

    public ContaView() {
        // Required empty public constructor
    }

    public static ContaView newInstance(Conta conta) {
        ContaView fragment = new ContaView();
        Bundle args = new Bundle();
        args.putSerializable(PARAM_ACCOUNT, conta);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            conta = (Conta) getArguments().getSerializable(PARAM_ACCOUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conta_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        TextView textViewAssociatedBank = getView().findViewById(R.id.associatedBank);
        TextView textViewAccountName = getView().findViewById(R.id.accountName);
        TextView textViewAccountAmount = getView().findViewById(R.id.accountAmount);
        textViewAssociatedBank.setText(conta.getAssociatedBank());
        textViewAccountName.setText(conta.getAccountName());
        textViewAccountAmount.setText(conta.getAccountAmount() + " MT");
    }
}