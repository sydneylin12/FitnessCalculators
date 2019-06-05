package com.sidlin.fitnessapp;

import java.util.ArrayList;

public class MacroList extends ArrayList<MacroTableObject> {
    public boolean contains(String s){
        String name;
        for(int i = 0; i < this.size(); i++){
            name = this.get(i).getName(); //returns name of a food at index i
            if(name.equals(s)){
                return true; //returns if item found with the SAME NAME
            }
        }
        return false;
    }

    public int find(String s){
        String name;
        for(int i = 0; i < this.size(); i++){
            name = this.get(i).getName(); //returns name of a food at index i
            if(name.equals(s)){
                return i; //returns first index
            }
        }
        return -1;
    }


    public void delete(int i){
        this.remove(i);
    }

    public int totalCals(){
        int rtrn = 0;
        for(int i = 0; i < this.size(); i++){
            rtrn += this.get(i).calculate();
        }
        return rtrn;
    }
}
