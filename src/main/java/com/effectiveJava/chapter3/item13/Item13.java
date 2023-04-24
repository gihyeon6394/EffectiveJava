package com.effectiveJava.chapter3.item13;


public class Item13 {

    public static void main(String[] args) {

        System.out.println(new PhoneNumber("skt", "01033333333"));

        PhoneNumber phoneNumber = new PhoneNumber("skt", "01033333333");
        PhoneNumber phoneNumber1 = phoneNumber.clone();

        System.out.println(phoneNumber1.equals(phoneNumber));
        System.out.println(phoneNumber1 == phoneNumber);

        Stack stack1 = new Stack();
        stack1.push(phoneNumber);
        stack1.push(phoneNumber1);

        Stack stack2 = stack1.clone();

        System.out.println(stack1 == stack2);
        // System.out.println(stack1.pop() == stack2.pop()); // true, 위험함
        stack1.pop();
        System.out.println(stack2.getElements().length);




    }


}