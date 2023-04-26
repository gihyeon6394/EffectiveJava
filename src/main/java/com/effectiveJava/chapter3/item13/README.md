<h1>item 13. clone 재정의는 주의해서 진행하라</h1>

> java.lang.Cloneable 을 구현하는 것보다  
> **복사 생성자(1)**, **팩터리(2)** 가 좋음
>
> 단, 배열은 clone() 이 적합함  
> Cloneable이 안좋지만, 널리 쓰이고 있어서 사용법은 알아야함

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

- 모순
    - clone은 Object에 선언 <sup>Cloneable 구현만으로 clone()을 재정의할 수 없음</sup>
    - clone은 multi-thread를 고려하지 않은채 구현되어있음
    - 상속에 대한 대비가 불가능
        - 자식에서 super.clone() 하면 잘못된 인스턴스<sup>부모</sup> 를 반환
- 형변환이 필요함  
- 위험한 객체 생성 매커니즘
    - 생성자를 사용 안하면서 생성자와 목적이 같음
    - 인스턴스 변수 중 가변객체가 있는 순간 재앙이 시작됨
        1. 가변 객체의 인스턴스 변수를 가진채 clone
        2. 인스턴스 변수 수정
        3. 다른 객체까지 수정사항 전파 **<sup>재앙 시작</sup>**
           - deepCopy() 전용 고수준 API로 해소가능하나 성능이 좋지 못함
- 문서가 엉성함
- 정상적인 final 필드 용법과 충돌
- 불필요한 CheckedExcpetion <sup>CloneNotSupportedException</sup>
    - Object의 자식 객체로 형변환하는데, 예외가 발생할 가능성이 없음
    - 런타임 예외로 정의되었어야함


<h3>자식의 clone() 재정의를 막는 법</h3>

~~~~
@Override
protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
}
~~~~

<h2>복사 생성자, 복사 팩터리가  이유</h2>

- Cloneable의 구린 이유를 모두 커버
- 인터페이스 타입을 인수로 받을 수 있음

~~~~
public Stack(Stack stack) {
  .... (copy)
}

public static Stack newInstance(Stack stack) {
    Stack result = new Stack();
    .... (copy)
    return result;
}
~~~~

<h2>Cloneable 제대로 구현하기</h2>

- 원본 객체에 아무런 영향 없이
- 사본객체의 불변성 보장
- Cloneable을 구현했으면 무조건 clone 재정의
- public 접근 제한자
- 먼저 super.clone() 호출 이후 예외적인 필드 (인스턴스)에 대해서 추가 수행 <sub>ex. deep copy</sub>

<h2>Cloneable 을 사용할만한 상황</h2>

- 이미 Cloneable을 구현한 클래스를 확장할 경우
- 배열의 복사

~~~~
int[] arr = {1, 2, 3};
int[] arr2 = arr.clone();

System.out.println(arr == arr2); // false
System.out.println(arr.equals(arr2)); // false
~~~~

<h3>메서드 하나 없는 Cloneable 인터페이스는 무슨 일을 하나</h3>

- clone() 의 동작방식 결정 <sup>따라하면 안되는 인터페이스 구현 방식</sup>
  - **인터페이스의 목적은 인터페이스에서 정의한 기능을 사용하라는 뜻**
- 필드를 하나하나 복사한 객체를 반한
- 아닌 클래스에서 호출하면 CloneNotSupportedException

