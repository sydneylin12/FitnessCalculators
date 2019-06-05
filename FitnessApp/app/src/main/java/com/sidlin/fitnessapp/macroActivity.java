package com.sidlin.fitnessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sidlin.fitnessapp.R;

public class MacroActivity extends AppCompatActivity {

    private Button macroBtn;
    private boolean testErrorCheckMacroFilled, testErrorCheckPercent;
    private int percentCarbs, percentFat, percentProtein, cals, total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macro);
        setTitle("Macronutrient Calculator");

        macroBtn = (Button) findViewById(R.id.macroBtn);
        macroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText carbsEditTxt = (EditText) findViewById(R.id.carbsEditTxt);
                EditText proteinEditTxt = (EditText) findViewById(R.id.proteinEditTxt);
                EditText fatEditTxt = (EditText) findViewById(R.id.fatEditTxt);
                EditText calsEditTxt = (EditText) findViewById(R.id.calsEditTxt);

                TextView carbsTxtView = (TextView) findViewById(R.id.carbsTxtView);
                TextView proteinTxtView = (TextView) findViewById(R.id.proteinTxtView);
                TextView fatTxtView = (TextView) findViewById(R.id.fatTxtView);

                //checks percentage
                testErrorCheckMacroFilled = false;
                testErrorCheckPercent = false;

                //checks for no user input
                if (carbsEditTxt.getText().toString().matches("")){
                    carbsEditTxt.setError("You must enter a % of carbohydrates.");
                    testErrorCheckMacroFilled = true;
                }

                if (proteinEditTxt.getText().toString().matches("")){
                    proteinEditTxt.setError("You must enter a % of protein.");
                    testErrorCheckMacroFilled = true;
                }

                if (fatEditTxt.getText().toString().matches("")){
                    fatEditTxt.setError("You must enter a % of fat.");
                    testErrorCheckMacroFilled = true;
                }

                if (calsEditTxt.getText().toString().matches("")){
                    calsEditTxt.setError("You must enter a TDEE.");
                    testErrorCheckMacroFilled = true;
                }

                if (testErrorCheckMacroFilled == false) {
                    //happens when the macro percents are filled in but not totaled yet
                    percentCarbs = Integer.parseInt(carbsEditTxt.getText().toString());
                    percentProtein = Integer.parseInt(proteinEditTxt.getText().toString());
                    percentFat = Integer.parseInt(fatEditTxt.getText().toString());
                    total = percentCarbs + percentProtein + percentFat;

                    if (total != 100) {
                        carbsEditTxt.setError("Percentages must add to 100%.");
                        proteinEditTxt.setError("Percentages must add to 100%.");
                        fatEditTxt.setError("Percentages must add to 100%.");
                        testErrorCheckPercent = true;
                    }

                    else{
                        //get rid of error messages
                        carbsEditTxt.setError(null);
                        proteinEditTxt.setError(null);
                        fatEditTxt.setError(null);

                        percentCarbs = Integer.parseInt(carbsEditTxt.getText().toString());
                        percentProtein = Integer.parseInt(proteinEditTxt.getText().toString());
                        percentFat = Integer.parseInt(fatEditTxt.getText().toString());
                        cals = Integer.parseInt(calsEditTxt.getText().toString());

                        double carbsTotal = (double)cals * percentCarbs * 0.01 / 4;
                        double proTotal = (double)cals * percentProtein * 0.01 / 4;
                        double fatTotal = (double)cals * percentFat * 0.01 / 9;

                        //cast all to an int and display
                        carbsTxtView.setText("Carbohydrate (g): " + (int) carbsTotal);
                        proteinTxtView.setText("Protein (g): " + (int) proTotal);
                        fatTxtView.setText("Fat (g): " + (int) fatTotal);
                    }
                }
            }
        });
    }
}
