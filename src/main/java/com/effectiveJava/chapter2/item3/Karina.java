package com.effectiveJava.chapter2.item3;

/**
 * Singletone 만들기 3 : 열거
 */
public enum Karina {
    INSTANCE;

    public void work() {
        System.out.println("Karina is working...");
    }

}
