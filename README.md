# GraphQL & Restful API

# Repository 소개

- 2021년 원메딕스인더스트리 백-엔드 채용 테스트 코드 (GraphQL 통신)

## 🎯 Target

- Restful API로 구현을 해봤지만, 처음 접해본 GraphQL 통신 방식과 차이점을 유관으로 확인해보기
- GraphQL에 대한 통신 방식 이해

## 개요

- 개발 환경: Springboot (Gradle), Jdk 1.8, Spring JPA, GraphQL

## Restful API 구조
![](images/RESTFUL-API.jpg)

## GraphQL 구조
![](images/GraphQL.jpg)

|기능|Method|URL| Request Params / Body|
|:---|:---:|:---:|:---:|
|Restful 유저 등록|POST|api/user| email, username, password |
|Restful 유저 조회|GET|/api/user||
|Restful 유저 수정|POST|/api/user/{id}| email, password |
|Restful 유저 삭제|DELETE|/api/user/{id}| username, password |
|GraphQL 유저 등록||/graphQL| mutation{addUser(email: "", username: "", password: "")} |
|GraphQL 유저 개별 조회|/graphQL| {user(username: "a"){username email password}} |
|GraphQL 전체 조회||/graphQL| {users{username email password}} |
|GraphQL 유저 수정||/graphQL| mutation{updateUser(username: "", email: "", password: "")}|
|GraphQL 유저 삭제||/graphQL| mutation{deleteUser(username: "")} |

