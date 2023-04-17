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
  &#58;


- item 11. equals를 재정의하려거든 hashCode도 재정의하라   
  &#58;


- item 12. toString을 항상 재정의하라   
  &#58;


- item 13. clone 재정의는 주의해서 진행하라   
  &#58;


- item 5. Comparable을 구현할지 고려하라   
  &#58; 

