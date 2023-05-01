package com.effectiveJava.chapter4.item16;

/**
 * item16. public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라
 */
public class Item16 {

    public static void main(String[] args) {

        Foo foo = new Foo();
        foo.a = 1;
    }

    class Point1 {
        public double x;
        public double y;
    }

    class Point2 {
        private double x;
        private double y;

        public Point2(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public void setX(double x) {
            this.x = x;
        }

        public void setY(double y) {
            this.y = y;
        }
    }

    /**
     * 불편 필드를 public으로 노출한 클래스
     */

    final class Time {
        private static final int HOURS_PER_DAY = 24;
        private static final int MINUTES_PER_HOUR = 60;

        public final int hour;
        public final int minute;

        public Time(int hour, int minute) {
            if (hour < 0 || hour >= HOURS_PER_DAY) {
                throw new IllegalArgumentException("시간: " + hour);
            }
            if (minute < 0 || minute >= MINUTES_PER_HOUR) {
                throw new IllegalArgumentException("분: " + minute);
            }
            this.hour = hour;
            this.minute = minute;
        }
    }

    private static class Foo {
        public int a;
        public int b;
    }
}
