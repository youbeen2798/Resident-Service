package com.nhnacademy.security.service.oauth;

import com.nhnacademy.security.entity.GithubOAuthToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthService {

  private final RestTemplate restTemplate;
  private final RedisTemplate redisTemplate;

  public OAuthService(RedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
    this.restTemplate = new RestTemplate();
  }

  public GithubOAuthToken sendInformation(String code, String clientId) {

    //HttpHeader
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(List.of(MediaType.APPLICATION_JSON)); //내가 받을 body의 생김새

    //HttpBody
    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("client_id", clientId);
    body.add("client_secret", "a1fdb94d07b4378dc4b86329cbd408f9f9ac5faf");
    body.add("code",code);
    body.add("redirect_uri", "http://localhost:8080/login/oauth2/code/github");

    HttpEntity<MultiValueMap<String, String>> githubTokenRequest = new HttpEntity<>(body, headers);
//    https://github.com/login/oauth/access_token
    ResponseEntity<GithubOAuthToken> exchange = restTemplate.exchange(
        "https://github.com/login/oauth/access_token",
        HttpMethod.POST,
        githubTokenRequest,
        GithubOAuthToken.class);

    return exchange.getBody();
  }


  public String getEmail(GithubOAuthToken token)
      throws IOException {
    URL url = new URL("https://api.github.com/user");

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestProperty("Accept", "application/vnd.github+json");

    conn.setRequestProperty("Authorization", "Bearer " + token.getAccess_token());
    conn.setRequestProperty("X-Github-Api-Version", "2022-11-28");
    conn.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    JSONTokener tokener = new JSONTokener(in);
    JSONObject json = new JSONObject(tokener);

    String email = json.get("email").toString();

    return email;
  }

  public void putSession(UserDetails userDetails){

    String sessionId = UUID.randomUUID().toString();

    String username = userDetails.getUsername();
    String authority = new ArrayList<>(userDetails.getAuthorities()).get(0).getAuthority();

    redisTemplate.opsForHash().put(sessionId, "username", username);
    redisTemplate.opsForHash().put(sessionId, "authority", authority);
  }



}
