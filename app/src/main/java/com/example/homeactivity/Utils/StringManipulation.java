package com.example.homeactivity.Utils;

/**
 * Created by Mingeon on 23/10/2019.
 */

public class StringManipulation {

    public static String expandUsername(String username){
        return username.replace("."," ");
    }

    public static String condenseUsername(String username){
        return username.replace(" ",".");
    }
}
