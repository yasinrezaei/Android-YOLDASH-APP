package com.example.yoldash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Information extends AppCompatActivity {
    TextView email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(MyPrefrenceManager.getIstance(this).getMode()==true){
            setContentView(R.layout.activity_information);
        }
        else{
            setContentView(R.layout.activity_information_dark);
        }


        email=(TextView)findViewById(R.id.email);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, email.getText().toString());
                intent.putExtra(Intent.EXTRA_SUBJECT," YOLDASH App Offers");
                startActivity(Intent.createChooser(intent, "Choose an Email Client"));
            }
        });


    }
}
