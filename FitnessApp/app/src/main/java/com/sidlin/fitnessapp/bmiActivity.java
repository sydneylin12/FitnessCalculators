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
    private EditText bmiWeightEditText, bmiHeightEditText;
    private TextView bmiTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        bmiWeightEditText = (EditText) findViewById(R.id.bmiWeightEditText);
        bmiHeightEditText = (EditText) findViewById(R.id.bmiHeightEditText);
        bmiTxt = (TextView) findViewById(R.id.scaleTxt);

        bmiBTN = (Button) findViewById(R.id.bmiBTN);
        bmiBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Get variables from ExitTexts
                 */
                test1 = bmiWeightEditText.getText().toString();
                test2 = bmiHeightEditText.getText().toString();
                boolean testBmiError = false;
                boolean testBmiNumbers = false;

                /*
                Test for empty text errors
                 */
                if (Helper.isEmpty(bmiWeightEditText)){
                    //bmiWeightEditText.setError("You must enter a weight.");
                    testBmiError = true;
                }
                if (Helper.isEmpty(bmiHeightEditText)){
                    //bmiHeightEditText.setError("You must enter a height.");
                    testBmiError = true;
                }

                /*
                Test for 0 case
                 */
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

                /*
                Do the final calculation
                 */
                if (testBmiNumbers == false && testBmiError == false) {
                    //clears error messages
                    bmiWeightEditText.setError(null);
                    bmiHeightEditText.setError(null);
                    wt = Integer.parseInt(test1);
                    ht = Integer.parseInt(test2);
                    resultBMI = (double)(wt)/(ht*ht)*703;
                    String bmi = String.format("%.2f", resultBMI);
                    getBmiCategory(resultBMI);
                    bmiTxt.setText("BMI: " + bmi + ", " + bmiCategory);
                }
            }
        });
    }

    private void getBmiCategory(double bmi){
        if (bmi < 18.5) {
            bmiCategory = "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            bmiCategory = "Normal";
        } else if (bmi >= 25 && bmi <= 29.9) {
            bmiCategory = "Overweight";
        } else if (bmi >= 30 && bmi <= 34.9) {
            bmiCategory = "Obese";
        } else {
            bmiCategory = "Extremely Obese";
        }
    }
}
