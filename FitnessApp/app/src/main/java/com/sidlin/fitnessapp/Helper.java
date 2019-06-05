package com.sidlin.fitnessapp;

import android.widget.EditText;

public class Helper {

    public Helper(){

    }

    public boolean isEmpty(EditText e){
        if(e.getText().toString().matches("")){
            e.setError("This cannot be left empty.");
            return true;
        }
        else{
            e.setError(null);
            return false;
        }
    }
}
