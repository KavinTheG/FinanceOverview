package com.example.financeoverview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context context;
    String s1[];
    String s2[];

    public RecyclerViewAdapter(Context ct, String s1[], String s2[]) {

        context = ct;
        this.s1 = s1;
        this.s2 = s2;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(s1[position]);
        holder.descrip.setText(s1[position]);
    }

    @Override
    public int getItemCount() {
        return s1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // Constructor receives itemView from onCreateViewHolder (line 27)
        // Must retrieve id of the items in m_row

        TextView title, descrip;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            descrip = itemView.findViewById(R.id.item_descrip);

        }
    }
}
