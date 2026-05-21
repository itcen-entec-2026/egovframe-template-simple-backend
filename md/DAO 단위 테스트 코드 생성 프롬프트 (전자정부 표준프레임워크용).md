다음 조건에 맞는 DAO 단위 테스트 코드를 생성하시오.

[프로젝트 환경]

* 프레임워크: 전자정부 표준프레임워크 (egovframework)
* 언어: Java
* 테스트 프레임워크: JUnit5
* ORM: MyBatis
* DB: H2 (테스트용 인메모리 DB)
* 빌드툴: Maven

[테스트 대상]

* DAO 클래스명: MberManageDAO.java
* Mapper XML 경로: EgovMberManage_SQL_mysql.xml
* 테스트 대상 메서드:

  * 전체 메서드명

[요구사항]

- @SpringBootTest 기반으로 테스트 구성
- @Transactional 적용하여 테스트 후 데이터 롤백 처리
- H2 DB 설정 포함 (schema.sql, data.sql 예시 포함)
- 테스트 데이터 Insert 후 조회/수정/삭제 검증
- AssertJ - Fluent Assertions for Java 사용
- 정상 케이스만 작성
- 테스트 코드 가독성을 위해 given-when-then 패턴 적용
- 테스트 코드만 생성
- 테스트 실행 제외
- 테스트 데이터 및 설정 파일은 생성하지 않음

[출력 형식]

1. DAO 테스트 클래스 전체 코드

[추가 요구]

* 한국어 주석 포함
* 실무 수준 코드로 작성 (바로 실행 가능하도록)
* 불필요한 설명 없이 코드 중심으로 출력

---

# 2026-05-18 월요일

```
단위 테스트 코드 생성
- mysql 테이블 DDL을 확인
  - @DATABASE/all_sht_data_mysql.sql
  - @DATABASE/all_sht_ddl_mysql.sql
- MberManageDAO.insertMber
```

단위 테스트 코드 생성
- mysql 테이블 DDL을 확인
  - @DATABASE/all_sht_data_mysql.sql
  - @DATABASE/all_sht_ddl_mysql.sql
- MberManageDAO.insertMber

---

- DAO와 관련 VO 클래스를 파악
- 기존 테스트 클래스와 mysql 테이블 스키마를 확인
- mysql SQL 매퍼 파일과 기존 테스트 구조를 확인

  - @src/main/java/egovframework/let/uss/umt/service/impl/MberManageDAO.java
  - @src/main/resources/egovframework/mapper/let/uss/umt/EgovMberManage_SQL_mysql.xml

단위 테스트 코드 생성
- mysql 테이블 DDL을 확인
  - @DATABASE/all_sht_data_mysql.sql
  - @DATABASE/all_sht_ddl_mysql.sql
- MberManageDAO.insertMber

- MberManageDAO의 insertMber 메서드만 대상으로 작업
- Test 클래스에 @Transactional 추가
- 테스트 실행 금지
- 테이블 컬럼 타입/사이즈에 맞춰 VO 값 설정
- throws Exception 제거
- 삽입 후 조회(select) 검증 로직 제외

단위 테스트 코드 생성
MberManageDAO의 insertMber 메서드만 대상으로 작업:
- mysql
- 테스트 데이터의 vo.setXXX 값 타입 검증
- DB 컬럼 길이(size) 초과 여부 확인
- 숫자형 범위 초과 검사
- 날짜/시간 타입 불일치 검사
- 필요한 경우 안전한 테스트 데이터로 수정
- 삽입 후 조회(select) 검증 로직 제외
- assert 조회 검증 추가하지 않음
- 다른 메서드는 수정 금지
- 테스트 실행 금지
- mvn test / gradle test 실행 금지

# 2026-05-19 화요일

기본 프로젝트 컨텍스트 자동 생성
```
C:\eGovFrameDev-5.0.0-Windows-64bit\workspace-egov\egovframe-template-simple-backend
```

```
cmd
```

```
/init
```

CEN.md 직접 보강
→ 팀 규칙 / 아키텍처 / 테스트 정책 추가

C:\eGovFrameDev-5.0.0-Windows-64bit\workspace-egov\egovframe-template-simple-backend\CEN.md

수정 내용 다시 반영
```
/memory refresh
```

clear (reset, new)  Clear conversation history and free up context
- 지우기(초기화, 새로 만들기) 대화 기록을 지우고 맥락을 확보합니다.
- Gemini CLI에서 현재 대화 컨텍스트를 초기화하는 기능
- 현재 채팅 기록 삭제
- 메모리(context window) 비우기
- 새 세션처럼 시작
```
/clear
```

단위 테스트 코드 생성
```
@"md/단위 테스트 코드 생성_v0.1_이백행_2026-05-19.md"
```

```java
String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
```

단위 테스트 코드 생성
- @src/main/java/egovframework/let/uss/umt/service/impl/MberManageDAO.java
- @src/test/java/egovframework/let/cop/bbs/service/impl/BBSAttributeManageDAOTest.java 기존 테스트 코드 패턴 확인
- 테스트 메서드는 대상 클래스의 메서드 선언 순서대로 작성
- testData() 메서드 활용
- 테스트 파일들을 한 번에 생성
- 테스트 실행 및 검증 제외

```
# 단위 테스트 코드 생성 컨텍스트

단위 테스트 코드 생성
- 기존 테스트가 있으면 생성 제외
- DAO: `~DAO.java`
- @src/test/java/egovframework/let/cop/bbs/service/impl/BBSAttributeManageDAOTest.java 기존 테스트 코드 패턴 확인
- 테스트 메서드는 대상 클래스의 메서드 선언 순서대로 작성
- 테스트 실행 및 검증 제외
```

# 2026-05-20 수요일

TestFileUtils.listFiles
1. CmmUseDAO.java
2. FileManageDAO.java
3. BBSAddedOptionsDAO.java
4. BBSAttributeManageDAO.java
5. BBSLoneMasterDAO.java
6. BBSManageDAO.java
7. BBSUseInfoManageDAO.java
8. EgovUserInfManageDAO.java
9. IndvdlSchdulManageDao.java
10. SiteManagerDAO.java
11. LoginDAO.java
12. MberManageDAO.java

```
    해당 문서의 내용을 확인했습니다. 문서 내용이 매우 간략하게 "단위 테스트 코드 생성"으로만 작성되어 있습니다.

    어떤 부분을 단위 테스트로 작성해야 할지 구체적인 안내가 필요합니다:

     1. 테스트 대상 클래스/모듈: 어떤 컨트롤러, 서비스, 또는 유틸리티 클래스를 테스트해야 하나요?
     2. 테스트 범위: 전체 모듈을 테스트할지, 특정 기능만 테스트할지
     3. 테스트 패턴: 기존 테스트 코드가 있다면 참고하여 동일한 패턴을 따를까요?

    테스트가 필요한 대상 파일 목록이나 우선순위를 알려주시면 해당 패턴에 맞춰 단위 테스트 코드를 생성하겠습니다.
```

테스트 대상 클래스/모듈:
- `4. BBSAttributeManageDAO.java`
- LETTNBBSMASTER

```
## 대상
- DAO: *DAO.java
- 경로: /src/main/java/**/service/impl/**
```

```
@"md/cen-단위 테스트 코드 생성_v0.1_이백행_2026-05-20.md"
```

# 2026-05-21(목)

```
'전자정부 표준프레임워크 DAO 단위 테스트 코드 생성' 제미나이 프롬프트 작성
```

```
'전자정부 표준프레임워크 DAO 단위 테스트 코드 생성' 제미나이 프롬프트 md로 다운로드
```

```
'전자정부 표준프레임워크 DAO 단위 테스트 코드 생성' 제미나이 프롬프트 md 다운로드
```
