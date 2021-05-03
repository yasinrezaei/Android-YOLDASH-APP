package com.example.yoldash;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EasyBox extends AppCompatActivity {
    TextView leveltext;
    DatabaseHelper mydb;
    List<BoxInfo> boxInfoList=new ArrayList<>();
    BoxAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(MyPrefrenceManager.getIstance(this).getMode()==true){
            setContentView(R.layout.level_box);
        }
        else{
            setContentView(R.layout.level_box_dark);
        }

        configureView();
        leveltext.setText("سطح ساده");

        mydb=new DatabaseHelper(getApplicationContext());
        Cursor res=mydb.getAllEasyBox();
        if(res.getCount()==0){
            Toast.makeText(getApplicationContext(),"هیچ لغتی اضافه نشده!" ,Toast.LENGTH_SHORT).show();
            return;
        }
        while (res.moveToNext()){
            BoxInfo boxInfo=new BoxInfo();
            boxInfo.id=res.getString(0);
            boxInfo.vocab=res.getString(1);
            boxInfo.text=res.getString(2);
            boxInfo.level=res.getString(3);
            boxInfo.count=res.getString(4);
            boxInfoList.add(boxInfo);
        }

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        Collections.reverse(boxInfoList);
        adapter=new BoxAdapter(getApplicationContext(),boxInfoList,2);
        recyclerView.setAdapter(adapter);
    }
    private void configureView(){
        leveltext=(TextView)findViewById(R.id.txt_level);
    }
}
