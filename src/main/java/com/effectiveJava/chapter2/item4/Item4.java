package com.effectiveJava.chapter2.item4;

/**
 * 인스턴스화를 막으려거든 private 생성자를 사용하라
 * : 정적 멤버들로만 이루어진 클래스 같은 것들은 객체가 될필요가 없음.
 * <p>
 * 용도 : 정적 메서드 + 정적 필드로만 이루어진 클래스 (유틸리티)
 * 기본적으로 이런 클래스는 객체지향적이지 않아서 좋은 건 아님 (그러나 그 쓰임이 있는 건 맞음)
 * <p>
 * 생성자를 명시하지 않으면, 기본생성자를 컴파일러가 만듦
 * 마음만 먹으면 인스턴스 생성 가능 {@link UtilityOne}
 * <p>
 * solution : private 생성자를 만들어 인스턴스화를 못하게 강제하라.
 * private 생성자의 장점
 * - 인스턴스화 불가능
 * - 상송 불가능
 */
public class Item4 {

    public static void main(String[] args) {
        UtilityOne.plusNo();

//        UtilityOne utilityOne = new UtilityOne(); // compile error: 'UtilityOne()' has private access in 'com.effectiveJava.chapter2.item4.UtilityOne'
//        utilityOne.plusNo();
    }
}
