# 단위 테스트 생성 규칙

## 대상
- DAO: *DAO.java
- 경로: /src/main/java/**/dao/**

## 규칙
- 기존 테스트 존재 시 생성 제외
- 테스트 메서드는 대상 메서드 선언 순서대로 작성
- 기존 테스트 클래스 패턴 100% 준수
- @BeforeEach 사용 여부는 기존 코드 기준
- 테스트 실행(assert/verify)은 제외 가능

## 범위
- public 메서드 전체 대상

## 출력
- JUnit5 테스트 코드 생성
- 패키지 구조 동일 유지
- 클래스명: {TargetClass}Test