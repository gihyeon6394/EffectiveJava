# item 28. 배열보다는 리스트를 사용하라

> 배열은 공변이고 실체화되기 떄문에 런타임에 타입 불안정하다. <sub>`ClassCastException`</sub>

## 배열 vs 리스트

- 공변 <sup>covariant</sup> vs 불공변 <sup>invariant</sup>
- 실체화 <sup>relify</sup> vs 실체화 불가 타입 <sup>non-reifiable type</sup>

### 베열은 공변 <sup>covariant</sup>

```java
public class Item28 {

    private void testCovariant() {

        // runtime error
        Object[] objectArray = new Long[1];
        objectArray[0] = "I don't fit in"; // Throws ArrayStoreException

        // compile error
        List<Object> list = new ArrayList<Long>(); // incompatible types: java.util.ArrayList<java.lang.Long> cannot be converted to java.util.List<java.lang.Object>
    }
}
```

> ### 공변
> `class A`가 `class B`의 하위 타입이라면 `A[]`는 `B[]`의 하위 타입이다.

- 컴파일 에러는 컴파일 시간에 에러를 바로잡을 수 있음

### 배열은 실체화 <sup>relify</sup>

- 배열은 런타임 중에 매번 타입을 확인함 <sub>위에서 확인 후 에러 발생</sub>
- 제네릭은 타입정보가 런타임전에 소거됨 <sub>erasure</sub>
    - 소거 <sup>erasure</sup> : 제네릭 지원 이전의 코드들이 제네릭과 호환되도록 해줌
- 실체화 불가 타입 <sup>non-reifiable type</sup> : 런타임에는 타입 정보가 소거되어 실체화 불가능한 타입
    - ex. `List<String>`은 런타임에 `List`로 소거됨
    - ex. `List<String>[]`는 런타임에 `List[]`로 소거됨
    - ex. `List<?>`는 런타임에 `List`로 소거됨
    - ex. `List<List<String>>`는 런타임에 `List<List>`로 소거됨
    - ex. `List`는 런타임에 `List`로 소거됨
- 매개변수화 타입 중 실체화 가능 타입은 비한정적 와일드카드 타입 뿐 `?`

#### 배열은 제네릭과 어울리지 못한다

- 배열은 제네릭 타입, 매개변수화 타입, 타입 매개변수로 사용할 수 없다 <sub>compile error</sub>

```java
ArrayList<String>[]stringLists=new ArrayList<String>[1]; // compile error : generic array creation
        ArrayList<Integer> intList=Arrays.asList(1); // compile error : incompatible types: no instance(s) of type variable(s) T exist so that java.util.List<T> conforms to java.util.ArrayList<java.lang.Integer>
        String[]strArray=new String[1];
        Object[]objects=stringLists;
        objects[0]=intList;
        String s=stringLists[0].get(0);
```

- 컴파일 에러 없이 위 코드를 허용한다면 마지막 줄에서 `ClassCastException`이 발생

## 타입 안정을 위해 배열을 리스트로 변환

```java
public class Item28 {

    public static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3, 4, 5);
        Chooser chooser1 = new Chooser(intList);
        Object rndNo = (Integer) chooser1.choose(); // 형변환 필요한데, 타입 불안정
    }

    public static class Chooser {
        private final Object[] choiceArray;

        public Chooser(Collection choices) {
            this.choiceArray = choices.toArray();
        }

        public Object choose() {
            return choiceArray[(int) (Math.random() * choiceArray.length)];
        }
    }
}
```

```java
public class Item28 {

    public static void main(String[] args) {
        List<Integer> intList = List.of(1, 2, 3, 4, 5);
        Chooser chooser1 = new Chooser(intList);
        Integer rndNo = (Integer) chooser1.choose(); // 형변환 필요한데, 타입 안정
    }

    public static class Chooser<T> {
        private final List<T> choiceList;

        public Chooser(Collection<T> choices) {
            choiceList = new ArrayList<>(choices);
        }

        public T choose() {
            Random rnd = ThreadLocalRandom.current();
            return choiceList.get(rnd.nextInt(choiceList.size()));
        }
    }
}
```


