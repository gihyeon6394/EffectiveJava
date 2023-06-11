# item 25. 톱레벨 클래스는 한 파일에 하나만 담으라

> 컴파일 순서에 따라 결과가 바뀌니, 다른 파일로 담거나 정적 멤버클래스로 선언해라

## 컴파일 순서에 따라 결과가 달라질 수 있다.

```java
// Main.java file
public class Main {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME); // pancake
    }
}

// Utensil.java file
public class Utensil {
    static final String NAME = "pan";
}

class Dessert {
    static final String NAME = "cake";
}

// Dessert.java file
class Utensil {
    static final String NAME = "pot";
}

class Dessert {
    static final String NAME = "pie";
}
```

- 위 소스는 컴파일 순서에 따라 아래와 같이 나뉠 수 있음
- 컴파일 에러
- 정상 출력
    - 출력 : pancake
    - 출력 : potpie

### 컴파일 에러 시나리오

```shell
javac Main.java Dessert.java
# Duplicate class found in the file...
```

1. Main.java 컴파일
    - Main.class 생성
    - Utensil 자바 파일 참조를 확인하여 Utensil.class 생성
2. Dessert.java 컴파일 도중 에러
    - Dessert.class 생성하던 중 Utensil과 Dessert가 중복되었다고 에러 발생

### 정상 출력 시나리오

#### "pancake" 출력 <sup>1</sup>

```shell
javac Main.java 
```

1. Main.java 컴파일
    - Main.class 생성
    - Utensil 자바 파일 참조를 확인하여 Utensil.class 생성

#### "pancake" 출력 <sup>2</sup>

```shell
javac Main.java Utensil.java
```

1. Main.java 컴파일
    - Main.class 생성
    - Utensil 자바 파일 참조를 확인하여 Utensil.class 생성
2. Utensil.java 컴파일
    - 이미 1단계에서 컴파일 완료

#### "potpie" 출력

```shell
javac Dessert.java Main.java
```

1. Dessert.java 컴파일
    - Dessert.class 생성
2. Main.java 컴파일
    - Dessert.class 참조를 확인

## 대안

- 톱레벨 클래스를 서로 다른 소스 파일로 분리
- or 정적 멤버 클래스로 생성
    - 부차적인 클래스임을 의미하고,
    - private으로 접근 제어 가능

```java
public class Main {

    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }

    private static class Utensil {
        static final String NAME = "pan";
    }

    private static class Dessert {
        static final String NAME = "cake";
    }
}

```