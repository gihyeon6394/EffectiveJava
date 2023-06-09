package com.effectiveJava.chapter4.item24;

import java.util.AbstractList;import java.util.List;public class Outer {

    private String outerValue = "this is outer value";
    public void outerMethod() {
        System.out.println("this is outer Method");
    }

    public class Nested {
        public void test() {
            System.out.println("Nested");
        }
    }

    public static class NestedStaticMember{

        public void test() {
            Outer outer = new Outer();
            System.out.println("outerValue : " + outer.outerValue);
        }
    }

    public NestedMember makeNestedMemberInstance() {
        return new NestedMember();
    }
    public class NestedMember{
        public void test() {
            Outer outer = new Outer();
            System.out.println("outerVlaue : "+ outer.outerValue);
            System.out.println("outerVlaue : "+ Outer.this.outerValue);
            Outer.this.outerMethod();
        }
    }

    private static class NestedStaticPrivateMember{
        public void test() {
            System.out.println("i'll not use outer value!");

            Outer outer = new Outer();
            System.out.println("outerVlaue : "+ outer.outerValue); // 가능은 함
            outer.outerMethod();
        }
    }

    void testAnonymousClass() {
        new AnonymousInterface() {
            @Override
            public void test() {
                System.out.println("this is anonymous class");
                System.out.println("outerVlaue : "+ outerValue);
            }
        };
    }
    public interface AnonymousInterface {
        void test();
    }

    public List<Integer> intArrayAsList(int[] a) {

        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return a[index]; // 오토박싱(아이템 6)
            }

            @Override
            public Integer set(int index, Integer element) {
                int oldVal = a[index];
                a[index] = element; // 오토언박싱
                return oldVal; // 오토박싱
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }

    class Inner {
        public void test() {
            System.out.println("Inner");
        }

    }
}
