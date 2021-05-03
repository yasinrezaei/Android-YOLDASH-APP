package com.example.yoldash;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class VocabList extends AppCompatActivity {

    Button vocabs,memVocabs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(MyPrefrenceManager.getIstance(this).getMode()==true){
            setContentView(R.layout.activity_vocab_list);
        }
        else{
            setContentView(R.layout.activity_vocab_list_dark);
        }

        configureView();

        ///vocab list fragment///

        VocabListFragment frag=new VocabListFragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,frag);
        fragmentTransaction.commit();

        ///

        vocabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VocabListFragment frag2=new VocabListFragment();
                FragmentManager fragmentManager2=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2=fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.main_frame,frag2);
                fragmentTransaction2.commit();
            }
        });
        memVocabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemVocabListFragment frag3=new MemVocabListFragment();
                FragmentManager fragmentManager3=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction3=fragmentManager3.beginTransaction();
                fragmentTransaction3.replace(R.id.main_frame,frag3);
                fragmentTransaction3.commit();
            }
        });

    }
    private void configureView(){
        vocabs=(Button)findViewById(R.id.vocabs);
        memVocabs=(Button)findViewById(R.id.mem_vocabs);
    }
}
