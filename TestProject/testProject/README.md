### 스프링 부트(Spring Boot) 기초 강의
https://www.youtube.com/watch?v=7t6tQ4KV37g

---

### 생성 패턴  
생성 패턴은 객체의 생성과 관련된 패턴  
특정 객체가 생성되거나 변경되어도 프로그램 구조에 영향을 최소화할 수 있도록 유연성 제공  

| 생성 패턴                   |의도| 
|:------------------------|---|  
 | 추상 팩토리 (Abstract Factory)|구체적인 클래스를 지정하지 않고 인터페이스를 통해 연관되는 객체들을 묶어줌|  
| 빌더 (Bulilder)           |객체의 생성과 표현을 분리하여 객체를 생성|  
| 팩토리 메소드 (Factory Method)|객체 생성을 서브클래스로 분리하여 위임 (캡술화)|  
| 프로토타입 (Prototype)       |원본 객체를 복사하여 객체를 생성 (클론)|  
| 싱글톤 (Singleton)         |한 클래스마다 인스턴스를 하나만 생성하여 어디서든 참조|  
---
### 행동(행위) 패턴  
|행동 패턴|의도|
|---|---|
|책임 연쇄 (Chain of Responsibility)|요청을 받는 객체를 연쇄적으로 묶어 요청을 처리하는 객체를 만날 때까지 객체 Chain을 따라 요청을 전달|
|커맨드 (Command)|요청을 객체의 형태로 캡술화하여 재사용하거나 취소|
|인터프리터 (Interpreter)|특정 언어의 문법 표현을 정의|
|박복자 (lterator)|컬렉션 구현 방법을 노출하지 않으면서 모든 항목에 접근할 수 있는 방법을 제공|
|중재자 (Mediator)|한 집합에 속해있는 객체들의 상호작용을 캡술화하여 새로운 객체로 정의|
|메멘토 (Memento)|객체가 특정 상태로 다시 되돌아 올 수 있도록 내부 상태를 실체화|
|옵저버 (Observer)|객체 상태가 변할 때관련 객체들이 그변화를 전달받아 자동으로 갱신|
|상태 (State)|객체의 상태에 따라 동일한 동작을 다르게 처리|
|전략 (Strategy)|동일 계열의 알고리즘군을 정의하고 캡술화하여 상호 교환이 가능하게 함|
|템플릿 메소드|상위 클래스는 알고리즘의 골격만을 작성하고 구체적인 처리는 서브 클래스로 위임|
|방문자 (Visitor)|객체의 원소에 대해 수행할 연산을 분리하여 별도의 클래스로 구성|
---
### 구조 패턴
구조 패턴은 프로그램 내 자료구조나 인터페이스 구조 등 프로그램 구조를 설계하는데 사용되는 패턴
클래스나 객체를 조합하여 더 큰 구조를 만들 수 있게 해줌  

|구조 패턴 | 의도                                                             |
|---|----------------------------------------------------------------|
|어댑터 (Adapter) | 클래스의 인터페이스를 어떤 클래스에서든 이용할 수 있도록 변환                             |
|브리지 (Bridge) | 구현부에서 추상층을 분리하여 각자 독립적으로 변형하고 확장할 수 있도록 함                      |
|컴포지트 (Composite) | 객체들의 관계를 트리 구조로 구성하여 표현하는 방식으로 복합 객체와 단일 객체를 구분없이 다름           |
|데코레이터 (Decorator) 파사드 (Facade) | 주어진 상황에 따라 객체에 다른 객체를 덧붙임 서브 시스템에 있는 인터페이스 집합에 대해 통합된 인터페이스 제공 |
|플라이웨이트 (Flyweight) |  크기가 작은 여러 개의 객체를 매번 생성하지 않고 최대한 공유하여 사용하도록 메모리 절약             |
|프록시 (Proxy)| 실제 기능을 수행하는 객체 대신 가상의 객체를 사용해 로직의 흐름을 제어                       |
---
### API (Application Programming Interface)
- 응용 프로그램에서 사용할 수 있도록 다른 응용 프로그램을 제어할 수 있게 만든 인터페이스를 뜻함  
*인터페이스: 어떤 장치간 정보를 교환하기 위한 수단이나 방법(마우스, 키보드 등)  
- API 를 사용하면 내부 구현 로직을 알지 못해도 정의되어 있는 기능을 쉽게 사용할 수 있음

### REST (Representational State Transfer)
- 자원의 이름으로 구분하여 해당 자원의 상태를 교환하는 것을 의미
- 서버와 클라이언트의 통신 방식 중 하나
- HTTP URI를 통해 자원을 명시하고 HTTP Method를 통해 자원을 교환하는 것  
*HTTP Method : Create, Read, Update, Delete

### REST 특징
- Server-Client 구조
- Stateless : client 정보가 서버에 저장X
- Cacheable
- 계층화(Layered System) : 로드밸런싱, 보안요소, 캐시 등
- Code on Demand(Optional) : 요청을 받으면 서버에서 client로 코드 또는 스크립트(로직)을 전달하여 client 기능 확장
- 인터페이스 일관성(Uniform Interface)

### REST 장점
- HTTP 표준 프로토콜을 사용하는 모든 플랫폼에서 호환 가능
- 서버와 클라이언트 역활을 명확히 분리
- 여러 서비스 설계에서 생길 수 있는 문제 최소화

### REST API
- REST 아키텍처의 조건을 준수하는 어플리케이션 프로그래밍 인터페이스를 뜻함  
*일반적으로 REST 아키텍처를 구현하는 웹 서비스를 RESTful 하다고 표현

### REST API 특징
- REST 기반으로 시스템을 분산하여 확장성과 재사용성을 높임
- HTTP 표준을 따르고 있어 여러 프로그래밍 언어로 구현 가능

### REST API 설계 규칙
- 웹 기반의 REST API를 설계할 경우 URI를 통해 자원 표현  
ex) https://xxx.studio/member/589  
Resource : memeber  
Resource id : 589

- 자원에 대한 조작은 HTTP Method(CRUD)를 통해 표현  
URI에 행위가 들어가면 안됨

- 메세지를 통한 리소스 조작  
HEADER를 통해 content-type을 지정하여 데이터 전달(HTML, XML, JSON, TEXT 등)

### REST API 설계 규칙
- URI는 소문자 사용
- Resource의 이름이나 URI가 길어질 경우 하이픈(-) 사용
- 언더바(_) 사용하지 않음
- 파일 확장자 표현하지 않음

### @RestController
- @Controller + @ResponseBody
- Controller 하위 메소드에 @ResponseBody를 붙이지 않아도 문자열과 JSON 등 전송 가능
- View를 거치지 않고 HTTP ResponseBody에 직접 return 값을 담아 보냄

### @RequestMapping
- 클래스와 메소드의 RequestMapping을 통해 URL을 매핑하여 경로를 설정하여 해당 메소드에서 처리
- value : url 설정
- method : GET, POST, DELETE, PUT, PATCH 등

### @PathVariable
- GET 형식의 요청에서 파라미터를 전달하기 위해 URL에 값을 담아 요청하는 방법

### @RequestParam
- GET 형식의 요청에서 쿼리 문자열을 전달하기 위해 사용되는 방법
- '?'를 기준으로 우측에 {키}={값} 형태로 전달, 복수 형태로 전달할 경우 & 사용

---

### Swagger
- 서버로 요청되는 API 리스트를 HTML 화면으로 문서화하여 테스트 할 수 있는 라이브러리
- 이 라이브러리는 서버가 가동되면서 @RestController를 읽어 API를 분석하여 HTML 문서를 작성함

- 2.x.x 버전: http://localhost:8080/swagger-ui.html
- 3.x.x 버전: http://localhost:8080/swagger-ui/index.html

---

### ResponseEntity
- Spring Framework에서 제공하는 클래스 중 HttpEntity라는 클래스를 상속받아 사용하는 클래스
- 사용자의 HttpRequest에 대한 응답 데이터를 포함
- 포함하는 클래서(HttpStatus, HttpHeaders, HttpBody)
