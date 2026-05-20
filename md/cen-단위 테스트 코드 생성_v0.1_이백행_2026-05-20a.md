# 단위 테스트 생성 규칙

## 대상
- DAO: `~DAO.java` 대상 DAO 파일을 자동 탐색하여 순차적으로 처리
- 경로: /src/main/java/egovframework/**

## 규칙
- @src/test/java/egovframework/let/cop/bbs/service/impl/BBSAttributeManageDAOTest.java 기존 테스트 클래스 패턴 100% 준수
- `출력 클래스명`과 동일한 테스트 클래스가 존재하는 경우 생성 제외
- 기존 테스트 클래스가 존재하더라도 기존 테스트 메서드는 제외하지 않고 전체 대상 메서드 기준으로 생성
- 테스트 메서드는 대상 메서드 선언 순서대로 작성
- 테스트 코드 빌드 검증 제외

## 범위
- public 메서드 전체 대상

## 출력
- JUnit5 테스트 코드 생성
- 패키지 구조 동일 유지
- 클래스명: `{~DAO}CenTest.java`