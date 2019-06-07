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
    private boolean testErrorCheckMacroFilled;
    private int percentCarbs, percentFat, percentProtein, cals, total;
    private EditText carbsEditTxt, proEditTxt, fatEditTxt, calsEditTxt;
    private TextView cpfTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macro);
        macroBtn = (Button) findViewById(R.id.macroBtn);

        /*
        Initialize data members that are a part of the layout
         */
        carbsEditTxt = (EditText) findViewById(R.id.carbsEditTxt);
        proEditTxt = (EditText) findViewById(R.id.proEditTxt);
        fatEditTxt = (EditText) findViewById(R.id.fatEditTxt);
        calsEditTxt = (EditText) findViewById(R.id.calsEditTxt);
        cpfTxtView = (TextView) findViewById(R.id.cpfTxtView);

        macroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //checks percentage
                testErrorCheckMacroFilled = false;
                //checks for no user input
                if (Helper.isEmpty(carbsEditTxt)){
                    testErrorCheckMacroFilled = true;
                }
                if(Helper.isEmpty(proEditTxt)){
                    testErrorCheckMacroFilled = true;
                }
                if(Helper.isEmpty(fatEditTxt)){
                    testErrorCheckMacroFilled = true;
                }
                if(Helper.isEmpty(calsEditTxt)){
                    testErrorCheckMacroFilled = true;
                }

                if (testErrorCheckMacroFilled == false) {
                    //happens when the macro percents are filled in but not totaled yet
                    percentCarbs = Integer.parseInt(carbsEditTxt.getText().toString());
                    percentProtein = Integer.parseInt(proEditTxt.getText().toString());
                    percentFat = Integer.parseInt(fatEditTxt.getText().toString());
                    total = percentCarbs + percentProtein + percentFat;

                    if (total != 100) {
                        carbsEditTxt.setError("Percentages must add to 100%.");
                        proEditTxt.setError("Percentages must add to 100%.");
                        fatEditTxt.setError("Percentages must add to 100%.");
                    }
                    else if(Double.parseDouble(calsEditTxt.getText().toString()) < 0){
                        calsEditTxt.setError("Calories cannot be negative.");
                    }
                    else{
                        String result = resultMacros();
                        cpfTxtView.setText(result);
                    }
                }
            }
        });
    }

    public String resultMacros(){
        String rtrn = "";
        //get rid of error messages
        carbsEditTxt.setError(null);
        proEditTxt.setError(null);
        fatEditTxt.setError(null);

        percentCarbs = Integer.parseInt(carbsEditTxt.getText().toString());
        percentProtein = Integer.parseInt(proEditTxt.getText().toString());
        percentFat = Integer.parseInt(fatEditTxt.getText().toString());
        cals = Integer.parseInt(calsEditTxt.getText().toString());

        double carbsTotal = (double)cals * percentCarbs * 0.01 / 4;
        double proTotal = (double)cals * percentProtein * 0.01 / 4;
        double fatTotal = (double)cals * percentFat * 0.01 / 9;

        //cast all to an int and display
        rtrn = "C: " + (int)carbsTotal + " P: " + (int)proTotal + " F: " + (int)fatTotal;
        return rtrn;
    }
}
