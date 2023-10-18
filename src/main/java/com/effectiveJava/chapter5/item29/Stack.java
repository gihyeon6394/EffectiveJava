package com.effectiveJava.chapter5.item29;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> {
    private E[] elements; // stack element
    private int size; //stack size

    private static final int DEFAULT_INITIAL_CAPACITY = 16; // stack default size

    @SuppressWarnings("unchecked")
    public Stack() {
//        elements = new E[DEFAULT_INITIAL_CAPACITY]; // unchecked warning : java: generic array creation

        /*java: unchecked cast
        required: E[]
        found:    java.lang.Object[]*/
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E element) {
        ensureCapacity();
        elements[size++] = element;
    }


    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }

    }

    public boolean isEmpty() {
        return size == 0;
    }


    @Test
    @DisplayName("test")
    public void tst() {
        Stack<String> stack = new Stack<>();
        stack.push("Karina");
        stack.push("Jenny");
        stack.push("Jisoo");
        stack.push("Winter");
        stack.push("Joy");
        stack.push("Giselle");

        while (!stack.isEmpty()) {
            // stack의 pop은 String을 반환하므로 String의 메서드를 사용할 수 있다. (String으로 형변환 필요 없음)
            System.out.println(stack.pop().toUpperCase());
        }
    }

}
