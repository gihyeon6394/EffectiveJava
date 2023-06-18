package com.effectiveJava.chapter5.item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Item28 {

    public static void main(String[] args) {
        testCovariant();
        testArrayGeneric();

        List<Integer> intList = List.of(1, 2, 3, 4, 5);
        Chooser chooser1 = new Chooser(intList);
        Object rndNo = chooser1.choose(); // 형변환 필요
    }

    private static void testArrayGeneric() {
        // ArrayList<String>[] stringLists = new ArrayList<String>[1]; // compile error : generic array creation
        // ArrayList<Integer> intList = Arrays.asList(1); // compile error : incompatible types: no instance(s) of type variable(s) T exist so that java.util.List<T> conforms to java.util.ArrayList<java.lang.Integer>
//        String[] strArray = new String[1];
//        Object[] objects = stringLists;
//        objects[0] = intList;
//        String s = stringLists[0].get(0);
    }

    private static void testCovariant() {

        // runtime error
        Object[] objectArray = new Long[1];
        objectArray[0] = "I don't fit in"; // Throws ArrayStoreException

        // compile error
        // List<Object> list = new ArrayList<Long>(); // incompatible types: java.util.ArrayList<java.lang.Long> cannot be converted to java.util.List<java.lang.Object>
    }

/*    public static class Chooser {
        private final Object[] choiceArray;

        public Chooser(Collection choices) {
            this.choiceArray = choices.toArray();
        }

        public Object choose() {
            return choiceArray[(int) (Math.random() * choiceArray.length)];
        }
    }*/

    public static class Chooser<T> {
        private final List<T> choiceList;

        public Chooser(Collection<T> choices) {
            choiceList = new ArrayList<>(choices);
        }

        public T choose() {
            Random rnd = ThreadLocalRandom.current();
            return choiceList.get(rnd.nextInt(choiceList.size()));
        }
    }
}
