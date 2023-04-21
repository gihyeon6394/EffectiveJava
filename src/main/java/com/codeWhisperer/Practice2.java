package com.codeWhisperer;
public class Practice2 {

    // make main method
    public static void main(String[] args) {
        // print hello word!
        System.out.println("Hello World!");

        int a = 10;

        int b = 20;
        int c = 30;
        System.out.println(a + b + c);
        System.out.println(a + b * c);
        System.out.println(a * b + c);

        // make String str1, str2 then equals each other
        String str1 = "Hello";
        String str2 = "Hello11";
        System.out.println(str1 == str2);
        System.out.println(str1.equals(str2));
        // make String str3, str4 then equals each other
        String str3 = "Hello";




    }

    // return String is null or empty
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    // throw NullPointerExcpetion when parameter is null
    public static void checkNotNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }

    // throw NullPointerException use isNullOrEmpty
    public static void checkNotNullOrEmpty(String str) {
        if (isNullOrEmpty(str)) {
            throw new NullPointerException();
        }
    }




}
