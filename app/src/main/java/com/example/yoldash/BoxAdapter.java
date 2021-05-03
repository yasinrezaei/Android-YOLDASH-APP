package com.example.yoldash;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BoxAdapter extends RecyclerView.Adapter<BoxAdapter.ViewHolder> {
        Context context;
        DatabaseHelper mydb;
        List<BoxInfo> boxInfos;
        Integer deleteRow,Level;

        public BoxAdapter(Context context, List<BoxInfo> boxInfos,int Level) {
            this.context = context;
            this.boxInfos = boxInfos;
            this.Level=Level;
        }
        public BoxAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if(MyPrefrenceManager.getIstance(context).getMode()==true){
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.box_item,parent,false);
                return new BoxAdapter.ViewHolder(view);
            }
            else {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.box_item_dark,parent,false);
                return new BoxAdapter.ViewHolder(view);
            }


        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final BoxInfo boxInfo=boxInfos.get(position);
        holder.vocab.setText(boxInfo.vocab);
        holder.text.setText("----");
        holder.count.setText( "تعداد مرور : " +boxInfo.count);
        holder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.text.setText(boxInfo.text);
            }
        });

        holder.bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mydb=new DatabaseHelper(context);
                if(Level==0){
                    deleteRow=mydb.deleteData_hard(boxInfo.id);
                }
                else if(Level==1){
                    deleteRow=mydb.deleteData_middle(boxInfo.id);
                }
                else if(Level==2){
                    deleteRow=mydb.deleteData_easy(boxInfo.id);
                }
                boxInfos.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, boxInfos.size());
            }
        });

        holder.bt_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int c=Integer.parseInt(boxInfo.count);
                c=c+1;
                mydb=new DatabaseHelper(context);
                mydb.update_box(boxInfo.id,String.valueOf(c),Level);
                holder.count.setText( "تعداد مرور : " +String.valueOf(c));

            }
        });
    }



    public int getItemCount() {
        return boxInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView vocab,text,count;
        Button show;
        ImageView bt_read,bt_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vocab=(TextView)itemView.findViewById(R.id.box_vocab);
            text=(TextView)itemView.findViewById(R.id.box_text);
            count=(TextView)itemView.findViewById(R.id.box_count);
            show=(Button)itemView.findViewById(R.id.box_show_text);


            bt_read=(ImageView)itemView.findViewById(R.id.box_read);
            bt_delete=(ImageView)itemView.findViewById(R.id.box_delete);


        }
    }
}
