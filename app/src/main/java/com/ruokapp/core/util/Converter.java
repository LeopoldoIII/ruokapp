package com.ruokapp.core.util;

public class Converter {

    public static boolean convertIntToBoolean(int value){
        return value == 1 ;
    }

    public static int convertBooleanToInt(boolean value){
        return value ? 1 : 0;
    }

}
