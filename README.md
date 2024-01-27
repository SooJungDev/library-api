# library-api

## 구성정보
- Java 21
- Spring Boot 3.2.2
    - spring-boot-starter-data-jpa
    - spring-boot-starter-web
    - spring-boot-starter-validation
    - spring-boot-starter-test
    - spring-boot-devtools
- H2 Database Engine 2.2.224
- Project Lombok 1.18.30
- Redisson/Spring Boot Starter 3.26.0
- MapStruct 1.5.5.Final

## 프로젝트 실행방법
1. 압축파일을 푼 후 해당 경로로 들어갑니다.
- ex) cd /Users/crystal/library-api
~~~shell
cd '본인이 다운받은 위치의 경로'
~~~
2. 해당 경로에서 docker-compose 를 실행시킵니다.
~~~shell
docker-compose up -d
~~~
3.해당 경로에서 클린 후 빌드 실행합니다.
~~~shell
./gradlew clean build
~~~
4.빌드 완료 후 아래 디렉토리로 이동합니다.
~~~shell
cd build/libs
~~~
5.해당 jar 파일을 실행시킵니다.
- 옵션에 local 을 줘서 실행시킵니다.
~~~
java -jar -Dspring.profiles.active=local library-api-0.0.1-SNAPSHOT.jar
~~~

## Usecase
- 고객이 책을 빌린다
- 고객이 책을 반납한다
- 도서관 사서가 책을 구입해서 넣는다
- 도서관 사서가 책을 버린다

## 구현한 API 목록
- [X] 고객이 책을 찾는 API (일단 ID로 찾도록함. 추후에 변경)
- [X] 고객이 책을 빌리는 API
- [X] 고객이 책을 반납하는 API
- [X] 도서관 사서가 책을 최초 등록하는 API
- [X] 도서관 사서가 책을 추가 구입하는 API
- [X] 도서관 사서가 책을 폐기하는 API

## 추가 요구사항
- [X] 책을 예약 주문하기 (스케쥴러 돌리기) 특정 시간에 주문 api 호출
- [X] 책이 주문될때마다 kafka queue에 쌓고(이벤트를 발행), 다른 어플리케이션을 만들어서 이 앱이 queue에 있는 이벤트를 컨슘해서, 슬랙으로 메세지 보내주기. “xxx 책이 주문되었습니다” 메세지
  - library-consumer-api 프로젝트로 구현



## 개선해야될점 (TODO)
- readme 파일 자세히 작성하기
- test 코드 좀 더 작성하기
- redisson lock 관련 중복 코드 AOP 로 변경하기
- API 호출 시 아무나 호출 할 수 있어서 인증 추가하기
- table 관련 index 추가하기
- doc 작성하기
