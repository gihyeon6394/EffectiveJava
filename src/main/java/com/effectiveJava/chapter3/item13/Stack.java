package com.effectiveJava.chapter3.item13;

import java.util.Arrays;
import java.util.EmptyStackException;


/**
 * Cloneable을 구현하는 클래스가 가변객체 변수를 가질 때 (안좋은 예)
 * 가변객체 elements를 참조하는 클래스
 */
public class Stack implements Cloneable {
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

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[--size] = null; // pop 할 객체는 참조 해제l
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public Object[] getElements() {
        return elements;
    }

    public void setElements(Object[] elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }

    @Override
    public Stack clone() {
        try {
            return (Stack) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
