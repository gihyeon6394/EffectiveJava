<h1>item 19. 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라.</h1>

> 상속용으로 설계할 때 고려할것이 많음. 상속용이 아니면 상속을 못하게 차단


<h2>상속용 클래스 작성법</h2>

- 재정의 가능 메서드 :  내부적으로 어떻게 이용하는지 <sup>자기사용</sup> 문서화
    - ex. java.util.AbstractCollection 의 interator()를 재정의하면 remove()에 영향을 주는걸 문서화
- hook 멤버 <sup>클래스 내부동작 중 끼어드는 것</sup>를 잘 선별하여 protected 로 공개
    - ex. java.util.AbstractList 의 removeRange()
- 상속 클래스의 생성자는 직간접적으로 재정의 가능 메서드를 호출하면 안됨
    - 상위 클래스 생성자가 하위 클래스의 재정의 메서드보다 먼저 실행되버림
    - 같은 이유로 Cloneable Serializable 인터페이스를 구현한 클래스는 clone readObject 에서 재정의 가능 메서드를 호출하면 안됨
        ~~~~
            static class Super{
                public Super() {
                    overrideMe();
                }
    
                public void overrideMe() {
                }
            }
    
            static final class Sub extends Super{
                private final String name;
    
                public Sub(String name) {
                    this.name = name;
                }
    
                @Override
                public void overrideMe() {
                    System.out.println(name);
                }
            }
        ~~~~
- 상속용으로 작성했으면 앞으로 그 결정에 영훤히 책임저야함

<h3>상속 클래스를 올바르게 작성했는지 확인하는 방법</h3>

- 직접 하위클래스를 만들어 보는 것이 유일한 방법
- 하위클래스를 3개 만들어보고 안쓰는 protected 멤버는 private으로

<h2>상속용으로 설계하지 않은 클래스는 상속을 금지시켜라</h2>

- 상속용으로 설계하지 않았더라도 상속 가능하게 두면 클라이언트들이 무분별하게 상속함
- 그러면 클래스에 수정이 가해질 때마다 오류 리포트 접수
- 따라서 상속용이 아닌클래스는 상속을 금지시켜야함

<h3>상속을 금지시키는 방법</h3>

- 클래스를 final로 선언 (더 쉬움)
- 생성자 모두 private 혹은 package-private 으로 선언
    - 그리고 public 정적 팩터리를 만들어 제공

