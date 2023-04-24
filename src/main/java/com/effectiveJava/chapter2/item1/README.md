<h1>item 1. 생성자 대신 정적 팩터리 메서드를 고려하라</h1>


> 무작정 public 생성자를 쓰지말고,  
> 정적 팩터리 메서드가 있다는 걸 고려해라

_디자인 패턴의 팩터리 Factory Method 와 다름_

<h3>생성자 말고도 정적 팩터리 메서드<sup>static factory method</sup>를 제공할 수 있다</h3>

> _java.lang.Boolean_
> ~~~~
> public static Boolean valueOf(boolean b) {
>     return (b ? TRUE : FALSE);
> }
> ~~~~


<h2>팩터리 메서드 작성 규약</h2>

- from : 매개변수를 받아 해당 타입의 인스턴스 반환 <sup>형변환</sup>
   ~~~~
   Date d = Date.from(instant);
   ~~~~
- of : 매개변수를 2개 이상 받아 인스턴스 반환  <sup>집계</sup>
   ~~~~
   Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
   ~~~~
- valueOf : from + of 의 자세한 버전
   ~~~~
   BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
   ~~~~
- instance or getInstance : 매개변수를 받는다면 매개변수의 인스턴스르 반환, 같은 인스턴스 보장 하지 않음  
   ~~~~
   StackWalker luke = StackWalker.getInstance(options);
   ~~~~
- create or newInstance : instance 와 같으나, 매번 새로운 인스턴스를 반환하는 걸 보장
   ~~~~
   Object newArray = Array.newInstance(classObject, arrayLen);
   ~~~~
- get _"Type"_ : getInstance 와 같으나, 생성할 인스턴스가 다른 클래스여서 해당 클래스를 메서드에 명시  
   ~~~~
   FileStore fs = Files.getFileStore(path);
   ~~~~
- new _"Type"_ : newInstance 와 같으나나, 생성할 인스턴스가 다른 클래스이여서 해당 클래스를 메서드에 명시  
   ~~~~
   BufferedReader br = Files.newBufferedReader(path);
   ~~~~
- _"type"_ : get _"Type"_, new _"Type"_ 의 간결한 버전
   ~~~~
     List<Complaint> litany = Collectors.list(legacyLitany);
   ~~~~

<h2>팩터리 메서드의 장점</h2>

1. 메서드의 이름을 가질 수 있음   
   - 메서드가 반환하는 객체를 나타내는 특성를 표현 가능
   - 시그니처가 같은 여러 생성자가 필요하다? -> 정적 팩터리 메서드 여러개 생성
2. **싱글톤** : 호출할 때마다 새로운 인스턴스 생성 안해도 됨    
   - 불변 클래스  
     - 캐싱 가능  
     - 인스턴스가 하나임을 보장 가능
   - Flywight Pattern 과도 비슷
   - instance-controlled <sup>인스턴스 통제</sup> 클래스 작성 가능
3. 반환 타입의 하위 타입 객체 반환 가능  
4. 매개변수에 따라 매번 다른 클래스의 객체 반환 가능  
5. 유연한 정적 팩터리 : 정적 팩터리 메서드 작성 시점에 반환할 객체의 클래스 존재 안해도 됨
   - 서비스 접근 API <sup>클라이언트</sup>가 원하는 구현체 명시 가능 
   - ex. JDBC, Bridge pattern, DI

    
<h2>팩터리 메서드의 단점</h2>

1. 정적 팩터리메서드만 있는 클래스는 상속 불가능  
   - 컴포지션 사용을 유도하기에 장점일지도..?  
2. 가독성 : 프로그래머가 찾기 힘듦
   - 클래스마다 정적 팩터리 메서드를 찾으러 가야함
   
