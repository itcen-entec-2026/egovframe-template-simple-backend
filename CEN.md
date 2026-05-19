# CEN Local Code RAG — 프로젝트 컨텍스트

## 프로젝트 개요

**프로젝트명**: 표준프레임워크 심플홈페이지 BackEnd (egovframe-boot-simple-backend v5.0.0)

**설명**: 한국 행정안전부 표준프레임워크(EgovFramework) 기반의 심플홈페이지 백엔드 API 프로젝트. 기존 JSP 뷰 방식에서 벗어나 Backend와 Frontend를 분리한 아키텍처를 제공하며, REST API를 통해 게시판, 회원관리, 로그인/인증, 일정관리, 파일관리 등 공통 기능을 제공합니다.

**GitHub**: https://github.com/eGovFramework/egovframe-template-simple-backend

---

## 기술 스택

| 구분 | 기술/버전 |
|------|----------|
| **언어** | Java 17 |
| **프레임워크** | Spring Boot 3.5.6 / Spring Framework 6.2.11 |
| **자카르타 EE** | Jakarta EE 10 / Servlet 6.0 |
| **빌드 도구** | Maven |
| **ORM** | MyBatis (EgovFramework PSL) |
| **데이터베이스** | MySQL (개발), MySQL (운영) |
| **인증** | JWT (jjwt 0.12.6) |
| **API 문서** | SpringDoc OpenAPI (Swagger UI 2.6.0) |
| **로깅** | Log4j2 2.25.3 / SLF4J 2.0.17 |
| **유틸리티** | Lombok, Commons Validator, Commons Codec, ICU4J |
| **프론트엔드** | React (별도 저장소: egovframe-template-simple-react) |

---

## 프로젝트 구조

```
egovframe-template-simple-backend/
├── pom.xml                          # Maven 빌드 설정 (EgovFramework Boot Parent 5.0.0)
├── README.md                        # 프로젝트 설명 및 구동 가이드
├── swagger.md                       # Swagger API 문서
├── Docs/                            # 변환 가이드 문서군 (JavaConfig 등)
├── DATABASE/                        # DB 스키마 (mysql)
├── src/
│   ├── main/
│   │   ├── java/egovframework/
│   │   │   ├── EgovBootApplication.java   # Spring Boot 메인 애플리케이션
│   │   │   ├── com/                       # 공통 모듈
│   │   │   │   ├── cmm/                   # 공통 컴포넌트 (VO, 유틸, 필터, 인터셉터)
│   │   │   │   ├── config/                # JavaConfig 설정 클래스들
│   │   │   │   ├── jwt/                   # JWT 인증 필터/토큰 유틸
│   │   │   │   └── security/              # Spring Security 설정
│   │   │   └── let/                       # 업무 모듈 (심플홈페이지)
│   │   │       ├── cop/                   # 공통업무 (게시판, 일정관리)
│   │   │       ├── main/                  # 메인페이지
│   │   │       ├── uat/                   # 사용자인증 (회원, 사이트관리)
│   │   │       ├── uss/                   # 사용자서비스 (회원관리)
│   │   │       └── utl/                   # 유틸리티
│   │   └── resources/
│   │       ├── application.properties     # 메인 설정 (프로필: dev)
│   │       ├── application-dev.properties # 개발환경
│   │       ├── application-prod.properties # 운영환경
│   │       ├── logback-spring.xml         # 로깅 설정
│   │       ├── db/shtdb.sql               # DB 스키마 (HSQLDB)
│   │       ├── egovframework/mapper/      # MyBatis XML 매apper (DB별)
│   │       ├── egovframework/message/     # 국제화 메시지
│   │       └── egovframework/validator/   # 검증 규칙 XML
│   └── test/                              # 테스트 코드
└── target/                                # 빌드 출력 디렉토리
```

---

## 핵심 모듈 구조

| 패키지 | 설명 |
|--------|------|
| `egovframework.com.cmm` | 공통 VO, 유틸리티, 메시지소스, 세션처리, HTML 필터 |
| `egovframework.com.config` | JavaConfig 설정 (DataSource, MyBatis, 트랜잭션, AOP, ID생성, Whitelist 등) |
| `egovframework.com.jwt` | JWT 토큰 생성/검증, JWT 인증 필터, 에ント्री포인트 |
| `egovframework.com.security` | Spring Security 설정, CustomUserDetails, 인증 리졸버 |
| `egovframework.let.cop.bbs` | 게시판 관리 (CRUD API, DTO, 도메인, 서비스) |
| `egovframework.let.uat` | 사용자 인증/가입/로그인 관련 기능 |
| `egovframework.let.uss` | 회원 정보 관리 |
| `egovframework.let.cop.smt.sim` | 개인 일정 관리 |

---

## 빌드 및 실행

### 환경 요구사항
- **JDK**: 17 이상
- **Maven**: 3.6+ 권장
- **DB**: HSQLDB (기본 내장), MySQL 8+, 또는 Oracle/Altibase/Tibero/CUBRID

### 빌드
```bash
mvn clean install
```

### 개발 서버 구동 (CLI)
```bash
mvn spring-boot:run
```

### 개발 서버 구동 (IDE)
- 프로젝트 우클릭 → Run As → Spring Boot App

### JAR 실행
```bash
java -jar egovframe-boot-simple-backend-5.0.0.jar --spring.profiles.active=prod
```

### 포트 변경
`src/main/resources/application.properties`에서:
```properties
server.port=8081
```

---

## API 접근

| 엔드포인트 | URL | 설명 |
|-----------|-----|------|
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` | API 문서 및 테스트 |
| API Docs | `http://localhost:8080/v3/api-docs` | OpenAPI JSON |
| 메인 API | `http://localhost:8080/` | 애플리케이션 루트 |

### 인증 방식
- **GET 요청**: JWT 토큰 없이 접근 가능
- **POST/PUT/DELETE**: JWT 토큰 인증 필요
- **로그인**: `POST /auth/login-jwt` (아이디: admin, 암호: 1) → JWT 토큰 반환
- **토큰 설정**: Swagger [Authorize] 버튼에 토큰 붙여넣기

---

## 설정 및 프로필

### 프로필
| 프로필 | 용도 |
|--------|------|
| `dev` | 개발환경 (기본값) |
| `prod` | 운영환경 |

### 주요 설정 항목 (`application.properties`)
- `Globals.DbType`: 데이터베이스 타입 (`mysql`)
- `Globals.pageUnit`: 페이지당 기본 건수 (기본: 10)
- `Globals.fileStorePath`: 파일 저장 경로 (기본: `./files`)
- `Globals.Allow.Origin`: CORS 허용 origin (기본: `http://localhost:3000`)
- `Globals.jwt.secret`: JWT 서명 키 (운영시 변경 필수)
- `Globals.crypto.algoritm`: 암호화 알고리즘 키 (기본: `egovframe`)

### 데이터베이스 연결
`Globals.DbType`에 따라 자동 전환되며, 각 DB별 연결 정보는 `application.properties`에서 설정:
- MySQL: `jdbc:log4jdbc:mysql://127.0.0.1:3306/sht`

---

## 개발 관례

### 패키지 네이밍
- `egovframework.com.*`: 표준프레임워크 공통 모듈
- `egovframework.let.*`: 심플홈페이지 업무 모듈
  - `uat`: Authentication/Registration (인증)
  - `uss`: User Service (회원서비스)
  - `cop`: Common Operations (게시판/일정)
  - `main`: 메인페이지
  - `utl`: Utilities

### 컨트롤러 네이밍
- API 컨트롤러: `~ApiController.java` 형식
- 기존 View 컨트롤러: `~Controller.java` 형식

### DTO 패턴
- Request DTO: `~RequestDTO.java`
- Response DTO: `~ResponseDTO.java`

### 도메인 패턴
- Entity/Model: `~.java`
- DAO: `~DAO.java`
- Service: `~Service.java` / `~ServiceImpl.java`
- VO: `~VO.java`

### 설정 방식
- XML → JavaConfig 마이그레이션 완료 (`egovframework.com.config.*`)
- `@Configuration` + `@Bean` 기반 스프링 빈 관리
- MyBatis XML mapper는 `resources/egovframework/mapper/`에 유지 (DB별 분리)

### 로깅
- Log4j2 + Log4jdbc (SQL 로깅)
- 로그 출력 경로: `./log/`
- 패키지별 로깅: `logging.level.egovframework=DEBUG`

---

## 주요 기능

| 기능 | 설명 |
|------|------|
| **회원 관리** | 가입, 수정, 삭제, 조회, 비밀번호 변경 |
| **회원 관리 (관리자)** | 회원 목록 조회, 상태 변경 |
| **게시판 관리** | 게시판 마스터 CRUD, 게시판 항목 관리 |
| **게시판 운영** | 글쓰기, 조회, 수정, 삭제, 파일첨부, 목록 조회 |
| **일정 관리** | 개인 일정 등록, 수정, 삭제, 조회 |
| **파일 관리** | 파일 업로드/다운로드, 목록 조회 |
| **코드 조회** | 공통 코드, 상세 코드 조회 |
| **JWT 인증** | 로그인, 토큰 발급/검증, 세션 관리 |
| **SNS 로그인** | Naver, Kakao OAuth (설정 필요) |

---

## 테스트

```bash
# 전체 테스트 실행
mvn test

# 특정 테스트만 실행
mvn test -Dtest=TestClassname
```

테스트 의존성: JUnit 4, Selenium WebDriver, Spring Boot Test, QueryDSL

---

## 참고 문서

| 문서 | 경로 | 설명 |
|------|------|------|
| JavaConfig 변환 가이드 | `Docs/java-config-convert.md` | web.xml 및 context-*.xml → JavaConfig 전체 가이드 |
| 데이터소스 설정 변환 | `Docs/context-datasource-convert.md` | DBCP, DataSource 설정 JavaConfig화 |
| 트랜잭션 설정 변환 | `Docs/context-transaction-convert.md` | 트랜잭션 AOP JavaConfig화 |
| AOP 설정 | `Docs/context-aspect.md` | 예외처리 AOP |
| AOP 변환 가이드 | `Docs/context-aspect-convert.md` | AOP JavaConfig화 |
| ID 생성기 설정 | `Docs/context-idgen-convert.md` | 테이블 기반 ID 생성기 |
| MyBatis 설정 변환 | `Docs/context-mapper-convert.md` | SqlSessionFactory, 매퍼 설정 |
| 프로퍼티 설정 변환 | `Docs/context-properties-convert.md` | 전역 프로퍼티 설정 |
| Validator 설정 변환 | `Docs/context-validator-convert.md` | Commons Validator 설정 |
| Whitelist 설정 변환 | `Docs/context-whitelist-convert.md` | 페이지 링크 화이트리스트 |
| DB 스키마 가이드 | `Docs/db-schema-guide.md` | shtdb.sql 기반 테이블/컬럼 설명 |
| Servlet 개념 | `Docs/servlet.md` | 서블릿/CGI 라이프사이클 |
| WebApplicationInitializer | `Docs/WebApplicationInitializer.md` | 순수 자바 부트스트랩 원리 |

---

## 보안 참고사항

- JWT 시크릿 키(`Globals.jwt.secret`)는 운영 환경에서 반드시 변경
- 암호화 알고리즘 키(`Globals.crypto.algoritm`)는 운영 환경에서 반드시 변경
- SNS OAuth clientId/clientSecret은 실제 값으로 설정 필요
- HTML 태그 필터(`HTMLTagFilter`)로 XSS 방지 적용됨
- 의존성 CVE 보완: log4j2, tomcat, commons-beanutils 등 직접 버전 주입으로 보안 취약점 대응

---

## Git/CI

- GitHub Actions를 통한 Maven 빌드 워크플로우 존재
- PR 템플릿, 보안 정책 파일 포함 (`.github/` 디렉토리)
