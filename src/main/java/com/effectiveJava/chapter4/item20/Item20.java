package com.effectiveJava.chapter4.item20;

import java.util.AbstractList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Item20 {

    public interface Idol {
        void sing();
    }

    public interface Actor {
        void act();
    }

    public interface IdolActor extends Idol, Actor {
        void sing();

        void act();
    }


    public interface Connector {

        /**
         * @implSpec 이 구현체는 다음과 같은 과정을 거쳐 연결한다.
         */
        default void connect() {
            //.... (상세 구현 생략)
            System.out.println("미리 구현해두었으니 그대로 사용하세요.");
        }
    }


    /**
     * int 배열을 Integer List로 변환하는 메서드
     */
    static List<Integer> intArrayAsList(int[] a) {

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

    public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {

        @Override
        public V setValue(V value) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Map.Entry)) {
                return false;
            }
            Map.Entry<?, ?> e = (Map.Entry) o;
            return Objects.equals(e.getKey(), getKey()) && Objects.equals(e.getValue(), getValue());
        }
    }
}
