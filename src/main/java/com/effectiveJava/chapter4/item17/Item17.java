package com.effectiveJava.chapter4.item17;

/**
 * item 17. 변경 가능성을 최소화하라
 */
public class Item17 {


    public static void main(String[] args) {
        // 불변 클래스를 사용하는 예
        Complex complex = new Complex(1, 2);
        Complex complex1 = new Complex(3, 4);
        Complex complex2 = complex.plus(complex1);
        // complex.plus(complex1) 은 complex1 과 complex2 를 변경하지 않는다.
        // 이것이 함수형 프로그래밍

        ComplexNotFinal complexNotFinal = ComplexNotFinal.valueOf(1, 2);
        ComplexNotFinal complexNotFinal1 = ComplexNotFinal.valueOf(3, 4);

        testMyObj();

    }

    private static void testMyObj() {

        System.out.println(IdolMember.KARINA);
        System.out.println(IdolMember.MINZI);

        System.out.println(IdolGroup.NEWJEANS);
        System.out.println(IdolGroup.AESPA);

        IdolMember idolMember = new IdolMember.Builder("윈터").age(2001).isLeader(0).isDebut(1).build();
        System.out.println(idolMember);

        IdolGroup IVE = IdolGroup.of("아이브", "스타쉽");
        System.out.println(IVE);
        System.out.println("소속사를 이동하면 새로운 객체 탄생한다  : " + (IVE != IVE.changeAgency("SM")));


    }

    /**
     * 함수형 프로그래밍을 적용한 불변 클래스
     */
    public static final class Complex {

        /**
         * 자주 사용하는 인스턴스는 상수로 제공
         */
        public static final Complex ZERO = new Complex(0, 0);
        public static final Complex ONE = new Complex(1, 0);
        public static final Complex I = new Complex(0, 1);


        private final double re;
        private final double im;

        private Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }

        public double realPart() {
            return re;
        }

        public double imaginaryPart() {
            return im;
        }

        public Complex plus(Complex c) {
            return new Complex(re + c.re, im + c.im);
        }

        public Complex minus(Complex c) {
            return new Complex(re - c.re, im - c.im);
        }

        public Complex times(Complex c) {
            return new Complex(re * c.re - im * c.im, re * c.im + im * c.re);
        }

        public Complex dividedBy(Complex c) {
            double tmp = c.re * c.re + c.im * c.im;
            return new Complex((re * c.re + im * c.im) / tmp, (im * c.re - re * c.im) / tmp);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Complex)) {
                return false;
            }
            Complex c = (Complex) o;

            return Double.compare(c.re, re) == 0 && Double.compare(c.im, im) == 0;
        }

        @Override
        public int hashCode() {
            return 31 * Double.hashCode(re) + Double.hashCode(im);
        }

        @Override
        public String toString() {
            return "(" + re + " + " + im + "i)";
        }
    }

    /**
     * final이 아닌 불변 클래스
     */
    public static class ComplexNotFinal {
        private final double re;
        private final double im;

        private ComplexNotFinal(double re, double im) {
            this.re = re;
            this.im = im;
        }

        public static ComplexNotFinal valueOf(double re, double im) {
            return new ComplexNotFinal(re, im);
        }
        //.. (중략)
    }
}
