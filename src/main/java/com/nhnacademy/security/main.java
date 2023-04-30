package com.nhnacademy.security;

import com.nhnacademy.security.controller.BCryptEncoder;

public class main {

  public static void main(String[] args) {

    BCryptEncoder b = new BCryptEncoder();

    b.dataEncrypt();
  }
}
