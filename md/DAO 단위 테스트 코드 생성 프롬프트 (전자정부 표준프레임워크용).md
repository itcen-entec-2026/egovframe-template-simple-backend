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