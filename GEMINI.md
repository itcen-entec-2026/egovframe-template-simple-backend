# 전자정부 표준프레임워크 심플홈페이지 백엔드 (Spring Boot 3)

이 프로젝트는 전자정부 표준프레임워크(eGovFrame)를 기반으로 한 Spring Boot 3 백엔드 API 서버입니다. 기존 JSP 중심의 아키텍처에서 벗어나 RESTful API를 제공하며, React 등의 프론트엔드 프레임워크와 결합하여 사용할 수 있도록 설계되었습니다.

## 프로젝트 개요

- **프레임워크:** Spring Boot 3.5.6, eGovFrame 5.0.0
- **언어:** Java 17
- **빌드 도구:** Maven
- **데이터베이스:** MyBatis (HSQL, MySQL 등 지원)
- **보안:** JWT (JSON Web Token) 기반 인증
- **API 문서:** Swagger 3 (Springdoc OpenAPI)

## 주요 아키텍처 및 설정

### 1. 설정 방식 (Java Config)
기존의 XML 기반 설정(`context-*.xml`, `web.xml`)을 모두 Java 기반 설정(`@Configuration`)으로 전환하였습니다.
- 관련 클래스: `src/main/java/egovframework/com/config/`
- `EgovConfigApp.java`: 전역 애플리케이션 설정
- `SecurityConfig.java`: Spring Security 및 JWT 설정

### 2. 패키지 구조
- `egovframework.com.cmm`: 공통 컴포넌트 및 유틸리티
- `egovframework.com.jwt`: JWT 생성, 검증 및 필터 로직
- `egovframework.let`: 간소화된 템플릿 기능 (BBS, UAT, USS 등)
- `egovframework.com.config`: 자바 기반 설정 클래스

### 3. 개발 규칙 (Conventions)
- **Controller:** REST API용 컨트롤러는 `*ApiController.java` 형식을 사용하며, `@RestController` 어노테이션을 적용합니다.
- **Service:** 인터페이스 기반의 서비스를 사용하며, 구현체는 `EgovAbstractServiceImpl`을 상속받습니다.
- **DAO/Mapper:** MyBatis를 사용하며, `EgovAbstractMapper`를 상속받는 DAO 클래스를 작성합니다.
- **DTO:** 요청(`request`)과 응답(`response`) 데이터를 분리하여 DTO 패키지에서 관리합니다.
- **응답 형식:** 공통 응답 포맷인 `IntermediateResultVO`를 사용하여 일관된 API 응답을 제공합니다.

## 실행 및 빌드

### 요구 사항
- JDK 17 이상
- Maven 3.x

### 주요 명령
- **애플리케이션 실행:**
  ```bash
  mvn spring-boot:run
  ```
- **프로젝트 빌드:**
  ```bash
  mvn clean install
  ```
- **Jar 실행:**
  ```bash
  java -jar target/egovframe-boot-simple-backend-5.0.0.jar --spring.profiles.active=dev
  ```

### API 확인
- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
- **인증 테스트:**
  1. `/auth/login-jwt` 엔드포인트에서 로그인 (`admin` / `1`)
  2. 반환된 `jToken`을 복사하여 Swagger 상단의 `Authorize` 버튼에 입력

## 데이터베이스 초기화
데이터베이스 스키마와 초기 데이터는 `src/main/resources/db/shtdb.sql` 또는 `DATABASE/` 디렉토리의 SQL 파일들을 참고하십시오. 기본적으로 `dev` 프로필에서는 내장 HSQLDB를 사용할 수 있도록 설정되어 있습니다.

## 참고 문서
상세한 설정 변환 과정이나 가이드는 `Docs/` 디렉토리의 마크다운 파일들을 참조하십시오.
