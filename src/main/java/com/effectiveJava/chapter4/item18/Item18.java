package com.effectiveJava.chapter4.item18;

import com.effectiveJava.chapter2.item1.User;

import java.util.*;

/**
 * item18. 상속보다는 컴포지션을 사용하라
 */
public class Item18 {

    public static void main(String[] args) {

        InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("틱", "탁탁", "펑"));

        System.out.println(s.getAddCount()); // 6

        InstrumentedSet<String> strSet = new InstrumentedSet<>(new TreeSet<>());
        strSet.addAll(List.of("틱", "탁탁", "펑"));
        System.out.println(strSet.getAddCount());



    }

    static void cntIdol(Set<String> idolSet){
        InstrumentedSet<String> idolSetNew = new InstrumentedSet<>(idolSet);
        // 여기선 idolSetNew 를 이용함
        System.out.println(idolSetNew.getAddCount()); // 3
    }

    /**
     * HashSet을 확장하여 추가된 원소를 알아내는 메서드 추가
     */

    static class InstrumentedHashSet<E> extends HashSet<E> {
        private int addCount = 0;

        public InstrumentedHashSet() {
        }

        public InstrumentedHashSet(int initCap, float loadFactor) {
            super(initCap, loadFactor);
        }

        @Override
        public boolean add(E e) {
            addCount++;
            return super.add(e);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            addCount += c.size();
            return super.addAll(c);
        }

        public int getAddCount() {
            return addCount;
        }
    }

    static class InstrumentedSet<E> extends ForwardingSet<E> {
        private int addCount = 0;

        public InstrumentedSet(Set<E> s) {
            super(s);
        }

        @Override
        public boolean add(E e) {
            addCount++;
            return super.add(e);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            addCount += c.size();
            return super.addAll(c);
        }

        public int getAddCount() {
            return addCount;
        }
    }

    static class ForwardingSet<E> implements Set<E> {

        private final Set<E> s;

        public ForwardingSet(Set<E> s) {
            this.s = s;
        }

        @Override
        public int size() {
            return s.size();
        }

        @Override
        public boolean isEmpty() {
            return s.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return s.contains(o);
        }

        @Override
        public Iterator<E> iterator() {
            return s.iterator();
        }

        @Override
        public Object[] toArray() {
            return s.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return s.toArray(a);
        }

        @Override
        public boolean add(E e) {
            return s.add(e);
        }

        @Override
        public boolean remove(Object o) {
            return s.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return s.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return s.addAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return s.retainAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return s.removeAll(c);
        }

        @Override
        public void clear() {
            s.clear();
        }
    }

}
