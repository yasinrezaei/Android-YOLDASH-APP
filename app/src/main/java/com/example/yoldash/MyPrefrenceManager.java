package com.example.yoldash;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPrefrenceManager {
    public static MyPrefrenceManager instance=null;

    private SharedPreferences sharedPreferences=null;
    private SharedPreferences.Editor editor=null;


    public static  MyPrefrenceManager getIstance(Context context){
        if (instance==null){
            instance=new MyPrefrenceManager(context);
        }
        return instance;
    }
    private MyPrefrenceManager(Context context){
        sharedPreferences=context.getSharedPreferences("MyPrefrences",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    //UserName//
    public String getUserName(){
        return sharedPreferences.getString("user_name","User");
    }
    public void putUserName(String username){
        editor.putString("user_name",username);
        editor.apply();
    }
    //Status//
    public String getStatus(){
        return sharedPreferences.getString("status","Learning");
    }
    public void putStatus(String status_text){
        editor.putString("status",status_text);
        editor.apply();
    }
    //Male Or Female//
    public String getSex(){
        return sharedPreferences.getString("sex","male");
    }
    public void putSex(String sex_num){
        editor.putString("sex",sex_num);
        editor.apply();
    }
    //Dark or Light//
    public boolean getMode(){
        return  sharedPreferences.getBoolean("Mode",true);
    }
    public void putMode(boolean mode){
        editor.putBoolean("Mode",mode);
        editor.apply();
    }

    ///
    //Dark or Light//
    public int getpoint(){
        return  sharedPreferences.getInt("Point",1);
    }
    public void putpoint(int point){
        editor.putInt("Point",point);
        editor.apply();
    }

}


