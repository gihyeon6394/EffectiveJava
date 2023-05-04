package com.effectiveJava.chapter2.item8;

/**
 * finalizer와 cleaner 사용을 피하라
 * : 자원 반납이 필요한 객체는 AutoCloseable을 구현하면 됨. 그래도 불안하면 Cleaner를 사용하는데, 불완전함
 * <p>
 * finalizer
 * - 예측할 수 없고, 상황에 따라 위험함
 * <p>
 * Cleaner
 * - finalizer보다 상대적으로 덜 위험
 * - 여전히 예측할 수 없고, 느림
 * - 자신을 수행할 스레드를 제어 가능
 * <p>
 * 사용하면 안되는 이유
 * - 수행 시점을 모름
 * - 수행 여부 보장 안됨
 * - 성능 저하 (AutoCloseable을 이용한 GC에 비해 너무 느림)
 * - 자원반납했다고 생각해도 실제로 안했을 수 있어서 프로그램 중단사태까지 이를 수 있음
 * - ex. 데이터 베이스 공유 락을 Cleaner가 해제하게 한다면 데이터 베이스 (분산 시스템)에 치명적임
 * <p>
 * 사용할만한 곳 (그나마)
 * 1. close()를 호출하지 않을 것을 대비한 안전망 {@link Room}
 * 2. native peer와 연결된 객체 : GC가 존재를 알지 못하기 떄문에
 * - 단 성능 저하를 감당할 수 잇고, 네이티브 피어가 중요자원을 가진게 아니라면 고려해볼 것
 * - 그게 아니라면 close()를 통해 자원 반납이 정상적임
 * * native peer : native method를 통해 기능을 위임한 네이티브 객체
 */
public class Item8 {

    public static void main(String[] args) {


        /**
         * {@link AutoCloseable}.close()로 자원 소멸
         * */
        try (Room room = new Room(7)) {
            System.out.println("hello");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("========");

        /**
         * {@link com.effectiveJava.chapter2.item8.Room.State}.run()으로 자원 반납
         * */
        Room room = new Room(10101);
        System.out.println("청소 되었니?");
        // System.gc();

    }
}
