package com.effectiveJava.chapter4.item20.idol;

public interface Idol {

    /**
     * @ImplSpec 이 메서드는 사람이 수면에 들어가는 것을 나타낸다.
     * */
    default void sleep(){System.out.println("사람 사는 거 다 똑같지. 사람은 이렇게 수면에 들어요.");}

    void practice();

    void goAirport();

    void sing();

    void viewMyCompany();
}
