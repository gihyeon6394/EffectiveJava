# item 22. 인터페이스는 타입을 정의하는 용도로만 사용하라

> 인터페이스는 타입을 정의하여 어떤 동작을 할 수 있는지 알려주는 것이 유일한 목적이다

## 인터페이스의 유일한 의미

- 구현클래스에게 인터페이스의 타입으로서
- 어떤 동작을 할 수 있는지 알려주는 것

## 상수 인터페이스는 안티패턴이다

- 상수 인터페이스
    - 상수 필드를 공개할 목적으로 작성한 인터페이스
    - 메서드는 없고 상수만 있음
- ex. java.io.ObjectStreamConstants

```java
public interface PhysicalConstants {
    static final double AVOGADROS_NUMBER = 6.022_140_857e23;
    static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
    static final double ELECTRON_MASS = 9.109_383_56e-31;
}
```

### 안티패턴인 이유

- 구현 클래스 내부에서 사용할 상수는 내부 구현에 해당
- 상수가 외부 <sup>인터페이스</sup>에 있음으로 인해 내부 구현이 노출됨
- final이 아닌 클래스가 상수 인터페이스 구현 시
    - 하위 클래스의 이름 공간이 오염되어버림

### 상수를 공개하는 방법

- 클래스나 인터페이스와 강한 결합의 상수이면 해당 클래스나 인터페이스에 추가한다
    - ex. Integer.MIN_VALUE, Integer.MAX_VALUE, Double.NEGATIVE_INFINITY
- 열거타입으로 공개
- 인스턴스화 할 수 없는 유틸리티 클래스에 담아 공개 [item4](../../chapter2/item4/README.md)

```java
public class PysicalConstants {
    private PysicalConstants() {
    } // 인스턴스화 방지

    public static final double AVOGADROS_NUMBER = 6.022_140_857e23;
    public static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;
    public static final double ELECTRON_MASS = 9.109_383_56e-31;
}
```
