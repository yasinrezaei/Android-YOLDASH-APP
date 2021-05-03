package com.example.yoldash;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import javax.swing.text.View;

public class MemVocabListFragment extends Fragment {
    DatabaseHelper mydb;
    List<VocabInfo> vocabInfoList=new ArrayList<>();
    MemRecyclerAdapter mem_adapter;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.vocab_fragment,container,false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mydb=new DatabaseHelper(getContext());
        Cursor res=mydb.getAllMemVocab();
        if(res.getCount()==0){
            Toast.makeText(getContext(),"هیچ لغتی حفظ نشده!" ,Toast.LENGTH_SHORT).show();
            return;
        }
        while (res.moveToNext()){
            VocabInfo vocabInfo=new VocabInfo();
            vocabInfo.id=res.getString(0);
            vocabInfo.vocab=res.getString(1);
            vocabInfo.text=res.getString(2);
            vocabInfo.level=res.getString(3);
            vocabInfoList.add(vocabInfo);
        }

        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        Collections.reverse(vocabInfoList);
        mem_adapter=new MemRecyclerAdapter(getContext(),vocabInfoList);
        recyclerView.setAdapter(mem_adapter);
    }
}
