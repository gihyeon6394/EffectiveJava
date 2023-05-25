package com.effectiveJava.chapter4.item21;

public class Item21 {

    private interface  Interface1{
        void method1();
        default void method2(){System.out.println("Interface1 method2");}}

    private class Class1 implements Interface1{

        @Override
        public void method1() {System.out.println("Class1 method1");}

    }
}
