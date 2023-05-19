package com.effectiveJava.chapter4.item20;

public class Item20 {

    public interface Idol {
        void sing();
    }

    public interface Actor {
        void act();
    }

    public interface IdolActor extends Idol, Actor {
        void sing();
        void act();
    }
}
