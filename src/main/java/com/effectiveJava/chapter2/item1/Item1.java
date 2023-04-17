package com.effectiveJava.chapter2.item1;

/**
 * 생성자 대신 정적 팩터리 메서드를 고려하라
 * ('디자인 패턴'의 정적 팩터리 메서드 (Factory Method)와 다름)
 * <p>
 * 요약 : 정적 팩터리 메서드, public 생성자는 장단점이 있음. 상황에 따라 선택하면 되나 대부분의 경우 정적 팩터리 메서드가 좋을걸?
 *
 * <p>
 * 장점
 * 1. 이름을 가질 수 있다.
 * 반환될 객체의 특성 묘사 가능
 * 시그니처가 같은 여러 생성자가 필요할 경우
 * <p>
 * 2.호출될 때마다 인스턴스를 새로 생성하지 않아도 된다.
 * Flyweight pattern -Boolean.valueOf()
 * instance-controlled (인스턴스 통제) 클래스
 * 동치 (identity)인 인스턴스가 하나임을 보장
 * <p>
 * 3. 반환 타입의 하위 타입 객체를 반환 가능 (유연성)
 * 4. 매개변수에 따라 다른 클래스 객체 (혹은 다른 내용의 객체)를 반환 가능
 * 5. factory method 작성 시점에 반환할 객체가 존재하지 않아도 됨
 * service provider framework (서비스 제공자 프레임워크) : JDBC
 * - 3개의 핵심 컴포넌트 : service interface, provider registration API, service access API (, service provider interface)
 * JDBC의 경우 Connection (service interface), DriverManager.registerDriver (provider registration API), DriverManager.getConnection (service access API)
 * Bridge pattern, dependency injection framework
 * <p>
 * 단점
 * 1. 상속 할 때 필요한 public, protected 생성자가 없다
 * 2. 정적 팩토리 메서드를 다른 프로그래머들이 쉽게 찾기가 힘듬 (메서드 네이밍 관례에 따르는 걸 추천)
 */
public class Item1 {

    public static void main(String[] args) {
        System.out.println("Hello Item !");

        User user1 = User.valueOf("팜하니", "서울특별시 강남구");
        User user2 = User.valueOfNotAge("집이 어딘지 모르는 팜하니");
        User user3 = User.valueOfSub("집이 어딘지 모르는 팜하니");
        User user4 = User.valueOfByName("비속어가 있는 이름");
        User userSingleTone1 = User.getInstance();
        User userSingleTone2 = User.getInstance();
        System.out.println(user1.toString());
        System.out.println(user2.toString());
        System.out.println(user3.toString());
        System.out.println(user4.toString());
        System.out.println(userSingleTone1.toString());
        System.out.println(userSingleTone2.toString());
        System.out.println(userSingleTone1.equals(userSingleTone2));

        // check user1 equals user 4 with equals
        System.out.println(user1.equals(user4));
        // check user1 equals user 4 with hashcode
        System.out.println(user1.hashCode() == user4.hashCode());
    }
}
