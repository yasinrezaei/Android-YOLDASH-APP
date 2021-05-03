package com.example.yoldash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfile extends AppCompatActivity {
    Button save_info_btn,dark_btn,light_btn;
    EditText user_name_edt,status_edt;
    RadioButton radioMale,radioFemale;
    RadioGroup radioGroup;
    RadioButton genderradioButton;
    String sex=MyPrefrenceManager.getIstance(this).getSex();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(MyPrefrenceManager.getIstance(this).getMode()==true){
            setContentView(R.layout.activity_editprofile);
        }
        else{
            setContentView(R.layout.activity_editprofile_dark);
        }


        configureView();

        user_name_edt.setText(MyPrefrenceManager.getIstance(this).getUserName());
        status_edt.setText(MyPrefrenceManager.getIstance(this).getStatus());


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==R.id.radioMale){
                    sex="male";

                }
                if(checkedId==R.id.radioFemale){
                    sex="female";
                }
            }
        });

        save_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateUserName(user_name_edt.getText().toString(),status_edt.getText().toString());
                Intent intent=new Intent(EditProfile.this,MainActivity.class);
                startActivity(intent);

            }
        });

        dark_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPrefrenceManager.getIstance(EditProfile.this).putMode(false);
                recreate();
            }
        });
        light_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPrefrenceManager.getIstance(EditProfile.this).putMode(true);
                recreate();
            }
        });
    }


    private void configureView(){
        save_info_btn=(Button)findViewById(R.id.save_info_btn);
        dark_btn=(Button)findViewById(R.id.dark_btn);
        light_btn=(Button)findViewById(R.id.light_btn);
        user_name_edt=(EditText)findViewById(R.id.user_name_edt);
        status_edt=(EditText)findViewById(R.id.status_edt);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
    }
    private void updateUserName(String name,String status_text){
        MyPrefrenceManager.getIstance(this).putUserName(name);
        MyPrefrenceManager.getIstance(this).putStatus(status_text);
        MyPrefrenceManager.getIstance(this).putSex(sex);

    }
}
