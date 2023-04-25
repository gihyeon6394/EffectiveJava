package com.effectiveJava.chapter3.item13;

public class Parent implements Cloneable {


    /**
     * 자식클래스 clone() 재정의를 막음
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
