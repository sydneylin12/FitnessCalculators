package com.sidlin.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class dashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        //sets click event for bmi activity
        //these will take you to the correct page
        CardView bmi_card = (CardView) findViewById(R.id.bmi_card);
        CardView tdee_card = (CardView) findViewById(R.id.tdee_card);
        CardView one_rep_max_card = (CardView) findViewById(R.id.one_rep_max_card);
        CardView macro_card = (CardView) findViewById(R.id.macro_card);

        //event to check for click
        bmi_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), bmiActivity.class);
                startActivity(i);
            }
        });

        tdee_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), tdeeActivity.class);
                startActivity(i);
            }
        });

        one_rep_max_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), maxActivity.class);
                startActivity(i);
            }
        });

        macro_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), macroActivity.class);
                startActivity(i);
            }
        });


    }
}
