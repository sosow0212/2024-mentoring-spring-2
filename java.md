## 객체 선언 관련 : 생성자에 관한 내용

- 클래스 내에서 필드로 객체를 선언하는 방식은 크게 3가지
    - = new ...로 초기화 해주는 방식 
    - 생성자를 통해 초기화 해주는 방식 
    - 정적 팩토리 메서드로 초기화해주는 방식

- 보통 이렇게 세개의 방법으로 초기화 할 수 있는데..
  - [ = new ~로 바로 필드에서 초기화 해주는 방식 ] 은 값이 무조건 고정이 되어서 새로운 생성 전략이 생김 (생성자를 추가해도 변하기 힘들다는 단점이 있고, 테스트를 할 때에도 Cars 를 만들고 .add(...) 이런식으로 다 채워줘야 함)
  - 반면, 생성자로 초기화를 해줄 땐 테스트할 때에도 목업 데이터를 미리 만들고 생성자에 주입을 바로 해주면 간결해지는 장점도 있음 또한, 생성 전략이 많아져도 오버로딩을 통해 다형성을 지킬 수 있슈
  - 정적 팩토리 메서드 같은 경우, 기본적으로 생성자를 순수하게 예를 들어 List<car>가 객체라면 정팩메를 쓸 때 생성자는 public Cars(List<Car>) { this.cars = cars } 이런식으로 고정시켜놓고, 정적 팩토리 메서드에서 public static Cars from(String names) { 1.names 예외 검사 / 2. names List로 파싱 / 3. 초기화 } 이런식으로 쓰면 훨씬 직관적이고 로직이 들어갈 수 있다는 장점이 있다. 추가적으로 정팩메를 사용하면 생성전략이 많아져도 퓨어한 생성자를 바탕으로 생성 전략마다 로직이 바뀌어도 수정에 큰 문제가 없어서 주로 많이 사용함.

- 결론
  - 어떤 걸 따른다보다는 상황에 맞게 효율적인 걸 쓰면 됨
  - 보통 Car의 필드로 int moveCount, String name이 있을 땐 처음에 moveCount = 0 이 기본 값이 될테니 이럴 땐 생성자도 좋지 않고 정적 팩토리 메서드로 public static Car createDefaultCar(String name) {this.name = name; this.moveCount = DEFAULT_MOVE_COUNT} 이런식으로 하면 외부에서 정적 팩토리 네이밍을 보고 어떤 전략인지도 파악하기 좋음!
---
## record 클래스

### 개요
* 데이터의 불변 상태를 유지하기 위해 우리는 데이터 클래스를 생성해왔음. like dto.
  * 각 데이터 조각에 대해 private이고 final인 필드를 만든다.
  * 각 필드에 대한 getter를 만든다.
  * 각 필드에 해당하는 인수를 가진 public 생성자를 만든다.
  * 모든 필드가 일치할 때 동일한 클래스 객체에 대해 true를 반환하는 equals 메서드를 만든다.
  * 모든 필드가 일치할 때 동일한 값을 반환하는 hashCode 메서드를 만든다.
  * 클래스 이름과 각 필드 이름 및 해당 값을 포함하는 toString 메서드를 만든다.

* 이와 같은 방식으로 데이터 불변 클래스를 생성하면 문제점이 발생함
  1. 보일러플레이트 코드가 많다.
  2. 클래스의 목적이 가려진다: ex) 이름과 주소를 가진 사람을 나타내는 클래스라는 점이 명확하지 않다.

* 따라서 데이터 클래스임을 나타내는 클래스에 대한 필요성이 생김. => 자바 14부터 반복적인 클래스를 record 로 대체 가능.

### 주요 기능 및 이점

1. 생성자, equals, hashCode, toString 메서드 자동 생성:
   Java 컴파일러가 자동으로 private 및 final 필드를 생성하고, 이에 해당하는 public 생성자, equals, hashCode, toString 메서드를 생성.
   ~~~
   public record Person(String name, String address) {}
   ~~~
   Record를 사용하면, 각 필드에 대한 인자를 가지는 public 생성자가 자동으로 생성.

2. Getter 메서드
   * 필드 이름과 동일한 이름을 가진 public getter 메서드가 자동으로 생성.

3. 생성자 커스터마이징
  * 기본 생성자는 자동으로 생성되지만, 사용자 정의 생성자도 가능. 예를 들어, null 값 확인을 추가할 수 있음(validation).
    ~~~
    public record Person(String name, String address) {
    public Person {
        Objects.requireNonNull(name);
        Objects.requireNonNull(address);
      }
    }
    ~~~
  * 다른 인수를 가진 생성자도 만들 수 있음.

4. static 메서드 사용 가능
  ~~~
  public record Person(String name, String address) {
      public static String UNKNOWN_ADDRESS = "Unknown";
  
      public static Person unnamed(String address) {
          return new Person("Unnamed", address);
      }
  }
  ~~~

### 결론
* Record를 사용하면 보일러플레이트 코드를 줄이고, 불변 클래스의 신뢰성을 높일 수 있음.
