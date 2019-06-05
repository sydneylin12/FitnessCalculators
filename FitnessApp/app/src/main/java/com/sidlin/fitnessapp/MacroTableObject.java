package com.sidlin.fitnessapp;

public class MacroTableObject {
    private String name;
    private int pro, carb, fat, servings;
    /*
    A table object that will be used in a linked list
    Has name, and nutritional contents
     */
    public MacroTableObject(String name, int pro, int carb, int fat, int servings){
        this.name = name;
        this.pro = pro;
        this.carb = carb;
        this.fat = fat;
        this.servings = servings;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPro(){
        return pro;
    }

    public int getCarb(){
        return carb;
    }

    public int getFat(){
        return this.fat;
    }

    public int getServings(){
        return this.servings;
    }

    public int calculate(){
        return servings*(pro*4 + carb*4 + fat*9);
    }

    public String toString(){
        String rtrn = name + "\nP: "  + pro + ", C: " + carb + ", F: "  + fat + ", Servings: " + servings + ", Cals: " + this.calculate();
        return rtrn;
    }


}
