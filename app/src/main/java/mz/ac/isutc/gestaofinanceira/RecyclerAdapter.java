package mz.ac.isutc.gestaofinanceira;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class RecyclerAdapter  extends RecyclerView.Adapter<ViewHoldermod> {

    List<Transaction> list = Collections.emptyList();
    List<Transaction> exampleListFull;

    Context context;

    ClickListiner listiner;

    public RecyclerAdapter(List<Transaction> list, Context context, ClickListiner listiner) {
        this.list = list;
        this.exampleListFull = new ArrayList<>(list);
        this.context = context;
        this.listiner = listiner;
    }

    public RecyclerAdapter(Context context, List<Transaction> list) {
    }

    @NonNull
    @Override
    public ViewHoldermod onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        View photoView = inflater.inflate(R.layout.activity_recycle_item, parent, false);
        ViewHoldermod viewHoldermod = new ViewHoldermod(photoView);
        return viewHoldermod;
    }

    @Override
    public void
    onBindViewHolder(final ViewHoldermod viewHoldermod, final int position) {
        final int index = viewHoldermod.getAdapterPosition();
        viewHoldermod.textViewTitle
                .setText(list.get(position).getTitle());
        viewHoldermod.textViewDate
                .setText(list.get(position).getDateTime());
        viewHoldermod.textViewEntity
                .setText(String.valueOf(list.get(position).getEntidade()));
        viewHoldermod.textViewTransaction
                .setText(list.get(position).getType());
        viewHoldermod.textViewAmount
                .setText(String.valueOf(list.get(position).getAmount()));
        viewHoldermod.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listiner.click(index);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public void filteredList(List<Transaction> filteredlist) {
        list = filteredlist;
        notifyDataSetChanged();
    }
}
