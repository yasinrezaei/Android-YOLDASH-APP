package com.example.yoldash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.michaldrabik.tapbarmenulib.TapBarMenu;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class MainActivity extends AppCompatActivity {

    Button addVocab,vocabList,search,box;
    ImageView profileImage,contact;
    ImageView editProfile;
    TextView userName,status,countAll,countMem;
    DatabaseHelper mydb;
    int count,memcount,hard_pie,middle_pie,easy_pie,point;
    PieChart pieChart;
    RatingBar ratingBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(MyPrefrenceManager.getIstance(this).getMode()==true){
            setContentView(R.layout.activity_main);
        }
        else{
            setContentView(R.layout.activity_main_dark);
        }

        configureView();




        ///profile///
        if(MyPrefrenceManager.getIstance(this).getSex().equals("male")){
            profileImage.setImageResource(R.drawable.female);
        }
        else {
            profileImage.setImageResource(R.drawable.male);
        }

        setPoint();
        ratingBar.setRating(MyPrefrenceManager.getIstance(this).getpoint());


        userName.setText(MyPrefrenceManager.getIstance(this).getUserName());
        status.setText(MyPrefrenceManager.getIstance(this).getStatus());

        //Set vocabs count ///

        mydb=new DatabaseHelper(getApplicationContext());
        memcount=mydb.getAllMemVocab().getCount();
        count=mydb.getAllVocab().getCount()+memcount;


        countAll.setText(String.valueOf(count));
        countMem.setText(String.valueOf(memcount));

        //////Pie chart /////

        mydb=new DatabaseHelper(getApplicationContext());
        hard_pie=mydb.getAllHardBox().getCount();
        easy_pie=mydb.getAllEasyBox().getCount();
        middle_pie=mydb.getAllMiddleBox().getCount();
        PieChart();

        ////////////////


        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,EditProfile.class);
                startActivity(intent);
            }
        });


        addVocab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddVocab.class);
                startActivity(intent);

            }
        });

        vocabList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,VocabList.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,BoxActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    void configureView(){
        addVocab=(Button)findViewById(R.id.btn_addvocab);
        vocabList=(Button)findViewById(R.id.vocab_list_btn);
        editProfile=(ImageView)findViewById(R.id.btn_editprofile);
        profileImage=(ImageView) findViewById(R.id.profile_image);
        //contact=(ImageView) findViewById(R.id.contact);
        search=(Button) findViewById(R.id.search);
        box=(Button) findViewById(R.id.box);
        userName=(TextView)findViewById(R.id.user_name);
        status=(TextView)findViewById(R.id.status);
        countAll=(TextView)findViewById(R.id.count_of_all);
        countMem=(TextView)findViewById(R.id.count_of_mem);

        pieChart = findViewById(R.id.piechart);
        ratingBar=findViewById(R.id.rating);
    }
    private void PieChart()
    {

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Hard",
                        hard_pie,
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Middle",
                        middle_pie,
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Easy",
                        easy_pie,
                        Color.parseColor("#EF5350")));


        // To animate the pie chart
        pieChart.startAnimation();
    }
    private void setPoint(){
        int all_mem;
        DatabaseHelper db=new DatabaseHelper(getApplicationContext());
        all_mem=db.getAllMemVocab().getCount();

        if(all_mem>=100){
            MyPrefrenceManager.getIstance(this).putpoint(2);
        }
        else if(all_mem>=300){
            MyPrefrenceManager.getIstance(this).putpoint(3);
       }
        else if(all_mem>=500){
            MyPrefrenceManager.getIstance(this).putpoint(4);
        }


    }
}