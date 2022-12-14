# 프로젝트 설명

- JAVA 11, Spring Boot
- 멀티모듈 프로젝트
- 새로운 블로그 검색 소스를 추가해야 할 때 기존 코드를 최대한 수정하지 않고 추가할 수 있도록 고려.<br/>검색 소스에서 오류가 발생 시 다음 검색 소스에서 데이터를 조회할 수 있도록 구현.
- Redis를 적용
    - 키워드 저장: ``` zincrby search:blog:keyword:total 1 {keyword} ```
    - 인기 키워드 10개 조회: ``` zrevrange search:blog:keyword:total 0 9 withscores ```

# 실행가능한 환경정보

- Linux, Mac OS
- JAVA 11 이상

# API 명세

## 공통

### 공통 응답 코드

| Code | Description   |
|------|---------------|
| 0000 | 성공          |
| 1001 | 파라미터 오류 |
| 9999 | 서버 오류     |

### Response Json 구조

| Name    | Type   | Description       |
|---------|--------|-------------------|
| code    | String | 공통 응답 코드        |
| message | String | 응답 메시지            |
| data    |        | 각 API Response 참고 |

### Sample

#### Response JSON

```json
{
  "code": "0000",
  "message": "Success",
  "data": {
    "sample": "{Response Object}"
  }
}
```

## /v1/documents: 블로그 검색

### Parameter

| Name  | Type    | Description                                                | Required |
|-------|---------|------------------------------------------------------------|----------|
| query | String  | 검색을 원하는 질의어<br/>- 한글의 경우 UTF-8 인코딩되어야 합니다.         | O        |
| sort  | String  | 결과 문서 정렬 방식<br/>- accuracy: 정확도순(기본 값)<br/> - recency: 최신순 | X        |
| page  | Integer | 결과 페이지 번호<br/>- 1~50 사이의 값(기본 값 1)                         | X        |
| size  | Integer | 한 페이지에 보여질 문서 수<br/>- 1~50 사이의 값(기본 값 1)                   | X        |

### Response Data

| Name      | Type            | Description          |
|-----------|-----------------|----------------------|
| meta      | Meta            | 블로그 조회 메타정보 |
| documents | Array(Document) | 블로그 조회결과      |

#### Meta

| Name          | Type    | Description            |
|---------------|---------|------------------------|
| totalCount    | Integer | 검색된 문서 수               |
| isEnd         | Boolean | 현재 페이지가 마지막 페이지 여부     |

#### Document

| Name      | Type | Description                                         |
|-----------|------|-----------------------------------------------------|
| title     | String | 블로그 글 제목                                            |
| contents  | String | 블로그 글 요약                                            |
| url       | String | 블로그 글 URL                                           |
| blogname  | String | 블로그의 이름                                             |
| thumbnail | String | 검색 시스템에서 추출한 대표 미리보기 이미지 URL                        |
| registrationDate  | Date | 블로그 글 작성시간, ISO 8601<br/>- ex: 2022-08-29 |

### Sample

#### Request curl

```curl
curl --location --request GET 'http://localhost:8080/v1/documents?query=galaxy&sort=accuracy&page=2&size=5'

curl --location --request GET 'http://localhost:8080/v1/documents?query=%EA%B0%A4%EB%9F%AD%EC%8B%9C&sort=accuracy&page=2&size=5'
```

#### Response JSON

```json
{
  "code": "0000",
  "message": "Success",
  "data": {
    "meta": {
      "totalCount": 2993144,
      "pageableCount": 797,
      "isEnd": false
    },
    "documents": [
      {
        "title": "<b>갤럭시</b>Z폴드4 구매이유",
        "contents": "삼성 <b>갤럭시</b> Z폴드 4가 드디어 출시했습니다. 완전 역대급 스펙과 디자인으로 출시가 되어, 많은 폴드시리즈 팬들이 사전예약 주문을 해서 쓰고 있다고 하는데요, 지금 살까 말까 고민하시는 분들을 위해, 디자인/성능/스펙/ 가격 모두 정리해서 한눈에 보기 쉽게 정리해드리도록 하겠습니다. <b>갤럭시</b> Z폴드 4 5G 자급제...",
        "url": "http://bottle33.tistory.com/3",
        "blogname": "물병세개",
        "thumbnail": "https://search2.kakaocdn.net/argon/130x130_85_c/DwsYX8L6Dvy",
        "datetime": "2022-09-09T04:25:15Z"
      },
      ...
    ]
  }
}
```

## /v1/keywords/popularTop10: 인기 검색어 조회

### Response Data

| Name      | Type                      | Description |
|-----------|---------------------------|-------------|
| popularKeywordInfos | Array(PopularKeywordInfo) | 인기검색어 정보    |

#### PopularKeywordInfo

| Name      | Type    | Description |
|-----------|---------|-------------|
| keyword   | String  | 검색어         |
| searchCount     | Integer | 검색 건수       |

### Sample

#### Request curl

```curl
curl --location --request GET 'http://localhost:8080/v1/keywords/popularTop10'
```

#### Response JSON

```json
{
  "code": "0000",
  "message": "Success",
  "data": {
    "popularKeywordInfos": [
      {
        "keyword": "갤럭시",
        "searchCount": 128
      },
      {
        "keyword": "맥북",
        "searchCount": 92
      },
      {
        "keyword": "아이폰14",
        "searchCount": 56
      },
      ...
    ]
  }
}
```

# 실행방법

``` shell
> ./gradlew clean :module-boot-api:buildNeeded --stacktrace --info --refresh-dependencies -x test
> java -jar module-boot-api/build/libs/module-boot-api-0.0.1-SNAPSHOT.jar
```
