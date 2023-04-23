<h1>item 2. 생성자에 매개변수가 많다면 빌더를 고려하라</h1>

> **필드가 4개 이상이라면** 빌더가 낫다  
> 이유는 생성자보다 확장성이 좋고, setter 보다 안정성이 있으니까  
> 4개 미만이어도, 결국 늘어날 걸 대비하려면 빌더가 나을 걸?  


<h2>기본 생성자 대신 객체를 완성하는 여러 방법</h2>

- **매개변수 커스터마이징이 가능한,** telescoping constructor pattern <sup>점층적 생성자 패턴</sup> 
  - 필드르 1개받는 생성자, 2개받는 생성자, n개 받는 생성자.....
  - 클라이언트 코드 너저분, 가독성 떨어짐
- **원하는 필드만 값지정이 가능한,** setter 를 이용한 java beans  
  - 객체를 완성하기 까지 여러 setter 를 호출  
  - setter가 끝나기 전까지 consistency <sup>일관성</sup> 으로부터 위험함
  - 따라서 불변 클래스에서 부적합 thread-not-safe    
- **둘을 합친,** builder pattern **(추천)**


<h3>Builder Builder?</h3>

- 클래스의 내부 클래스
- 인스턴스화할때 필드를 채워나가는 역할

<h2>Builder pattern 장점</h2>

- 계층적 클래스에 적합  
- 유연함  

<h2>Builder pattern 단점</h2>

- 클래스 작성, 인스턴스 생성 시 빌더부터 만들어야 함  
- 성능에 민감할 시 문제 발생 가능성이 있음  
- 코드가 장황해짐  


~~~~
// Idol 인스턴스 생성 시
Idol hani4 = new Idol.Builder("팜하니", 21).isLeader(0).isDebut(1).isMarried(0).build();
...

// Idol 클래스
public class Idol {

    private final String memberName;
    ....
    
    public static class Builder {
        private final String memberName;
        ...

        private String home = "-";      
        ...
        
        public Builder(String memberName, int age) {
            this.memberName = memberName;
            ...
        }

        public Builder home(String home) {
            this.home = home;
            return this;
        }
        
        ...

        public Idol build(){
            return new Idol(this);
        }
    }

    public Idol(Builder builder) {
        this.memberName = builder.memberName;
        ....
    }

}
~~~~




