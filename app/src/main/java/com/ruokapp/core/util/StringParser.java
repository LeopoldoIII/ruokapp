package com.ruokapp.core.util;

public class StringParser {

    private static final int MAX_LENGTH_TITLE_FAV = 20;

    public static String getRecipeTitleToFav(String title){
        String[] titleSplit = title.split(" ");
        int i=0;
        int j=0;
        String parserTitle = "";
        while (i<titleSplit.length){
            if(j<MAX_LENGTH_TITLE_FAV){
                parserTitle +=titleSplit[i];
            } else{
                parserTitle += "\n"+titleSplit[i];
                j=0;
            }
            parserTitle+=" ";
            j +=titleSplit[i].length();
            i++;
        }
        return parserTitle;
    }
}
