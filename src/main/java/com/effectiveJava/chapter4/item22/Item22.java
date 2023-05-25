package com.effectiveJava.chapter4.item22;

public class Item22 {

    /**
     * 상수 인터페이스
     * : 메서드 없이 상수만 있는 인터페이스
     * 인터페이스 안티패턴 - 사용금지!
     */
    private interface PhysicalConstants {
        static final double AVOGADROS_NUMBER = 6.022_140_857e23;
        static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
        static final double ELECTRON_MASS = 9.109_383_56e-31;
    }

    private class ccc implements PhysicalConstants {

        double BOLTZMANN_CONSTANT = 1.380_648_52e-23;

    public void test() {
                System.out.println(BOLTZMANN_CONSTANT);
            }

    }

    public class PysicalConstants {
        private PysicalConstants() {
        } // 인스턴스화 방지

        public static final double AVOGADROS_NUMBER = 6.022_140_857e23;
        public static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
        public static final double ELECTRON_MASS = 9.109_383_56e-31;
    }
}
