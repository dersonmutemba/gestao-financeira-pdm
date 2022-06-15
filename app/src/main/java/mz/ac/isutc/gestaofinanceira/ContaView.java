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

    private static final String ACCOUNT_NAME = "accountName";
    private static final String ACCOUNT_AMOUNT = "accountAmount";
    private static final String ASSOCIATED_BANK = "associatedBank";

    private String accountName;
    private double accountAmount;
    private String associatedBank;

    public ContaView() {
        // Required empty public constructor
    }

    public static ContaView newInstance(String accountName, double accountAmount, String associatedBank) {
        ContaView fragment = new ContaView();
        Bundle args = new Bundle();
        args.putString(ACCOUNT_NAME, accountName);
        args.putDouble(ACCOUNT_AMOUNT, accountAmount);
        args.putString(ASSOCIATED_BANK, associatedBank);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            accountName = getArguments().getString(ACCOUNT_NAME);
            accountAmount = getArguments().getDouble(ACCOUNT_AMOUNT);
            associatedBank = getArguments().getString(ASSOCIATED_BANK);
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
        TextView textViewAssociatedBank = getView().findViewById(R.id.associatedBank);
        TextView textViewAccountName = getView().findViewById(R.id.accountName);
        TextView textViewAccountAmount = getView().findViewById(R.id.accountAmount);
        textViewAssociatedBank.setText(associatedBank);
        textViewAccountName.setText(accountName);
        textViewAccountAmount.setText(accountAmount + " MT");
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}