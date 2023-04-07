package com.effectiveJava.chapter2.item3;

/**
 * private 생성자나 열거 타입으로 싱글턴임을 보증하라
 * : 싱글톤을 만드려면 enum이 대부분의 경우 좋다.
 *
 * <p>
 * Singletone : 클래스이다. 클래스인데 인스턴스를 오로지 하나만 만들 수 있는 클래스.
 * Singletone 사용처
 * - 무상태 (stateless 객체) : 함수형 객체 (mvc의 service, dao, controller)
 * - 설계상 시스템 전역에서 하나만 있어야하는 시스템 컴포넌트 : Connector 등
 * <p>
 * Singletone의 단점
 * - 테스트 시 mock 객체를 자유자재로 만들기 어려움
 * <p>
 * Signletone을 만드는 방법
 * 1. public static final field {@link Hani}
 * - 장점
 * 해당 클래스가 싱글턴임이 java에 명백하게 드러남
 * 간결함
 * - 단점 : 리플렉션 API를 통해 사용자가 객체를 2개 만들수도 있음. 따라서 생성자에서 2번째 객체 만드려고할 떄 예외던지는 코드가 필요해짐 (TODO.item65)
 * 2. 정적 팩터리 메서드 이용 {@link Minzi}
 * - 장점
 * 싱글턴이 아니게 바꿀 때도 쉽게 바뀜
 * 정적 팩터리 메서드를 제너릭 싱글턴 팩터리로 만들 수 있음 (TODO.item30)
 * 정적 팩터리 메서드 참조를 공급자 (supplier)로 사용 가능 (TODO.item43~44)
 * <p>
 * 3. 열거 타입 (일반적으로 best) {@link Karina}
 * - 장점
 * 간결함
 * 추가 작업 없이 직렬화
 * - 단점 : 클래스 구조가 어색함
 */
public class Item3 {

    public static void main(String[] args) {
        Hani hani = Hani.INASTANCE;
        hani.work();

        Minzi minzi = Minzi.getInstance();
        minzi.work();

        Karina.INSTANCE.work();
    }
}
