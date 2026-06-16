# 전체 DAO 단위 테스트 코드 생성 프롬프트

## 전체 DAO 테스트 생성 기본 프롬프트

```text
다음 컨텍스트를 기준으로 전자정부 표준프레임워크 기반 DAO 단위 테스트 코드를 생성하라.

컨텍스트:
@md/DAO 단위 테스트 코드 생성 컨텍스트_v0.1_이백행_2026-06-16.md

분석 기준 테스트:
@src/test/java/egovframework/let/cop/bbs/domain/repository/BBSAttributeManageDAOTest.java

요청:
대상 DAO의 전체 public 메서드에 대한 단위 테스트 코드를 생성하라.

생성 원칙:
* 대상 DAO와 같은 패키지에 테스트 클래스를 작성한다.
* 클래스명은 `{DAO명}Test` 형식으로 작성한다.
* `@SpringBootTest`, `@Transactional`, `@Slf4j`를 사용한다.
* DAO는 `@Autowired private` 필드로 주입한다.
* JUnit 5 `@Test`와 AssertJ `assertThat()`을 사용한다.
* public DAO 메서드 하나당 성공 시나리오 테스트 하나를 작성한다.
* 테스트 메서드명은 DAO 메서드명과 동일하게 작성한다.
* `given`, `when`, `then` 흐름을 사용한다.
* MyBatis mapper XML과 DB DDL을 분석해 필수 테스트 데이터를 누락 없이 만든다.
* 테스트 데이터 생성은 private 보조 메서드로 분리한다.
* insert/update/delete/select 결과를 각 메서드 성격에 맞게 검증한다.
* 주석 처리된 코드는 생성하지 않는다.
* 사용하지 않는 import는 생성하지 않는다.
* 최종 답변은 설명 없이 컴파일 가능한 Java 코드만 출력한다.
```

## 특정 DAO 전체 메서드 생성 프롬프트

```text
다음 컨텍스트를 기준으로 DAO 단위 테스트 코드를 생성하라.

컨텍스트:
@md/DAO 단위 테스트 코드 생성 컨텍스트_v0.1_이백행_2026-06-16.md

대상 DAO:
@{대상 DAO Java 파일 경로}

대상 mapper:
@{대상 MyBatis XML 파일 경로}

대상 DDL 또는 seed SQL:
@src/main/resources/db/shtdb.sql

요청:
대상 DAO의 전체 public 메서드에 대한 성공 시나리오 DAO 단위 테스트 코드를 생성하라.

반드시 수행할 분석:
1. DAO public 메서드 목록을 확인한다.
2. 각 메서드가 호출하는 MyBatis statement id를 확인한다.
3. mapper XML에서 SQL 유형과 파라미터 필드를 확인한다.
4. 관련 테이블의 PK, FK, NOT NULL 컬럼을 확인한다.
5. seed 데이터 재사용 가능 여부를 판단한다.
6. 필요한 독립 테스트 데이터 생성 메서드를 작성한다.

검증 기준:
* insert 결과는 `assertThat(result).isGreaterThan(0)`로 검증한다.
* update 결과가 int이면 `0`보다 큰지 검증한다.
* update 결과가 void이면 재조회로 변경 필드를 검증한다.
* delete 결과가 int이면 `0`보다 큰지 검증한다.
* 논리 삭제는 재조회로 상태값 변경을 검증한다.
* 단건 조회는 null이 아니고 핵심 식별자가 일치하는지 검증한다.
* 목록 조회는 null이 아니고 비어 있지 않은지 검증한다.
* 카운트 조회는 `1` 이상인지 검증한다.
* boolean 반환 메서드는 기대 boolean 값을 검증한다.

최종 답변은 설명 없이 Java 코드만 출력하라.
```

## BBSAttributeManageDAO 전체 메서드 생성 프롬프트

```text
다음 컨텍스트를 기준으로 `BBSAttributeManageDAO`의 전체 public 메서드에 대한 DAO 단위 테스트 코드를 생성하라.

컨텍스트:
@md/DAO 단위 테스트 코드 생성 컨텍스트_v0.1_이백행_2026-06-16.md

대상 DAO:
@src/main/java/egovframework/let/cop/bbs/domain/repository/BBSAttributeManageDAO.java

대상 mapper:
@src/main/resources/egovframework/mapper/let/cop/bbs/EgovBBSMaster_SQL_hsql.xml

분석 기준 테스트:
@src/test/java/egovframework/let/cop/bbs/domain/repository/BBSAttributeManageDAOTest.java

테스트 클래스:
`egovframework.let.cop.bbs.domain.repository.BBSAttributeManageDAOTest`

대상 메서드:
* deleteBBSMasterInf
* insertBBSMasterInf
* selectBBSMasterInf
* selectBBSMasterInfs
* selectBBSMasterInfsCnt
* updateBBSMasterInf
* validateTemplate
* selectAllBBSMasteInf(BoardMasterVO)
* selectAllBBSMasteInf(BbsAttributeUpdateRequestDTO)
* selectBdMstrListByTrget
* selectBdMstrListCntByTrget
* selectAllBdMstrByTrget
* selectNotUsedBdMstrList
* selectNotUsedBdMstrListCnt

필수 테스트 데이터:
* `LETTNBBSMASTER`의 NOT NULL 컬럼을 모두 만족하는 `BoardMaster`를 생성한다.
* `bbsId`는 `"TEST0_" + now2` 형식으로 생성한다.
* `bbsTyCode = "BBST03"`
* `bbsAttrbCode = "BBSA03"`
* `fileAtchPosblAt = "Y"`
* `posblAtchFileNumber = 2`
* `posblAtchFileSize = "5242880"`
* `useAt = "Y"`
* `frstRegisterId = "USRCNFRM_00000000000"`
* `lastUpdusrId = "USRCNFRM_00000000000"`
* `uniqId = "USRCNFRM_00000000000"`
* `tmplatId = "TMPLAT_BOARD_DEFAULT"`

검증 기준:
* 등록은 영향받은 행 수를 검증한다.
* 삭제는 논리 삭제이므로 `USE_AT = 'N'` 상태를 재조회로 검증한다.
* 수정은 void 메서드이므로 수정 후 재조회로 검증한다.
* 단건 조회는 `bbsId` 일치를 검증한다.
* 목록 조회는 비어 있지 않음을 검증한다.
* 카운트 조회는 `1` 이상을 검증한다.
* 대상 기반 조회는 `trgetId = "SYSTEM_DEFAULT_BOARD"`를 사용한다.
* 미사용 게시판 조회는 `LETTNBBSUSE`에 연결하지 않은 신규 `LETTNBBSMASTER` 데이터로 검증한다.
* `validateTemplate`은 현재 구현 기준 `true`를 검증한다.

금지:
* `EgovIdGnrService` 사용 금지
* `UUID` 사용 금지
* `System.currentTimeMillis()` 사용 금지
* 주석 처리된 코드 생성 금지
* JUnit assertion과 AssertJ 혼용 금지

최종 답변은 설명 없이 Java 코드만 출력하라.
```

## 단일 DAO 메서드 생성 프롬프트

```text
다음 컨텍스트를 기준으로 지정한 DAO 메서드의 단위 테스트 코드를 생성하라.

컨텍스트:
@md/DAO 단위 테스트 코드 생성 컨텍스트_v0.1_이백행_2026-06-16.md

대상 DAO:
@{대상 DAO Java 파일 경로}

대상 메서드:
`{메서드명}`

요청:
대상 메서드의 성공 시나리오 DAO 단위 테스트 코드를 생성하라.

생성 기준:
* 기존 테스트 클래스가 있으면 같은 클래스에 추가 가능한 형태로 작성한다.
* 기존 테스트 클래스가 없으면 `{DAO명}Test` 전체 클래스를 생성한다.
* mapper XML과 DDL을 분석해 필요한 테스트 데이터를 만든다.
* 메서드 반환 타입과 SQL 유형에 맞는 검증을 작성한다.
* 최종 답변은 설명 없이 Java 코드만 출력한다.
```
