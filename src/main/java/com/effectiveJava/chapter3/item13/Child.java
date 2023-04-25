package com.effectiveJava.chapter3.item13;

public class Child extends Parent {


    @Override
    protected Object clone() throws CloneNotSupportedException {
        try {
            return (Child) super.clone(); // throw exception
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
