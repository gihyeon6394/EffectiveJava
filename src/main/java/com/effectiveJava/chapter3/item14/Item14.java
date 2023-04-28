package com.effectiveJava.chapter3.item14;


import java.math.BigDecimal;
import java.util.*;

/**
 * Comparable 을 구현할지 고려하라
 */
public class Item14 {

    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        s.add("c");
        s.add("b");
        s.add("a");
        s.add("d");
        System.out.println(s);

        // case1();
        // case2();
        case3();
    }

    private static void case3() {
        PhoneNumber phoneNumber1 = new PhoneNumber("B telecom", "123");
        PhoneNumber phoneNumber2 = new PhoneNumber("A telecom", "1234");
        PhoneNumber phoneNumber3 = new PhoneNumber("B telecom", "1234");
        Set<PhoneNumber> s = new TreeSet<>();
        s.add(phoneNumber1);
        s.add(phoneNumber2);
        s.add(phoneNumber3);
        System.out.println(s);
    }

    /**
     * CaseInSensitive 의 정렬
     * */
    private static void case2() {

        CaseInSensitive caseInSensitive1 = new CaseInSensitive("b");
        CaseInSensitive caseInSensitive2 = new CaseInSensitive("z");
        CaseInSensitive caseInSensitive3 = new CaseInSensitive("a");
        CaseInSensitive caseInSensitive4 = new CaseInSensitive("J");
        CaseInSensitive caseInSensitive5 = new CaseInSensitive("B");


        Set<CaseInSensitive> s = new TreeSet<>();
        s.add(caseInSensitive1);
        s.add(caseInSensitive2);
        s.add(caseInSensitive3);
        s.add(caseInSensitive4);
        s.add(caseInSensitive5);
        System.out.println(s); // a b j z 정렬

    }

    /**
     * equals()와 compareTo()의 일관성을 지키지 못하면 문제가 발생한다.
     */
    private static void case1() {

        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("1.00");

        System.out.println(a.equals(b)); // false
        System.out.println(a.compareTo(b)); // true(0)

        // equals()
        HashSet<BigDecimal> hashSet = new HashSet<>();
        hashSet.add(a);
        hashSet.add(b);
        System.out.println(hashSet.toString());

        // compareTo()
        TreeSet<BigDecimal> treeSet = new TreeSet<>();
        treeSet.add(a);
        treeSet.add(b);
        System.out.println(treeSet.toString());
    }

    public static final class CaseInSensitive implements Comparable<CaseInSensitive> {
        private final String s;

        public CaseInSensitive(String s) {
            this.s = Objects.requireNonNull(s);
        }

        @Override
        public boolean equals(Object o) {
            return (o instanceof CaseInSensitive) && ((CaseInSensitive) o).s.equalsIgnoreCase(s);
        }

        @Override
        public int compareTo(CaseInSensitive o) {
            return String.CASE_INSENSITIVE_ORDER.compare(s, o.s);
        }

        @Override
        public String toString() {
            return "CaseInSensitive{" +
                    "s='" + s + '\'' +
                    '}';
        }
    }
}
