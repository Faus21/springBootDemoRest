package com.example.demo.templates;

import com.example.demo.entities.Seat;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static String capitalize(String toCapitalize){
        return toCapitalize.substring(0,1).toUpperCase() + toCapitalize.substring(1);
    }

    public static String capilizeLastName(String lastName){
        String[] splittedBySpace = lastName.split(" ");
        if (splittedBySpace.length > 1){
            return capitalize(splittedBySpace[0])+"-"+capitalize(splittedBySpace[1]);
        }

        String[] splittedByDash = lastName.split("-");
        if (splittedByDash.length > 1){
            return capitalize(splittedByDash[0])+"-"+capitalize(splittedByDash[1]);
        }

        return capitalize(lastName);
    }

    public static String createBinaryRow(List<Seat> seats, Integer maxSeatNumber){
        String binaryRow = "";
        seats.sort(Comparator.comparingInt(Seat::getSeatNumber));
        for (int i = 0, j = 0; i < maxSeatNumber; i++) {
            if (j < seats.size()){
                if (seats.get(j).getSeatNumber() - 1 == i){
                    j++;
                    binaryRow = binaryRow + "0";
                    continue;
                }
            }
            binaryRow = binaryRow + "1";
        }

        return binaryRow;
    }

    public static String createBinaryRowBySeatNumbers(List<Integer> seats, Integer maxSeatNumber){
        String binaryRow = "";
        seats.sort(Comparator.comparingInt(x -> x));
        for (int i = 0, j = 0; i < maxSeatNumber; i++) {
            if (j < seats.size()){
                if (seats.get(j) - 1 == i){
                    j++;
                    binaryRow = binaryRow + "1";
                    continue;
                }
            }
            binaryRow = binaryRow + "0";
        }

        return binaryRow;
    }
    
    public static boolean checkPlaces(String binaryRow){
        Pattern pattern = Pattern.compile("101");
        Matcher matcher = pattern.matcher(binaryRow);
        return matcher.find();
    }


    

}
