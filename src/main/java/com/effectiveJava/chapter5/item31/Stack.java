package com.effectiveJava.chapter5.item31;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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

    //    public void pushAll(Iterable<E> src){
    // E는 생산자 : E를 생산
    public void pushAll(Iterable<? extends E> src) {
        for (E e : src) {
            push(e);
        }
    }

    //   public void popAll(Collection<E> dst){
    // E는 소비자 : E를 소비해서, dst에 넣는다.
    public void popAll(Collection<? super E> dst) {
        while (!isEmpty()) {
            dst.add(pop());
        }
    }

    @Test
    @DisplayName("pushAll")
    public void tstPushAll() {
        Stack<Number> numberStack = new Stack<>();
        Iterable<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        numberStack.pushAll(integers);
    }

    @Test
    @DisplayName("popAll")
    public void tstPopAll() {
        Stack<Number> numberStack = new Stack<>();
        numberStack.push(1);
        numberStack.push(2);
        numberStack.push(3);

        Collection<Object> objects = new ArrayList<>();
        numberStack.popAll(objects);
    }

}
