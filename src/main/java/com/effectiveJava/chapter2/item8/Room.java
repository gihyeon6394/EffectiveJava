package com.effectiveJava.chapter2.item8;


import java.lang.ref.Cleaner;

/**
 * 방 class
 * cleaner를 안전망으로 사용하는 {@link AutoCloseable} class
 */

/**
 * {@link AutoCloseable}
 * AutoCloseable을 구현한 객체가 try{} 에서 선언되었을 때,
 * try가 종료되면 자동으로 close() 해줌
 */
public class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create(); //내부 클래스 State를 등록해서 안전망 cleaner로 사용

    /**
     * 방 상태 class
     * 청소 (clean)가 필요한 자원
     * <p>
     * 절대 Room을 참조하지 마라!
     * - Room과 State 간의 순환 참조로, GC 불가
     * - State가 static class인 이유도 이것. 일반 중첩 클래스는 자동으로 외부 클래스를 참조하게 됨
     */
    private static class State implements Runnable {
        int numJunkPiles; // Room안의 쓰레기 수 (자원)

        public State(int numJunkPiles) {
            this.numJunkPiles = numJunkPiles;
        }

        /**
         * run()이 사용되는 시점 2가지
         * 1. Room.close() 호출 시
         * 2. GC가 Room을 회수하려고 할 때, close() 호출 된적 없다면 (바라건대 언젠가는) cleaner가 Room.run()을 호출해줄 걸?
         * => 결과적으로 내 PC에서 청소해주는거 못봤음
         */
        @Override
        public void run() {
            System.out.println("cleaner로 방청소");
            numJunkPiles = 0;
        }
    }

    //방의 상태. cleanable과 공유
    private final State state;

    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Room.close()");
        cleanable.clean();
    }
}
