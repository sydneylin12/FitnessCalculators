package com.sidlin.fitnessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class PopActivity extends AppCompatActivity {

    private EditText name, p, c, f, s;

    private Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        /*
        Initialize all variables
        */
        name = (EditText)findViewById(R.id.addName);
        p = (EditText)findViewById(R.id.addPro);
        c = (EditText)findViewById(R.id.addCarb);
        f = (EditText)findViewById(R.id.addFat);
        s = (EditText)findViewById(R.id.addServ);
        /*
        Manages the popup window
        */

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.65)); //adjust to fix clicking out of popup bugs

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        /*
        Intent testing for popup
        */
        addBtn = (Button)findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();

                /*
                Check to see if there are errors that need to be thrown
                Update text box with error popup if so
                 */
                boolean sendCheck = true;
                if(name.getText().toString().matches("")){
                    sendCheck = false;
                    name.setError("You must enter a food.");
                }
                if(p.getText().toString().matches("")){
                    sendCheck = false;
                    p.setError("You must enter grams of protein.");
                }
                if(c.getText().toString().matches("")){
                    sendCheck = false;
                    c.setError("You must enter grams of carbs.");
                }
                if(f.getText().toString().matches("")){
                    sendCheck = false;
                    f.setError("You must enter grams of fat.");
                }

                if(sendCheck == true) { //all error checks passed, throw values to last intent
                    i.putExtra("name_return", name.getText().toString());
                    i.putExtra("pro_return", Integer.parseInt(p.getText().toString()));
                    i.putExtra("carb_return", Integer.parseInt(c.getText().toString()));
                    i.putExtra("fat_return", Integer.parseInt(f.getText().toString()));
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });
    }
}
