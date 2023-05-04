<h1>item 6. 불필요한 객체 생성을 피하라</h1>

> 기존 객체를 재사용해도 된다면 재사용을 우선적으로 고려하자

<h3>불필요한 객체 생성은 코드 가독성, 성능에 영향을 준다</h3>

~~~~
String strBad = new String("bad String instance!");
~~~~

<h2>매번 똑같은 기능의 객체를 생성하기 보다는, 기존 객체를 재사용해보라</h2>

- 빠르고 세련되다
- 불변 객체
    - 언제든 안심하고 재사용 가능
    - 정적 팩터리 메서들 사용한다면 재사용 보장 가능
- 생산 비용이 비싼 객체에도 적합
    - ex. java.util.regex.**Pattern**
    - 캐싱한 뒤 재사용한다
      ~~~~
      private static final Pattern patternHasNumber = Pattern.compile(".*[0-9]+.*");
      ...
      boolean isCorrectGood = patternHasNumber.matcher("abc123xxx").matches();
      ~~~~
    - 캐싱하는 시기를 지연초기화 <sup>lazy initialization</sup>로 개선할 수 있지만, 권하진 않음
        - 코드가 복잡해지고, 성능개선이 크지 못함

<h3>불필요한 객체를 만드는 또 다른 예 : **오토박싱**</h3>

- 의미상으로는 기본타입의 박싱클래스로 별다를 것 없으나
- **성능은 그렇지 않음**
- 박싱된 기본타입보다는 기본타입을 사용

~~~
    private static void sum() {
        Long sum = 0L; 
        
        // sum 인스턴스가 Integer.MAX_VALUE 개 만들어짐!!
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            sum += i;
        }
    }
~~~~
