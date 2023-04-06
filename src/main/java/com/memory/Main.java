package com.memory;

public class Main {

    public static void main(String[] args) {

        int a = 1;
        System.out.println("main() a : " + a);
        tmp1(a);
        System.out.println("main() a : " + a);

        System.out.println("============================");
        System.out.println("여기까지는 너무 쉽다. 자 그렇다면!");

        int[] items = {1, 2, 3};
        System.out.println("main() items[0] : " + items[0]);
        tmp2(items);
        System.out.println("main() items[0] : " + items[0]);

        System.out.println("한번 더!");

        Integer q = 1;
        System.out.println("main() q : " + q);
        tmp3(q);
        System.out.println("main() q : " + q);


    }

    public static void tmp1(int a) {
        a = 100;
        System.out.println("tmp1() a : " + a);
    }


    private static void tmp2(int[] items) {
        items[0] = 1000000;
        System.out.println("tmp2() items[0] : " + items[0]);
    }

    private static void tmp3(Integer q) {
        q = 1000000;
        System.out.println("tmp3() q : " + q);
    }

}
