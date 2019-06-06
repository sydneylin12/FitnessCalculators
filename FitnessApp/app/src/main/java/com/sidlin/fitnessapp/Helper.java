package com.sidlin.fitnessapp;

import android.widget.EditText;

public class Helper {

    public static boolean isEmpty(EditText e){
        /*
        Checks for an empty editText
         */
        if(e.getText().toString().matches("")){
            e.setError("This cannot be left empty.");
            return true;
        }
        else{
            e.setError(null);
            return false;
        }
    }

    public static void set(EditText e){
        //test method
        e.setError("LOL");
    }
}
