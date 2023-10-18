package com.effectiveJava.chapter5.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack2<E> {
    private Object[] elements; // stack element
    private int size; //stack size

    private static final int DEFAULT_INITIAL_CAPACITY = 16; // stack default size


    public Stack2() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E element) {
        ensureCapacity();
        elements[size++] = element;
    }


    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
//        E result =  elements[--size];
        // push에서 E 타입만 허용하므로 이 형변환은 안전
        @SuppressWarnings("unchecked")
        E result = (E) elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }

    }
}
