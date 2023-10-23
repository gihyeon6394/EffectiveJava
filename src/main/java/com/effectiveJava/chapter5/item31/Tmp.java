package com.effectiveJava.chapter5.item31;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;

public class Tmp {

    // E는 생산자 : return 타입
//    public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
//    public static <E> Set<? extends E> union(Set<? extends E> s1, Set<? extends E> s2) {
    public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
        Set<E> result = new HashSet<E>(s1);
        result.addAll(s2);
        return result;
    }

    @Test
    @DisplayName("한정적 와일드 카드 사용하기")
    public void ts1() {
        Set<Integer> integers = Set.of(1, 2, 3);
        Set<Double> doubles = Set.of(1.1, 2.2, 3.3);
        /**
         *   required: Set<E>,Set<E>
         *   found: Set<Integer>,Set<Double>
         *   reason: inference variable E has incompatible equality constraints Double,Integer
         *   where E is a type-variable:
         *     E extends Object declared in method <E>union(Set<E>,Set<E>)
         *
         */
//        Set<? extends Number> numbers = union(integers, doubles);
        Set<Number> numbers = union(integers, doubles);

    }

    // E는 생산
//    public static <E extends Comparable<E>> E max(List<E> list) {
    public static <E extends Comparable<? super E>> E max(List<? extends E> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("빈 집합");
        }

        E result = null;
        for (E e : list) {
            if (result == null || e.compareTo(result) > 0) {
                result = e;
            }
        }

        return result;
    }

    @Test
    @DisplayName("한정적 와일드 카드 사용하기")
    public void tst3() {
        List<String> aespa = List.of("Winter", "Karina", "Ningning", "Giselle");
        System.out.println(max(aespa));

        List<Integer> ages = List.of(20, 21, 22, 23, 24);
        System.out.println(max(ages));


        /**
         *   required: List<E>
         *   found: List<ScheduledFuture<?>>
         *   reason: inference variable E has incompatible equality constraints Delayed,ScheduledFuture<?>
         *   where E is a type-variable:
         *     E extends Comparable<E> declared in method <E>max(List<E>)
         * */
        List<ScheduledFuture<?>> scheduledFutures = List.of();
        System.out.println(max(scheduledFutures));
    }

}
