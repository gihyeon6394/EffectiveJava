package com.effectiveJava.chapter2.item3;

/**
 * Singletone 만들기 1 : public static final 필드
 */
public class Hani {

    public static final Hani INASTANCE; // 객체를 static final 로 최초 한번만 인스턴스 생성

    static {
        try {
            INASTANCE = new Hani();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 생성자를 private 으로
    private Hani() throws Exception {

        // null 이 아닌데 객체를 만드려고 한다면 예외를 던짐
        if (INASTANCE != null) {
            throw new Exception("INSTANCE Already!");
        }
    }

    public void work() {
        System.out.println("Hani is working...");
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
