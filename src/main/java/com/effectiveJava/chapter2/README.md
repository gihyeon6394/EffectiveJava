<h1>2장 객체 생성과 파괴</h1>

- 객체를 만들 때와 만들지 말아야할 를 구분하는 법
- 올바른 객체 생성 방법
- 불필요한 생성을 피하는 방법
- 객체의 적절한 파괴시점을 보장하는 방법
- 객체 파괴 전에 수행해야하는 정리작업들

<h2>contents</h2>

- [item 1.생성자 대신 정적 팩터리 메서드를 고려하라](https://github.com/gihyeon6394/practice-effective-java/tree/main/src/main/java/com/effectiveJava/chapter2/item1)  
    > 생성자보다 커스터 마이징 (이름, 싱글턴)이 가능한 정적 팩터리 메서드가 좋음 (대부분)


- [item 2.생성자에 매개변수가 많다면 빌더를 고려하라](https://github.com/gihyeon6394/practice-effective-java/tree/main/src/main/java/com/effectiveJava/chapter2/item2)  
    > 매개변수가 4개 이상이라면 빌더를 고려. 4개 미만이어도 결국 많아질 것이기 때문에 애초에 빌더를 적용


- [item 3.private 생성자나 열거 타입으로 싱글턴임을 보증하라](https://github.com/gihyeon6394/practice-effective-java/tree/main/src/main/java/com/effectiveJava/chapter2/item3)  
    > 무상태 객체는 싱글턴으로 만들어라. 그리고 싱글턴은 enum이 좋음


- item 4.인스턴스화를 막으려거든 private 생성자를 사용하라   
   > private 생성자를 이용하면 인스턴스화를 막을 수 있다 <sup>정적 클래스</sup>


- item 5.자원을 직접 명시하지 말고 의존 객체 주입을 사용하라  
  > 의존 자원에 따라 동작이 달라진다면, 의존 자원을 주입해라


- item 6.불필요한 객체 생성을 피하라  
  > 기존 객체를 재사용해도 된다면 재사용을 우선적으로 고려하자


- item 7.다 쓴 객체 참조를 해제하라  
  > 개발자가 메모리 관리를 직접 코드로 해주는 경우는 드무나, 필요한 경우 참조를 해제하여 GC Target으로 만들라


- item 8.finalizer와 cleaner 사용을 피하라  
    > 자원 반납이 필요한 객체는 AutoCloseable 을 구현하면 됨. 그래도 불안하면 Cleaner를 사용하는데, 불완전함


- item 9.tyr-finally 보다는 try-with-resources를 사용하라  
    > 코드가 깔끔해지고, Suppressed (중첩 예외) 가능




