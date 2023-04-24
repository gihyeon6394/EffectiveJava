<h1>item 13. clone 재정의는 주의해서 진행하라</h1>

> java.lang.Cloneable 을 구현하는 것보다  
> 복제기능을 갖춘 **생성자(1)** 나 **팩터리(2)** 가 훨씬 남
>
> 단, 배열은 clone() 이 제일 적합함  
> Cloneable이 구린건 다 아는데도, 널리 쓰이고 있어서 사용법은 알아야함

~~~~
public class PhoneNumber implements Cloneable {

...

      @Override
      public PhoneNumber clone() {
          try {
              return (PhoneNumber) super.clone();
          } catch (CloneNotSupportedException e) {
              throw new AssertionError();
          }
      }
  }

~~~~

<h2>Cloneable 이 구린 이유</h2>

- Cloneable 을 구현하는 과정이 번거로움
    - clone() 이 선언 된 곳이 Cloneable이 아니고, Object
    - protected
    - 따라서 Cloneable을 구현하는 것만으로 clone()을 재정의할 수 없음
- 리플랙션 <sup>TODO.65</sup> 사용하면 가능은 함, 100% 성공 보장 X
- 생성자를 호출하지 않고도 객체를 생성할 수 있게 함
- 상속 대비가 불가능함
    - 자식에서 super.clone() 하면 잘못된 인스턴스<sup>부모</sup> 를 반환할 수도 있음
- clone() 이 체크드 예외를 던짐 <sup>CloneNotSupportedException</sup>
    - Object를 하위 객체로 형변환하는데, 이런 예외가 던져질 가능성이 아예 없음
    - 즉 런타임 예외로 정의되었어야함
- 인스턴스 변수 중 가변객체가 있는 순간 재앙이 시작됨
-

<h3>메서드 하나 없는 Cloneable 인터페이스는 무슨 일을 하나</h3>

- 따라하면 안되는 인터페이스 구현 방식
    - clone() 의 동작방식 결정
    - 필드를 하나하나 복하나 객체를 반한
    - 아닌 클래스에서 호출하면 CloneNotSupportedException
- 일반적으로 인터페이스의 목적은 인터페이스에서 정의한 기능을 사용하라는 뜻









