package com.example.yoldash;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SearchActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    List<VocabInfo> vocabInfoList=new ArrayList<>();
    SearchRecyclerAdapter adapter;
    RecyclerView recyclerView;
    Button search_btn;
    EditText search_txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MyPrefrenceManager.getIstance(this).getMode() == true) {
            setContentView(R.layout.activity_search);
        } else {
            setContentView(R.layout.activity_search_dark);
        }

        configureView();
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search(search_txt.getText().toString());
            }
        });



    }

    public void search(String text){
        vocabInfoList.clear();
        mydb=new DatabaseHelper(getApplicationContext());
        Cursor res=mydb.getAllVocab();
        while (res.moveToNext()){

            VocabInfo vocabInfo=new VocabInfo();
            vocabInfo.id=res.getString(0);
            vocabInfo.vocab=res.getString(1);
            vocabInfo.text=res.getString(2);
            vocabInfo.level=res.getString(3);
            if(vocabInfo.text.contains(text) || vocabInfo.vocab.contains(text)){
                vocabInfoList.add(vocabInfo);
            }

        }
        ///
        Cursor res2=mydb.getAllMemVocab();
        while (res2.moveToNext()){
            VocabInfo vocabInfo=new VocabInfo();
            vocabInfo.id=res2.getString(0);
            vocabInfo.vocab=res2.getString(1);
            vocabInfo.text=res2.getString(2);
            vocabInfo.level=res2.getString(3);
            if(vocabInfo.text.contains(text) || vocabInfo.vocab.contains(text)){
                vocabInfoList.add(vocabInfo);
            }

        }


        ///
        if(vocabInfoList.size()==0){
            Toast.makeText(this, "واژه ای پیدا نشد!", Toast.LENGTH_SHORT).show();
        }


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Collections.reverse(vocabInfoList);
        adapter=new SearchRecyclerAdapter(getApplicationContext(),vocabInfoList);
        recyclerView.setAdapter(adapter);
    }

    public void configureView(){
        recyclerView=(RecyclerView)findViewById(R.id.search_recyclerview);
        search_btn=(Button)findViewById(R.id.search_btn);
        search_txt=(EditText)findViewById(R.id.search_txt);

    }

}
