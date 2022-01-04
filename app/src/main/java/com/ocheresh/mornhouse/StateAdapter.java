package com.ocheresh.mornhouse;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.Intent;

import java.util.List;
import android.util.Log;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder>{

    interface OnStateClickListener{
        void onStateClick(Mornhouse mornh, int position);
    }

    private final OnStateClickListener onClickListener;

    private final LayoutInflater inflater;
    private List<Mornhouse> mornhouse;

    public StateAdapter(Context context, List<Mornhouse> mornhouse, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.mornhouse = mornhouse;
        this.inflater = LayoutInflater.from(context);

    }
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateAdapter.ViewHolder holder, int position) {
        Mornhouse mornh = mornhouse.get(position);
        holder.nameView.setText(mornh.getInfo());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onClickListener.onStateClick(mornh, position);}
        });

    }

    @Override
    public int getItemCount() {
        return mornhouse.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameView;

        ViewHolder(View view){
            super(view);
            this.nameView = view.findViewById(R.id.textViewRec);
        }
    }

    public void updatwRecyclerView(List<Mornhouse> list)
    {
        mornhouse = list;
        notifyDataSetChanged();
    }
}
