package com.codeWhisperer;

/**
 * Created by jon on 7/21/17.
 * This is a class for practicing
 * @author kkh04511
 * @version 1.0
 * */
public class Practice {

    // make main method
    public static void main(String[] args) {
        //print hello CodeWhisperer
        System.out.println("Hello CodeWhisperer");
    }

    // return String is Empty?
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    // return Integer is not null and bigger than 1011
    public static boolean numberIsNotNullAndBiggerThan1011(Integer integer) {
        return integer != null && integer > 1011;
    }

    // throw Exception when parameter is not int
    public static void throwExceptionWhenParameterIsNotInt(Object object) throws Exception {
        if (object instanceof Integer) {
            throw new Exception("Parameter is not int");
        }
    }

    // make inner class extends to NumberFormatException

}
