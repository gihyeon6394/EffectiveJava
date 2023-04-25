<h1>3장 모든 객체의 공통 메서드</h1>

- final이 아닌 Object 메서드들을 **언제 어떻게** 재정의 해야하는지

<h3>final이 아닌 Object 메서드</h3>

~~~~
    @Override
    public int hashCode() {
      return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
   ~~~~

<h2>contents</h2>

- item 10. equals는 일반 규약을 지켜 재정의하라   
    > 필수로 정의해야하는 건 아님, 근데 **규약**을 지켜서 정의할 것


- [item 11. equals를 재정의하려거든 hashCode도 재정의하라](https://github.com/gihyeon6394/practice-effective-java/tree/main/src/main/java/com/effectiveJava/chapter3/item11)   
    > euqals로 같은 인스턴스는 hashCode도 같아야함


- [item 12. toString을 항상 재정의하라](https://github.com/gihyeon6394/practice-effective-java/tree/main/src/main/java/com/effectiveJava/chapter3/item12)   
    > 간결하면서 사람이 읽기 쉬운 형태의 유익한 정보를 반환하면 디버깅도 쉽고 프로그래밍도 즐거움


- [item 13. clone 재정의는 주의해서 진행하라](https://github.com/gihyeon6394/practice-effective-java/tree/main/src/main/java/com/effectiveJava/chapter3/item13)   
    > 배열 제외 복사 생성자, 복사 팩터리가 낫다. 널리 사용중이라 사용법<sup>재정의 방법</sup> 은 익히자


- item 5. Comparable을 구현할지 고려하라   
    >  

