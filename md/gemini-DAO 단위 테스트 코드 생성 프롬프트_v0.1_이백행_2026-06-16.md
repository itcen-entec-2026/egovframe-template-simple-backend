# gemini-DAO 단위 테스트 코드 생성 프롬프트

## DAO 단위 테스트 코드 생성 요청 (Standard)

```text
다음 컨텍스트를 바탕으로 지정된 DAO의 단위 테스트 코드를 생성해줘.

컨텍스트 문서:
@md/gemini-DAO 단위 테스트 코드 생성 컨텍스트_v0.1_이백행_2026-06-16.md

대상 DAO 파일:
@{대상 DAO 파일 경로}

대상 Mapper XML 파일:
@{대상 Mapper XML 파일 경로}

데이터베이스 DDL/DML:
@src/main/resources/db/shtdb.sql

요청 사항:
1. 대상 DAO의 모든 public 메서드에 대해 성공 시나리오 테스트 메서드를 작성해줘.
2. 각 테스트는 `given-when-then` 구조를 따라야 해.
3. Mapper XML과 DDL을 분석해서 `NOT NULL` 컬럼과 필수 필드값이 누락되지 않도록 `testData()` 보조 메서드를 구현해줘.
4. PK 생성은 `LocalDateTime`을 활용한 `"TEST0_yyyyMMddHHmmss"` 형식을 사용해줘.
5. AssertJ의 `assertThat`을 사용하여 결과를 검증하고, 필요시 `log.debug`를 포함해줘.
6. 결과는 설명 없이 즉시 컴파일 가능한 Java 코드만 출력해줘.
```

## BBSAttributeManageDAO 전용 생성 요청 (Example)

```text
`BBSAttributeManageDAO`에 대한 전체 단위 테스트 코드를 생성해줘.

컨텍스트: @md/gemini-DAO 단위 테스트 코드 생성 컨텍스트_v0.1_이백행_2026-06-16.md
DAO: @src/main/java/egovframework/let/cop/bbs/domain/repository/BBSAttributeManageDAO.java
Mapper: @src/main/resources/egovframework/mapper/let/cop/bbs/EgovBBSMaster_SQL_hsql.xml
DDL: @src/main/resources/db/shtdb.sql

특별 지시사항:
- `deleteBBSMasterInf`는 실제 삭제가 아닌 논리 삭제(USE_AT='N')이므로, 수정 후 재조회를 통해 상태값을 검증하는 로직을 포함해줘.
- `validateTemplate` 메서드는 구현 로직에 따라 기대되는 boolean 값을 검증해줘.
- `BoardMaster` 객체 생성 시 `BBS_TY_CODE`, `BBS_ATTRB_CODE`, `TMPLAT_ID` 등 필수 외래키 성격의 값들은 기존 seed 데이터나 기본값을 참고해서 채워줘.
```

## 단일 메서드 추가 생성 요청

```text
기존 `{DAO명}Test` 클래스에 `{메서드명}` 메서드에 대한 테스트 케이스를 추가해줘.

컨텍스트: @md/gemini-DAO 단위 테스트 코드 생성 컨텍스트_v0.1_이백행_2026-06-16.md
대상 메서드: `{메서드명}`
참고 파일:
- DAO: @{DAO 경로}
- Mapper: @{Mapper 경로}

요청 사항:
- 기존 테스트 클래스의 스타일과 `testData()` 활용 방식을 유지하면서 새로운 테스트 메서드만 작성해줘.
- 만약 기존에 `testData()`가 없다면 새로 정의해줘.
```
