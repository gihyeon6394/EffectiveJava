package com.effectiveJava.chapter2.item1;

public class UserSub extends User{

    @Override
    public String toString() {
        return "나는 하위 클래스! UserSub{" +
                "userName='" + userName + '\'' +
                ", home='" + home + '\'' +
                '}';
    }
}
