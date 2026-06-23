# DAO 단위 테스트 코드 생성 표준 및 프롬프트

## 1. DAO 단위 테스트 코드 생성 표준

### 1.1 기본 테스트 구조

DAO 단위 테스트는 Spring Boot 테스트 컨텍스트에서 실제 DAO Bean을 주입받아 실행한다.

```java
@SpringBootTest
@Transactional
@Slf4j
class {DAO명}Test {
}
```

기본 규칙은 다음과 같다.

- JUnit 5 `@Test`를 사용한다.
- AssertJ `assertThat`을 사용한다.
- `@SpringBootTest`로 Spring Bean을 로드한다.
- `@Transactional`로 테스트 데이터가 자동 롤백되도록 한다.
- `@Slf4j`로 디버그 로그를 출력한다.
- 대상 DAO는 `@Autowired`로 주입한다.

### 1.2 테스트 메서드 작성 규칙

테스트 메서드는 DAO public 메서드 단위로 작성한다.

```java
@Test
void insertBBSMasterInf() {
}
```

각 테스트는 다음 흐름을 따른다.

```java
// given
// when
// then
```

기준 코드처럼 테스트 대상 DAO 메서드 호출 결과를 명확히 검증한다.

```java
int expected = 0;

int result = bbsAttributeManageDAO.insertBBSMasterInf(boardMaster);

assertThat(result).isGreaterThan(expected);
```

### 1.3 테스트 데이터 생성 규칙

테스트 데이터는 테스트 내부에서 직접 생성한다.

- PK 또는 고유 식별자는 현재 시각 기반 문자열을 사용해 충돌을 줄인다.
- 테스트 간 실행 순서에 의존하지 않는다.
- insert, update, delete 테스트에 필요한 데이터는 helper 메서드로 생성한다.
- DDL의 `NOT NULL` 컬럼은 반드시 채운다.
- FK 컬럼은 seed 데이터에 존재하는 값을 사용하거나 선행 데이터를 생성한다.
- seed 데이터에만 의존하지 않는다.

예시:

```java
LocalDateTime now = LocalDateTime.now();
String now2 = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
String now3 = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

boardMaster.setBbsId("TEST0_" + now2);
boardMaster.setBbsNm("test 게시판명 " + now3);
```

### 1.4 공통 Helper 메서드 작성 규칙

반복되는 테스트 데이터 생성은 helper 메서드로 분리한다.

```java
private BoardMaster testData() {
    BoardMaster boardMaster = new BoardMaster();

    // 필수 필드 설정

    int result = bbsAttributeManageDAO.insertBBSMasterInf(boardMaster);

    assertThat(result).isGreaterThan(0);

    return boardMaster;
}
```

helper 메서드에서도 데이터 생성 성공 여부를 검증하는 것을 권장한다.

### 1.5 검증 규칙

DAO 메서드의 반환 타입과 SQL 유형에 따라 다음 기준을 적용한다.

- insert 결과가 `int`이면 `assertThat(result).isGreaterThan(0)`로 검증한다.
- update 결과가 `int`이면 `assertThat(result).isGreaterThan(0)`로 검증한다.
- update 결과가 `void`이면 재조회하여 변경 필드를 검증한다.
- delete 결과가 `int`이면 `assertThat(result).isGreaterThan(0)`로 검증한다.
- 논리 삭제는 재조회하여 상태값 변경을 검증한다.
- 단건 조회는 `null`이 아니고 핵심 식별자가 일치하는지 검증한다.
- 목록 조회는 `null`이 아니고 비어 있지 않은지 검증한다.
- 카운트 조회는 `1` 이상인지 검증한다.
- boolean 반환 메서드는 기대 boolean 값을 검증한다.

### 1.6 로그 작성 규칙

디버그 로그는 `log.isDebugEnabled()` 조건 안에서만 출력한다.

```java
if (log.isDebugEnabled()) {
    log.debug("actual, expected={}, {}", result, expected);
}
```

또는

```java
if (log.isDebugEnabled()) {
    log.debug("result={}", result);
}
```

### 1.7 사전 분석 규칙

DAO 단위 테스트 생성 전 반드시 다음을 분석한다.

1. DAO public 메서드 목록
2. 각 메서드가 호출하는 MyBatis statement id
3. mapper XML의 SQL 유형
4. mapper XML의 파라미터 필드
5. 관련 테이블의 PK, FK, NOT NULL 컬럼
6. seed SQL 재사용 가능 여부
7. 독립 테스트 데이터 생성 필요 여부
8. insert/update/delete 이후 재조회 검증 가능 여부

## 2. DAO 단위 테스트 코드 생성 프롬프트

```text
다음 기준에 따라 대상 DAO의 DAO 단위 테스트 코드를 생성하라.

목표:
대상 DAO의 전체 public 메서드에 대해 성공 시나리오 중심의 DAO 단위 테스트 클래스를 작성한다.

개발 표준:
- JUnit 5를 사용한다.
- AssertJ `assertThat`을 사용한다.
- 테스트 클래스에는 `@SpringBootTest`, `@Transactional`, `@Slf4j`를 선언한다.
- 대상 DAO는 `@Autowired`로 주입한다.
- 테스트 클래스명은 `{DAO명}Test`로 작성한다.
- 대상 DAO와 같은 테스트 패키지에 작성한다.
- 각 DAO public 메서드마다 테스트 메서드를 작성한다.
- 테스트 메서드명은 DAO 메서드명과 동일하게 작성한다.
- 오버로드 메서드는 파라미터 타입을 구분할 수 있는 접미사를 붙인다.
- 각 테스트는 `given / when / then` 주석 구조를 따른다.
- mock을 사용하지 않고 실제 Spring Bean, MyBatis mapper, 테스트 DB를 사용한다.
- 테스트 데이터는 helper 메서드에서 독립적으로 생성한다.
- 테스트 간 실행 순서에 의존하지 않는다.
- `@Transactional` 롤백을 전제로 별도 삭제 정리는 작성하지 않는다.

반드시 수행할 분석:
1. 대상 DAO의 전체 public 메서드 목록을 확인한다.
2. 각 public 메서드가 호출하는 MyBatis statement id를 확인한다.
3. mapper XML에서 각 statement id의 SQL 유형을 확인한다.
4. mapper XML에서 필요한 파라미터 필드를 확인한다.
5. 관련 DDL 또는 seed SQL에서 테이블의 PK, FK, NOT NULL 컬럼을 확인한다.
6. seed 데이터 재사용 가능 여부를 판단한다.
7. seed 데이터만으로 부족하면 독립 테스트 데이터 생성 helper 메서드를 작성한다.
8. insert/update/delete 이후 재조회 검증이 가능한지 확인한다.
9. mapper resultMap에 실제 매핑된 필드만 검증한다.

검증 기준:
- insert 결과가 `int`이면 `assertThat(result).isGreaterThan(0)`로 검증한다.
- update 결과가 `int`이면 `assertThat(result).isGreaterThan(0)`로 검증한다.
- update 결과가 `void`이면 재조회하여 변경 필드를 검증한다.
- delete 결과가 `int`이면 `assertThat(result).isGreaterThan(0)`로 검증한다.
- 논리 삭제는 재조회하여 상태값 변경을 검증한다.
- 단건 조회는 null이 아니고 핵심 식별자가 일치하는지 검증한다.
- 목록 조회는 null이 아니고 비어 있지 않은지 검증한다.
- 카운트 조회는 `1` 이상인지 검증한다.
- boolean 반환 메서드는 기대 boolean 값을 검증한다.

테스트 데이터 작성 기준:
- PK 값은 현재 시각 기반 문자열 또는 프로젝트 ID 생성 규칙을 사용한다.
- DDL의 NOT NULL 컬럼은 반드시 값을 채운다.
- FK 컬럼은 seed 데이터에 존재하는 값을 사용하거나 선행 데이터를 생성한다.
- mapper XML에서 참조하는 파라미터 필드는 명시적으로 세팅한다.
- 목록/카운트 조회용 VO에는 페이징 필드를 명시한다.
- 반복되는 객체 생성은 private helper 메서드로 분리한다.
- insert helper 내부에서도 insert 결과가 0보다 큰지 검증한다.

로그 작성 기준:
- `log.isDebugEnabled()` 조건 안에서만 debug 로그를 출력한다.
- actual/expected 또는 result 중심으로 간결하게 출력한다.

출력 형식:
- 설명 없이 Java 테스트 클래스 전체 소스코드만 출력한다.
- package, import, class 선언을 포함한다.
- 사용하지 않는 import는 포함하지 않는다.
- 테스트 흐름 주석 `// given`, `// when`, `// then`은 유지한다.

대상 DAO:
@{대상 DAO 경로}

대상 mapper:
@{대상 mapper XML 경로}

대상 DDL 또는 seed SQL:
@{대상 DDL 또는 seed SQL 경로}
```
