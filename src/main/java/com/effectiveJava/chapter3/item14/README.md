<h1>item 14. Comparable을 구현할지 고려하라</h1>

> 인스턴스의 순서를 고려할 필요가 있을 때 Comparable 인터페이스를 구현

<h2>Comparable 의 장점</h2>

- 동치성 비교
- 순서 비교 <sup>정렬</sup>
- 제네릭  
- 자바 플랫폼 라이브러리의 모든 값 클래스와 열거타입이 Comparable 을 구현
- compareTo 메서드의 일반 규약을 지키면, 인스턴스들은 자연적인 순서로 정렬 가능

<h3>compareTo 메서드의 일반 규약</h3>

> 이 객체와 주어진 객채의 순서를 비교  
> 이 객체가 주어진 객체보다 작으면 음의 정수, 같으면 0, 크면 양의 정수를 반환  
> 비교할 수 없는 타입의 객체가 주어지면 ClassCastException
> - 대칭성 : 두 객체의 참조의 순서를 바꿔도 예상한 결과가 나와야 한다.
> - 추이성 : 첫번째 객체가 두번째 객체보다 크고, 두번째 객체가 세번째 객체보다 크면, 첫번째 객체는 세번째 객체보다 커야 한다.
> - 반사성 : 모든 객체가 자기 자신과 같아야 한다. <sup>즉, 크기가 같은 객체끼리는 어떤객체와 비교해도 항상 같아야 함</sup>
> - 추가 권장사항 <sup>**꼭 지킬 것**</sup>    
>   - x.compareTo(y) == 0 이면, x.equals(y) == true
> 
>       ~~~~
>       // 안지키면 이렇게 됨
>       BigDecimal a = new BigDecimal("1.0");
>       BigDecimal b = new BigDecimal("1.00");
>
>       System.out.println(a.equals(b)); // false
>       System.out.println(a.compareTo(b)); // true(0)
>      
>        // equals()
>        HashSet<BigDecimal> hashSet = new HashSet<>();
>        hashSet.add(a);
>        hashSet.add(b);
>        System.out.println(hashSet.toString()); // 1.0. 1.00
>     
>        // compareTo()
>        TreeSet<BigDecimal> treeSet = new TreeSet<>();
>        treeSet.add(a);
>        treeSet.add(b);
>        System.out.println(treeSet.toString()); // 1.0  
>   
>        ~~~~   

<h3>comapreTo는 euqals와 유사하다</h3>

- 대칭, 추이, 반사성을 요구
- Comparable 구현 클래스의 확장 시 규약을 지킬 수 없으므로 view 메서드를 이용하여 우회


<h2>Comparable을 구현하는 법</h2>

1. compareTo 메서드의 인자 타입은 Comparable 인터페이스를 구현한 클래스의 인스턴스
2. 각 필드를 비교
   - 핵심 필드 부터 비교 시작
     - **핵심 필드에서 순서가 판명나면 바로 반환 <sup>성능</sup>**
   - 객체 참조 필드는 compareTo 메서드를 재귀적으로 호출
   - 기본 타입 필드는 박싱 클래스의 정적 compare 메서드나 Comparator 인터페이스의 비교자 생성 메서드를 사용


<h4>주의사항</h4>

- 필드 값을 비교할 때 관계연산자 <sup>-, < </sup> 사용하지 말 것
- 대신, static compare 메서드나 Comparator 의비교자 생성 메서드 사용


~~~~
@Override
public int compareTo(PhoneNumber o) {
    int result = this.telecom.compareTo(o.telecom);
    
    // 핵심필드에서 판명나지 않은 경우에만 나머지 필드 비교
    if (result == 0) {
        result = this.phoneNumber.compareTo(o.phoneNumber);
    }
    return result;
}
~~~~


<h4>비교자 생성 메서드</h4>

- 단점 : 성능 저하
- 장점 : 간결함, 메서드연쇄 방식을 통한 비교자 생성

~~~~
public static final Comparator<PhoneNumber> COMPARATOR = Comparator.comparing(PhoneNumber::getTelecom)
        .thenComparing(PhoneNumber::getPhoneNumber);
~~~~

