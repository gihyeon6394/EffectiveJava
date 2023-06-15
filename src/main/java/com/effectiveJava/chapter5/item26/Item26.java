package com.effectiveJava.chapter5.item26;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Item26 {
    public static void main(String[] args) {

        List list = new ArrayList();
        list.add("1");
        list.add(1);
        list.add(2.90);

        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, Integer.valueOf(42));
        unsafeAdd(strings, "42");

        int a = 0;

        /**
         * 컴파일러가 자동 형변환 코드를 넣음
         * java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String
         * */
        // String s = strings.get(0);


        // safeAdd(strings, Integer.valueOf(42)); // compile error : incompatible types: java.util.List<java.lang.String> cannot be converted to java.util.List<java.lang.Object>

        Set<Integer> integers = Set.of(1, 2, 3);
        Set<Double> doubles = Set.of(1.0, 2.0, 3.0);
        integers.add(1);

        numElementInCommon(integers, doubles); // compile warning : Raw use of parameterized class 'Set'

        Set<?> set1 = Set.of(1, 2, 3.0);
        set1.add(null);
        //set1.add(1.9); // compile error : incompatible types: java.lang.Double cannot be converted to capture#1 of ?
        //set1.add(2); // incompatible types: int cannot be converted to capture#2 of ?

        Set<Integer> set2 = Set.of(1, 2, 3);
        set2.add(2);

    }

    private static void unsafeAdd(List list, Object val) { // compile warning : Raw use of parameterized class 'List'
        list.add(val);
    }

    private static void safeAdd(List<Object> list, Object val) { // compile warning : Raw use of parameterized class 'List'
        list.add(val);
    }

    private static int numElementInCommon(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o1 : s1) {
            if (s2.contains(o1)) {
                result++;
            }
        }
        return result;
    }

    private static void checkInstanceOfSet(Object o ){
        if(o instanceof Set){
          Set<?> set = (Set<?>) o;
          // ...
        }
    }
}
