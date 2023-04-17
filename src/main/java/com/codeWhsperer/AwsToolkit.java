package com.codeWhsperer;

import org.junit.Test;

import java.io.IOException;

public class AwsToolkit {

    public static void main(String[] args) {
        System.out.println("Hello AWS Toolkit");
    }

    // throw NullPointerException when parmeter is null
    public static void checkNotNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        // make o To Integer
        Integer i = (Integer) o;

        System.out.println(i);
    }

    // catch When throw IOException
    public static void catchIOException() {
        try {
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
            // throw new Runtime Exception
            throw new RuntimeException();
        }
    }

    // make inner class Extend Exception
    public static class MyException extends Exception {
        public MyException() {
            super();
        }
    }

    // make inner class Extend RuntimeException
    public static class MyRuntimeException extends RuntimeException {
        public MyRuntimeException() {
            super();
        }
    }

    @Test
    public void testMyException() {
        try {
            throw new MyException();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    // 파라미터가 널인지 확인하는 메서드
    public static void checkNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    // 파라미터가 널일 때 메서드가 망했어요... 출력하는 메서드
    public static void printNull() {

        //여기서 좀 헤매네
        System.out.println("메서드가 ");
    }

    // when parameter is null then print "shit, parameter is null!!"
    public static void printWhenParmIsNull(String param){
        // 이것도 그닥이네
        System.out.println("shit, parameter is null!!");
    }



}
