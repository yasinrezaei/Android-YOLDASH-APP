package com.example.yoldash;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder> {
    Context context;
    DatabaseHelper mydb;
    List<VocabInfo> vocabInfos;

    public SearchRecyclerAdapter(Context context, List<VocabInfo> vocabInfos) {
        this.context = context;
        this.vocabInfos = vocabInfos;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(MyPrefrenceManager.getIstance(context).getMode()==true){
             view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item,parent,false);
        }
        else{
             view= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item_dark,parent,false);
        }
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final VocabInfo vocabInfo=vocabInfos.get(position);
        holder.vocab.setText(vocabInfo.vocab);
        holder.text.setText(vocabInfo.text);

    }


    public int getItemCount() {
        return vocabInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView vocab,text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vocab=(TextView)itemView.findViewById(R.id.t_vocab);
            text=(TextView)itemView.findViewById(R.id.t_text);



        }
    }


}
