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

    public static Float stringToFloat (String value){
        float f = 0;
        try {
            f = Float.parseFloat(value);
        }
        catch (Exception e){
            Log.e("Err stringToFloat",e.toString());
        }
        return f;
    }
}
