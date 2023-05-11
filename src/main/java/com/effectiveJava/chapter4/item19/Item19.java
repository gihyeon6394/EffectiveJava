package com.effectiveJava.chapter4.item19;

/**
 * item19. 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라.
 */
public class Item19 {

    public static void main(String[] args) {

        Sub sub = new Sub("sub");
        sub.overrideMe();



    }

    static class Super{
        public Super() {
            overrideMe();
        }

        public void overrideMe() {
        }
    }

    static final class Sub extends Super{
        private final String name;

        public Sub(String name) {
            this.name = name;
        }

        @Override
        public void overrideMe() {
            System.out.println(name);
        }
    }

}
