package com.example.taammar.helper;

import android.util.Log;

public class Utility {

    public static int stringToInt (String value){
        int a = 0;
        try {
            a = Integer.parseInt(value);
        }
        catch (Exception e ){
            Log.e("Error", e.toString());
        }
        return a;
    }
}
