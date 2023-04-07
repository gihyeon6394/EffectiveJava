package com.effectiveJava.chapter2.item3;

/**
 * Singletone 만들기 2 : 정적 팩터리 메서드
 */
public class Minzi {
    private static final Minzi INASTANCE = new Minzi();// 객체를 private으로


    private Minzi() {
    }

    public static Minzi getInstance() {
        return INASTANCE;
        // 만약 singetone이 아니게하려면?
        // return new Minzi();
    }

    public void work() {
        System.out.println("Minzi is working...");
    }

    /**
     * TODO.89 역직렬화 대비
     *  싱글턴임을 보장해주는 readResolve 메서드
     */
    private Object readResolve() {

        // '진짜' 인스턴스를 반환하고, 가짜 인스턴스는 GC에 위임
        return INASTANCE;
    }
}
