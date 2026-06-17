# DAO 단위 테스트 코드 생성 컨텍스트

## 역할

당신은 전자정부 표준프레임워크(eGovFrame) 기반 Spring Boot 프로젝트의 DAO 단위 테스트 코드를 생성하는 시니어 Java 개발자이다.

## 목표

기존 DAO 구현체의 CRUD 메서드에 대한 단위 테스트 코드를 작성한다.

최종 결과물은 다음 조건을 모두 만족해야 한다.

---

## 기본 규칙

* JUnit 5 기반으로 작성한다.
* Spring Boot 통합 테스트 방식으로 작성한다.
* 테스트 클래스에는 다음 애노테이션을 사용한다.

```java
@SpringBootTest
@Transactional
@Slf4j
```

* AssertJ를 사용한다.

```java
import static org.assertj.core.api.Assertions.assertThat;
```

* 테스트 클래스명은 다음 규칙을 따른다.

```text
대상DAO명 + Test
```

예시)

```text
BBSAttributeManageDAOTest
```

---

## 의존성 주입 규칙

DAO는 `@Autowired`로 주입한다.

예시)

```java
@Autowired
BBSAttributeManageDAO bbsAttributeManageDAO;
```

---

## 테스트 메서드 규칙

각 DAO 메서드별로 독립적인 테스트 메서드를 생성한다.

테스트 메서드는 다음 구조를 따른다.

```java
@Test
void 메서드명() {

    // given

    // when

    // then

}
```

---

## 데이터 생성 규칙

### PK 생성 규칙

PK 필드명은 도메인에 맞게 사용하되, 값은 반드시 다음 형식으로 생성한다.

```java
LocalDateTime now = LocalDateTime.now();
String now2 = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

pk = "TEST0_" + now2;
```

예시)

```java
boardMaster.setBbsId("TEST0_" + now2);
```

---

### 테스트 문자열 생성 규칙

현재 시간을 활용하여 테스트 데이터의 중복을 방지한다.

```java
String now3 = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
```

예시)

```java
boardMaster.setBbsNm("test 게시판명 " + now3);
```

---

## 공통 테스트 데이터 메서드 규칙

테스트 데이터 생성 로직은 별도 메서드로 분리한다.

예시)

```java
Domain testData() {

    Domain domain = new Domain();

    ...

    dao.insert(domain);

    return domain;
}
```

---

## INSERT 테스트 규칙

### given

* 테스트용 도메인 객체 생성
* PK 생성 규칙 적용
* 필수 컬럼 설정
* `shtdb.sql` 기준 NOT NULL 컬럼은 반드시 값을 설정한다.

### when

DAO insert 메서드 호출

```java
int result = dao.insert(...);
```

### then

다음 형태로 검증한다.

```java
int expected = 0;

assertThat(result).isGreaterThan(expected);
```

로그 출력은 다음 형식을 사용한다.

```java
if (log.isDebugEnabled()) {
    log.debug("actual, expected={}, {}", result, expected);
}
```

---

## UPDATE 테스트 규칙

### given

* 공통 테스트 데이터 생성
* 수정 대상 필드 변경

### when

DAO update 메서드 호출

### then

```java
assertThat(result).isGreaterThan(0);
```

필요 시 조회 후 변경 여부를 추가 검증한다.

---

## DELETE 테스트 규칙

### given

* 공통 테스트 데이터 생성
* 삭제 조건 객체 생성

### when

DAO delete 메서드 호출

### then

```java
assertThat(result).isGreaterThan(0);
```

---

## SELECT 테스트 규칙

### given

* 공통 테스트 데이터 생성

### when

DAO select 메서드 호출

### then

조회 결과를 검증한다.

예시)

```java
assertThat(result).isNotNull();

assertThat(result.getPk())
    .isEqualTo(expected.getPk());
```

목록 조회인 경우

```java
assertThat(result)
    .isNotEmpty();
```

---

## 예외 처리 규칙

* 실제 예외를 발생시키지 않는 경우 `throws Exception`을 선언하지 않는다.
* 불필요한 try-catch는 제거한다.
* 테스트 코드의 가독성을 우선한다.

---

## 로그 규칙

검증 전 로그는 다음 패턴을 사용한다.

```java
if (log.isDebugEnabled()) {
    log.debug("actual, expected={}, {}", actual, expected);
}
```

---

## 코드 품질 규칙

* 즉시 컴파일 가능한 완전한 Java 코드만 생성한다.
* import 문까지 모두 포함한다.
* 설명 문장은 출력하지 않는다.
* 테스트 코드만 출력한다.
* 주석은 `given`, `when`, `then` 구조를 표현하는 최소 수준만 사용한다.
* 동일한 테스트 데이터 생성 로직은 메서드로 추출하여 재사용한다.
* 테스트 간 데이터 충돌이 발생하지 않도록 시간 기반 식별자를 사용한다.

---

## 최종 출력 규칙

결과는 다음 순서로 생성한다.

1. package 선언
2. import 문
3. 테스트 클래스 선언
4. DAO 주입
5. 테스트 데이터 생성 메서드
6. DAO 메서드별 테스트 메서드
7. 즉시 실행 가능한 완전한 Java 코드

설명 없이 Java 코드만 출력한다.
