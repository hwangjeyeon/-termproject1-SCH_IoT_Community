# SCH_IoT_Community term project
---
## 개요
순천향대학교 IoT 학과 학생들이 자유롭게 소통하고 정보를 공유할 수 있는 커뮤니티입니다

## 기능

### 로그인 기능
SCH_IoT학과 학생들을 위한 커뮤니티 이므로, 로그인 인증된 유저만 해당 사이트에 접속할 수 있습니다
### 회원가입 기능
로그인을 위해서는 먼저 회원가입을 진행해야합니다. 해당 기능을 통해 사용자는 커뮤니티에 가입할 수 있습니다
### 커뮤니티 페이지 글 리스트 확인
여러 가입한 사용자가 작성한 글 목록을 확인할 수 있습니다. 사용자는 여러 글의 작성날짜와 제목 및 작성자 그리고 조회수까지 확인 가능합니다
### 커뮤니티 페이지 글 확인
다른 사용자가 작성한 글을 확인할 수 있습니다
### 커뮤티니 페이지 댓글 확인
특정 사용자 작성한 글에서 댓글을 확인할 수 있습니다. 사용자 역시 해당 글에 댓글을 작성할 수 있습니다
### 커뮤니티 페이지 글 작성
사용자 역시 커뮤니티 페이지에 글을 작성할 수 있습니다

## 페이지 접속 Flowchart
<img src="/plan_image/프로젝트 페이지 순서도.jpg">

## 기능별 화면 구성

### 로그인 화면 구성 / 회원가입 화면 구성

<img src="/plan_image/화면구성1.jpg">

### 게시글 목록 화면 구성 / 특정 게시글 화면 구성 / 게시글 작성 화면 구성 / 게시글 수정 화면 구성
<img src="/plan_image/화면구성2.jpg">


## 인증 Sequence Diagram
로그인 인증 안 되어있는 사용자가 로그인/회원가입 페이지 외에 접근할 시, 인터셉터가 차단하는 Sequence Diagram
<img src="/plan_image/인증 순서도.jpg">


## 클래스 다이어그램
<img src="/plan_image/클래스 다이어그램.jpg">


## DB 다이어그램
<img src="/plan_image/DB ER 다이어그램.jpg">

3개의 테이블 모두 양방향 연관관계입니다.




## endpoint

### 기본 endpoint
"sch/iot/community/" 해당 endpoint를 기준으로 모든 페이지 endpoint가 시작됩니다

### 로그인
GET "/login"

### 회원가입
GET "/register"
POST "/register"

### 게시글 목록
GET "/writelist"

### 로그아웃
GET "/logout"

## 특정 게시글 확인
GET "/content/{게시글 번호}?학번"

### 게시글 작성
GET "/write"
POST "/write"

### 게시글 수정
GET "/content/{게시글 번호}/update?학번"
POST "/content/{게시글 번호}/update?학번"

### 게시글 삭제
POST "/content/{postNumber}/delete"

### 댓글 작성
POST "/lists/{게시글 번호}/comment"

### 댓글 삭제
POST "/lists/{게시글 번호}/comment/delete"

### 게시글 조회수 기능


### Form 조건 위반
다음과 같은 파라미터가 추가로 븥는다
login: loginfail
register: registerfail
update: updatefail
comment: commentfail


## 사용 기술 스택
- openJDK 17
- Spring boot 3.2.4
- sprint data jpa
- Thymeleaf

## 사용 DB
- MYSQL 

## 기타사항
- 최초 버전은 1.0버전으로 시작하며, 추후 여러 기능 추가 및 리팩토링이 진행되면서 버전이 업그레이드 될 수도 있습니다.
- 해당 사이트에 대한 배포는 계획에는 없지만, 진행 상황에 따라 배포될 수도 있습니다.
