package com.effectiveJava.chapter2.item4;

public class UtilityOne {

    public static int no = 10;

    /**
     * private 생성자 : 객체화를 못하게 만듦
     * 단, private 생성자 자체가 어색한 것이므로 주석에 그 목적을 반드시 달아주자.
     */
    private UtilityOne() {
        // UtilityOne 의 인스턴스화를 막기위해 private 생성자 + 예외를 던짐!
        throw new AssertionError();
    }

    public static void plusNo() {
        no++;
        System.out.println("no : " + no);
    }
}
