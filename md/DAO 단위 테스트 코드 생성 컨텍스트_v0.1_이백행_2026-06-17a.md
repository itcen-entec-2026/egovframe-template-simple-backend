# DAO 단위 테스트 코드 생성 컨텍스트

## 기술 스택

* 전자정부 표준프레임워크(eGovFrame)
* Spring Boot
* JUnit 5
* AssertJ
* Lombok

## 테스트 클래스 규칙

* `@SpringBootTest` 사용
* `@Transactional` 사용
* `@Slf4j` 사용
* 클래스명은 `대상DAO명 + Test` 형식으로 생성한다.

## DAO 주입 규칙

* DAO는 `@Autowired`로 주입한다.

## 테스트 메서드 규칙

* DAO 메서드별로 테스트 메서드를 생성한다.
* `given`, `when`, `then` 구조를 따른다.
* `@Test`를 사용한다.

## 검증 규칙

* AssertJ를 사용한다.
* INSERT/UPDATE/DELETE는 영향받은 행 수를 검증한다.

```java
assertThat(result).isGreaterThan(0);
```

* SELECT 단건 조회는 `isNotNull()` 및 주요 필드를 검증한다.
* SELECT 목록 조회는 `isNotEmpty()`를 검증한다.

## 테스트 데이터 규칙

* 테스트 데이터 생성 로직은 별도 메서드로 분리한다.
* 중복 방지를 위해 현재 시간을 사용한다.

```java
LocalDateTime now = LocalDateTime.now();
String now2 = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
String now3 = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
```

## PK 생성 규칙

* PK 값은 반드시 다음 형식을 사용한다.

```java
"TEST0_" + now2
```

## 데이터베이스 규칙

* `src/main/resources/db/shtdb.sql` 기준 NOT NULL 컬럼은 반드시 값을 설정한다.
* FK 제약 조건을 고려하여 테스트 데이터를 생성한다.

## 코드 품질 규칙

* import 문까지 포함한 완전한 Java 코드를 생성한다.
* 컴파일 가능해야 한다.
* 불필요한 `throws Exception`을 사용하지 않는다.
* 설명 없이 Java 코드만 출력한다.
