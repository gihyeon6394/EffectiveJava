<h1>item 11. equals를 재정의하려거든 hashCode도 재정의하라</h1>

eqals를 재정의한 클래스 모두에서 hashCode도 재정의할 것  
안그러면 HashMap, HashSet 같은 컬렉션 원소에서 문제 발생  
ex. HashMap 은 동치성 <sup>logical equality</sup> 비교 시 hashCode가 다른 객체끼리 애초에 비교조차 안함  
AutoValue 프레임워크를 사용하면 자동으로 만들어주니까 걱정 말 것


<h2>hashCode 생성 규칙을 API 사용자에게 자세하게 알려주지 마라</h2>

- 클라이언트가 값에 의지할 수 없음
- 클라이언트가 계산 방식을 바꿀 위험도 있음
- String, Integer 같은 클래스가 오픈했고, 실수였음

<h3>Object docs 발췌</h3>

>- equals 비교에 사용되는 정보가 변경되지 않았다면, 런타임 중 그 객체의 hashCode()를 몇번 호출되어도 값이 일관되어야 함   
    단, 애플리케이션 재실행 시 값이 변해도 됨
>- **equals(Object) 가 두 객체 간에 같다고 판별했다면, hashCode도 항상 같아야 함 <sup>중요!!</sup>**
>- equals(Object) 가 다르다고 판단했어도, hashCode가 다를 필요는 없음. 그러나, **다른 객체 사이에서 다른 hashCode를 반환해야 해시테이블 성능이 좋아짐**


<h3>hashCode() override 안했을 경우</h3>

~~~~
Map<PhoneNumber1, String> map = new java.util.HashMap<>();
map.put(new PhoneNumber1("SK", "01012345678"), "Kim");

System.out.println(map.get(new PhoneNumber1("SK", "01012345678"))); //null
~~~~

<h2>hashCode override 방법</h2>

- 직접 재정의 하는 법 <sup>전형적인 방법</sup>
- com.google.common.hash.Hashing <sup>hash 충돌이 상대적으로 낮음</sup>
- Object 클래스의 hash **(성능이 별로임)**

<h3>직접 재정의 하는 법</h3>

1. 반환할 hashCode int 변수 result 초기화
   - 초기화 첫번쨰 핵심필드를 가지고 2-1 방법 수행
2. 나머지 핵심 필드 f 들에 대해 다음을 수행  
   1. 필드의 hashCode c를 계산
      - 필드가 기본 타입일 때, Type.hashCode(f) <sup>Type은 해당 필드의 박싱 클래스</sup>
      - 필드가 참조 타입일 때, 이 필드의 hashCode를 호출
          - 필드가 null일 때, 0 사용 <sup>전통적으로 0</sup>
          - hashCode 호출 시 계산이 복잡해질 것 같으면, 필드의 표준형<sup>ca-nonica representaion</sup>을 만들어 hashCode 호출
      - 필드가 배열일 때, 핵심 원소 각각을 별도의 필드로 간주
        - 배열에 핵심원소가 하나도 없다면, 상수 반환 (0)
        - 모든 원소가 핵심원소라면, Arrays.hashCode 사용
   2. c로 result 갱신
3. result 반환

* 파생 필드는 해시코드 계산 시 제외해도 됨
* **equals()에서 사용되지 않는 필드는 반드시 제외**

~~~~
@Override
public int hashCode() {
    int result = telecom != null ? telecom.hashCode() : 0;
    result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
    return result;
}
~~~~


<h3>Object 클래스의 hash</h3>

~~~~
@Override
public int hashCode() {
    return Objects.hash(telecom, phoneNumber);
}
~~~~

<h2>hashCode의 지연 초기화 <sup>lazy initialization</sup></h2>

- 클래스가 불변
- 해시코드 계산 비용이 큼
- 캐싱 전략 : **지연 초기화<sup>lazy initialization</sup>** 활용
- **주의사항 : 필드를 지연초기화한다면 thead-safe를 반드시 고려**


~~~~
....
private int hashCode;
....

@Override
public int hashCode() {
    int result = hashCode;
    
    // hashCode가 정의된적이 없을 때만 초기화
    if (result == 0) {
        result = telecom != null ? telecom.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
    }
    
    return result;
}
~~~~
