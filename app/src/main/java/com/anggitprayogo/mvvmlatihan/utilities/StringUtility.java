package com.anggitprayogo.mvvmlatihan.utilities;

/**
 * Created by Personal on 25/03/18.
 */

public class StringUtility {

    //Bikin Number jadi String
    public static String stringFromNumber(int... numbers){
        StringBuilder stringBuilder = new StringBuilder();
        for (int number : numbers){
            stringBuilder.append(number);
        }

        return stringBuilder.toString();
    }

    public static boolean isNullOrEmpty(String value){
        return  value == null || value.length() == 0;
    }
}
