package com.basic.spanishlearning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.futuremind.recyclerviewfastscroll.SectionTitleProvider;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> implements SectionTitleProvider {

    RecyclerViewOnClickInterface recyclerViewOnClickInterface;

    String[] str_contents;

    MyAdapter(String[] str_contents, RecyclerViewOnClickInterface recyclerViewOnClickInterface) {

        this.str_contents = str_contents;
        this.recyclerViewOnClickInterface=recyclerViewOnClickInterface;
    }

    @Override
    public String getSectionTitle(int position) {
        return str_contents[position].substring(0,3);
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView bt_contents;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            bt_contents = itemView.findViewById(R.id.bt_contents);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewOnClickInterface.OnClick(getAdapterPosition());
                }
            });
        }


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.content_view_layout,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        String str_content_name= str_contents[position].trim();
        holder.bt_contents.setText(str_content_name);

    }


    @Override
    public int getItemCount() {
        return str_contents.length;
    }


}
