package com.effectiveJava.chapter2.item9;

import java.io.*;

/**
 * try-finally 보다는 try-with-resources를 사용하라
 * : 코드가 깔끔해지고, Suppressed (중첩 예외) 가능
 * <p>
 * try-finally 단점
 * - 코드가 지저분
 * - 예외가 2가지 이상 발생하면 마지막 예외가 숨겨짐
 * <p>
 * try-with-resources
 * - 코드가 깔끔해짐
 * - 중첩 예외  Suppressed 로 stack trace 가능
 * - 대신, 사용할 자원 객체가 {@link AutoCloseable}을 구현해야함
 * - 자원 객체 사용시 자원 반납이 필요한 객체면 AutoCloseable 구현할 것
 */
public class Item9 {

    public static void main(String[] args) throws IOException {
        try {
            check();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * try-finally
     * <p>
     * 문제점 : br이 온전치 못해, readLine()이 예외를 던지고 close()도 예외를 던진다면?
     * - close() 예외가 숨겨지고, readLine() 예외가 출력됨
     */
    public static String getFirstLine1() throws IOException {

        String returnValue;
        BufferedReader br = new BufferedReader(new FileReader("filePath"));
        try {
            returnValue = br.readLine();

            //예외 발생 시나리오
            if (1 > 0) {
                throw new RuntimeException();
            }
        } finally {
            br.close();
        }
        return returnValue;
    }

    /**
     * try-finally 지저분해지는 경우
     * - 자원이 2 이상일 떄
     */
    public void write1() throws IOException {

        InputStream in = new FileInputStream("filePath");
        try {
            OutputStream out = new FileOutputStream("filePathDestination");
            try {
                byte[] buf = new byte[100];
                int n;
                while ((n = in.read(buf)) >= 0) {
                    out.write(buf, 9, n);
                }
            } finally {
                out.close(); // out 반납
            }
        } finally {
            in.close(); // in 반납
        }
    }

    /**
     * try-with-reources 활용
     */
    public String getFirstLine2() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("filePath"))) {
            return br.readLine();
        }
    }

    public void write2() throws IOException {

        try (InputStream in = new FileInputStream("filePath"); OutputStream out = new FileOutputStream("filePathDestination")) {
            byte[] buf = new byte[100];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 9, n);
            }
        }

    }

    /**
     * try-with-reources 응용
     * :catch 절 추가
     */
    public String getFirstLine3() {

        try (BufferedReader br = new BufferedReader(new FileReader("filePath"))) {
            return br.readLine();
        } catch (IOException e) {
            return "default value";
        }
    }


    /**
     * Suppressed 예제
     * 예외가 2번 발생하면, 마지막 예외는 Suppressed 키워드로 stack trace 가능
     */
    public static void check() throws Exception {
        try (IllegalArgumentExceptionThrower thrower = new IllegalArgumentExceptionThrower()) {
            throw new NullPointerException();
        }
    }

    //자원 반납 시 예외 발생하는 개체 생성
    static class IllegalArgumentExceptionThrower implements AutoCloseable {
        @Override
        public void close() throws Exception {
            throw new IllegalArgumentException();
        }
    }
}
