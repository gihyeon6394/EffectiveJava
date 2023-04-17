package com.effectiveJava.chapter3.item10;

import java.util.Objects;

/**
 * eqauls 는 일반 규약을 지켜 재정의하라
 * :
 * equals 를 잘못 재정의 하면 잘못된 결과를 초래.
 * 잘못할 거 같으면 애초에 재정의하지 않는 것이 낫다.
 * equals는 재정의 하지 않으면 오직 자기 자신과만 같다.
 * 즉 equals()의 default는 identity 비교이다.
 */


public class Item10 {

/**
 * equals 를 재정의하면 안될 때, 혹은 할 필요 없을 때 (다음중 하나라도)
 * 1. 각 인스턴스가 본질적으로 고유할 때
 * ex. Thread
 * 2. logical equality를 검사할 일이 없을 때
 * - 클라이언트가 객체의 논리적 동치성을 검사할 일이 없는 클래스
 * 3. 상위 클래스의 equals가 하위 클래스에도 적절
 * ex. {@link java.util.Set}, {@link java.util.List}, {@link java.util.Map}
 * 4. class가 private이거나 packaget private이고, equals() 호출을 강제로 막고 싶을 때
 * 5. 불변 클래스이며 factory method를 사용한 객체 (인스턴스가 하나뿐이기 떄문)
 * */

/**
 * equals를 재정의해야할 때 (다음중 하나라도)
 * -  identity 말고 equality를 비교해야할때 (&& 상위클래스가 재정의 하지 않았을 때)
 * - 주로 값 클래스 (불변 말고)
 * - ex. {@link Integer}, {@link String}
 *장점
 * -프로그래머가 논리적 비교 (euqality)가 가능해짐
 * - {@link java.util.Set}, {@link java.util.Map}의 원소로 사용 (정상적으로)
 *
 * */


    /**
     * equals 규약
     * 1. reflexivity (반사성) : 객체는 항상 본인과 같아야한다.
     * null이 아닌 참조값 x에 대해
     * x.equals(x)가 true이다.
     * <p>
     * 2. symmetry (대칭성) : 서로의 동치여부가 같다.
     * null이 아닌 모든 참조값 x, y에 대해
     * x,euqals(y)가 true이면 -> y.eqauls(x)도 true 이다.
     * <p>
     * 3. trasitivity (추이성)
     * null이 아닌 모든 참조값 x, y, z에 대해
     * x.equals(y)가 true && y.equals(z)가 true이면 -> x.equals(z)도 true이다.
     * <p>
     * 4. consitency (일관성)
     * null이 아닌 모든 참조값 x, y에 대해
     * x.equals(y)를 반복해서 호춯하면 항상 true를 반환하거나 false를 반환해야한다.
     * <p>
     * 5. null-아님
     * null이 아닌 모든 참조 값 x에 대해
     * x.equals(null)은 false다.
     */
    public static void main(String[] args) {

        // reflexivity가 안되었다면?
        Integer a = 1;
        java.util.Set<Integer> set = new java.util.HashSet<>();

        set.add(a);
        System.out.println(set.contains(a)); //여기서 false를 뱉을 것!! contains는 내부적으로 equals를 사용
        System.out.println("==========");

        CaseInSensitive1 caseSensitive1 = new CaseInSensitive1("Hani");
        String hani1 = "hani";
        System.out.println(caseSensitive1.equals(hani1)); // true
        System.out.println(hani1.equals(caseSensitive1)); //false
        System.out.println("==========");

        CaseInSensitive2 caseSensitive2 = new CaseInSensitive2("Hani");
        String hani2 = "hani";
        CaseInSensitive2 caseSensitive3 = new CaseInSensitive2("hani");

        System.out.println(caseSensitive2.equals(hani2)); // false
        System.out.println(hani1.equals(caseSensitive2)); // false
        System.out.println(caseSensitive3.equals(caseSensitive2)); // true
        System.out.println(caseSensitive2.equals(caseSensitive3)); // true


    }

    /**
     * symmetry 오류 예제
     * - 한방향으로만 작동하는 오류
     * 의도 : s 가 대소문자 구별 없이 같기만 하면 true
     * solution : {@link CaseInSensitive1}의 equals를 String과 연동하려하지 마라
     */
    public static final class CaseInSensitive1 {
        private final String s;

        public CaseInSensitive1(String s) {
            this.s = Objects.requireNonNull(s);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof CaseInSensitive1) {
                return s.equals(((CaseInSensitive1) o).s);
            } else if (o instanceof String) {
                return s.equalsIgnoreCase((String) o); // "String과도 비교할 수 있지 않을까?" => 한방향으로만 작동하게 만든 코드 (shit)
            } else {
                return false;
            }
        }
    }

    public static final class CaseInSensitive2 {
        private final String s;

        public CaseInSensitive2(String s) {
            this.s = Objects.requireNonNull(s);
        }

        @Override
        public boolean equals(Object o) {
            return (o instanceof CaseInSensitive2) && ((CaseInSensitive2) o).s.equalsIgnoreCase(s);
        }
    }




    /**
     * equals() 호출을 막는다.
     */
    @Override
    public boolean equals(Object o) {
        throw new AssertionError();
    }

}
