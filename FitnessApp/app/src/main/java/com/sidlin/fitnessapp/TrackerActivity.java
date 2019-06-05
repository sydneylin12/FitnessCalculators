package com.sidlin.fitnessapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TrackerActivity extends AppCompatActivity {

    private TextView total;
    private Button btn, del, clear;
    private ListView ls;
    private MacroList data;
    private ArrayList<String> viewData;
    private ArrayAdapter<String> arrayAdapter;
    private final static int REQUEST_CODE_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        load();
        initialize();
        total.setText("Total Calories: " + data.totalCals()); //update the total calories

        /*
        Set click listener for "add" button
         */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //click this button for popup and passing data back
                Intent i = new Intent(getApplicationContext(), PopActivity.class);
                startActivityForResult(i, REQUEST_CODE_1);
            }
        });
        /*
        Event for when the "delete" button is clicked
         */
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!data.isEmpty()){
                    int latest = data.size();
                    data.remove(latest-1);
                    viewData.remove(latest-1);
                    total.setText("Total Calories: " + data.totalCals()); //update the total calories
                    save();
                    arrayAdapter.notifyDataSetChanged(); //update list view
                }
            }
        });

        /*
        Event for when the "clear" button is clicked
         */
       clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.clear();
                viewData.clear();
                total.setText("Total Calories: " + data.totalCals()); //update the total calories
                save();
                arrayAdapter.notifyDataSetChanged(); //update list view
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent){
        super.onActivityResult(requestCode, resultCode, dataIntent);
        if(requestCode == REQUEST_CODE_1 && resultCode == RESULT_OK){
            //getting data from PopActivity, error checking is done there
            String next = dataIntent.getStringExtra("name_return");
            int tempPro = dataIntent.getIntExtra("pro_return", -1);
            int tempCarb = dataIntent.getIntExtra("carb_return", -1);
            int tempFat = dataIntent.getIntExtra("fat_return", -1);

            //make a new macro object for the macro table
            MacroTableObject temp = new MacroTableObject(next, tempPro, tempCarb, tempFat, 1);
            data.add(temp); //adds the object
            viewData.add(temp.toString()); //adds the string to the ArrayList (linked to the ListView)
            total.setText("Total Calories: " + data.totalCals()); //update the total calories
            save();
            arrayAdapter.notifyDataSetChanged(); //update list view
        }
    }

    private void save(){
        SharedPreferences sp = getSharedPreferences("shared prefrences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String dataJson = gson.toJson(data);
        String viewDataJson = gson.toJson(viewData);
        editor.putString("data", dataJson);
        editor.putString("viewData", viewDataJson);
        editor.apply();
    }

    private void load(){
        SharedPreferences sp = getSharedPreferences("shared prefrences", MODE_PRIVATE);
        Gson gson = new Gson();
        String dataJson = sp.getString("data", null);
        String viewDataJson = sp.getString("viewData", null);
        Type typeData = new TypeToken<MacroList>() {}.getType();
        Type typeViewData = new TypeToken<ArrayList<String>>() {}.getType();
        data = gson.fromJson(dataJson, typeData);
        viewData = gson.fromJson(viewDataJson, typeViewData);

        if(data == null){
            data = new MacroList();
        }
        if(viewData == null){
            viewData = new ArrayList<>();
        }

    }

    public void initialize(){
        total = (TextView) findViewById(R.id.total);
        btn = (Button) findViewById(R.id.btn);
        del = (Button) findViewById(R.id.del);
        clear = (Button) findViewById(R.id.clear);
        ls = (ListView) findViewById(R.id.list);
        //macro list is derived from ArrayList, will share indexes when adding to both at the same time
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, viewData);
        ls.setAdapter(arrayAdapter);
    }


}
