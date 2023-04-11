package com.effectiveJava.chapter2.item6;

import java.util.regex.Pattern;

/**
 * 불필요한 객체 생성을 피하라
 * : 매번 똑같은 기능의 객체는 새롭게 생성하지말고, 재사용해라 (defensive copy 반대)
 * <p>
 * Java 객체 재사용의 예
 * - String
 * - Boolean 대신  Boolean.valueOf() 팩터리 메서드
 * - 생성자 대신 팩터리 메서드를 사용하는 것들 {@link com.effectiveJava.chapter2.item1.User}
 * <p>
 * 불필요한 객체생성의 예
 * - String을 생성자로 초기화
 * - {@link Pattern}과 같이 생산 비용이 비싼 객체를 한번쓰고 버리기
 * - auto boxing (ex. long 대신 Long)
 * <p>
 * defensive copy (방어적 복사) : 기존객체를 사용하지 말고 재사용해라.
 * 방어적 복사를 이용하면 버그, 보안 개선
 * 불필요한 객체생성을 피하면 코드 모양과 성능 개선
 */
public class Item6 {

    /**
     * 생산 비용이 비싼 {@link Pattern}은 정적 초기화 하여 후에 재사용한다.
     * lazy initialization (지연 초기화)를 통해 사용하지 않는다면 애초에 초기화 하지 않도록 할수도 있음 (비추)
     * 이유 : lazy initialization 코드를 복잡하게 하고, 성능도 딱히 도움 안됨
     */
    private static final Pattern patternHasNumber = Pattern.compile(".*[0-9]+.*");


    public static void main(String[] args) {
        // bad
        String strBad = new String("bad object");

        /**
         * good
         * 하나의 String instance를 사용함
         * 같은 가상머신 안에서 모든 코드가 같은 객체를 재사용함을 보장
         *
         * */
        String strGood = "good Object";

        /**
         * 생산 비용이 비싼 객체는 '캐싱'하고 재사용하라
         * {@link java.util.regex.Pattern}
         * .matches()는 내부적으로 {@link java.util.regex.Pattern}을 사용함
         * */

        /**
         * 문제점 : 매번 {@link java.util.regex.Pattern} 객체를 만듦
         * solution : {@link java.util.regex.Pattern}을 클래스 초기화 (정적 초기화)하여 재사용
         * */
        boolean isCorrectBad = "abc123xxx".matches(".*[0-9]+.*");
        System.out.println(isCorrectBad);

        boolean isCorrectGood = patternHasNumber.matcher("abc123xxx").matches();
        System.out.println(isCorrectGood);

        sum();

    }

    /**
     * 불필요한 auto boxing 예시
     * Long은 불변객체. for문의 횟수 (Integer.MAX_VALUE)만큼 {@link Long}을 쓰고 버림
     * solution :  Long -> long
     */
    private static void sum() {
        // Long sum = 0L;
        long sum = 0L;

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }

        System.out.println("결과 : " + sum);
    }
}
