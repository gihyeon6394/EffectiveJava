package com.effectiveJava.chapter2.item7;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * null처리를 해야할 때
 * 자기 메모리를 직접 관리하는 클래스일 때
 * Stack은 elements 배열을 가짐
 * 배열의 타입이 {@link Object} (참조형)
 * pop()하고 더이상 쓰지 않을 원소 (비활성 영역)가 차지한 메모리를 누가 관리하는가? => GC는 알지 못함.
 *
 * */
public class Stack {
    private Object[] elements; // stack element
    private int size; //stack size

    private static final int DEFAULT_INITIAL_CAPACITY = 16; // stack default size

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object element) {
        ensureCapacity();
        elements[size++] = element;
    }

    /**
     * memory leak!!!
     * 뺴낸 객체의 참조를 해제하지 않음
     * solution : 다쓴 객체의 참조 해제
     */
    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[--size] = null; // pop 할 객체는 참조 해제
        return result;
    }

    /**
     * stack의 공간이 다찼을 때 사이즈를 2배로 늘려줌
     */
    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }

    }
}
