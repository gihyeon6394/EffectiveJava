package com.effectiveJava.chapter2.item2;


/**
 * 생성자에 매개변수가 많다면 빌더를 고려하라
 * <p>
 * 요약 : 생성자 (혹은 정적 팩터리 메서드)에 매개변수가 많다면 (4개 이상) 빌더를 고려해라.
 * 작성시에 매개변수가 적더라도 결국 많아지기 때문에, 애초에 빌더로 시작하는 것이 좋다.
 * <p>
 * 객체의 매개변수가 많을 경우 어떻게 객체의 멤버 변수들을 지정할 수 있을까?
 * 객체 : {@link Girl}
 * <p>
 * 1. telescoping constructor pattern (점층적 생성자 패턴) : 확장이 어렵다!
 * 장점 : 매개변수 customizing 가능
 * 단점 : 클라이언트가 코드를 작성하기 까다로움. 필수값만 가지는 생성자가 뭔지 어떻게 암?
 * <p>
 * 2. JavaBeans pattern (자바 빈즈 패턴) : setter 가 너무 많음! 객체 만들때 길어짐..
 * 장점 : 매개변수 개많은 생성자 몰라도됨
 * 단점 : setter가 너무 많아서 객체한번 만드려면 수고스러움. 객체가 다 만들어질 때까지 불변이 아님
 * <p>
 * <p>
 * 3. Builder pattern (빌더 패턴) : 장점만을 모아서 쓰자
 * 장점 : 클라이언트 코드가 쓰기 쉬워짐, 객체를 불변으로 만들 수 있음. 계층 구조에서 용이함 {@link NewJeans,GirlGroup}
 * 단점 : 객체 내부 코드가 복잡해짐 (inner class Builder 작성), 객체를 만들 떄 빌더부터 작성해야함
 */
public class Item2 {

    public static void main(String[] args) {

        Girl hani1 = new Girl("팜하니", 21);

        Girl hani2 = new Girl();
        hani2.setMemberName("팜하니");
        hani2.setAge(21);
        hani2.setHeight(160); // 이 즈음에서 런타임 에러가 난다면? -> 객체가 불완전함
        hani2.setHome("부산광역시 해운대"); //... setter 쓰기 귀찮아..


        Idol hani3 = new Idol.Builder("팜하니", 21).build();
        Idol hani4 = new Idol.Builder("팜하니", 21).isLeader(0).isDebut(1).isMarried(0).build();

        System.out.println(hani3.toString());
        System.out.println(hani4.toString());

        NewJeans newJeans = new NewJeans.Builder(NewJeans.NowStatus.ING)
                .addMembers(GirlGroup.MemberFavorite.MINZI)
                .addMembers(GirlGroup.MemberFavorite.HAERIN)
                .addMembers(GirlGroup.MemberFavorite.HANI)
                .build();

        System.out.println(newJeans.toString());

    }
}
