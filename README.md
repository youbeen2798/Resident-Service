# Resident-Service
출생 신고, 사망 신고, 가족 관계 증명서, 거주지, 거주 이동까지 관리 가능한 서비스 / Github OAuth를 명세서를 통해 직접 구현


- 주민등록등본, 가족관계증명서, 출생신고서, 사망신고서

- 해당 프로젝트 확장 내용

1. 아이디/비밀번호 인증 추가
2. 비밀번호는 단방향 hash 함수의 digest 기반으로 저장
3. 인증 쿠키는 expire를 3일
4. 세션은 redis에 저장
5. 로그인 시 전체 주민 목록이 아니라 로그인한 본인과 본인의 세대에 속하는 주민들 목록 출력
6. OAuth2 인증 추가 
    - 아이디/비밀번호 인증과 OAuth2 인증은 동시에 기능이 제공
    - 로그인 시 아이디/비밀번호 인증과 OAuth2 인증 중 한 가지를 사용자가 선택 가능
    - Spring Security 라이브러리를 이용하지 않고 github API를 이용해서 직접 구현
    (참고한 링크: https://docs.github.com/en/developers/apps/building-oauth-apps/authorizing-oauth-apps#web-application-flow)
    
7. OAuth2 인증 처리 방식

- github에서 인증한 결과 중 email 값이 실제 resident 테이블에 있는 값이면 그 계정으로 로그인을 시켜준다
- github에서 인증이 성공했다고 하더라도 email 값이 resident 테이블에 없는 값이면 로그아웃 시킨다
(참고한 링크 : https://docs.github.com/en/developers/apps/building-oauth-apps/authorizing-oauth-apps#3-use-the-access-token-to-access-the-api)
