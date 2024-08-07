부부개발단 - 즐겁게 프로그래밍 배우기  
https://www.youtube.com/watch?v=5BMtAow_0fA&list=PLHpaQi-LiUCzoALNuLOqLdyRGrlhn7sMD

실행 방법

- frontend 코드를 vscode로 open 후, terminal을 열고 **npm run dev** 명령어로 실행
- backend는 ShopingMallApiApplication 실행


---
회원 가입
```
curl --location --request POST 'localhost:8080/members/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
"name":"이름",
"email":"이메일",
"password":"암호"
}'
```
---
회원가입
```
curl --location --request POST 'localhost:8080/members/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
"name":"이름",
"email":"이메일",
"password":"8자이상,대소문자특수문자섞은암호",
"birthYear":"년도",
"birthMonth":"월",
"birthDay":"일",
"gender": "M or F"
}'
```
---
로그인
```
curl --location --request POST 'localhost:8080/members/login' \
--header 'Content-Type: application/json' \
--data-raw '{
"email":"이메일",
"password":"암호"
}'
```
---
회원정보 읽어오기
```
curl  --request GET 'http://localhost:8080/members/info' \
--header 'Authorization: Bearer 엑세스키' \
--header 'Content-Type: application/json'
```
---
로그아웃
```
curl --location --request DELETE 'http://localhost:8080/members/logout' \
--header 'Authorization: Bearer accessToken' \
--header 'Content-Type: application/json' \
--data '{
"refreshToken" : "리프래시토큰"
}'
```
---
리프래시 토큰
```
curl --location 'http://localhost:8080/members/refreshToken' \
--header 'Content-Type: application/json' \
--data '{
"refreshToken" : "리프래시토큰"
}'
```
응답값
```
{
"accessToken": "JWT토큰",
"refreshToken": "JWT토큰",
"memberId": memberId,
"nickname": "이름"
}
```
