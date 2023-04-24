package com.effectiveJava.chapter3.item13;


public class Item13 {

    public static void main(String[] args) {
        // case1();
        case2();


    }

    private static void case2() {
        PhoneNumber phoneNumber1 = new PhoneNumber("skt", "01033333333");
        PhoneNumber phoneNumber2 = new PhoneNumber("skt", "1231312222");
        Stack stack1 = new Stack();
        stack1.push(phoneNumber1);
        stack1.push(phoneNumber2);

        Stack stack2 = stack1.clone();

        System.out.println(stack1 == stack2);
        // System.out.println(stack1.pop() == stack2.pop()); // true, 위험함
        ((PhoneNumber) stack1.pop()).setTelecom("KT");
        System.out.println(stack2.pop()); //kt가 나옴 위험함

    }

    private static void case1() {
        System.out.println(new PhoneNumber("skt", "01033333333"));

        PhoneNumber phoneNumber = new PhoneNumber("skt", "01033333333");
        PhoneNumber phoneNumber1 = phoneNumber.clone();

        System.out.println(phoneNumber1.equals(phoneNumber));
        System.out.println(phoneNumber1 == phoneNumber);

        Stack stack1 = new Stack();
        stack1.push(phoneNumber);
        stack1.push(phoneNumber1);

    }


}