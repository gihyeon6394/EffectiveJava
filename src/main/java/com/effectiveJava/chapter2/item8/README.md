<h1>item 8. finalizer와 cleaner 사용을 피하라</h1>

> 중요치 않은 native 자원에 사용하자, 그러나 실행여부와 성능 보장은 안된다  
> 반납해야하는 자원이 있는 클래스는 Autocloseable 을 구현하면 된다

<h3>자바의 객체 소멸자</h3>

- finalizer <sup>~ java 8</sup>
  - 예측이 힘들고 위험
  - 작업 중 발생한 예외 무시되고, 작업이 남아도 그 즉시 종료
- cleaner <sup>java 8~</sup>
  - finalizer deprecated 에 따라 등장
  - 예측불가, 느림, 불필요

<h3>자바의 객체 소멸자가 구린 이유</h3>

- 즉시 수행된다는 보장 없다
- 실행 시점을 예측할 수 없다
- 수행 여부 보장 안된다
- 성능 문제
  - AutoCloseable 구현 대비 약 50배 느림
- 따라서 상태를 영구적으로 저장하는 작업에는 절대 사용하면 안됨
- 보안 문제 : finalizer 공격
    - 생성자나 직렬화 과정에서 예외가 발생하먼, 악의적인 하위클래스의 finalizer 수행
    - 예방방법 : final 클래스로 만들거나, finalize 메서드를 final로 재정의



<h2>finalizer 와 cleaner 의 쓰임새</h2>

- 안전망 : 클라이언트가 자원 반납을 안했을때 나중에라도 해줄 것이라는 **기대를 가지고** 구현
  - ex. FileInputStream/OutputStream
- 네이티브 피어(네이티브 메서드를 통해 기능을 위임한 객체)와 연결된 객체
  - 자바 객체가 아니어서 GC Target 이 아님

~~~~
public class Room implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create(); //내부 클래스 State를 등록해서 안전망 cleaner로 사용

    private static class State implements Runnable {
        
        ...

        /**
         * run()이 사용되는 시점
         * 1. Room.close() 호출 시
         * 2. GC가 Room을 회수하려고 할 때, close() 호출 된적 없다면 (바라건대 언젠가는) cleaner가 Room.run()을 호출해줄 걸?
         */
        @Override
        public void run() {
            System.out.println("cleaner로 방청소");
            numJunkPiles = 0;
        }
    }

    private final State state;

    private final Cleaner.Cleanable cleanable;

    public Room(int numJunkPiles) {
        state = new State(numJunkPiles);
        cleanable = cleaner.register(this, state); // cleaner에 등록
    }
    
    // AutoCloseable 의 close
    @Override
    public void close() throws Exception {
        cleanable.clean();
    }
}

~~~~

<h2>인스턴스가 종료해야하는 자원 <sup>파일, 스레드 등</sup>을 다룰 때?</h2>

훌륭한 대안 : **Autocloseable** 구현 _Item9 참고_

