package com.example.yoldash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BoxActivity extends AppCompatActivity {
    Button hardBox,middleBox,easyBox;
    TextView hardCount,middleCount,easyCount;
    DatabaseHelper mydb;
    int h_count,m_count,e_count;

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(MyPrefrenceManager.getIstance(this).getMode()==true){
            setContentView(R.layout.activity_box);
        }
        else{
            setContentView(R.layout.activity_box_dark);
        }
        configureView();
        mydb=new DatabaseHelper(getApplicationContext());
        h_count=mydb.getAllHardBox().getCount();
        m_count=mydb.getAllMiddleBox().getCount();
        e_count=mydb.getAllEasyBox().getCount();




        hardCount.setText(" تعداد لغت ها: "+String.valueOf(h_count));
        middleCount.setText(" تعداد لغت ها: "+String.valueOf(m_count));
        easyCount.setText(" تعداد لغت ها: "+String.valueOf(e_count));



        hardBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BoxActivity.this,HardBox.class);
                startActivity(intent);
            }
        });

        middleBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BoxActivity.this,MiddleBox.class);
                startActivity(intent);
            }
        });

        easyBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BoxActivity.this,EasyBox.class);
                startActivity(intent);
            }
        });



    }

    private void configureView(){
        hardBox=(Button)findViewById(R.id.btn_hard_box);
        middleBox=(Button)findViewById(R.id.btn_middle_box);
        easyBox=(Button)findViewById(R.id.btn_easy_box);

        hardCount=(TextView)findViewById(R.id.hard_count);
        easyCount=(TextView)findViewById(R.id.easy_count);
        middleCount=(TextView)findViewById(R.id.midle_count);
    }
}
