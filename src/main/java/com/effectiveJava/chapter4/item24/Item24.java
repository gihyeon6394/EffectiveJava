package com.effectiveJava.chapter4.item24;

import java.util.HashMap;

public class Item24 {

    public static void main(String[] args) {
        Outer.NestedStaticMember nestedStaticMember = new Outer.NestedStaticMember();

        Outer outer = new Outer();
        Outer.NestedMember nestedMember1 = outer.makeNestedMemberInstance();

        Outer.NestedMember nestedMember2 = new Outer().new NestedMember();

        AnonymousInterface anonymousInterface = new AnonymousInterface() {
            @Override
            public void test() {
                System.out.println("this is anonymous class");

                Item24 item24 = new Item24();
            }
        };

        anonymousInterface.test();



    }

    public interface AnonymousInterface {
        void test();
    }

}
