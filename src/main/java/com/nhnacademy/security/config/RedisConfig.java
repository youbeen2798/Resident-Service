package com.nhnacademy.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.annotations.Config;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@EnableRedisHttpSession
@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig implements BeanClassLoaderAware {

  @Value("${redis.host}")
  private String host;

  @Value("${redis.password}")
  private String password;

  @Value("${redis.port}")
  private int port;

  @Value("${redis.database}")
  private int database;

  private ClassLoader classLoader;

  @Bean
  public RedisConnectionFactory redisConnectionFactory(){
    RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
    configuration.setHostName(host);
    configuration.setPort(port);
    configuration.setPassword(password);
    configuration.setDatabase(database);

    return new LettuceConnectionFactory(configuration);
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(){
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

    return redisTemplate;
  }

  @Bean
  public CookieSerializer cookieSerializer(){
    //쿠키 직렬화(
    DefaultCookieSerializer serializer = new DefaultCookieSerializer();
    serializer.setCookieName("SESSION"); //session 이름인데, 값은 session.getId()의 값이 저장되어있음(쿠키에)
    //redisTemplate에 key(String), Object로 생겼는데, key는 session.getId()이고, Ojbect는 LoginSuccessHandler에서 지정한거
    serializer.setCookieMaxAge(259200); //3일(초)
    serializer.setCookiePath("/"); //특정 url 하위에 이 쿠키가 적용

    return serializer;
  }

  @Bean
  public RedisSerializer<Object> springSessionDefaultRedisSerializer(){
    return new GenericJackson2JsonRedisSerializer(objectMapper());
  }

  @Bean
  public ObjectMapper objectMapper(){
    //직렬화를 시켜주는 클래스임
    //serialize를 하기 위해 필요
    ObjectMapper objectMapepr = new ObjectMapper();
    objectMapepr.registerModules(SecurityJackson2Modules.getModules(classLoader));

    return objectMapepr;
  }

  @Override
  public void setBeanClassLoader(ClassLoader classLoader){
    this.classLoader = classLoader;
  }

}
