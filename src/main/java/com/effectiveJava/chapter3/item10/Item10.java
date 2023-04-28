package com.effectiveJava.chapter3.item10;

import java.util.Objects;

/**
 * eqauls 는 일반 규약을 지켜 재정의하라
 * : 필수로 정의해야하는 건 아님, 근데 규약을 지켜서 정의할 것
 * equals 를 잘못 재정의 하면 잘못된 결과를 초래.
 * 잘못할 거 같으면 애초에 재정의하지 않는 것이 낫다.
 * equals는 재정의 하지 않으면 오직 자기 자신과만 같다.
 * 즉 equals default는 identity 비교이다.
 */


public class Item10 {

/**
 * equals 를 재정의하면 안될 때, 혹은 할 필요 없을 때 (다음중 하나라도)
 * 1. 각 인스턴스가 본질적으로 고유할 때
 * ex. Thread
 * 2. logical equality를 검사할 일이 없을 때
 * - 클라이언트가 객체의 논리적 동치성을 검사할 일이 없는 클래스
 * 3. 상위 클래스의 equals가 하위 클래스에도 적절하게 사용가능하게 정의되어 있을 때
 * ex. {@link java.util.Set}, {@link java.util.List}, {@link java.util.Map}
 * 4. class가 private이거나 packaget private이고, equals() 호출을 강제로 막고 싶을 때
 * 5. 불변 클래스이며 factory method를 사용한 객체 (인스턴스가 하나뿐이기 떄문)
 *  ex. Enum
 * */

/**
 * equals를 재정의해야할 때 (다음중 하나라도)
 * -  identity 말고 equality를 비교해야할때 (&& 상위클래스가 재정의 하지 않았을 때)
 * - 주로 값 클래스 (불변 말고)
 * - ex. {@link Integer}, {@link String}
 * 장점
 * -프로그래머가 논리적 비교 (euqality)가 가능해짐
 * - {@link java.util.Set}, {@link java.util.Map}의 원소로 사용 (정상적으로)
 *
 * */


    /**
     * equals 규약
     * 1. reflexivity (반사성) : 객체는 항상 본인과 같아야한다.
     * null이 아닌 참조값 x에 대해
     * x.equals(x)가 true이다.
     * <p>
     * 2. symmetry (대칭성) : 서로의 동치여부가 같다.
     * null이 아닌 모든 참조값 x, y에 대해
     * x,euqals(y)가 true이면 -> y.eqauls(x)도 true 이다.
     * <p>
     * 3. trasitivity (추이성) : 1과 2가 같고, 2와 3이 같으면 1과 3이 같다.
     * null이 아닌 모든 참조값 x, y, z에 대해
     * x.equals(y)가 true && y.equals(z)가 true이면 -> x.equals(z)도 true이다.
     * <p>
     * 4. consitency (일관성)
     * null이 아닌 모든 참조값 x, y에 대해
     * x.equals(y)를 반복해서 호출하면 항상 true를 반환하거나 false를 반환해야한다.
     * <p>
     * 5. null-아님
     * null이 아닌 모든 참조 값 x에 대해
     * x.equals(null)은 false다.
     */
    public static void main(String[] args) {

        // reflexivity가 안되었다면?
        Integer a = 1;
        java.util.Set<Integer> set = new java.util.HashSet<>();

        set.add(a);
        System.out.println(set.contains(a)); //여기서 false를 뱉을 것!! contains는 내부적으로 equals를 사용
        System.out.println("==========");

        CaseInSensitive1 caseSensitive1 = new CaseInSensitive1("Hani");
        String hani1 = "hani";
        System.out.println(caseSensitive1.equals(hani1)); // true
        System.out.println(hani1.equals(caseSensitive1)); //false
        System.out.println("==========");

        CaseInSensitive2 caseSensitive2 = new CaseInSensitive2("Hani");
        String hani2 = "hani";
        CaseInSensitive2 caseSensitive3 = new CaseInSensitive2("hani");

        System.out.println(caseSensitive2.equals(hani2)); // false
        System.out.println(hani1.equals(caseSensitive2)); // false
        System.out.println(caseSensitive3.equals(caseSensitive2)); // true
        System.out.println(caseSensitive2.equals(caseSensitive3)); // true
        System.out.println("==========");

        Point p1 = new Point(1, 9);
        PointWithName1 pointWithName1 = new PointWithName1(1, 9, "newJeans");
        System.out.println(p1.equals(pointWithName1)); // true
        System.out.println(pointWithName1.equals(p1)); // false 대치성 위배!

        System.out.println("==========");
        Point p2 = new Point(1, 9);
        PointWithName2 pointWithName2 = new PointWithName2(1, 9, "newJeans");
        System.out.println(p2.equals(pointWithName1)); // true
        System.out.println(pointWithName2.equals(p2)); // true 대치성은 지킴

        PointWithName2 pointWithName3 = new PointWithName2(1, 9, "IVE");
        // 추이성 test
        System.out.println(pointWithName2.equals(p2)); // true
        System.out.println(p2.equals(pointWithName3)); // true
        System.out.println(pointWithName2.equals(pointWithName3)); // false 추이성 위배!

        System.out.println("==========");

        PointWithName4 pname1 = new PointWithName4("newJeans", new Point(1, 9));
        PointWithName4 pname2 = new PointWithName4("newJeans", new Point(1, 9));
        PointWithName4 pname3 = new PointWithName4("newJeans", new Point(1, 9));
        System.out.println(pname1.equals(pname2)); //true
        System.out.println(pname2.equals(pname3)); //true
        System.out.println(pname1.equals(pname3)); //true

    }

    /**
     * symmetry 오류 예제
     * - 한방향으로만 작동하는 오류
     * 의도 : s 가 대소문자 구별 없이 같기만 하면 true
     * solution : {@link CaseInSensitive1}의 equals를 String과 연동하려하지 마라
     */
    public static final class CaseInSensitive1 {
        private final String s;

        public CaseInSensitive1(String s) {
            this.s = Objects.requireNonNull(s);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof CaseInSensitive1) {
                return s.equals(((CaseInSensitive1) o).s);
            } else if (o instanceof String) {
                return s.equalsIgnoreCase((String) o); // "String과도 비교할 수 있지 않을까?" => 한방향으로만 작동하게 만든 코드 (shit)
            } else {
                return false;
            }
        }
    }

    public static final class CaseInSensitive2 {
        private final String s;

        public CaseInSensitive2(String s) {
            this.s = Objects.requireNonNull(s);
        }

        @Override
        public boolean equals(Object o) {
            return (o instanceof CaseInSensitive2) && ((CaseInSensitive2) o).s.equalsIgnoreCase(s);
        }
    }


    /**
     * trasitivity 오류 예제
     * 의도 : 구현클래스를 확장해서 부모 자식간에 equals가 성립 가능하게 재정의 가능할까?
     * 원인
     * - 객체지향 언어의 문제점
     * - 구현 클래스를 확장시켜 equals를 만족스럽게 재정의하는 방법 존재하지 앟음
     * - 확장 클래스면 확장 field가 있을텐데, 확장 필드를 포함해서 추이성을 지키게 불가능
     * - TODO. 리스코프 치환 원칙?
     * solution
     * - 상속 대신 컴포지션을 사용하라
     * <p>
     * 참고
     * - JAVA API 에도 구체 클래스에서 equals 재정의한 거 있음. 대치성 위배
     * - ex. {@link java.sql.Timestamp}
     * - 그래서 API 명세에 주의해두었고, 엉뚱하게 동작함.
     */

    public static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) {
                return false;
            } else {
                Point p = (Point) o;
                return p.x == x && p.y == y;
            }
        }
    }

    public static class PointWithName1 extends Point {
        private final String name;

        public PointWithName1(int x, int y, String name) {
            super(x, y);
            this.name = name;
        }

        //equals를 재정의 하지 않으면 name은 무시한채 비교함!
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof PointWithName1)) {
                return false;
            } else {
                return super.equals(o) && ((PointWithName1) o).name.equals(name);
            }
        }
    }


    //name을 무시하고 좌표로만 비교하는 건?
    public static class PointWithName2 extends Point {
        private final String name;

        public PointWithName2(int x, int y, String name) {
            super(x, y);
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point)) {
                return false;
            }
            // Point면 이름 무시
            if (!(o instanceof PointWithName2)) {
                return o.equals(this);
            }
            return super.equals(o) && ((PointWithName2) o).name.equals(name);

        }
    }

    public static class PointWithName4 {

        private final String name;
        private final Point point;

        public PointWithName4(String name, Point point) {
            this.name = name;
            this.point = Objects.requireNonNull(point);
        }

        /**
         * view method
         * : 인스턴스의 인스턴스 변수를 반환하는 메서드
         * */

        public Point asPoint() {
            return point;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof PointWithName4)) {
                return false;
            } else {
                PointWithName4 pointWithName4 = (PointWithName4) o;
                return pointWithName4.name.equals(((PointWithName4) o).name) && pointWithName4.point.equals(((PointWithName4) o).point);
            }

        }

    }

    /**
     * 일관성 (consitency)
     * 두 객체가 같다면 어느하나라도 수정되지 않는 한 앞으로도 영원히 같아야함
     * 가변객체는 비교시점에 따라 다를 수 있음
     * 불변객체는 한번 다르면 계속 달라야함
     * 따라서 일관성의 면에서라도 클래스 작성 시 불변 클래스로 만들어야할 지 심사숙고할 것
     *  => 불변클래스로 만들기로 했다면 한번 같다면 영원히 같도록, 즉 일관성을 지키도록 만들어야함
     *
     * equals 안에 신뢰할 수 없는 자원이 끼어들게하지마라
     * ex. {@link java.net.URL} : 주어진 URL과 매핑된 호스트 IP 주소를 가지고 비교함. 따라서 가끔 문제를 일으킴!
     * equals는 메모리에 존재하는 객체만을 사용해 deterministic (결정적) 계산만 할 것.
     * */

    /**
     * null-아님
     * null이 아닌 모든객체는 null과 같지 않다
     * o.euqals(null)이 true를 던지지 않더라도, {@link NullPointerException}을 던지는데 이것도 안됨. (instanceof 쓰면 되니까)
     */

    public class foo {

        //instanceof 연산자를 활용한 null 체크
        @Override
        public boolean equals(Object o) {
            // null이면 여기서 false return함
            if (!(o instanceof foo)) {
                return false;
            }
            return this == o;
        }
    }


    /**
     * equals() 호출을 막는다.
     */
    @Override
    public boolean equals(Object o) {
        throw new AssertionError();
    }


    /**
     * equals 를 재정의 잘하는 법 (순서)
     * 1. == 을 이용해 자기 자신인지 체크. (제일 처음 활용하면 성능 up)
     * 2. instanceof 로 타입 체크 : 일반적으로 자기 타입과 비교하지만, 가끔 구현 인터페이스와 비교하기도 함
     * 3. 형변환 : instanceof 이후라 100% 성공
     * 4. '핵심' 필드 모두 일치 여부 검사: 단하나라도 다르면 False
     * 기본 필드는 모두 == 로 검사 (float, double 제외)
     * 참조타입은 equals로 검사
     *
     * 필드가 null인게 정상인 경우가 있는데 이떄는 Objects.equals(Object, Object)로 NullPointerException 예방
     * 비교 비용이 싼 필드부터 먼저 비교하면 equals 성능이 좋아짐
     *
     * 주의사항
     * - hashCode도 반드시 재정의 해라 (TODO.11)
     * - 복잡하게 해결하려고 하지마라. 공격적으로 파고들다가 오히려 문제생김.
     * - equals()를 오버로딩 하지마라
     *- 구글 AUtoValue framework 사용하면 작성~테스트 모두 완벽 (강추) (TODO)
     *
     * */
}
