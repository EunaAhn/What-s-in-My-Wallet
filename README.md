# 카드 추천 및 소비 분석 서비스
![9](https://github.com/user-attachments/assets/ee6da67a-9704-480d-bc39-30032e5bb75b)

## 프로젝트 개요

이 프로젝트는 사용자의 소비 패턴을 분석하고 맞춤형 카드를 추천해주는 웹 서비스입니다. 
사용자의 지출 내역을 시각화하고, 소비 MBTI를 통해 개인화된 분석을 제공합니다.

## 주요 기능

1. **회원 관리**
   - 일반 로그인 및 소셜 로그인(구글)
   - 사용자 정보 관리 (관심사, 월지출 목표액 등)

2. **카드 관리**
   - 인기 카드 표시
   - 카드 검색 및 상세 정보 제공
   - 카드 찜하기 기능

3. **소비 분석**
   - 소비 MBTI 분석
     ![15](https://github.com/user-attachments/assets/2c605053-98d6-486f-8458-8171079562f1)
   - 시간대별 지출 분석
   - 월별 지출 추이 분석

4. **카드 추천**
   - 사용자 성향 기반 카드 추천
   - 소비 카테고리별 지출 분석 (파이 차트, 라인 차트)
     ![14](https://github.com/user-attachments/assets/b6dfe66b-e3c9-4b60-a45b-fa521eee8654)

5. **소비 내역 관리**
   - 달력 형태의 소비 내역 표시
   - 일별 상세 소비 내역 및 위치 정보 제공
     ![13](https://github.com/user-attachments/assets/f4d2bc86-049f-41d3-8474-95b01ef157ff)
   - 소비 내역 메모 기능

## 기술 스택

### 1. Platform
- OS: Window8, AWS EC2(Linux ubuntu v16.0.4)
- WAS: Apache Server Tomcat v9.0
- Database: MariaDB(MySQL) v14.14
- JDK: Java SE v1.8 (JDK 8u161)

### 2. Build Tool
- Maven v2.9

### 3. Version Control
- Git(GitHub)
- Google Drive

### 4. Front-END Skills
- HTML5, CSS3, JavaScript, jQuery, Bootstrap
- jsTree, SockJS v1.1.5, STOMP v4.0.6

### 5. Back-END Skills
- Spring Framework v4.2.5
- Spring Security v4.0.1
- Spring websocket v4.2.5
- Mybatis v3.1.1
- Java Mail Sender v1.4.3
- Velocity v1.7

### 6. Development API
- Plug-ins: jQuery (AjaxForm, Confirm, ContextMenu, Drag&Drop, checkbox, whole-row, sort), Sweet Alert, DataTable
- APIs: AM Chart, Jsoup v1.8.3, Selenium v2.45.0, Java Mail Sender v1.4.3

### 7. Development Tools
- Spring STS v3.9.4, Atom v1.28.1, Bracket v1.12
- Exerd v2.5.0, WorkBench v6.3, StarUML v3.0.1
- SourceTree, Slack

## API 문서

API 상세 정보는 [여기](https://standing-ball-696.notion.site/API-d16de83c461d4fe987a73d6dd3053505)에서 확인할 수 있습니다.
