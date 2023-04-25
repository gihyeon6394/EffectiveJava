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

    /**
     * 복사 생성자 (deepCopy)
     */
    public Stack(Stack stack) {

        size = stack.size;
        PhoneNumber[] elementsCopy = new PhoneNumber[stack.elements.length];
        for (int i = 0; i < stack.elements.length - 1; i++) {
            if (stack.elements[i] == null) {
                break;
            }
            PhoneNumber phoneNumber = (PhoneNumber) stack.elements[i];
            elementsCopy[i] = new PhoneNumber(phoneNumber.getTelecom(), phoneNumber.getPhoneNumber());
        }

        elements = elementsCopy;
    }

    /**
     * 복사 팩터리
     */

    public static Stack newInstance(Stack stack) {
        Stack result = new Stack();
        result.size = stack.size;
        PhoneNumber[] elementsCopy = new PhoneNumber[stack.elements.length];
        for (int i = 0; i < stack.elements.length - 1; i++) {
            if (stack.elements[i] == null) {
                break;
            }
            PhoneNumber phoneNumber = (PhoneNumber) stack.elements[i];
            elementsCopy[i] = new PhoneNumber(phoneNumber.getTelecom(), phoneNumber.getPhoneNumber());
        }

        result.elements = elementsCopy;
        return result;
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
        /*try {
            return (Stack) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }*/

        /*try {
            Stack result = (Stack) super.clone();
            result.elements = elements.clone(); //배열의 clone() 활용
            return result;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }*/

        /** deepCopy를 이용한 clone*/
        try {
            Stack result = (Stack) super.clone();
            PhoneNumber[] elementsCopy = new PhoneNumber[elements.length];
            for (int i = 0; i < elements.length - 1; i++) {
                if (elements[i] == null) {
                    break;
                }
                PhoneNumber phoneNumber = (PhoneNumber) elements[i];
                elementsCopy[i] = new PhoneNumber(phoneNumber.getTelecom(), phoneNumber.getPhoneNumber());
            }

            result.elements = elementsCopy; //배열의 clone() 활용
            return result;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
