package com.effectiveJava.chapter5.item30;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.UnaryOperator;

public class Tmp {

    public static Set union1(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }

    public static <E> Set<E> union2(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<E>(s1);
        result.addAll(s2);
        return result;
    }

    @Test
    @DisplayName("제네릭 메서드 사용하기")
    public void ts1() {
        Set<String> aespa = Set.of("Winter", "Karina", "Ningning", "Giselle");
        Set<String> redVelvet = Set.of("Irene", "Seulgi", "Wendy", "Joy", "Yeri");
        Set<String> result = union2(aespa, redVelvet);
        System.out.println(result);

//        Set<Integer> ages = Set.of(20, 21, 22, 23, 24);
//        Set<String> result2 = union2(aespa, ages); // compile err : inference variable E has incompatible equality constraints Integer,String
    }

    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;


    // return UnaryOperator<T>
    // T -> Object는 항상 안전
    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    @Test
    @DisplayName("제네릭 싱글턴 팩터리")
    public void tst2() {
        // String 타입의 UnaryOperator
        UnaryOperator<String> sameString = identityFunction();
        String[] aespa = {"Winter", "Karina", "Ningning", "Giselle"};
        Arrays.stream(aespa).forEach(str -> System.out.println(sameString.apply(str)));

        // Number 타입의 UnaryOperator
        UnaryOperator<Number> sameNumber = identityFunction();
        Number[] numbers = {1, 2.0, 3L};
        Arrays.stream(numbers).forEach(num -> System.out.println(sameNumber.apply(num)));
    }

    // E 타입 Set의 원소 중 최대값 반환
    // 타입 E는 Comparable<E>를 구현해야 함
    public static <E extends Comparable<E>> E max(Set<E> s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException("빈 집합");
        }

        E result = null;
        for (E e : s) {
            if (result == null || e.compareTo(result) > 0) {
                result = e;
            }
        }

        return result;
    }

    @Test
    @DisplayName("Recursive type bound")
    public void tst3() {
        Set<String> aespa = Set.of("Winter", "Karina", "Ningning", "Giselle");
        System.out.println(max(aespa));

        Set<Integer> ages = Set.of(20, 21, 22, 23, 24);
        System.out.println(max(ages));
    }
}
