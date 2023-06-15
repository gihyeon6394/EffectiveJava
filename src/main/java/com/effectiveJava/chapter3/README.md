<h1>3장 모든 객체의 공통 메서드</h1>

- final이 아닌 Object 메서드들을 **언제 어떻게** 재정의 해야하는지

<h3>final이 아닌 Object 메서드</h3>

```java
public class foo{
    
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
}
     
```

<h2>contents</h2>

- [item 10. equals는 일반 규약을 지켜 재정의하라](item10/README.md)    
  > equals 재정의는 필요한 경우에만 재정의 한다. 오히려 잘못 정의하면 안좋은 결과를 초래
  > 재정의 할때는 일반 규약을 반드시 지킨다


- [item 11. equals를 재정의하려거든 hashCode도 재정의하라](item11/README.md)    
    > euqals() 결과가 같은 인스턴스는 hashCode() 결과도 같아야함


- [item 12. toString을 항상 재정의하라](item12/README.md)    
    > 간결하면서 사람이 읽기 쉬운 형태의 유익한 정보를 반환하면 디버깅이 쉬움


- [item 13. clone 재정의는 주의해서 진행하라](item13/README.md)    
    > 배열 제외하면 복사 생성자, 복사 팩터리가 낫다. 널리 사용중이라 사용법<sup>재정의 방법</sup> 은 익히자


- [item 5. Comparable을 구현할지 고려하라](item14/README.md)     
    > 인스턴스의 순서를 고려할 필요가 있을 때 Comparable 인터페이스를 구현

