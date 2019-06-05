package com.sidlin.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sidlin.fitnessapp.R;

import java.util.HashMap;
import java.util.Map;

public class maxActivity extends AppCompatActivity {

    private Button maxBtn;
    private String testLoad, testReps;
    private int load, reps;
    private boolean testMaxError, testFilled;
    private double total;
    private String roundedMax;
    private EditText loadTxt, repTxt;
    private TextView resultMaxTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max);
        setTitle("1-Rep Maximum Calculator");

        maxBtn = (Button) findViewById(R.id.maxBtn);
        maxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadTxt = (EditText) findViewById(R.id.loadTxt);
                repTxt = (EditText) findViewById(R.id.repTxt);
                resultMaxTxt = (TextView) findViewById(R.id.resultMaxTxt);
                testLoad = loadTxt.getText().toString();
                testReps = repTxt.getText().toString();
                testFilled = false;
                testMaxError = false;

                if (testLoad.matches("")){
                    loadTxt.setError("You must enter a weight.");
                    testFilled = true;
                }

                if (testReps.matches(""))
                {
                    repTxt.setError("You must enter a number of reps.");
                    testFilled = true;
                }


                if(testFilled == false){
                    if (Integer.parseInt(testReps) > 10 || Integer.parseInt(testReps) <= 0){
                        repTxt.setError("Reps must be between 1 and 10.");
                        testMaxError = true;
                    }
                }

                if (testMaxError == false && testFilled == false){
                    load = Integer.parseInt(testLoad);
                    reps = Integer.parseInt(testReps);
                    double percentage = 1;

                    if (reps <= 10 && reps > 0) {

                        if (reps == 10) {
                            percentage = .75;
                        } else if (reps == 9) {
                            percentage = .775;
                        } else if (reps == 8) {
                            percentage = .80;
                        } else if (reps == 7) {
                            percentage = .825;
                        } else if (reps == 6) {
                            percentage = .85;
                        } else if (reps == 5) {
                            percentage = .875;
                        } else if (reps == 4) {
                            percentage = .90;
                        } else if (reps == 3) {
                            percentage = .925;
                        } else if (reps == 2) {
                            percentage = .95;
                        } else if (reps == 1) {
                            percentage = 1;
                        }

                        total = (double)load /percentage;
                        roundedMax = String.format("%.0f", total);
                        resultMaxTxt.setText("1RM: " + roundedMax + " lbs");
                    }
                }

            }
        });

    };
};

