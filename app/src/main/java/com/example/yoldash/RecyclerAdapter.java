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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    DatabaseHelper mydb;
    List<VocabInfo> vocabInfos;
    Integer deleteRow;

    public RecyclerAdapter(Context context, List<VocabInfo> vocabInfos) {
        this.context = context;
        this.vocabInfos = vocabInfos;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(MyPrefrenceManager.getIstance(context).getMode()==true){
             view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        }
        else{
             view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_dark,parent,false);
        }
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final VocabInfo vocabInfo=vocabInfos.get(position);
        holder.vocab.setText(vocabInfo.vocab);
        holder.text.setText(vocabInfo.text);

        holder.bt_mem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.drawable.ic_baseline_check_24);
                builder.setMessage("آیا این واژه را حفظ شده اید ؟")
                        .setCancelable(false)
                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                mydb=new DatabaseHelper(context);
                                //add to mem dta base///
                                mydb.insert_mem(vocabInfo.vocab,vocabInfo.text,vocabInfo.level);

                                //delete vocab from all vocab//

                                deleteRow=mydb.deleteData(vocabInfo.id);
                                vocabInfos.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, vocabInfos.size());
                                dialog.dismiss();
                            }

                        })
                        .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        holder.bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setMessage("آیا میخواهید این واژه را حذف کنید ؟")
                        .setCancelable(false)
                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                mydb=new DatabaseHelper(context);
                                //delete vocab from all vocab//

                                deleteRow=mydb.deleteData(vocabInfo.id);
                                vocabInfos.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, vocabInfos.size());
                                dialog.dismiss();
                            }

                        })
                        .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        holder.bt_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(R.string.app_name);
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setMessage("به جعبه لایتنر اضافه شود ؟")
                        .setCancelable(false)
                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int id) {
                                mydb=new DatabaseHelper(context);
                                //add to box data base///
                                if(vocabInfo.level.equals("Easy")){
                                    if(mydb.insert_easy(vocabInfo.vocab,vocabInfo.text,vocabInfo.level,0)){
                                        Toast.makeText(context, "اضافه شد", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(context, "خطا", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                if(vocabInfo.level.equals("Middle")){
                                    if(mydb.insert_middle(vocabInfo.vocab,vocabInfo.text,vocabInfo.level,0)){
                                        Toast.makeText(context, "اضافه شد", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(context, "خطا", Toast.LENGTH_SHORT).show();
                                    }

                                }
                                if(vocabInfo.level.equals("Hard")){
                                    if(mydb.insert_hard(vocabInfo.vocab,vocabInfo.text,vocabInfo.level,0)){
                                        Toast.makeText(context, "اضافه شد", Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(context, "خطا", Toast.LENGTH_SHORT).show();
                                    }

                                }




                                dialog.dismiss();
                            }

                        })
                        .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });


    }


    public int getItemCount() {
        return vocabInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView vocab,text;
        ImageView bt_mem,bt_delete,bt_box;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vocab=(TextView)itemView.findViewById(R.id.t_vocab);
            text=(TextView)itemView.findViewById(R.id.t_text);

            bt_mem=(ImageView)itemView.findViewById(R.id.bt_mem);
            bt_delete=(ImageView)itemView.findViewById(R.id.bt_del);
            bt_box=(ImageView)itemView.findViewById(R.id.bt_box);

        }
    }


}
