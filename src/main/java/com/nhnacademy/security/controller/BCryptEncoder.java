package com.nhnacademy.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoder {

  @Autowired
  BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

  public void dataEncrypt() {

    String encode1 = passwordEncoder.encode("password1");
    String encode2 = passwordEncoder.encode("password2");
    String encode3 = passwordEncoder.encode("password3");
    String encode4 = passwordEncoder.encode("password4");
    String encode5 = passwordEncoder.encode("password5");
    String encode6 = passwordEncoder.encode("password6");
    String encode7 = passwordEncoder.encode("password7");
    String encode8 = passwordEncoder.encode("password8");
    String encode9 = passwordEncoder.encode("password9");
    String encode10 = passwordEncoder.encode("password10");
    String encode11 = passwordEncoder.encode("password11");

    System.out.println("인코딩 데이터1 : " + encode1);
    System.out.println("인코딩 데이터2 : " + encode2);
    System.out.println("인코딩 데이터3 : " + encode3);
    System.out.println("인코딩 데이터4 : " + encode4);
    System.out.println("인코딩 데이터5 : " + encode5);
    System.out.println("인코딩 데이터6 : " + encode6);
    System.out.println("인코딩 데이터7 : " + encode7);
    System.out.println("인코딩 데이터8 : " + encode8);
    System.out.println("인코딩 데이터9 : " + encode9);
    System.out.println("인코딩 데이터10 : " + encode10);
    System.out.println("인코딩 데이터11 : " + encode11);


  }
}