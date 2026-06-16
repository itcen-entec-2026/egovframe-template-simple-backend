# 전체 DAO 단위 테스트 코드 생성 컨텍스트

## 목적

이 문서는 `BBSAttributeManageDAOTest.java`를 분석해, 전자정부 표준프레임워크 기반 프로젝트의 전체 DAO 단위 테스트 코드를 생성하기 위한 기준을 정의한다.

특정 DAO 하나의 테스트를 복사하는 것이 아니라, `EgovAbstractMapper`를 상속한 모든 DAO에 반복 적용할 수 있는 테스트 생성 원칙을 제공한다.

## 기준 샘플

분석 기준 파일:

`src/test/java/egovframework/let/cop/bbs/domain/repository/BBSAttributeManageDAOTest.java`

샘플에서 유지할 패턴:

* `@SpringBootTest`로 실제 Spring 테스트 컨텍스트를 로드한다.
* `@Transactional`로 테스트 데이터 변경을 롤백한다.
* `@Slf4j`로 디버그 로그를 남긴다.
* DAO를 `@Autowired`로 주입한다.
* AssertJ의 `assertThat()`을 사용한다.
* `given`, `when`, `then` 흐름으로 테스트를 구성한다.
* 등록, 수정, 삭제 결과는 영향받은 행 수가 `0`보다 큰지 검증한다.
* 테스트 데이터 생성은 보조 메서드로 분리한다.
* 테스트용 PK는 `LocalDateTime`과 `DateTimeFormatter`를 사용해 식별 가능한 값으로 만든다.

샘플에서 개선할 점:

* DAO 필드는 `private`으로 선언한다.
* 주석 처리된 코드는 생성하지 않는다.
* `EgovIdGnrService`는 DAO 단위 테스트의 기본 ID 생성 방식으로 사용하지 않는다.
* DB `NOT NULL` 컬럼과 MyBatis insert 매퍼의 필수 파라미터를 모두 분석해 테스트 데이터를 완성한다.
* `testData()` 안에서 insert한 결과도 검증한다.
* `null`을 허용하지 않는 컬럼에 대응하는 필드는 절대 `null`로 설정하지 않는다.

## 적용 대상 DAO

다음과 같은 DAO를 대상으로 한다.

* `EgovAbstractMapper`를 상속한 DAO
* `@Repository`가 선언된 DAO
* MyBatis mapper XML의 namespace와 연결되는 DAO
* `insert`, `update`, `delete`, `selectOne`, `selectList`를 사용하는 DAO

이 프로젝트에서 확인되는 DAO 예시는 다음과 같다.

* `BBSAttributeManageDAO`
* `BBSManageDAO`
* `BBSLoneMasterDAO`
* `BBSAddedOptionsDAO`
* `BBSUseInfoManageDAO`
* `EgovUserInfManageDAO`
* `MberManageDAO`
* `LoginDAO`
* `CmmUseDAO`
* `FileManageDAO`
* `IndvdlSchdulManageDao`
* `SiteManagerDAO`

## 테스트 생성 전 분석 순서

DAO 단위 테스트를 생성하기 전에 반드시 다음 순서로 분석한다.

1. 대상 DAO 클래스의 public 메서드 목록을 확인한다.
2. 각 DAO 메서드가 호출하는 MyBatis statement id를 확인한다.
3. mapper XML에서 statement의 SQL 유형을 확인한다.
4. insert/update/delete/select별 파라미터 필드를 확인한다.
5. SQL이 접근하는 테이블을 확인한다.
6. `src/main/resources/db/shtdb.sql` 또는 관련 DDL에서 PK, FK, `NOT NULL` 컬럼을 확인한다.
7. 기존 seed 데이터가 있으면 조회 테스트에서 재사용 가능 여부를 판단한다.
8. 독립 데이터가 필요한 경우 테스트 보조 메서드로 직접 생성한다.

## 테스트 클래스 기본 형태

```java
@SpringBootTest
@Transactional
@Slf4j
class TargetDAOTest {

	@Autowired
	private TargetDAO targetDAO;
}
```

규칙:

* 클래스명은 `{DAO명}Test`로 작성한다.
* 패키지는 대상 DAO와 같은 패키지를 기본으로 한다.
* 테스트 클래스는 package-private으로 둔다.
* DAO 필드는 `private`으로 둔다.
* 불필요한 생성자 주입, Lombok `@RequiredArgsConstructor`는 사용하지 않는다.
* JUnit 5 `@Test`를 사용한다.
* AssertJ `assertThat()`만 사용한다.

## 테스트 메서드 작성 규칙

* 테스트 메서드명은 DAO 메서드명과 동일하게 작성한다.
* public DAO 메서드 하나당 성공 시나리오 테스트 하나를 만든다.
* 실패, 예외, 미존재 데이터 시나리오는 별도 요청이 없으면 만들지 않는다.
* 메서드 내부는 `given`, `when`, `then` 주석으로 구분한다.
* checked exception이 필요한 DAO 메서드에만 `throws Exception`을 붙인다.
* 테스트끼리 실행 순서에 의존하지 않는다.
* 테스트 데이터는 각 테스트가 직접 만들거나 seed 데이터에 명확히 의존한다.

## 데이터 생성 규칙

테스트 데이터는 다음 조건을 만족해야 한다.

* insert 매퍼가 참조하는 모든 필드를 채운다.
* 테이블의 `NOT NULL` 컬럼을 모두 만족한다.
* FK가 있으면 seed 데이터에 존재하는 값을 사용하거나 선행 데이터를 함께 만든다.
* PK는 충돌하지 않도록 시간 기반 문자열을 사용한다.
* 문자열 PK는 가능한 한 `"TEST0_" + now2` 형식을 사용한다.
* 숫자 PK는 DAO 또는 DB 구조에 맞는 안전한 값을 사용하되, 기존 seed 데이터와 충돌하지 않게 한다.
* 테스트 데이터 생성 메서드는 insert 결과를 검증한다.

권장 ID 생성:

```java
LocalDateTime now = LocalDateTime.now();
String now2 = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
String now3 = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
```

금지:

```java
UUID.randomUUID()
System.currentTimeMillis()
System.nanoTime()
egovIdGnrService.getNextStringId()
```

## BBSAttributeManageDAO 기준 데이터 예시

`LETTNBBSMASTER` 테스트 데이터는 다음 필드를 채운다.

```java
BoardMaster boardMaster = new BoardMaster();
boardMaster.setBbsId("TEST0_" + now2);
boardMaster.setBbsNm("test 이백행 게시판명 " + now3);
boardMaster.setBbsIntrcn("test 이백행 게시판 소개 " + now3);
boardMaster.setBbsTyCode("BBST03");
boardMaster.setBbsAttrbCode("BBSA03");
boardMaster.setReplyPosblAt("Y");
boardMaster.setFileAtchPosblAt("Y");
boardMaster.setPosblAtchFileNumber(2);
boardMaster.setPosblAtchFileSize("5242880");
boardMaster.setTmplatId("TMPLAT_BOARD_DEFAULT");
boardMaster.setUseAt("Y");
boardMaster.setFrstRegisterId("USRCNFRM_00000000000");
boardMaster.setLastUpdusrId("USRCNFRM_00000000000");
boardMaster.setUniqId("USRCNFRM_00000000000");
```

## 검증 전략

등록:

```java
assertThat(result).isGreaterThan(0);
```

수정:

* update 결과가 int이면 `0`보다 큰지 검증한다.
* update 결과가 void이면 수정 후 select로 재조회해 변경 필드를 검증한다.

삭제:

* delete 결과가 int이면 `0`보다 큰지 검증한다.
* 논리 삭제이면 삭제 후 상태값을 재조회해 검증한다.

단건 조회:

```java
assertThat(result).isNotNull();
assertThat(result.getId()).isEqualTo(testData.getId());
```

목록 조회:

```java
assertThat(result).isNotNull();
assertThat(result).isNotEmpty();
```

카운트 조회:

```java
assertThat(result).isGreaterThanOrEqualTo(1);
```

boolean 메서드:

```java
assertThat(result).isTrue();
```

## 로그 규칙

디버그 로그는 조건부로 출력한다.

```java
if (log.isDebugEnabled()) {
	log.debug("actual, expected={}, {}", result, expected);
}
```

객체나 목록을 출력할 때도 `log.isDebugEnabled()` 안에서만 출력한다.

## 보조 메서드 규칙

테스트 데이터 생성은 private 메서드로 분리한다.

```java
private BoardMaster testData() {
	BoardMaster boardMaster = boardMaster();

	int result = bbsAttributeManageDAO.insertBBSMasterInf(boardMaster);

	assertThat(result).isGreaterThan(0);

	return boardMaster;
}
```

객체 생성과 DB insert를 분리하면 더 좋다.

```java
private BoardMaster boardMaster() {
	// 필수 필드 설정
}
```

## 생성 금지 항목

다음은 생성하지 않는다.

* 주석 처리된 죽은 코드
* 사용하지 않는 import
* 테스트 목적과 무관한 service/controller 호출
* DB 상태를 영구 변경하는 테스트
* 실행 순서에 의존하는 테스트
* 실패 시나리오
* 과도한 설명 주석
* AssertJ와 JUnit assertion 혼용
* DAO 단위 테스트에서 불필요한 MockMvc, WebTestClient, controller 테스트 도구

## 최종 출력 규칙

테스트 코드 생성 요청을 받으면 설명 없이 Java 코드만 출력한다.

출력 순서:

1. package
2. static import
3. 일반 import
4. annotation
5. class
6. DAO 필드
7. 테스트 메서드
8. 테스트 데이터 생성 메서드
9. 객체 생성 보조 메서드
