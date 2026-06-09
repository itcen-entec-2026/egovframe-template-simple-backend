# 전자정부 표준프레임워크 DAO 단위 테스트 코드 생성 컨텍스트 적용만

- 테스트 코드 생성 금지
- 컨텍스트만 적용
- `@src/test/java/egovframework/let/cop/bbs/service/impl/BBSAttributeManageDAOTest.java` 기존 테스트 코드 스타일과 패턴을 동일하게 우선 적용
- `@DATABASE/all_sht_ddl_mysql.sql` DDL 분석
- `@DATABASE/all_sht_data_mysql.sql` DML 분석
- VO set 데이터타입 및 길이에 맞는 테스트 데이터 사용
- VO set `"TEST0_" + now2` 수정 금지
- `#{recordCountPerPage}` → `VO.setRecordCountPerPage(10);`
- `#{firstIndex}` → `VO.setFirstIndex(0);`
- update/delete는 insert와 동일하게 작성
- 메서드 선언 순서대로 테스트 작성
- 테스트 코드 빌드 검증 제외