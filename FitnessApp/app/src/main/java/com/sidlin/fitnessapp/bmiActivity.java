package com.sidlin.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sidlin.fitnessapp.R;

import org.w3c.dom.Text;

public class BmiActivity extends AppCompatActivity {

    private int wt;
    private int ht;
    private double resultBMI;
    private Button bmiBTN;
    private String bmiCategory, test1, test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        setTitle("BMI Calculator");

        bmiBTN = (Button) findViewById(R.id.bmiBTN);
        bmiBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //declare variables
                EditText bmiWeightEditText = (EditText) findViewById(R.id.bmiWeightEditText);
                EditText bmiHeightEditText = (EditText) findViewById(R.id.bmiHeightEditText);
                TextView scaleTxt = (TextView) findViewById(R.id.scaleTxt);

                test1 = bmiWeightEditText.getText().toString();
                test2 = bmiHeightEditText.getText().toString();
                boolean testBmiError = false;
                boolean testBmiNumbers = false;

                //testing for errors
                if (test1.matches("")){
                    bmiWeightEditText.setError("You must enter a weight.");
                    testBmiError = true;
                }

                if (test2.matches("")){
                    bmiHeightEditText.setError("You must enter a height.");
                    testBmiError = true;
                }


                if (testBmiError == false){
                    wt = Integer.parseInt(test1);
                    ht = Integer.parseInt(test2);

                    if (wt == 0){
                        bmiWeightEditText.setError("Weight cannot be 0.");
                        testBmiNumbers = true;
                    }

                    if (ht == 0){
                        bmiHeightEditText.setError("Height cannot be 0.");
                        testBmiNumbers = true;
                    }
                }

                if (testBmiNumbers == false && testBmiError == false) {
                    //clears error messages
                    bmiWeightEditText.setError(null);
                    bmiHeightEditText.setError(null);
                    wt = Integer.parseInt(test1);
                    ht = Integer.parseInt(test2);
                    resultBMI = (double)(wt)/(ht*ht)*703;
                    String bmi = String.format("%.2f", resultBMI);

                    bmiCategory = "";
                    if (resultBMI < 18.5) {
                        bmiCategory = "Underweight";
                    } else if (resultBMI >= 18.5 && resultBMI <= 24.9) {
                        bmiCategory = "Normal";
                    } else if (resultBMI >= 25 && resultBMI <= 29.9) {
                        bmiCategory = "Overweight";
                    } else if (resultBMI >= 30 && resultBMI <= 34.9) {
                        bmiCategory = "Obese";
                    } else {
                        bmiCategory = "Extremely Obese";
                    }
                    scaleTxt.setText("BMI: " + bmi + ", " + bmiCategory);
                }
            }
        });

    }
}
