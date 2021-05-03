package com.example.yoldash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.michaldrabik.tapbarmenulib.TapBarMenu;

public class AddVocab extends AppCompatActivity {
    DatabaseHelper mydb;
    Button addVocab;
    EditText vocab,text;
    RadioGroup radioGroup_vocab_level;
    String level="Easy";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(MyPrefrenceManager.getIstance(this).getMode()==true){
            setContentView(R.layout.acticity_addvocab);
        }
        else{
            setContentView(R.layout.activity_addvocab_dark);
        }


        mydb = new DatabaseHelper(this);
        configureView();

        radioGroup_vocab_level.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.radioHard){
                    level="Hard";

                }
                if(checkedId==R.id.radioMiddle){
                    level="Middle";
                }
                if(checkedId==R.id.radioEasy){
                    level="Easy";
                }

            }
        });

        addVocab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInsert;

                isInsert = mydb.insert_all(vocab.getText().toString(), text.getText().toString(),level);

                if(isInsert==true){
                    Toast.makeText(AddVocab.this,"لغت جدید با موفقیت اضافه شد" ,Toast.LENGTH_SHORT).show();
                    vocab.setText("");
                    text.setText("");
                    radioGroup_vocab_level.clearCheck();

                }
                else{
                    Toast.makeText(AddVocab.this,"لغت جدید اضافه نشد" ,Toast.LENGTH_SHORT).show();
                }

            }
        });




    }

    private void configureView(){
        addVocab=(Button)findViewById(R.id.save_vocab);
        vocab=(EditText)findViewById(R.id.vocab_edt);
        text=(EditText)findViewById(R.id.meaning_edt);
        radioGroup_vocab_level=(RadioGroup)findViewById(R.id.radioGroup_vocab_level);

    }

}
