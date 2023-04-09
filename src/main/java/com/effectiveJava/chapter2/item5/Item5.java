package com.effectiveJava.chapter2.item5;

/**
 * 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라
 * : 클래스의 인스턴스 변수가 용도에 따라 유연해져야한다면, 인스턴스 변수 (의존 객체)를 주입하도록 해라
 *  왜? 그래야 테스트가 용이하고, 코드 변경이 쉽고, 클래스 확장성이좋아짐
 * <p>
 * {@link ConnectionUtils}의 의존 객체 {@link Connector}가 용도에 따라 바뀌어야한다면
 * EX. 네이버랑 연동할 때, 카카오랑 연동할 때
 * <p>
 * bad : 의존 객체를 변동하는 메서드 생성
 * - 방식이 어색함
 * - 의존 객체에 따라 동작이 달라지는 클래스에는 singletone방식이 적합하지 않음
 * - 왜? 싱글톤은 변경이 없는 인스턴스 멤버들을 가진 클래스에 적합하기 때문에
 * - 변경이잦은 인스턴스 멥버들이 싱글톤이 되버리면 thread-safe 하지 않음
 * <p>
 * Good : 의존 객체 주입 (DI, Dependency Injection)
 * 1. 생성자
 * 2. 정적 팩터리 메서드
 * 3. 빌더
 * <p>
 * DI 단점 : 프로젝트가 커짐에 따라 의존성이 많아져 복잡해지고, 코드가 어지러워짐
 * => 대안으로 Spring, Dagger, Guice같은 의존객체 주입 프레임워크를 잘 사용해라
 *
 * DI 변형 : 생성자에 자원 팩터리 넘기기
 * * 팩터리 : 호출할때마다 특정 타입의 인스턴스를 만들어줌 (즉, 팩터리 메서드 패턴)
 * TODO : 팩터리 메서드 패턴이 정확하게 어떤 것인가.
 * TODO.31 : 한정적 와일드 카드? Supplier<T> 인터페이스가 팩터리를 표현한 완벽한 예시
 */
public class Item5 {

    public static void main(String[] args) {
        //bad way : 의존 객체를 변경하는 메서드 changeConnector() 사용
        ConnectionUtils.getInstance().runConnector();
        //Naver로 교체
        ConnectionUtils.getInstance().changeConnector(new ConnectorNaver());
        ConnectionUtils.getInstance().runConnector();

        //good solution
        //1. 생성자 주입
        ConnectionUtilsGood connNaver1 = new ConnectionUtilsGood(new ConnectorNaver());
        ConnectionUtilsGood connKakao1 = new ConnectionUtilsGood(new ConnectorKakao());
        connNaver1.runConnector();
        connKakao1.runConnector();

        //2. 정적 팩터리 메서드에 주입
        ConnectionUtilsGood connNaver2 = ConnectionUtilsGood.valueOf(new ConnectorNaver());
        ConnectionUtilsGood connKakao2 = ConnectionUtilsGood.valueOf(new ConnectorKakao());

        connNaver2.runConnector();
        connKakao2.runConnector();

        // 3. Builder 주입
        ConnectionUtilsGood connNaver3 = new ConnectionUtilsGood.Builder(new ConnectorNaver()).build();
        ConnectionUtilsGood connKakao3 = new ConnectionUtilsGood.Builder(new ConnectorKakao()).build();

        connNaver3.runConnector();
        connKakao3.runConnector();
    }


}
