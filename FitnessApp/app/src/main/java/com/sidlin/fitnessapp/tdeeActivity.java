package com.sidlin.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.sidlin.fitnessapp.R;

public class tdeeActivity extends AppCompatActivity {
    private Button tdeeBtn;
    private TextView hb, km, msj;
    private EditText weightTxt, heightTxt, bfTxt, ageTxt;
    private String testWt, testHt, testBf, testAge;
    private boolean testErrorCheckTdee;
    private double resultHb, resultKm, resultMsj;
    private String bmrHb, bmrKm, bmrMsj;
    private int ht, wt, bf, age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tdee);
        setTitle("Total Daily Energy Expenditure Calculator");

        tdeeBtn = (Button) findViewById(R.id.tdeeBtn);
        tdeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hb = (TextView) findViewById(R.id.hbTxt);
                km = (TextView) findViewById(R.id.kmTxt);
                msj = (TextView) findViewById(R.id.msjTxt);

                //user filled-in texts
                weightTxt = (EditText) findViewById(R.id.weightTxt);
                heightTxt = (EditText) findViewById(R.id.heightTxt);
                bfTxt = (EditText) findViewById(R.id.bfTxt);
                ageTxt = (EditText) findViewById(R.id.ageTxt);

                //test strings to check for errors
                testWt = weightTxt.getText().toString();
                testHt = heightTxt.getText().toString();
                testBf = bfTxt.getText().toString();
                testAge = ageTxt.getText().toString();

                //returns true if errors are present
                testErrorCheckTdee = false;

                //spinner for multipliers
                Spinner multiplierSpinner = (Spinner) findViewById(R.id.multiplierSpinner);
                String multiplierCheck = multiplierSpinner.getSelectedItem().toString();
                double multiplier = 1.0;

                if (multiplierCheck.matches("1.0 - BMR")){
                    multiplier = 1.0;
                }

                if (multiplierCheck.matches("1.2 - Sedentary")){
                    multiplier = 1.2;
                }

                else if (multiplierCheck.matches("1.375 - Light Exercise/Sports")){
                    multiplier = 1.375;
                }

                else if (multiplierCheck.matches("1.55 - Moderate Exercise/Sports")){
                    multiplier = 1.55;
                }

                else if (multiplierCheck.matches("1.725 - Heavy Exercise/Sports")){
                    multiplier = 1.725;
                }

                else if (multiplierCheck.matches("1.9 - Very Heavy Exercise/Physical Job")){
                    multiplier = 1.9;
                }

                if (testWt.matches("")){
                    weightTxt.setError("You must enter a weight.");
                    testErrorCheckTdee = true;
                }

                if (testAge.matches("")){
                    ageTxt.setError("You must enter an age.");
                    testErrorCheckTdee = true;
                }

                if (testBf.matches("")){
                    bfTxt.setError("You must enter a body fat %.");
                    testErrorCheckTdee = true;
                }

                if (testHt.matches("")){
                    heightTxt.setError("You must enter a height.");
                    testErrorCheckTdee = true;
                }


                if (testErrorCheckTdee == false){
                    //boolean checker for if the calculation can be done
                    boolean testErrorTdeeNumbers = false;
                    wt = Integer.parseInt(weightTxt.getText().toString());
                    ht = Integer.parseInt(heightTxt.getText().toString());
                    age = Integer.parseInt(ageTxt.getText().toString());
                    bf = Integer.parseInt(bfTxt.getText().toString());

                    double wtKg = wt / 2.2;
                    double htCm = ht * 2.54;
                    double lbm = (1 - (bf * 0.01)) * wt;
                    double lbmKg = lbm / 2.2;

                    if (wt == 0){
                        weightTxt.setError("Weight cannot be 0.");
                        testErrorTdeeNumbers = true;
                    }

                    if (ht == 0){
                        heightTxt.setError("Weight cannot be 0.");
                        testErrorTdeeNumbers = true;
                    }

                    if (bf == 0){
                        bfTxt.setError("Body fat % cannot be 0.");
                        testErrorTdeeNumbers = true;
                    }

                    if (age == 0){
                        ageTxt.setError("Age cannot be 0.");
                        testErrorTdeeNumbers = true;
                    }

                    if (testAge.contains(".")){
                        ageTxt.setError("Age must be a whole number.");
                        testErrorTdeeNumbers = true;
                    }

                    if (testErrorTdeeNumbers == false) {

                        ageTxt.setError(null);
                        weightTxt.setError(null);
                        heightTxt.setError(null);
                        bfTxt.setError(null);

                        resultHb = (66 + (6.2 * wt) + (12.7 * ht) - (6.76 * age)) * multiplier;
                        resultKm = (370 + (21.6 * lbmKg)) * multiplier;
                        resultMsj = ((10 * wtKg) + (6.25 * htCm) - (5 * age) + 5) * multiplier;

                        //print with 2 decimal places
                        bmrHb = String.format("%.2f", resultHb);
                        bmrKm = String.format("%.2f", resultKm);
                        bmrMsj = String.format("%.2f", resultMsj);
                        hb.setText("Harris-Benedict: " + bmrHb);
                        km.setText("Katch-McCardle: " + bmrKm);
                        msj.setText("Mifflin-St. Jeor: " + bmrMsj);
                    }
                }

            }
        });

    }
}
