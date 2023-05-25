# item 23. 태그 달린 클래스보다는 클래스 계층구조를 활용하라

> 태그 달린 클래스보다 계층구조가 가독성, 효율, 메모리 사용면에서 더 낫다

### 태그 달린 클래스

- 하나의 클래스가 2가지 이상의 의미를 표현
- 현재 의미를 태그값으로 표현

```java
/**
 * 2가지 이상의 의미를 가질 수 있는 Figure 클래스
 * */
private class Figure {
    enum Shape {RECTANGLE, CIRCLE}; // 태그들
  
    final Shape shape;
  
    // 태그별로 쓰이는 필드
    double length;
    double width;
    double radius;
  
    // 태그별 생성자
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }
  
    // 태그별 생성자
    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }
  
    // 태그별로 다르게 동작하는 메서드
    double area() {
      switch (shape) {
        case RECTANGLE:
          return length * width;
        case CIRCLE:
          return Math.PI * (radius * radius);
        default:
          throw new AssertionError(shape);
      }
    }
}
```


## 태그달린 클래스는 장황하고, 오류를 내기 쉽고, 비효율 적이다

- 쓸데 없는 코드가 많아짐
    - 열거타입, 태그 필드, switch 문 등
- 가독성 떨어짐
- 메모리 점유
    - 다른 의미를 위한 코드도 항상 붙어있음
- 불변 클래스로 만들려면 안쓰는 의미의 필드까지 생성자에서 초기화
- 의미가 추가되면 코드를 수정해야함
    - switch 문, 필드, 메서드 전면 수정

## 태그달린 클래스의 대체로 계층구조를 사용하자

### 계층구조로 태그달린 클래스를 개선하는 방법

1. root가 될 추상 클래스 정의
2. 추상 메서드 : 태그 값에 따라 동작이 달라지는 메서드들
3. 일반 메서드 : 태그 값에 상관 없이 동작이 일정한 메서드들
4. 필드 : 모든 하위 클래스에서 사용할 데이터 필드들
5. root를 확장한 구체 클래스를 의미 별로 하나씩 정의

```java
// root 클래스
abstract class Figure{
    abstract double area();
}

// 구체 클래스들
private class Circle extends Figure{
    final double radius;

    Circle(double radius){
        this.radius = radius;
    }

    @Override
    double area(){
        return Math.PI * (radius * radius);
    }
}

private class Rectangle extends Figure{
    final double length;
    final double width;

    Rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }

    @Override
    double area(){
        return length * width;
    }
}
```
